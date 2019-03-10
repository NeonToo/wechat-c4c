package com.sap.csc.service.wechat.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sap.csc.domain.exception.wechat.WechatPlatformNotFoundException;
import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;
import com.sap.csc.domain.persistence.repository.wechat.WechatPublicPlatformRepository;
import com.sap.csc.service.GeneralServiceImpl;
import com.sap.csc.service.wechat.WxPublicService;
import com.sap.csc.service.wechat.handler.LocationHandler;
import com.sap.csc.service.wechat.handler.LogHandler;
import com.sap.csc.service.wechat.handler.MenuHandler;
import com.sap.csc.service.wechat.handler.MsgHandler;
import com.sap.csc.service.wechat.handler.ScanHandler;
import com.sap.csc.service.wechat.handler.SubscribeHandler;
import com.sap.csc.service.wechat.handler.UnsubscribeHandler;
import com.sap.csc.service.wechat.handler.ViewHandler;
import com.sap.csc.util.constant.URLConstants;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.bean.menu.WxMenuRule;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

@Service
public class WxPublicServiceImpl extends GeneralServiceImpl implements WxPublicService {

	private static final String DEFAULT_TOKEN = "ComGCSCCNWeChatIntegration";
	
	private static final String HOST_URL = "https://wechatc7dcab222.ap1.hana.ondemand.com/wechat/";

	private static final boolean IS_ASYNC = false;

	private final WechatPublicPlatformRepository wechatPublicPlatformRepository;

	private final MenuHandler menuHandler;

	private final LogHandler logHandler;

	private final SubscribeHandler subscribeHandler;

	private final UnsubscribeHandler unsubscribeHandler;

	private final LocationHandler locationHandler;

	private final ScanHandler scanHandler;

	private final MsgHandler msgHandler;

	private final ViewHandler viewHandler;

	@Autowired
	public WxPublicServiceImpl(WechatPublicPlatformRepository wechatPublicPlatformRepository, MenuHandler menuHandler,
			LogHandler logHandler, SubscribeHandler subscribeHandler, UnsubscribeHandler unsubscribeHandler,
			LocationHandler locationHandler, ScanHandler scanHandler, MsgHandler msgHandler, ViewHandler viewHandler) {
		this.wechatPublicPlatformRepository = wechatPublicPlatformRepository;
		this.menuHandler = menuHandler;
		this.logHandler = logHandler;
		this.subscribeHandler = subscribeHandler;
		this.unsubscribeHandler = unsubscribeHandler;
		this.locationHandler = locationHandler;
		this.scanHandler = scanHandler;
		this.msgHandler = msgHandler;
		this.viewHandler = viewHandler;
	}

	@Override
	public Boolean checkSignature(String timestamp, String nonce, String signature) {
		try {
			return SHA1.gen(DEFAULT_TOKEN, timestamp, nonce).equals(signature);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Cacheable(cacheNames = "mpConfigs", key = "#originID", sync = true)
	public WxMpInMemoryConfigStorage getWxMpConfigStorage(String originID) {
		WxMpInMemoryConfigStorage wxMpConfig = new WxMpInMemoryConfigStorage();
		WechatPublicPlatform wechatPublicPlatform = wechatPublicPlatformRepository.findByOriginID(originID);

		if (wechatPublicPlatform != null) {
			wxMpConfig.setAppId(wechatPublicPlatform.getAppID());
			wxMpConfig.setSecret(wechatPublicPlatform.getAppSecret());
			wxMpConfig.setToken(wechatPublicPlatform.getToken());
			wxMpConfig.setAesKey(wechatPublicPlatform.getAesKey());
			wxMpConfig.setPartnerId(wechatPublicPlatform.getPartnerID());
			wxMpConfig.setPartnerKey(wechatPublicPlatform.getPartnerKey());
		} else {
			throw new WechatPlatformNotFoundException();
		}

		return wxMpConfig;
	}

	@Override
	@Cacheable(cacheNames = "mpServices", key = "#originID", sync = true)
	public WxMpService getWxMpService(String originID) {
		WxMpService wxMpService = new WxMpServiceImpl();

		wxMpService.setWxMpConfigStorage(this.getWxMpConfigStorage(originID));

		return wxMpService;
	}

	@Override
	@Cacheable(cacheNames = "mpMsgRouters", key = "#originID", sync = true)
	public WxMpMessageRouter getWxMpMessageRouter(String originID) {
		WxMpMessageRouter wxMsgRouter = new WxMpMessageRouter(this.getWxMpService(originID));

		// 记录所有事件的日志
		wxMsgRouter.rule().handler(this.logHandler).next();
		// 自定义菜单事件
		wxMsgRouter.rule().async(IS_ASYNC).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.BUTTON_CLICK)
				.handler(this.menuHandler).end();
		// 点击菜单链接事件
		wxMsgRouter.rule().async(IS_ASYNC).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.BUTTON_VIEW)
				.handler(this.viewHandler).end();
		// 关注事件
		wxMsgRouter.rule().async(IS_ASYNC).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SUBSCRIBE)
				.handler(this.subscribeHandler).end();
		// 取消关注事件
		wxMsgRouter.rule().async(IS_ASYNC).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_UNSUBSCRIBE)
				.handler(this.unsubscribeHandler).end();
		// 上报地理位置事件
		wxMsgRouter.rule().async(IS_ASYNC).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_LOCATION)
				.handler(this.locationHandler).end();
		// 接收地理位置消息
		wxMsgRouter.rule().async(IS_ASYNC).msgType(WxConsts.XML_MSG_LOCATION).handler(this.locationHandler).end();
		// 扫码事件
		wxMsgRouter.rule().async(IS_ASYNC).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SCAN)
				.handler(this.scanHandler).end();
		// 默认
		wxMsgRouter.rule().async(IS_ASYNC).handler(this.msgHandler).end();

		return wxMsgRouter;
	}

	@Override
	public WxMpMenuService getWxMenuService(String originID) {
		return this.getWxMpService(originID).getMenuService();
	}

	@Override
	public WxMpUserTagService getWxUserTagService(String originID) {
		return this.getWxMpService(originID).getUserTagService();
	}

	@Override
	public void createMenu(String originID, WxMpService wxMpService) {
		WxMpMenuService wxMpMenuService = wxMpService.getMenuService();
		this.createMenu(originID, wxMpMenuService);
	}

	@Override
	public Map<String, Integer> createPersonlizedMenu(String originID, WxMpService wxMpService) {
		Map<String, Integer> tagIdMap = this.createTags(originID, wxMpService.getUserTagService());

		this.createMenuForC4C(originID, tagIdMap.get("C4C"), wxMpService);
		this.createMenuForCustomer(originID, tagIdMap.get("CUSTOMER"), wxMpService);
		this.createMenuForSupplier(originID, tagIdMap.get("SUPPLIER"), wxMpService);

		return tagIdMap;
	}

	private void createMenu(String originID, WxMpMenuService wxMpMenuService) {
		WxMenu menu = new WxMenu();
		List<WxMenuButton> buttons = new ArrayList<>();
		WxMenuButton button = new WxMenuButton();

		button.setType(WxConsts.BUTTON_VIEW);
		button.setName("未知用户");
		button.setUrl(URLConstants.UNKNOWN_USER_ENTRY_URL);

		buttons.add(button);
		menu.setButtons(buttons);

		try {
			wxMpMenuService.menuCreate(menu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createMenuForC4C(String originID, int c4cTagId, WxMpService wxMpService) {
		WxMenu menu = new WxMenu();
		WxMenuRule matchRule = new WxMenuRule();
		List<WxMenuButton> buttons = new ArrayList<>();
		WxMenuButton c4cButton = new WxMenuButton();
		StringBuilder sb = new StringBuilder(HOST_URL);

		c4cButton.setType(WxConsts.BUTTON_VIEW);
		c4cButton.setName("我是内部员工");
		c4cButton.setUrl(
				sb.append("wechat/oauth?origin_id=").append(originID).append("&redirect_url_component=").toString());

		buttons.add(c4cButton);
		menu.setButtons(buttons);

		matchRule.setTagId(String.valueOf(c4cTagId));
		menu.setMatchRule(matchRule);

		try {
			wxMpService.getMenuService().menuCreate(menu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createMenuForCustomer(String originID, int customerTagId, WxMpService wxMpService) {
		WxMenu menu = new WxMenu();
		WxMenuRule matchRule = new WxMenuRule();
		List<WxMenuButton> buttons = new ArrayList<>();
		WxMenuButton button = new WxMenuButton();
		StringBuilder sb = new StringBuilder(HOST_URL);

		button.setType(WxConsts.BUTTON_VIEW);
		button.setName("我是购买客户");
		button.setUrl(
				sb.append("wechat/oauth?origin_id=").append(originID).append("&redirect_url_component=").toString());
//		button.setUrl("https://www.sap.com/index.html");

		buttons.add(button);
		menu.setButtons(buttons);

		matchRule.setTagId(String.valueOf(customerTagId));
		menu.setMatchRule(matchRule);

		try {
			wxMpService.getMenuService().menuCreate(menu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createMenuForSupplier(String originID, int supplierTagId, WxMpService wxMpService) {
		WxMenu menu = new WxMenu();
		WxMenuRule matchRule = new WxMenuRule();
		List<WxMenuButton> buttons = new ArrayList<>();
		WxMenuButton button = new WxMenuButton();

		button.setType(WxConsts.BUTTON_VIEW);
		button.setName("我是经销商");
		button.setUrl("https://www.sap.com/index.html");

		buttons.add(button);
		menu.setButtons(buttons);

		matchRule.setTagId(String.valueOf(supplierTagId));
		menu.setMatchRule(matchRule);

		try {
			wxMpService.getMenuService().menuCreate(menu);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Map<String, Integer> createTags(String originID, WxMpUserTagService wxMpUserTagService) {
		Map<String, Integer> tagIdMap = new HashMap<>();

		try {
			List<WxUserTag> userTags = wxMpUserTagService.tagGet();
			Map<String, Long> tagMap = new HashMap<>();

			if (CollectionUtils.isNotEmpty(userTags)) {
				userTags.stream().map(userTag -> tagMap.put(userTag.getName(), userTag.getId()))
						.collect(Collectors.toList());
				if (!tagMap.containsKey("UNKNOWN")) {
					WxUserTag unknownTag = wxMpUserTagService.tagCreate("UNKNOWN");
					tagIdMap.put("UNKNOWN", unknownTag.getId().intValue());
				} else {
					tagIdMap.put("UNKNOWN", tagMap.get("UNKNOWN").intValue());
				}

				if (!tagMap.containsKey("C4C")) {
					WxUserTag c4cTag = wxMpUserTagService.tagCreate("C4C");
					tagIdMap.put("C4C", c4cTag.getId().intValue());
				} else {
					tagIdMap.put("C4C", tagMap.get("C4C").intValue());
				}

				if (!tagMap.containsKey("CUSTOMER")) {
					WxUserTag noneC4CTag = wxMpUserTagService.tagCreate("CUSTOMER");
					tagIdMap.put("CUSTOMER", noneC4CTag.getId().intValue());
				} else {
					tagIdMap.put("CUSTOMER", tagMap.get("CUSTOMER").intValue());
				}

				if (!tagMap.containsKey("SUPPLIER")) {
					WxUserTag noneC4CTag = wxMpUserTagService.tagCreate("SUPPLIER");
					tagIdMap.put("SUPPLIER", noneC4CTag.getId().intValue());
				} else {
					tagIdMap.put("SUPPLIER", tagMap.get("SUPPLIER").intValue());
				}
			}
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tagIdMap;
	}

}
