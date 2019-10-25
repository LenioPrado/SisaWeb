/*
* File:	     BibliographyException.java
* Creation date: 19/06/2015
* Author:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class BibliographyException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8216730527432875106L;

	/**
	 * Instantiates a new Bibliography Exception.
	 *
	 * @param message the message
	 */
	public BibliographyException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Bibliography Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public BibliographyException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Bibliography Exception.
	 *
	 * @param cause the cause
	 */
	public BibliographyException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Bibliography Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected BibliographyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
