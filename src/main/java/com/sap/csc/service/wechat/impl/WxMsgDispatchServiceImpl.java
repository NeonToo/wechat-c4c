package com.sap.csc.service.wechat.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sap.csc.service.wechat.WxMsgDispatchService;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class WxMsgDispatchServiceImpl implements WxMsgDispatchService {
	
	@PostConstruct
	public synchronized void initRouter() {
		
	}

	@Override
	public WxMpXmlOutMessage dispatch(WxMpXmlMessage inMessage) {
		// TODO Auto-generated method stub
		return null;
	}

}
