package com.sap.csc.domain.model.dto.response.party.customer;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.sap.csc.domain.model.c4c.customer.C4CAccount;
import com.sap.csc.domain.model.c4c.customer.C4CIndividualCustomer;

public abstract class CustomerCommonResponse implements Serializable {

	private static final long serialVersionUID = 483716824376999735L;

	private String uri;

	private String objectID;

	private String internalID;
	
	private String categoryCode;
	
	private String category;

	private String roleCode;

	private String role;

	private String name;

	private String phone;

	private String email;

	private String street;

	private String city;

	private String regionCode;

	private String region;

	private String countryCode;

	private String country;

	private String defaultAddress;

	private String ownerUUID;
	
	private String ownerID;

	private String ownerName;

	private String createOn;

	private String updateOn;

	private String addresses;
	
	public CustomerCommonResponse(C4CAccount customer) {
		StringBuilder sb = new StringBuilder();
		String street = customer.getStreet();
		String city = customer.getCity();
		String region = customer.getRegion();
		String country = customer.getCountry();

		if (StringUtils.isNotBlank(country)) {
			sb.append(country);
		}

		if (StringUtils.isNotBlank(region)) {
			sb.append(region + "省");
		}

		if (StringUtils.isNotBlank(city)) {
			sb.append(city + "市");
		}

		if (StringUtils.isNotBlank(street)) {
			sb.append(street);
		}
		
		this.uri = customer.getMetadata().getUri();
		this.objectID = customer.getObjectID();
		this.internalID = customer.getInternalID();
		this.categoryCode = customer.getCategoryCode();
		this.category = customer.getCategory();
		this.roleCode = customer.getRoleCode();
		this.role = customer.getRole();
		this.name = customer.getName();
		this.phone = customer.getPhone();
		this.email = customer.getEmail();
		this.street = street;
		this.city = city;
		this.regionCode = customer.getRegionCode();
		this.region = region;
		this.countryCode = customer.getCountryCode();
		this.country = customer.getCountry();
		this.defaultAddress = sb.toString();
		this.ownerUUID = customer.getOwnerUUID();
		this.ownerName = customer.getOwnerName();
		this.createOn = customer.getCreateOn();
		this.updateOn = customer.getUpdateOn();
		
		if(customer.getAddresses() != null) {
			this.addresses = customer.getAddresses().getEntry().getUri();
		}
	}
	
	public CustomerCommonResponse(C4CIndividualCustomer customer) {
		StringBuilder sb = new StringBuilder();
		String street = customer.getStreet();
		String city = customer.getCity();
		String region = customer.getRegion();
		String country = customer.getCountry();

		if (StringUtils.isNotBlank(country)) {
			sb.append(country);
		}

		if (StringUtils.isNotBlank(region)) {
			sb.append(region + "省");
		}

		if (StringUtils.isNotBlank(city)) {
			sb.append(city + "市");
		}

		if (StringUtils.isNotBlank(street)) {
			sb.append(street);
		}
		
		this.uri = customer.getMetadata().getUri();
		this.objectID = customer.getObjectID();
		this.internalID = customer.getInternalID();
		this.categoryCode = customer.getCategoryCode();
		this.category = customer.getCategory();
		this.roleCode = customer.getRoleCode();
		this.role = customer.getRole();
		this.name = customer.getName();
		this.phone = customer.getPhone();
		this.email = customer.getEmail();
		this.street = street;
		this.city = city;
		this.regionCode = customer.getRegionCode();
		this.region = region;
		this.countryCode = customer.getCountryCode();
		this.country = customer.getCountry();
		this.defaultAddress = sb.toString();
		this.ownerUUID = customer.getOwnerUUID();
		this.ownerName = customer.getOwnerName();
		this.createOn = customer.getCreateOn();
		this.updateOn = customer.getUpdateOn();
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

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
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

	public String getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(String updateOn) {
		this.updateOn = updateOn;
	}

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

}
