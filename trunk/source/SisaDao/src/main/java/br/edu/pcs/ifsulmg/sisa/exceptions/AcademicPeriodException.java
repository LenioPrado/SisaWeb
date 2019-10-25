/*
* File:	     AcademicPeriodException.java
* Creation date: 19/06/2015
* Author:        Rarvs
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class AcademicPeriodException.
 */
public class AcademicPeriodException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8517812116421189498L;

	/**
	 * Instantiates a new academic period exception.
	 *
	 * @param message the message
	 */
	public AcademicPeriodException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new academic period exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public AcademicPeriodException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new academic period exception.
	 *
	 * @param cause the cause
	 */
	public AcademicPeriodException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new academic period exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected AcademicPeriodException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
