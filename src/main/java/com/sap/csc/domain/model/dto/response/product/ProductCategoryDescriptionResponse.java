package com.sap.csc.domain.model.dto.response.product;

import java.io.Serializable;

import com.sap.csc.domain.model.c4c.product.C4CProductCategoryDescription;

public class ProductCategoryDescriptionResponse implements Serializable {

	private static final long serialVersionUID = -5685026049355179798L;

	private String uri;

	private String objectID;

	private String categoryObjectID;

	private String languageCode;

	private String language;

	private String content;

	public ProductCategoryDescriptionResponse(C4CProductCategoryDescription description) {
		this.uri = description.getMetadata().getUri();
		this.objectID = description.getObjectID();
		this.categoryObjectID = description.getCategortyObjectID();
		this.languageCode = description.getLanguageCode();
		this.language = description.getLanguage();
		this.content = description.getContent();
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

	public String getCategoryObjectID() {
		return categoryObjectID;
	}

	public void setCategoryObjectID(String categoryObjectID) {
		this.categoryObjectID = categoryObjectID;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
