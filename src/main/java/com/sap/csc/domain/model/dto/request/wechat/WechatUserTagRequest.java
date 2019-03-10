package com.sap.csc.domain.model.dto.request.wechat;

import java.io.Serializable;

/**
 * @author I326950
 */
public class WechatUserTagRequest implements Serializable {

	private static final long serialVersionUID = 6079003994700775742L;

	private String originId;

	private String openId;

	private String type;

	public String getOriginId() {
		return originId;
	}

	public void setOriginId(String originId) {
		this.originId = originId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
