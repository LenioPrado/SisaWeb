/*
* File:	     EvaluationException.java
* Creation date: 12/06/2015
* Discipline:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class EvaluationException.
 */
public class EvaluationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5836184720573625664L;

	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param message the message
	 */
	public EvaluationException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public EvaluationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param cause the cause
	 */
	public EvaluationException(Throwable cause) {
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
	protected EvaluationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}