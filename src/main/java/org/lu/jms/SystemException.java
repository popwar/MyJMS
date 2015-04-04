package org.lu.jms;

/**
 * 
 * business exception
 * 
 * @author Lorry.guo
 * 
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -8984119466781288528L;

	private String errorCode;

	private String paramter;

	public SystemException() {
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String errorCode, String paramter) {
		this.errorCode = errorCode;
		this.paramter = paramter;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setParamter(String paramter) {
		this.paramter = paramter;
	}

	public String getParamter() {
		return this.paramter;
	}

	@Override
	public String toString() {
		return "{\"errorCode\":\"" + errorCode + "\",\"paramter\":\""
				+ this.paramter + "\"}";
	}

}
