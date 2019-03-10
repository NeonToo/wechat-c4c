package com.sap.csc.domain.model.c4c.sales;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.C4CModificableEntity;
import com.sap.csc.domain.model.c4c.datatype.C4CAmount;
import com.sap.csc.domain.model.c4c.datatype.C4CAssociationEntry;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;
import com.sap.csc.domain.model.enumeration.c4c.SalesOrderStatus;

/**
 * @author I326950
 */
public class C4CSalesOrder extends C4CModificableEntity {

	private static final long serialVersionUID = 1173103520477100875L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("InternalID")
	private String internalID;

	@JsonProperty("TypeCode")
	private String typeCode;

	@JsonProperty("OrderingStatusCode")
	private SalesOrderStatus statusCode;

	@JsonProperty("OrderingStatusCodeText")
	private String status;

	@JsonProperty("Name")
	private String description;

	@JsonProperty("OwnerUUID")
	private String ownerUUID;

	@JsonProperty("OwnerID")
	private String ownerID;

	@JsonProperty("CustomerUUID")
	private String customerUUID;

	@JsonProperty("CustomerID")
	private String customerID;

	@JsonProperty("CustomerName")
	private String customerName;

	@JsonProperty("SalesTerritoryUUID")
	private String territoryUUID;

	@JsonProperty("SalesTerritoryID")
	private String territoryID;

	@JsonProperty("RequestedDateTime")
	private String requestedDate;

	@JsonProperty("NetAmount")
	private C4CAmount netAmount;

	@JsonProperty("Items")
	private Set<C4CSalesOrderItem> items;

	@JsonProperty("Parties")
	private C4CAssociationEntry partyEntry;

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

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public SalesOrderStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(SalesOrderStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
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

	public String getTerritoryUUID() {
		return territoryUUID;
	}

	public void setTerritoryUUID(String territoryUUID) {
		this.territoryUUID = territoryUUID;
	}

	public String getTerritoryID() {
		return territoryID;
	}

	public void setTerritoryID(String territoryID) {
		this.territoryID = territoryID;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public C4CAmount getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(C4CAmount netAmount) {
		this.netAmount = netAmount;
	}

	public Set<C4CSalesOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<C4CSalesOrderItem> items) {
		this.items = items;
	}

	public C4CAssociationEntry getPartyEntry() {
		return partyEntry;
	}

	public void setPartyEntry(C4CAssociationEntry partyEntry) {
		this.partyEntry = partyEntry;
	}

}
