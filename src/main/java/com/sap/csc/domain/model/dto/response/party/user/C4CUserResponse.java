package com.sap.csc.domain.model.dto.response.party.user;

import java.io.Serializable;

import com.sap.csc.domain.model.dto.response.system.SystemResponse;
import com.sap.csc.domain.model.jpa.c4c.C4CUser;

/**
 * @author I326950
 */
public class C4CUserResponse implements Serializable {

	private static final long serialVersionUID = -2041429982266597051L;

	private Long id;

	private String username;

	private Boolean isPrimary;
	
	private SystemResponse system;

	public C4CUserResponse(C4CUser c4cUser) {
		this.id = c4cUser.getId();
		this.username = c4cUser.getUsername();
		this.isPrimary = c4cUser.getIsPrimary();
		this.system = new SystemResponse(c4cUser.getSystem());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public SystemResponse getSystem() {
		return system;
	}

	public void setSystem(SystemResponse system) {
		this.system = system;
	}

}
