package com.sap.csc.domain.model.dto.request.party;

import java.io.Serializable;

import com.sap.csc.domain.model.dto.request.c4c.C4CUserRequest;

/**
 * @author I326950
 */
public class EmployeeRequest implements Serializable {

	private static final long serialVersionUID = 5582126105939827572L;

	private Long id;

	private String name;

	private String mobile;

	private String email;

	private Boolean isPrimary;

	private C4CUserRequest c4cUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public C4CUserRequest getC4cUser() {
		return c4cUser;
	}

	public void setC4cUser(C4CUserRequest c4cUser) {
		this.c4cUser = c4cUser;
	}

}
