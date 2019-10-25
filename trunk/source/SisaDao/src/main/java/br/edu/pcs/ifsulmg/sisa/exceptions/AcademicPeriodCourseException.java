/*
* File:	     AcademicPeriodCourseException.java
* Creation date: 	19/06/2015
* Verification:     PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class AcademicPeriodCourseException.
 */
public class AcademicPeriodCourseException extends Exception {
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1792658158275026174L;
	
	/**
	 * Instantiates a new academic period course exception.
	 *
	 * @param message the message
	 */
	public AcademicPeriodCourseException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new academic period course exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public AcademicPeriodCourseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new academic period course exception.
	 *
	 * @param cause the cause
	 */
	public AcademicPeriodCourseException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new academic period course exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected AcademicPeriodCourseException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}