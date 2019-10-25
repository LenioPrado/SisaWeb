/*
* File:	     ConnectionFactoryException.java
* Creation date: 03/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class ConnectionFactoryException.
 */
public class ConnectionFactoryException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7543775772352876214L;

	/**
	 * Instantiates a new connection factory exception.
	 * 
	 * @param message the message
	 */
	public ConnectionFactoryException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new connection factory exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public ConnectionFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new connection factory exception.
	 * 
	 * @param cause the cause
	 */
	public ConnectionFactoryException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new connection factory exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected ConnectionFactoryException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
