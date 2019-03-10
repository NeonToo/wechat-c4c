package com.sap.csc.domain.model.dto.wechat;

import java.io.Serializable;

import me.chanjar.weixin.common.bean.WxJsapiSignature;

/**
 * @author I326950
 */
public class WxJssdkSignature implements Serializable {

	private static final long serialVersionUID = 9080193137774887273L;

	private String appId;

	private Long timestamp;

	private String nonceStr;

	private String signature;

	public WxJssdkSignature() {

	}

	public WxJssdkSignature(WxJsapiSignature signature) {
		if (signature == null) {
			return;
		}
		this.appId = signature.getAppId();
		this.timestamp = signature.getTimestamp();
		this.nonceStr = signature.getNonceStr();
		this.signature = signature.getSignature();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
