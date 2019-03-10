package com.sap.csc.domain.model.c4c.datatype;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author I326950
 */
public class C4CAssociationEntry implements Serializable {

	private static final long serialVersionUID = 1093307553917598144L;

	@JsonProperty("__deferred")
	private Entry entry;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public static class Entry implements Serializable {

		private static final long serialVersionUID = 8111159657629131467L;
		@JsonProperty("uri")
		private String uri;

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

	}
}
