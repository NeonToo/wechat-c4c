package com.sap.csc.domain.model.c4c.datatype;

import com.sap.csc.domain.model.c4c.C4CModificableEntity;

public class C4CDescription extends C4CModificableEntity {

	private static final long serialVersionUID = -8381018448447861028L;

	private String languageCode;

	private String content;

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
