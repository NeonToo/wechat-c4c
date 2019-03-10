package com.sap.csc.domain.model.c4c.party;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CLoginUser implements Serializable {

	private static final long serialVersionUID = -4136875283445119995L;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("UserID")
	private String username;

	@JsonProperty("UserName")
	private String name;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("EmployeeUUID")
	private String employeeUUID;

	@JsonProperty("EmployeeID")
	private String employeeInternalID;

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeUUID() {
		return employeeUUID;
	}

	public void setEmployeeUUID(String employeeUUID) {
		this.employeeUUID = employeeUUID;
	}

	public String getEmployeeInternalID() {
		return employeeInternalID;
	}

	public void setEmployeeInternalID(String employeeInternalID) {
		this.employeeInternalID = employeeInternalID;
	}

}
