package com.sap.csc.domain.model.dto.response.party.customer;

import com.sap.csc.domain.model.c4c.customer.C4CIndividualCustomer;

public class IndividualCustomerResponse extends CustomerCommonResponse {

	private static final long serialVersionUID = 5480135646187198024L;

	private String genderCode;

	private String gender;

	private String lastName;

	private String firstName;

	private String birthday;

	public IndividualCustomerResponse(C4CIndividualCustomer customer) {
		super(customer);
		this.genderCode = customer.getGenderCode();
		this.gender = customer.getGender();
		this.lastName = customer.getLastName();
		this.firstName = customer.getFirstName();
		this.birthday = customer.getBirthday();
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

}
