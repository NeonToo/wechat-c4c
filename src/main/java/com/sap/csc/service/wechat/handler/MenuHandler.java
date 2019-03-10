package com.sap.csc.service.wechat.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sap.csc.domain.model.dto.wechat.WxMenuKey;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author i071053
 */
@Component
public class MenuHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
		WxMenuKey menuKey = gson.fromJson(wxMessage.getEventKey(), WxMenuKey.class);
		switch (menuKey.getType()) {
		case WxConsts.XML_MSG_TEXT:
			return WxMpXmlOutMessage.TEXT().content(menuKey.getContent()).fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
		case WxConsts.XML_MSG_IMAGE:
			return WxMpXmlOutMessage.IMAGE().mediaId(menuKey.getContent()).fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
		case WxConsts.XML_MSG_VOICE:
		case WxConsts.XML_MSG_NEWS:
		default:
			break;
		}
		return null;
	}
}
