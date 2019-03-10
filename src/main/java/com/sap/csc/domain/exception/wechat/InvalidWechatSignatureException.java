package com.sap.csc.domain.exception.wechat;

import com.sap.csc.web.exception.CommonWebException;

/**
 * @author i071053
 */
public class InvalidWechatSignatureException extends CommonWebException {

	private static final long serialVersionUID = -6232184168894285342L;

	private static final String ERROR_CODE = "INVALID_WECHAT_SIGNATURE";

	private static final String ERROR_MESSAGE = "The signture is invalid";

	private String timestamp;
	private String nonce;
	private String signature;

	public InvalidWechatSignatureException(String timestamp, String nonce, String signature) {
		super(ERROR_CODE, ERROR_MESSAGE);
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.signature = signature;
	}

	public InvalidWechatSignatureException(String timestamp, String nonce, String signature, Exception ex) {
		super(ERROR_CODE, ERROR_MESSAGE, ex);
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
