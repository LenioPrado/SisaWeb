/*
 * File:	    CampusCourseException.java
 * Creation date: 19/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class CampusCourseException.
 */
public class CampusCourseException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7478520519367186747L;

	/**
	 * Instantiates a new campus course exception.
	 * 
	 * @param message
	 *            the message
	 */
	public CampusCourseException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new campus course exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CampusCourseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new campus course exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public CampusCourseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new campus course exception.
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
	protected CampusCourseException(String message, Throwable cause,
			boolean enableSupression, boolean writableStackTrace) {
		super(message, cause, enableSupression, writableStackTrace);
	}
}
