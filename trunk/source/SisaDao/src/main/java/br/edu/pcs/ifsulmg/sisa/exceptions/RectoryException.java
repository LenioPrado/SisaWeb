/*
* File:	     RectoryException.java
* Creation date: 	19/06/2015
* Verification:     PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class RectoryException.
 */
public class RectoryException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1792658158275026174L;;
	
	/**
	 * Instantiates a new rectory exception.
	 *
	 * @param message the message
	 */
	public RectoryException(String message){
		super(message);
	}	
	
	/**
	 * Instantiates a new rectory exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public RectoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new rectory exception.
	 *
	 * @param cause the cause
	 */
	public RectoryException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new rectory exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected RectoryException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	}

	
	

	
	}