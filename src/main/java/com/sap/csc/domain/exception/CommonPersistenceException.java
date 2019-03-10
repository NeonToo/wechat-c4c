package com.sap.csc.domain.exception;

/**
 * @author I071053
 */
public class CommonPersistenceException extends CommonApplicationException {

	private static final long serialVersionUID = 9025450200084955245L;

	public CommonPersistenceException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public CommonPersistenceException(String errorCode, String errorMessage, Exception ex) {
		super(errorCode, errorMessage, ex);
	}
}
