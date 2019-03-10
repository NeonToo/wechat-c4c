package com.sap.csc.domain.model.c4c.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.datatype.C4CAssociationEntry;

/**
 * @author I326950
 */
public class C4CAccount extends C4CCustomerCommon {

	private static final long serialVersionUID = -5892358545827312833L;

	@JsonProperty("AccountID")
	private String internalID;

	@JsonProperty("AccountFormattedName")
	private String name;

	@JsonProperty("IndustryCodeText")
	private String industry;

	@JsonProperty("OwnerFormattedName")
	private String ownerName;

	@JsonProperty("PrimaryContactID")
	private String contactID;

	@JsonProperty("PrimaryContactName")
	private String contactName;
	
	@JsonProperty("CreatedOn")
	private String createOn;

	@JsonProperty("ChangedOn")
	private String updateOn;

	@JsonProperty("AccountTeam")
	private C4CAssociationEntry teamMembers;

	@JsonProperty("AccountAddress")
	private C4CAssociationEntry addresses;

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getContactID() {
		return contactID;
	}

	public void setContactID(String contactID) {
		this.contactID = contactID;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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

	public C4CAssociationEntry getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(C4CAssociationEntry teamMembers) {
		this.teamMembers = teamMembers;
	}

	public C4CAssociationEntry getAddresses() {
		return addresses;
	}

	public void setAddresses(C4CAssociationEntry addresses) {
		this.addresses = addresses;
	}

}
