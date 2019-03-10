package com.sap.csc.domain.model.c4c.datatype;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CMetadataEntry implements Serializable {

	private static final long serialVersionUID = -8257736598191435666L;

	@JsonProperty("uri")
	private String uri;

	@JsonProperty("type")
	private String type;

	@JsonProperty("etag")
	private String etag;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

}
