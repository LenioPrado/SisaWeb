/*
* File:	     ClassProgrammingException.java
* Creation date: 19/06/2015
* ClassProgramming:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class ClassProgrammingException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1563247895620156397L;

	/**
	 * Instantiates a new ClassProgramming Exception.
	 *
	 * @param message the message
	 */
	public ClassProgrammingException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new ClassProgramming Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ClassProgrammingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new ClassProgramming Exception.
	 *
	 * @param cause the cause
	 */
	public ClassProgrammingException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new ClassProgramming Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected ClassProgrammingException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
