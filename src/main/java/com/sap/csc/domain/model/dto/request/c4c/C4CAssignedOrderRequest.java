package com.sap.csc.domain.model.dto.request.c4c;

import java.io.Serializable;

import com.sap.csc.domain.model.c4c.datatype.C4CAmount;

/**
 * @author I326950
 */
public class C4CAssignedOrderRequest implements Serializable {

	private static final long serialVersionUID = 460831828397031078L;

	private String originID;

	private String uuid;

	private String internalID;

	private String description;

	private String ownerUUID;

	private String customerName;

	private String requestedDate;
	
	private String distributionChannel;

	private C4CAmount netAmount;

	public String getOriginID() {
		return originID;
	}

	public void setOriginID(String originID) {
		this.originID = originID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(String ownerUUID) {
		this.ownerUUID = ownerUUID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}

	public C4CAmount getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(C4CAmount netAmount) {
		this.netAmount = netAmount;
	}

}
