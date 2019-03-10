package com.sap.csc.domain.model.c4c.customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;

/**
 * @author I326950
 */
public class C4CAccountAddress implements Serializable {

	private static final long serialVersionUID = -7875010084896042226L;
	
	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("Phone")
	private String phone;

	@JsonProperty("EMail")
	private String email;

	@JsonProperty("FormattedAddress")
	private String address;

	@JsonProperty("CountryCode")
	private String countryCode;

	@JsonProperty("CountryCodeText")
	private String country;

	@JsonProperty("State")
	private String stateCode;

	@JsonProperty("StateText")
	private String state;

	@JsonProperty("City")
	private String city;

	@JsonProperty("Street")
	private String street;

	@JsonProperty("PostalCode")
	private String postalCode;

	public C4CMetadataEntry getMetadata() {
		return metadata;
	}

	public void setMetadata(C4CMetadataEntry metadata) {
		this.metadata = metadata;
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
