package com.sap.csc.service.wechat.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.csc.domain.model.enumeration.wechat.WechatUserRole;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;
import com.sap.csc.domain.persistence.repository.wechat.WechatUserRepository;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author i071053
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

//	private final C4CRestTemplate c4cRestTemplate;

	private final WechatUserRepository wechatUserRepository;

	@Autowired
	public UnsubscribeHandler(WechatUserRepository wechatUserRepository) {
		this.wechatUserRepository = wechatUserRepository;
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		String openId = wxMessage.getFromUser();
		this.logger.info("取消关注用户 OPENID: " + openId);
		WechatUser wechatUser = wechatUserRepository.findByOpenId(openId);

		if (wechatUser != null) {
			// 关注状态 & Role
			wechatUser.setSubscribe(false);
			wechatUser.setRole(WechatUserRole.UNKNOWN);
		}

		return null;
	}

}
