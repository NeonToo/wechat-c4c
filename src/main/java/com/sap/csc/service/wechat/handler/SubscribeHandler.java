package com.sap.csc.service.wechat.handler;

import java.util.Calendar;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.csc.domain.model.enumeration.wechat.WechatUserRole;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;
import com.sap.csc.domain.persistence.repository.wechat.WechatUserRepository;
import com.sap.csc.util.constant.URLConstants;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author i071053
 */
@Component
public class SubscribeHandler extends AbstractHandler {

//	private final C4CRestTemplate c4cRestTemplate;

	private final WechatUserRepository wechatUserRepository;

	@Autowired
	public SubscribeHandler(WechatUserRepository wechatUserRepository) {
		this.wechatUserRepository = wechatUserRepository;
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		String openId = wxMessage.getFromUser();
		String originId = wxMessage.getToUser();
		this.logger.info("关注用户 OPENID: " + wxMessage.getFromUser());

		WechatUser wechatUser = wechatUserRepository.findByOpenId(openId);

		if (wechatUser != null) { // 用户以前关注过
			wechatUser.setSubscribe(true);
		} else { // 新关注用户
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
		}

		WxMpXmlOutMessage responseResult = null;
		try {
			responseResult = handleSpecial(wxMessage);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		if (responseResult != null) {
			return responseResult;
		}

		try {
			StringBuilder content = new StringBuilder("感谢关注\n");
			
			content.append("<a href=\"" + URLConstants.SERVER_ROOT_URL + "?originId=" + originId + "&openId=" + openId + "&type=c4c#/role\">我是内部员工</a>\n");
			content.append("<a href=\"" + URLConstants.SERVER_ROOT_URL + "?originId=" + originId + "&openId=" + openId + "&type=customer#/role\">我是购买客户</a>\n");
			content.append("<a href=\"" + URLConstants.SERVER_ROOT_URL + "?originId=" + originId + "&openId=" + openId + "&type=supplier#/role\">我是经销商</a>");

			return WxMpXmlOutMessage.TEXT().content(content.toString()).fromUser(wxMessage.getToUser())
					.toUser(wxMessage.getFromUser()).build();
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
	 */
	protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
		return null;
	}

}
