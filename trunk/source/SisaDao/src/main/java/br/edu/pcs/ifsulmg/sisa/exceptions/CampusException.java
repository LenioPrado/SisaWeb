/*
 * File:	     CampusException.java
 * Creation date: 19/06/2015
 * Author:        Amanda
 */
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class CampusException.
 */
public class CampusException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1234567894567891232L;

	/**
	 * Instantiates a new campus exception.
	 *
	 * @param message the message
	 */
	public CampusException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new campus exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public CampusException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new campus exception.
	 *
	 * @param cause the cause
	 */
	public CampusException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new campus exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected CampusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
