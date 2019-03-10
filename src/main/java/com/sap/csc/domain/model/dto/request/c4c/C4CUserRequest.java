package com.sap.csc.domain.model.dto.request.c4c;

import java.io.Serializable;

/**
 * @author I326950
 */
public class C4CUserRequest implements Serializable {

	private static final long serialVersionUID = -4675481166903900473L;

	private Long id;

	private String username;

	private String password;

	private Boolean isPrimary;

	private C4CSystemRequest system;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public C4CSystemRequest getSystem() {
		return system;
	}

	public void setSystem(C4CSystemRequest system) {
		this.system = system;
	}

}
