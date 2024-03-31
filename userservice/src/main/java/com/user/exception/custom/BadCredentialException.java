package com.user.exception.custom;

public class BadCredentialException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadCredentialException(String msg) {
		super(msg);
	}

}
