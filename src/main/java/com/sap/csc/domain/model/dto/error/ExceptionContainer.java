package com.sap.csc.domain.model.dto.error;

import com.sap.csc.domain.exception.CommonApplicationException;

/**
 * 异常的信息容器，包含最基本的错误信息。
 *
 * @author i071053
 */
public class ExceptionContainer {

	protected String code;

	protected String message;

	public ExceptionContainer(CommonApplicationException exception) {
		if (exception == null) {
			return;
		}
		this.setCode(exception.getErrorCode());
		this.setMessage(exception.getErrorMessage());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
