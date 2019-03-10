package com.sap.csc.domain.model.c4c;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
@MappedSuperclass
public abstract class C4CModificableEntity implements Serializable {

	private static final long serialVersionUID = -7081682211610452352L;

	@JsonProperty("CreatedOn")
	private String createOn;

	@JsonProperty("ChangedOn")
	private String updateOn;
	
	public Long getCreateTimestamp() {
		return Long.valueOf(this.getCreateOn().replace("/Date(", "").replace(")/", ""));
	}
	
	public Long getUpdateTimestamp() {
		return Long.valueOf(this.getUpdateOn().replace("/Date(", "").replace(")/", ""));
	}

	public String getCreateOn() {
		return createOn;
	}

	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}

	public String getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(String updateOn) {
		this.updateOn = updateOn;
	}

}
