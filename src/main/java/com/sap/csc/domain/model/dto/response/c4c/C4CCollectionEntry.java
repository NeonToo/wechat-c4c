package com.sap.csc.domain.model.dto.response.c4c;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CCollectionEntry<T> implements Serializable {

	private static final long serialVersionUID = -5413874600548616405L;

	@JsonProperty("__count")
	private Long count;

	@JsonProperty("results")
	private List<T> results;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

}
