package com.sap.csc.service.wechat.handler;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sap.csc.util.constant.URLConstants;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author i071053
 */
@Component
public class MsgHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		String openId = wxMessage.getFromUser();
		String originId = wxMessage.getToUser();
		if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
			// TODO 可以选择将消息保存到本地
		}

		// 当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
		if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")) {
			return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
					.toUser(wxMessage.getFromUser()).build();
		}

		// 组装回复消息
		String msg = wxMessage.getContent();

		if (StringUtils.contains(msg, "注册")) {
			StringBuilder content = new StringBuilder();

			content.append("<a href=\"" + URLConstants.SERVER_ROOT_URL + "?originId=" + originId + "&openId=" + openId
					+ "&type=c4c#/role\">我是内部员工</a>\n");
			content.append("<a href=\"" + URLConstants.SERVER_ROOT_URL + "?originId=" + originId + "&openId=" + openId
					+ "&type=customer#/role\">我是购买客户</a>\n");
			content.append("<a href=\"" + URLConstants.SERVER_ROOT_URL + "?originId=" + originId + "&openId=" + openId
					+ "&type=supplier#/role\">我是经销商</a>");

			return WxMpXmlOutMessage.TEXT().content(content.toString()).fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
		}

		return null;
	}

}
