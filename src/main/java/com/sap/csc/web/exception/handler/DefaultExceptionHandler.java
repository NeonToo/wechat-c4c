package com.sap.csc.web.exception.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.sap.csc.domain.exception.CommonApplicationException;
import com.sap.csc.domain.model.dto.error.ExceptionContainer;

/**
 * Default handler to handle all
 *
 * @author i071053
 */
@Component
public class DefaultExceptionHandler extends GeneralExceptionHandler<Exception> {
	
	protected static final String ERROR_CODE = "UNKNOWN_ERROR";

	protected static final String ERROR_MESSAGE = "Server is unavailable to handle your request, please try again later to contact administrator";

	@Override
	public int getStatusCode() {
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}
	
	@Override
	public Object getBody(Exception ex) {
		return new ExceptionContainer(new CommonApplicationException(ERROR_CODE, ERROR_MESSAGE, ex));
	}

}
