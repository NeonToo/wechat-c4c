package com.sap.csc.domain.model.dto.response.c4c;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CEntityResponse<T> implements Serializable {

	private static final long serialVersionUID = 2706124356563190964L;

	@JsonProperty("d")
	private C4CEntityEntry<T> d;

	public C4CEntityEntry<T> getD() {
		return d;
	}

	public void setD(C4CEntityEntry<T> d) {
		this.d = d;
	}

}
