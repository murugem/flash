package com.rbc.snc.Exception;

public class SncException extends Exception {

	/**
	 * Exception class holder to hold PostEdgeExceptions 
	 * 
	 */
	private String message;
	private String sncErrorCode;
	
	private static final long serialVersionUID = 111111100000091234L;

	public SncException() { 
		super();

	}
	/**
	 * 
	 * @param faultInfo
	 * @param message
	 * @param postEdgeErrorCode
	 */
	public SncException(String message,String sncErrorCode) { 
		super(message);
		this.message = message;
		this.sncErrorCode = sncErrorCode;
	}
	
	/**
	 * 
	 * @param faultInfo
	 * @param message
	 */
	public SncException(String message) { 
		super(message);
		this.message = message;
	}

	/**
	 * 
	 * @param faultInfo
	 * @param message
	 * @param cause
	 */
	public SncException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
	
	/**
	 * 
	 * @param faultInfo
	 * @param message
	 * @param postEdgeErrorCode
	 * @param cause
	 */
	public SncException(String message,String  sncErrorCode, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.sncErrorCode = sncErrorCode;
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getSncErrorCode() {
		return sncErrorCode;
	}
	public void setSncErrorCode(String sncErrorCode) {
		this.sncErrorCode = sncErrorCode;
	}
	
	
}


