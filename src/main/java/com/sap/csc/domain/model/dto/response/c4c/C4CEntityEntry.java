package com.sap.csc.domain.model.dto.response.c4c;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CEntityEntry<T> implements Serializable {

	private static final long serialVersionUID = -6096982020153174273L;

	@JsonProperty("results")
	private T result;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
