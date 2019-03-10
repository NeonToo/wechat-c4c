package com.sap.csc.service.wechat.handler;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.csc.domain.model.enumeration.wechat.WechatUserRole;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;
import com.sap.csc.domain.persistence.repository.wechat.WechatUserRepository;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author I326950
 */
@Component
public class LocationHandler extends AbstractHandler {

	private final WechatUserRepository wechatUserRepository;

	@Autowired
	public LocationHandler(WechatUserRepository wechatUserRepository) {
		this.wechatUserRepository = wechatUserRepository;
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		String openId = wxMessage.getFromUser();
		WechatUser wechatUser = wechatUserRepository.findByOpenId(openId);

		if (wechatUser == null) {
			try {
				// 获取微信用户基本信息
				WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);
				Calendar subscribeTime = Calendar.getInstance();
				
				wechatUser = new WechatUser();
				subscribeTime.setTimeInMillis(wxMpUser.getSubscribeTime());
				wechatUser.setOpenId(wxMpUser.getOpenId());
				wechatUser.setSubscribe(wxMpUser.getSubscribe());
				wechatUser.setNickname(wxMpUser.getNickname());
				wechatUser.setSexId(wxMpUser.getSexId());
				wechatUser.setSex(wxMpUser.getSex());
				wechatUser.setLanguage(wxMpUser.getLanguage());
				wechatUser.setCity(wxMpUser.getCity());
				wechatUser.setProvince(wxMpUser.getProvince());
				wechatUser.setCountry(wxMpUser.getCountry());
				wechatUser.setHeadImgUrl(wxMpUser.getHeadImgUrl());
				wechatUser.setSubscribeTime(subscribeTime);
				wechatUser.setUnionId(wxMpUser.getUnionId());
				wechatUser.setRemark(wxMpUser.getRemark());
				wechatUser.setGroupId(wxMpUser.getGroupId());
				wechatUser.setRole(WechatUserRole.UNKNOWN);
				
				wechatUserRepository.saveAndFlush(wechatUser);
			} catch (WxErrorException e) {
				this.logger.error("微信用户信息获取失败", e);
				return null;
			}
		}

		if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_LOCATION)) {
			// TODO 接收处理用户发送的地理位置消息
			try {
				String content = "感谢反馈，您的的地理位置已收到！";
				return WxMpXmlOutMessage.TEXT().content(content).fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser()).build();
			} catch (Exception e) {
				this.logger.error("位置消息接收处理失败", e);
				return null;
			}
		}

		// 上报地理位置事件
		this.logger.info("\n上报地理位置 。。。 ");
		this.logger.info("\n纬度 : " + wxMessage.getLatitude());
		this.logger.info("\n经度 : " + wxMessage.getLongitude());
		this.logger.info("\n精度 : " + String.valueOf(wxMessage.getPrecision()));

		// TODO 可以将用户地理位置信息保存到本地数据库，以便以后使用

		return null;
	}

}
