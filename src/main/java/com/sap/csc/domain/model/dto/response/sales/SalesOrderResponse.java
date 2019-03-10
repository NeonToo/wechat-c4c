package com.sap.csc.domain.model.dto.response.sales;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.sap.csc.domain.model.c4c.datatype.C4CAmount;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrder;
import com.sap.csc.domain.model.enumeration.c4c.SalesOrderStatus;

/**
 * @author I326950
 */
public class SalesOrderResponse implements Serializable {

	private static final long serialVersionUID = 4133988089316241334L;

	private String uri;

	private String internalID;

	private String objectID;

	private SalesOrderStatus statusCode;

	private String status;

	private String description;

	private String customerUUID;

	private String customerID;

	private String customerName;

	private String territoryID;

	private String requestedDate;

	private C4CAmount netAmount;

	private String createOn;

	private String updateOn;

	private List<SalesOrderItemResponse> items;

	private String parties;

	public SalesOrderResponse(C4CSalesOrder salesOrder) {
		this.uri = salesOrder.getMetadata().getUri();
		this.internalID = salesOrder.getInternalID();
		this.objectID = salesOrder.getObjectID();
		this.statusCode = salesOrder.getStatusCode();
		this.status = salesOrder.getStatus();
		this.description = salesOrder.getDescription();
		this.customerUUID = salesOrder.getCustomerUUID();
		this.customerID = salesOrder.getCustomerID();
		this.customerName = salesOrder.getCustomerName();
		this.territoryID = salesOrder.getTerritoryID();
		this.netAmount = salesOrder.getNetAmount();

		if (StringUtils.isNotBlank(salesOrder.getRequestedDate())) {
			this.requestedDate = salesOrder.getRequestedDate().split("T")[0];
		}

		if (salesOrder.getCreateOn() != null) {
			this.createOn = salesOrder.getCreateOn();
		}

		if (salesOrder.getUpdateOn() != null) {
			this.updateOn = salesOrder.getUpdateOn();
		}

		if (CollectionUtils.isNotEmpty(salesOrder.getItems())) {
			this.items = salesOrder.getItems().stream().map(item -> new SalesOrderItemResponse(item))
					.collect(Collectors.toList());
		}

		// this.items = salesOrder.getItemEntry().getEntry().getUri();
		this.parties = salesOrder.getPartyEntry().getEntry().getUri();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getInternalID() {
		return internalID;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
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

	public List<SalesOrderItemResponse> getItems() {
		return items;
	}

	public void setItems(List<SalesOrderItemResponse> items) {
		this.items = items;
	}

	public String getParties() {
		return parties;
	}

	public void setParties(String parties) {
		this.parties = parties;
	}

}
