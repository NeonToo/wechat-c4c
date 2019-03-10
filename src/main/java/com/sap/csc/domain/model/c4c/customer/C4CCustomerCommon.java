package com.sap.csc.domain.model.c4c.customer;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;

@MappedSuperclass
public abstract class C4CCustomerCommon implements Serializable {

	private static final long serialVersionUID = 1297872137781342534L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("CategoryCode")
	private String categoryCode;

	@JsonProperty("CategoryCodeText")
	private String category;

	@JsonProperty("RoleCode")
	private String roleCode;

	@JsonProperty("RoleCodeText")
	private String role;

	@JsonProperty("Phone")
	private String phone;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("Mobile")
	private String Mobile;

	@JsonProperty("StreetName")
	private String street;

	@JsonProperty("CityName")
	private String city;

	@JsonProperty("RegionCode")
	private String regionCode;

	@JsonProperty("RegionCodeText")
	private String region;

	@JsonProperty("CountryCode")
	private String countryCode;

	@JsonProperty("CountryCodeText")
	private String country;

	@JsonProperty("OwnerUUID")
	private String ownerUUID;

	@JsonProperty("OwnerID")
	private String ownerID;

	@JsonProperty("ChangedOn")
	private String updateOn;

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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public String getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(String ownerUUID) {
		this.ownerUUID = ownerUUID;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public String getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(String updateOn) {
		this.updateOn = updateOn;
	}

}
