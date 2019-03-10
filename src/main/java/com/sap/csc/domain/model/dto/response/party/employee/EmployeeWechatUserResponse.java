package com.sap.csc.domain.model.dto.response.party.employee;

import java.io.Serializable;
import com.sap.csc.domain.model.dto.response.wechat.WechatUserResponse;
import com.sap.csc.domain.model.jpa.wechat.WechatUser;

/**
 * @author I326950
 */
public class EmployeeWechatUserResponse implements Serializable {

	private static final long serialVersionUID = -6707775721825052228L;

	private WechatUserResponse wechatUser;

	private int systemCount;

	public EmployeeWechatUserResponse(WechatUser wechatUser, int systemCount) {
		this.wechatUser = new WechatUserResponse(wechatUser);
		this.systemCount = systemCount;
	}

	public WechatUserResponse getWechatUser() {
		return wechatUser;
	}

	public void setWechatUser(WechatUserResponse wechatUser) {
		this.wechatUser = wechatUser;
	}

	public int getSystemCount() {
		return systemCount;
	}

	public void setSystemCount(int systemCount) {
		this.systemCount = systemCount;
	}

}
