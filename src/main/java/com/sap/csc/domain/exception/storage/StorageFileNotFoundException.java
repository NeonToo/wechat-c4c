package com.sap.csc.domain.exception.storage;

import com.sap.csc.domain.exception.CommonBusinessException;

/**
 * @author I326950
 */
public class StorageFileNotFoundException extends CommonBusinessException {

	private static final long serialVersionUID = 416229608998985619L;
	
	private static final String ERROR_CODE = "FILE_NOT_FOUND";

	private static final String ERROR_MESSAGE = "The file not found";

	public StorageFileNotFoundException() {
		super(ERROR_CODE, ERROR_MESSAGE);
	}

	public StorageFileNotFoundException(Exception ex) {
		super(ERROR_CODE, ERROR_MESSAGE, ex);
	}

}
