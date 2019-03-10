package com.sap.csc.domain.exception.storage;

import com.sap.csc.domain.exception.CommonApplicationException;

/**
 * @author I326950
 */
public class StorageException extends CommonApplicationException {

	private static final long serialVersionUID = 363836429703830379L;
	
	private static final String ERROR_CODE = "INVALID_FILE_STORAGE";

	private static final String ERROR_MESSAGE = "The file storage could not be intialized";

	public StorageException() {
		super(ERROR_CODE, ERROR_MESSAGE);
	}

	public StorageException(Exception ex) {
		super(ERROR_CODE, ERROR_MESSAGE, ex);
	}
	
	public StorageException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public StorageException(String errorCode, String errorMessage, Exception ex) {
		super(errorCode, errorMessage, ex);
	}

}
