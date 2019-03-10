package com.sap.csc.domain.model.c4c.sales;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.datatype.C4CAmount;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;
import com.sap.csc.domain.model.c4c.datatype.C4CQuantity;

/**
 * @author I326950
 */
public class C4CSalesOrderItem implements Serializable {

	private static final long serialVersionUID = -2582145243275589332L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("ItemNo")
	private String itemNO;

	@JsonProperty("ProductID")
	private String productID;

	@JsonProperty("ProductInternalID")
	private String productInternalID;

	@JsonProperty("ProductCategoryID")
	private String productCategoryID;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Quantity")
	private C4CQuantity quantity;
	
	@JsonProperty("NetAmount")
	private C4CAmount netAmount;
	
	@JsonProperty("NetPriceAmount")
	private C4CAmount netPriceAmount;

	@JsonProperty("ParentObjectID")
	private String orderObjectID;

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

	public String getItemNO() {
		return itemNO;
	}

	public void setItemNO(String itemNO) {
		this.itemNO = itemNO;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductInternalID() {
		return productInternalID;
	}

	public void setProductInternalID(String productInternalID) {
		this.productInternalID = productInternalID;
	}

	public String getProductCategoryID() {
		return productCategoryID;
	}

	public void setProductCategoryID(String productCategoryID) {
		this.productCategoryID = productCategoryID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public C4CQuantity getQuantity() {
		return quantity;
	}

	public void setQuantity(C4CQuantity quantity) {
		this.quantity = quantity;
	}

	public C4CAmount getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(C4CAmount netAmount) {
		this.netAmount = netAmount;
	}

	public C4CAmount getNetPriceAmount() {
		return netPriceAmount;
	}

	public void setNetPriceAmount(C4CAmount netPriceAmount) {
		this.netPriceAmount = netPriceAmount;
	}

	public String getOrderObjectID() {
		return orderObjectID;
	}

	public void setOrderObjectID(String orderObjectID) {
		this.orderObjectID = orderObjectID;
	}

}
