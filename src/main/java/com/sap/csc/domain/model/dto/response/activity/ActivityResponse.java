package com.sap.csc.domain.model.dto.response.activity;

import java.io.Serializable;

import com.sap.csc.domain.model.c4c.activity.C4CActivity;

public class ActivityResponse implements Serializable {

	private static final long serialVersionUID = -9052672238427533780L;

	private String uri;

	private String objectID;

	private String internalID;

	private String categoryCode;

	private String category;

	private String typeCode;

	private String type;

	private String subject;

	private String location;

	private String scheduledStartDate;

	private String scheduledEndDate;

	private String ownerUUID;

	private String ownerID;

	private String ownerName;

	private String customerUUID;

	private String customerID;

	private String customerName;

	private String customerPhone;

	private String customerEmail;

	private String customerAddress;

	private String createOn;

	private String updateOn;

	public ActivityResponse(C4CActivity activity) {
		this.uri = activity.getMetadata().getUri();
		this.internalID = activity.getInternalID();
		this.objectID = activity.getObjectID();
		this.categoryCode = activity.getCategoryCode();
		this.category = activity.getCategory();
		this.typeCode = activity.getTypeCode();
		this.type = activity.getType();
		this.subject = activity.getSubject();
		this.location = activity.getLocation();
		this.scheduledStartDate = activity.getScheduledStartDate();
		this.scheduledEndDate = activity.getScheduledEndDate();
		this.ownerUUID = activity.getOwnerUUID();
		this.ownerID = activity.getOwnerID();
		this.ownerName = activity.getOwnerName();
		this.customerUUID = activity.getCustomerUUID();
		this.customerID = activity.getCustomerID();
		this.customerName = activity.getCustomerName();
		this.customerPhone = activity.getCustomerPhone();
		this.customerEmail = activity.getCustomerEmail();
		this.customerAddress = activity.getCustomerAddress();

		if (activity.getCreateOn() != null) {
			this.createOn = activity.getCreateOn();
		}

		if (activity.getUpdateOn() != null) {
			this.updateOn = activity.getUpdateOn();
		}
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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getScheduledStartDate() {
		return scheduledStartDate;
	}

	public void setScheduledStartDate(String scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}

	public String getScheduledEndDate() {
		return scheduledEndDate;
	}

	public void setScheduledEndDate(String scheduledEndDate) {
		this.scheduledEndDate = scheduledEndDate;
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

	public String getCustomerUUID() {
		return customerUUID;
	}

	public void setCustomerUUID(String customerUUID) {
		this.customerUUID = customerUUID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

}
