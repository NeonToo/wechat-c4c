package com.sap.csc.domain.exception;

/**
 * @author I071053
 */
public class CommonBusinessException extends CommonApplicationException {

	private static final long serialVersionUID = -2854497459395419345L;

	public CommonBusinessException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public CommonBusinessException(String errorCode, String errorMessage, Exception ex) {
		super(errorCode, errorMessage, ex);
	}
}
