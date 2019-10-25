/*
* File:	     ProcedureEvaluationException.java
* Creation date: 19/06/2015
* Discipline:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class ProcedureEvaluationException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -956412035165102061L;

	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param message the message
	 */
	public ProcedureEvaluationException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ProcedureEvaluationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new evaluation exception.
	 *
	 * @param cause the cause
	 */
	public ProcedureEvaluationException(Throwable cause) {
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
	protected ProcedureEvaluationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
