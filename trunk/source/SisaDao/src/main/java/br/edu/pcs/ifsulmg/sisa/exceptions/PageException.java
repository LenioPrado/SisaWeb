/*
 * File:	     PageException.java
 * Creation date: 30/03/2016
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class Page Exception.
 */
public class PageException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5938719832779625664L;

	/**
	 * Instantiates a new Page Exception.
	 *
	 * @param message the message
	 */
	public PageException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Page Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public PageException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Page Exception.
	 *
	 * @param cause the cause
	 */
	public PageException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Page Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected PageException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
