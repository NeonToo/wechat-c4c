package com.sap.csc.domain.model.dto.response.c4c;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CCollectionResponse<T> implements Serializable {

	private static final long serialVersionUID = 5879186585138695951L;

	@JsonProperty("d")
	private C4CCollectionEntry<T> d;

	public C4CCollectionEntry<T> getD() {
		return d;
	}

	public void setD(C4CCollectionEntry<T> d) {
		this.d = d;
	}

}
