/*
* File:	     VerificationException.java
* Creation date: 	12/06/2015
* Verification:     PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class VerificationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1792658158275026174L;
	
	/**
	 * Instantiates a new Verification Exception.
	 *
	 * @param message the message
	 */
	public VerificationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Verification Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public VerificationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Verification Exception.
	 *
	 * @param cause the cause
	 */
	public VerificationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Verification Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected VerificationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
