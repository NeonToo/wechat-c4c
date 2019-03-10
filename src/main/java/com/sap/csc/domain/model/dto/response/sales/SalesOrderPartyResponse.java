package com.sap.csc.domain.model.dto.response.sales;

import java.io.Serializable;

import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderParty;

/**
 * @author I326950
 */
public class SalesOrderPartyResponse implements Serializable {

	private static final long serialVersionUID = 8649584883677174139L;

	private String internalID;

	private String roleCode;

	private String role;

	private String name;

	private Boolean isMain;

	private String email;

	private String phone;

	private String address;

	public SalesOrderPartyResponse(C4CSalesOrderParty party) {
		this.internalID = party.getInternalID();
		this.roleCode = party.getRoleCode();
		this.role = party.getRole();
		this.name = party.getName();
		this.isMain = party.getIsMain();
		this.email = party.getEmail();
		this.phone = party.getPhone();
		this.address = party.getAddress();
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
