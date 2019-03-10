package com.sap.csc.web.controller.wechat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.exception.wechat.InvalidWechatSignatureException;
import com.sap.csc.domain.model.enumeration.wechat.EncryptType;
import com.sap.csc.service.wechat.WxPublicService;
import com.sap.csc.web.controller.GeneralController;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@RestController
@RequestMapping("/wechat/push")
public class WxPushController extends GeneralController {
	
	private final WxPublicService wxPublicService;
	
	@Autowired
	public WxPushController(WxPublicService wxPublicService) {
		this.wxPublicService = wxPublicService;
	}
	
	/**
	 * @param signature
	 * @param nonce
	 * @param timestamp
	 * @param echostr
	 * @param encryptType
	 * @param msgSignature
	 * @return
	 */
	@GetMapping
	public String echo(
			// signature
			@RequestParam(value = "signature") String signature,
			// nonce
			@RequestParam(value = "nonce") String nonce,
			// timestamp
			@RequestParam(value = "timestamp") String timestamp,
			// echostr
			@RequestParam(value = "echostr", required = false) String echostr) {
		if(!wxPublicService.checkSignature(timestamp, nonce, signature)) {
			throw new InvalidWechatSignatureException(timestamp, nonce, signature);
		}
		
		if(StringUtils.isNotBlank(echostr)) {
			return echostr;
		}
		
		return StringUtils.EMPTY;
	}
	
	@PostMapping
	public String dispatch(
			// Request Body
			@RequestBody String requestBody,
			// signature
			@RequestParam(value = "signature") String signature,
			// nonce
			@RequestParam(value = "nonce") String nonce,
			// timestamp
			@RequestParam(value = "timestamp") String timestamp,
			// encrypt_type
			@RequestParam(value = "encrypt_type", required = false, defaultValue = "RAW") EncryptType encryptType,
			// msgSignature
			@RequestParam(value = "msgSignature", required = false) String msgSignature) {
		// Validation
		if(!wxPublicService.checkSignature(timestamp, nonce, signature)) {
			throw new InvalidWechatSignatureException(timestamp, nonce, signature);
		}
		
		WxMpMessageRouter wxMsgRouter;
		WxMpXmlMessage wxMpXmlMsg;
		
		// Dispatch
		switch(encryptType) {
		case RAW: {
			wxMpXmlMsg = WxMpXmlMessage.fromXml(requestBody);
			WxMpXmlOutMessage wxMpXmlOutMsg;
			
			wxMsgRouter = wxPublicService.getWxMpMessageRouter(wxMpXmlMsg.getToUser());
			wxMpXmlOutMsg = wxMsgRouter.route(wxMpXmlMsg);
						
			if(wxMpXmlOutMsg == null) { // 重复消息或无匹配的规则
				return StringUtils.EMPTY;
			}
			
			logger.info(wxMpXmlOutMsg.getMsgType());
			return wxMpXmlOutMsg.toXml();
		}
		default:
			return StringUtils.EMPTY;
		}
	}
	
}
