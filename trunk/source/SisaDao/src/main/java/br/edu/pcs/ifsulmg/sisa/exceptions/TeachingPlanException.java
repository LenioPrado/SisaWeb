/*
* File:	     TeachingPlanException.java
* Creation date: 	19/06/2015
* Verification:     PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class TeachingPlanException.
 */
public class TeachingPlanException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1792658158275026174L;;
	
	/**
	 * Instantiates a new teaching plan exception.
	 *
	 * @param message the message
	 */
	public TeachingPlanException(String message){
		super(message);
	}	
	
	/**
	 * Instantiates a new teaching plan exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public TeachingPlanException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new teaching plan exception.
	 *
	 * @param cause the cause
	 */
	public TeachingPlanException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new teaching plan exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected TeachingPlanException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	}

	
	

	
	}
