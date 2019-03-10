package com.sap.csc.web.exception.handler;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletResponse;

public abstract class GeneralExceptionHandler<TException extends Exception> implements ExceptionHandler<TException> {

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<TException> getExceptionType() {
		return (Class<TException>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments())[0];
	}

}
