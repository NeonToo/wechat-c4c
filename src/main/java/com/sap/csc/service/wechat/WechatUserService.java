package com.sap.csc.service.wechat;

import java.io.InputStream;
import java.util.Optional;

import com.sap.csc.domain.model.jpa.wechat.WechatUser;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

public interface WechatUserService {
	
	public InputStream getUserConfig(String originId, String openId);
	
	public Optional<WechatUser> addWechatUser(WxMpUser wxMpUser);
	
	public Optional<WechatUser> findByOpenID(String openID);
	
	public Optional<WechatUser> findByOpenIDAndForceUpdate(String originID, String openID);
	
	public boolean openIDExist(String openID);

	public Optional<WechatUser> updateUserTag(String type, String originId, String openId);
	
}
