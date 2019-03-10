package com.sap.csc.domain.model.dto.response.product;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.commons.collections4.CollectionUtils;

import com.sap.csc.domain.model.c4c.product.C4CProduct;
import com.sap.csc.domain.model.c4c.product.C4CProduct.C4CProductCategory;

public class ProductResponse implements Serializable {

	private static final long serialVersionUID = 3135386341418722891L;

	private String uri;

	private String objectID;

	private String internalID;

	private String unitCode;

	private String unit;

	private String description;

	private String categoryUUID;

	private String categoryID;

	private String category;

	public ProductResponse(C4CProduct product) {
		this.uri = product.getMetadata().getUri();
		this.objectID = product.getObjectID();
		this.internalID = product.getInternalID();
		this.unitCode = product.getUnitCode();
		this.unit = product.getUnit();
		this.description = product.getDescription();

		if (CollectionUtils.isNotEmpty(product.getCategory())) {
			Iterator<C4CProductCategory> it = product.getCategory().iterator();

			while (it.hasNext()) {
				C4CProductCategory category = it.next();

				this.categoryUUID = category.getUuid();
				this.categoryID = category.getInternalID();

				if (category.getDescription() != null) {
					this.category = category.getDescription().getContent();
				}

				break;
			}
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

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryUUID() {
		return categoryUUID;
	}

	public void setCategoryUUID(String categoryUUID) {
		this.categoryUUID = categoryUUID;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
