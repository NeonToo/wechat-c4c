package com.sap.csc.domain.model.c4c.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.csc.domain.model.c4c.C4CModificableEntity;
import com.sap.csc.domain.model.c4c.datatype.C4CMetadataEntry;

public class C4CProductCategoryDescription extends C4CModificableEntity {

	private static final long serialVersionUID = 4169480632643846355L;

	@JsonProperty("__metadata")
	private C4CMetadataEntry metadata;

	@JsonProperty("ObjectID")
	private String objectID;

	@JsonProperty("ParentObjectID")
	private String categortyObjectID;

	@JsonProperty("languageCode")
	private String languageCode;

	@JsonProperty("languageCodeText")
	private String language;

	@JsonProperty("Description")
	private String content;

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

	public String getCategortyObjectID() {
		return categortyObjectID;
	}

	public void setCategortyObjectID(String categortyObjectID) {
		this.categortyObjectID = categortyObjectID;
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
