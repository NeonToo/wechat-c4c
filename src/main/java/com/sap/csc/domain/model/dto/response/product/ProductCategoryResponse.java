package com.sap.csc.domain.model.dto.response.product;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import com.sap.csc.domain.model.c4c.product.C4CProductCategory;

public class ProductCategoryResponse implements Serializable {

	private static final long serialVersionUID = 8956609241474102521L;

	private String uri;

	private String objectID;

	private String internalID;

	private String parentInternalID;

	private boolean isAllowedAssignment;
	
	private String description;

	private List<ProductCategoryDescriptionResponse> descriptions;

	public ProductCategoryResponse(C4CProductCategory category) {
		this.uri = category.getMetadata().getUri();
		this.objectID = category.getObjectID();
		this.internalID = category.getInternalID();
		this.parentInternalID = category.getParentInternalID();
		this.isAllowedAssignment = category.isAllowedAssignment();

		if (CollectionUtils.isNotEmpty(category.getDescriptions())) {
//			Iterator<C4CProductCategoryDescription> it = category.getDescriptions().iterator();
//			
//			while(it.hasNext()) {
//				C4CProductCategoryDescription description = it.next();
//				
//				if(category.getDescriptions().size() == 1) {
//					this.description = description.getContent();
//					break;
//				} else if(StringUtils.equals(description.getLanguageCode(), "1")) {
//					this.description = description.getContent();
//				}
//			}
			
			this.descriptions = category.getDescriptions().stream()
					.map(description -> new ProductCategoryDescriptionResponse(description))
					.collect(Collectors.toList());
			
			
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductCategoryDescriptionResponse> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<ProductCategoryDescriptionResponse> descriptions) {
		this.descriptions = descriptions;
	}

}
