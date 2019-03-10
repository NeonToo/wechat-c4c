package com.sap.csc.service.wechat;

import java.util.Map;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;

public interface WxPublicService {

	public Boolean checkSignature(String timestamp, String nonce, String signature);

	public WxMpInMemoryConfigStorage getWxMpConfigStorage(String originId);

	public WxMpService getWxMpService(String originId);

	public WxMpMessageRouter getWxMpMessageRouter(String originId);
	
	public WxMpMenuService getWxMenuService(String originId);
	
	public WxMpUserTagService getWxUserTagService(String originId);
	
	public void createMenu(String originId, WxMpService wxMpService);
	
	public Map<String, Integer> createPersonlizedMenu(String originId, WxMpService wxMpService);

}
