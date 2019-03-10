package com.sap.csc.util.builder;

public class QueryOption<T> {
	private String key;
	private T value;
	
	public QueryOption(String key, T value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
