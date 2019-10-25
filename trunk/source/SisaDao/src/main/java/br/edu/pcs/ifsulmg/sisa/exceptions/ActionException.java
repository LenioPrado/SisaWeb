/*
* File:	     ActionException.java
* Creation date: 	30/03/2016
* Verification:     PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class ActionException.
 */
public class ActionException extends Exception {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -5938234532779625664L;

	/**
	 * Instantiates a new action exception.
	 *
	 * @param message the message
	 */
	public ActionException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new action exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new action exception.
	 *
	 * @param cause the cause
	 */
	public ActionException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new action exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected ActionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
