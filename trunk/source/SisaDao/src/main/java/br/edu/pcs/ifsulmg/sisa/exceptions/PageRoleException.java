/*
 * File:	     PageRoleEsception.java
 * Creation date: 30/03/2016
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class PageRoleException.
 */
public class PageRoleException extends Exception {
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1792658158275026174L;
	
	/**
	 * Instantiates a new page role exception.
	 *
	 * @param message the message
	 */
	public PageRoleException(String message) {
		super(message);
	}
	
	/**
	 * Instantiates a new page role exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public PageRoleException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new page role exception.
	 *
	 * @param cause the cause
	 */
	public PageRoleException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Instantiates a new page role exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected PageRoleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
