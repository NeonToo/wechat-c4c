package com.sap.csc.domain.model.c4c.product;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.C4CModificableEntity;
import com.sap.csc.domain.model.c4c.datatype.C4CDescription;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;

public class C4CProduct extends C4CModificableEntity {

	private static final long serialVersionUID = 5273207588371542603L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("ID")
	private String internalID;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("UnitOfMeasureCode")
	private String unitCode;

	@JsonProperty("UnitOfMeasureCodeText")
	private String unit;

	@JsonProperty("ProductCategoryAssignment")
	private Set<C4CProductCategory> category;

	public static class C4CProductCategory extends C4CModificableEntity {

		private static final long serialVersionUID = 4428366549440423491L;

		@JsonProperty("ObjectID")
		private String objectID;

		@JsonProperty("ProductCategoryUUID")
		private String uuid;

		@JsonProperty("ProductCategoryID")
		private String internalID;

		@JsonProperty("ParentObjectID")
		private String productObjectID;

		@JsonProperty("ProductCategoryDescription")
		private C4CDescription description;

		public String getObjectID() {
			return objectID;
		}

		public void setObjectID(String objectID) {
			this.objectID = objectID;
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

		public String getProductObjectID() {
			return productObjectID;
		}

		public void setProductObjectID(String productObjectID) {
			this.productObjectID = productObjectID;
		}

		public C4CDescription getDescription() {
			return description;
		}

		public void setDescription(C4CDescription description) {
			this.description = description;
		}

	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set<C4CProductCategory> getCategory() {
		return category;
	}

	public void setCategory(Set<C4CProductCategory> category) {
		this.category = category;
	}

}
