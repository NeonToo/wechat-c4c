package com.sap.csc.domain.exception.wechat;

import com.sap.csc.domain.exception.CommonBusinessException;

/**
 * @author I326950
 */
public class WechatPlatformNotFoundException extends CommonBusinessException {

	private static final long serialVersionUID = 4414193726147701112L;

	private static final String ERROR_CODE = "WECHAT_PLATFORM_NOT_FOUND";

	private static final String ERROR_MESSAGE = "Wechat platform not found";
	
	public WechatPlatformNotFoundException() {
		super(ERROR_CODE, ERROR_MESSAGE);
	}
	
	public WechatPlatformNotFoundException(Exception ex) {
		super(ERROR_CODE, ERROR_MESSAGE, ex);
	}
}
