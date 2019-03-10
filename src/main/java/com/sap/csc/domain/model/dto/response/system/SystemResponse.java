package com.sap.csc.domain.model.dto.response.system;

import java.io.Serializable;

import com.sap.csc.domain.model.jpa.c4c.C4CSystem;

/**
 * @author I326950
 */
public class SystemResponse implements Serializable {

	private static final long serialVersionUID = -5391668556943355043L;

	private Long id;

	private String url;
	
	public SystemResponse(C4CSystem system) {
		this.id = system.getId();
		this.url = system.getUrl();
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
