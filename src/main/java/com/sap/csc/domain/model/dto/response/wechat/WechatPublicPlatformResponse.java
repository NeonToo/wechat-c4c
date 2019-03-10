package com.sap.csc.domain.model.dto.response.wechat;

import java.io.Serializable;

import com.sap.csc.domain.model.dto.response.system.SystemResponse;
import com.sap.csc.domain.model.jpa.wechat.WechatPublicPlatform;

/**
 * @author I326950
 */
public class WechatPublicPlatformResponse implements Serializable {

	private static final long serialVersionUID = 7667557578974554012L;
	
	private Long id;

	private String originID;

	private String appID;

	private String appSecret;

	private String token;

	private String aesKey;

	private String partnerID;

	private String partnerKey;

	private SystemResponse system;
	
	public WechatPublicPlatformResponse(WechatPublicPlatform wechatPublicPlatform) {
		this.id = wechatPublicPlatform.getId();
		this.originID = wechatPublicPlatform.getOriginID();
		this.appID = wechatPublicPlatform.getAppID();
		this.appSecret = wechatPublicPlatform.getAppSecret();
		this.token = wechatPublicPlatform.getToken();
		this.aesKey = wechatPublicPlatform.getAesKey();
		this.partnerID = wechatPublicPlatform.getPartnerID();
		this.partnerKey = wechatPublicPlatform.getPartnerKey();
		this.system = new SystemResponse(wechatPublicPlatform.getSystem());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginID() {
		return originID;
	}

	public void setOriginID(String originID) {
		this.originID = originID;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public SystemResponse getSystem() {
		return system;
	}

	public void setSystem(SystemResponse system) {
		this.system = system;
	}

	

}
