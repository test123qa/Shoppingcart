package com.virtualagent.boot.Exception;

public class ShoppingCartException extends Exception {
	public static final long SERIAL_VERSION_UID = 1L;

	private String errorMesssage;
	private String errorCode;

	public ShoppingCartException() {
		super();
	}

	public ShoppingCartException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(String message, Throwable cause) {

		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(String message, String errorCode) {

		this.errorMesssage = message;
		this.errorCode = errorCode;
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(String message) {

		// TODO Auto-generated constructor stub
	}

	public ShoppingCartException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
