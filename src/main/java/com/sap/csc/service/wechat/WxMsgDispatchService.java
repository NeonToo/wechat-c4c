package com.sap.csc.service.wechat;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public interface WxMsgDispatchService {

	public WxMpXmlOutMessage dispatch(WxMpXmlMessage inMessage);
	
}
