/*
* File:	     EntityConstraintViolationException.java
* Creation date: 16/03/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class EntityConstraintViolationException.
 */
public class EntityConstraintViolationException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -57396836104332664L;

	/**
	 * Instantiates a new entity constraint violation exception.
	 *
	 * @param message the message
	 */
	public EntityConstraintViolationException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new entity constraint violation exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public EntityConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new entity constraint violation exception.
	 *
	 * @param cause the cause
	 */
	public EntityConstraintViolationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new entity constraint violation exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected EntityConstraintViolationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}