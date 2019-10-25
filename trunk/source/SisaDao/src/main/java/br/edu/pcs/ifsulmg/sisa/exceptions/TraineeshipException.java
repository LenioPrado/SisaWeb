/*
* File:	    TraineeshipException.java
* Creation date: 19/06/2015
* Author:        Rarvs
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class TraineeshipException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8754205193267186747L;

	/**
	 * Instantiates a new traineeship exception.
	 * 
	 * @param message
	 *            the message
	 */
	public TraineeshipException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new traineeship exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public TraineeshipException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new traineeship exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public TraineeshipException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new traineeship exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSupression
	 *            the enable supression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	protected TraineeshipException(String message, Throwable cause,
			boolean enableSupression, boolean writableStackTrace) {
		super(message, cause, enableSupression, writableStackTrace);
	}

}
