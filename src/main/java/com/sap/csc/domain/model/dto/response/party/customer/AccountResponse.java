package com.sap.csc.domain.model.dto.response.party.customer;

import com.sap.csc.domain.model.c4c.customer.C4CAccount;

/**
 * @author I326950
 */
public class AccountResponse extends CustomerCommonResponse {

	private static final long serialVersionUID = 1511872389245112808L;

	private String industry;

	private String contactID;

	private String contactName;

	private String teamMembers;

	public AccountResponse(C4CAccount customer) {
		super(customer);
		this.industry = customer.getIndustry();
		this.contactID = customer.getContactID();
		this.contactName = customer.getContactName();

		if (customer.getTeamMembers() != null) {
			this.teamMembers = customer.getTeamMembers().getEntry().getUri();
		}
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
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

	public String getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(String teamMembers) {
		this.teamMembers = teamMembers;
	}

}
