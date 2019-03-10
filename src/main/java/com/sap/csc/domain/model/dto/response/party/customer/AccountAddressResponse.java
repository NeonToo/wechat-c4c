package com.sap.csc.domain.model.dto.response.party.customer;

import java.io.Serializable;

import com.sap.csc.domain.model.c4c.customer.C4CAccountAddress;

/**
 * @author I326950
 */
public class AccountAddressResponse implements Serializable {

	private static final long serialVersionUID = -2901013237005178331L;

	private String uri;

	private String objectID;

	private String phone;

	private String email;

	private String address;

	private String countryCode;

	private String country;

	private String stateCode;

	private String state;

	private String city;

	private String street;

	private String postalCode;
	
	public AccountAddressResponse(C4CAccountAddress address) {
		this.uri = address.getMetadata().getUri();
		this.objectID = address.getObjectID();
		this.phone = address.getPhone();
		this.email = address.getEmail();
		this.address = address.getAddress();
		this.countryCode = address.getCountryCode();
		this.country = address.getCountry();
		this.stateCode = address.getStateCode();
		this.state = address.getState();
		this.city = address.getCity();
		this.street = address.getStreet();
		this.postalCode = address.getPostalCode();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
