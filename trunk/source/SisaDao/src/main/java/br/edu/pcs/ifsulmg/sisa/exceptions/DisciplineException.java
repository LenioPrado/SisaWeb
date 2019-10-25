/*
* File:	     DisciplineException.java
* Creation date: 19/06/2015
* Discipline:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class DisciplineException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -945637918456731249L;

	/**
	 * Instantiates a new Discipline Exception.
	 *
	 * @param message the message
	 */
	public DisciplineException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Discipline Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DisciplineException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Discipline Exception.
	 *
	 * @param cause the cause
	 */
	public DisciplineException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Discipline Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected DisciplineException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
