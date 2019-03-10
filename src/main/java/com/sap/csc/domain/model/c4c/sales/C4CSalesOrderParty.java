package com.sap.csc.domain.model.c4c.sales;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CSalesOrderParty implements Serializable {

	private static final long serialVersionUID = 1039377021957536356L;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("InternalID")
	private String internalID;

	@JsonProperty("RoleCode")
	private String roleCode;

	@JsonProperty("RoleCodeText")
	private String role;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("MainIndicator")
	private Boolean isMain;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("Phone")
	private String phone;

	@JsonProperty("Address")
	private String address;

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
