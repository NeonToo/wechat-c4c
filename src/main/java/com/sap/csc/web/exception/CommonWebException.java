package com.sap.csc.web.exception;

import com.sap.csc.domain.exception.CommonApplicationException;

/**
 * @author Diouf Du
 */
public class CommonWebException extends CommonApplicationException {

	private static final long serialVersionUID = -49093352328654622L;

	public CommonWebException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public CommonWebException(String errorCode, String errorMessage, Exception ex) {
		super(errorCode, errorMessage, ex);
	}
}
