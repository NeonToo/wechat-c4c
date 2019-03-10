package com.sap.csc.web.exception.wechat;

import com.sap.csc.web.exception.CommonWebException;

/**
 * @author I326950
 */
public class InvalidWechatUserException extends CommonWebException {

	private static final long serialVersionUID = -2275441076377937966L;

	private static final String ERROR_CODE = "INVALID_WECHAT_USER";

	private static final String ERROR_MESSAGE = "The wechat user is invalid";

	public InvalidWechatUserException() {
		super(ERROR_CODE, ERROR_MESSAGE);
	}

	public InvalidWechatUserException(Exception ex) {
		super(ERROR_CODE, ERROR_MESSAGE, ex);
	}
}
