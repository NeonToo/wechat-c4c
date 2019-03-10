package com.sap.csc.domain.model.c4c.product;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.C4CModificableEntity;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;

public class C4CProductCategory extends C4CModificableEntity {

	private static final long serialVersionUID = -8824698706090141228L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("InternalID")
	private String internalID;

	@JsonProperty("ParentInternalID")
	private String parentInternalID;

	@JsonProperty("ProductAssignmentAllowedIndicator")
	private boolean isAllowedAssignment;

	@JsonProperty("ProductCategoryHierarchyDescription")
	private Set<C4CProductCategoryDescription> descriptions;

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

	public String getParentInternalID() {
		return parentInternalID;
	}

	public void setParentInternalID(String parentInternalID) {
		this.parentInternalID = parentInternalID;
	}

	public boolean isAllowedAssignment() {
		return isAllowedAssignment;
	}

	public void setAllowedAssignment(boolean isAllowedAssignment) {
		this.isAllowedAssignment = isAllowedAssignment;
	}

	public Set<C4CProductCategoryDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<C4CProductCategoryDescription> descriptions) {
		this.descriptions = descriptions;
	}

}
