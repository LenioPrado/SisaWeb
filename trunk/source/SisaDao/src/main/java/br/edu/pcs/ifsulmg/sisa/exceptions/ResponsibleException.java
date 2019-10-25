/*
* File:	     ResponsibleException.java
* Creation date: 19/06/2015
* Discipline:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class ResponsibleException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -951203627541289632L;

	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param message the message
	 */
	public ResponsibleException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ResponsibleException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param cause the cause
	 */
	public ResponsibleException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected ResponsibleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
