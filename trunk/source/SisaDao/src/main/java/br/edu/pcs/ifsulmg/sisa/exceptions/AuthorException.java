/*
* File:	     AuthorException.java
* Creation date: 03/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class Author Exception.
 */
public class AuthorException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5938719832779625664L;

	/**
	 * Instantiates a new Author Exception.
	 *
	 * @param message the message
	 */
	public AuthorException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Author Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public AuthorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Author Exception.
	 *
	 * @param cause the cause
	 */
	public AuthorException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Author Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected AuthorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
