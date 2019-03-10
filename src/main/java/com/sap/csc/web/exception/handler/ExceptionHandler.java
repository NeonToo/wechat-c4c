package com.sap.csc.web.exception.handler;

/**
 * 处理制定类型的异常,屏蔽掉异常的技术信息并返回统一的信息，注意异常会被最接近异常类型的处理器优化处理。
 *
 * @author i071053
 */
public interface ExceptionHandler<TException extends Exception> {

	/**
	 * 获取异常的实例类型。
	 *
	 * @return 异常的实例类型
	 */
	Class<TException> getExceptionType();

	/**
	 * 获取特定异常应当返回的HTTP状态码。
	 *
	 * @return 特定异常应当返回的HTTP状态码
	 */
	int getStatusCode();

	/**
	 * 获取特定异常应当返回的错误信息返回给Web前端。
	 *
	 * @param ex
	 *            特定异常
	 * @return 应当返回的错误信息返回给Web前端
	 */
	Object getBody(TException ex);

}
