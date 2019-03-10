package com.sap.csc.domain.model.dto.request.c4c;

import java.io.Serializable;

/**
 * @author I326950
 */
public class C4CSystemRequest implements Serializable {

	private static final long serialVersionUID = 5274465106448558291L;
	
	private Long id;

	private String url;

	public C4CSystemRequest() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
