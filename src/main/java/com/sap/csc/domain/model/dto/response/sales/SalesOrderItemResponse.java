package com.sap.csc.domain.model.dto.response.sales;

import java.io.Serializable;

import com.sap.csc.domain.model.c4c.datatype.C4CAmount;
import com.sap.csc.domain.model.c4c.datatype.C4CQuantity;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderItem;

/**
 * @author I326950
 */
public class SalesOrderItemResponse implements Serializable {

	private static final long serialVersionUID = -672027715098172678L;
	
	private String uri;

	private String itemNO;

	private String productID;

	private String productCategoryID;

	private String description;

	private C4CQuantity quantity;
	
	private C4CAmount netAmount;
	
	private C4CAmount netPriceAmount;

	public SalesOrderItemResponse(C4CSalesOrderItem item) {
		this.uri = item.getMetadata().getUri();
		this.itemNO = item.getItemNO();
		this.productID = item.getProductID();
		this.productCategoryID = item.getProductCategoryID();
		this.description = item.getDescription();
		this.quantity = item.getQuantity();
		this.netAmount = item.getNetAmount();
		this.netPriceAmount = item.getNetPriceAmount();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
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

}
