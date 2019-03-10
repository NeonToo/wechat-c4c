package com.sap.csc.domain.model.dto.request;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;

/**
 * @author I326950
 */
public class GeneralQueryRequest implements Serializable {

	private static final long serialVersionUID = -3958875371456545426L;

	private PageRequest pageRequest;

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}

}
