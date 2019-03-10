package com.sap.csc.domain.model.dto.request.c4c;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class C4CEmployeeRequest implements Serializable {

	private static final long serialVersionUID = -6459086742046868604L;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("IsWeChatUser")
	private Boolean isWeChatUser;

	@JsonProperty("OriginID")
	private String originID;

	@JsonProperty("OpenID")
	private String openID;

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public Boolean getIsWeChatUser() {
		return isWeChatUser;
	}

	public void setIsWeChatUser(Boolean isWeChatUser) {
		this.isWeChatUser = isWeChatUser;
	}

	public String getOriginID() {
		return originID;
	}

	public void setOriginID(String originID) {
		this.originID = originID;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}
}
