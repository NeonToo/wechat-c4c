package com.sap.csc.domain.model.c4c.party;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.C4CModificableEntity;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;

/**
 * @author I326950
 */
public class C4CEmployee extends C4CModificableEntity implements Serializable {

	private static final long serialVersionUID = -4344277963478808012L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("IsWeChatUser")
	private Boolean isWeChatUser;

	@JsonProperty("OriginID")
	private String originID;

	@JsonProperty("OpenID")
	private String openID;

	public C4CMetadataEntry getMetadata() {
		return metadata;
	}

	public void setMetadata(C4CMetadataEntry metadata) {
		this.metadata = metadata;
	}

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
