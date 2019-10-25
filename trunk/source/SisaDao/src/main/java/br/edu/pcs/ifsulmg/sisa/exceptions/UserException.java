/*
* File:	     UserException.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class User Exception.
 */
public class UserException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5836184720573625664L;

	/**
	 * Instantiates a new User Exception.
	 *
	 * @param message the message
	 */
	public UserException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new User Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new User Exception.
	 *
	 * @param cause the cause
	 */
	public UserException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new User Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected UserException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}