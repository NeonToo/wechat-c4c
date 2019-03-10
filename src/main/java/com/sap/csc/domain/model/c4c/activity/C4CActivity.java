package com.sap.csc.domain.model.c4c.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.C4CModificableEntity;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;

public class C4CActivity extends C4CModificableEntity {

	private static final long serialVersionUID = 5876177922383935980L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("InternalID")
	private String internalID;

	@JsonProperty("CategoryCode")
	private String categoryCode;

	@JsonProperty("CategoryCodeText")
	private String category;

	@JsonProperty("TypeCode")
	private String typeCode;

	@JsonProperty("TypeCodeText")
	private String type;

	@JsonProperty("Subject")
	private String subject;

	@JsonProperty("Location")
	private String location;

	@JsonProperty("ScheduledStartDate")
	private String scheduledStartDate;

	@JsonProperty("ScheduledEndDate")
	private String scheduledEndDate;

	@JsonProperty("OwnerUUID")
	private String ownerUUID;

	@JsonProperty("OwnerID")
	private String ownerID;

	@JsonProperty("OwnerName")
	private String ownerName;

	@JsonProperty("CustomerUUID")
	private String customerUUID;

	@JsonProperty("CustomerID")
	private String customerID;

	@JsonProperty("CustomerName")
	private String customerName;

	@JsonProperty("CustomerPhone")
	private String customerPhone;

	@JsonProperty("CustomerEmail")
	private String customerEmail;

	@JsonProperty("CustomerAddress")
	private String customerAddress;

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

}
