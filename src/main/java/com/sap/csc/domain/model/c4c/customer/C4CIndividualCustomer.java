package com.sap.csc.domain.model.c4c.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class C4CIndividualCustomer extends C4CCustomerCommon {

	private static final long serialVersionUID = 4710523364954607691L;

	@JsonProperty("CustomerID")
	private String internalID;

	@JsonProperty("GenderCode")
	private String genderCode;

	@JsonProperty("GenderCodeText")
	private String gender;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("DateOfBirth")
	private String birthday;

	@JsonProperty("Owner")
	private String ownerName;

	@JsonProperty("CreationOn")
	private String createOn;

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCreateOn() {
		return createOn;
	}

	public void setCreateOn(String createOn) {
		this.createOn = createOn;
	}

}
