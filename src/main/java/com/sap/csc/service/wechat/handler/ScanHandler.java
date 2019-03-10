package com.sap.csc.service.wechat.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

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
public class ScanHandler extends AbstractHandler {

//	@Autowired
//	private WxPubPlatformService wxPubPlatformService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		this.logger.info("扫码用户 OPENID: " + wxMessage.getFromUser());

		// 获取微信用户基本信息
		WxMpUser userWxInfo = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);

		if (userWxInfo != null) {
			// TODO add logic for scan handler
//			int sceneId = Integer.valueOf(wxMessage.getEventKey());
//			QRCodeRequest qrCodeReq = wxPubPlatformService.getQRCodeRequest(sceneId, null);
//
//			if (qrCodeReq != null) {
//				
//			}
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
			return WxMpXmlOutMessage.TEXT().content("Thanks for following.").fromUser(wxMessage.getToUser())
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
