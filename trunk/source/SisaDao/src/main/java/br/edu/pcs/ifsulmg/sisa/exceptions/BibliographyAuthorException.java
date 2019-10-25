/*
* File:	     BibliographyAuthorException.java
* Creation date: 19/06/2015
* Author:        Rarvs
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class BibliographyAuthorException.
 */
public class BibliographyAuthorException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1923608550301840192L;

	/**
	 * Instantiates a new bibliography author exception.
	 *
	 * @param message the message
	 */
	public BibliographyAuthorException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new bibliography author exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public BibliographyAuthorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new bibliography author exception.
	 *
	 * @param cause the cause
	 */
	public BibliographyAuthorException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new bibliography author exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSupression the enable supression
	 * @param writableStackTrace the writable stack trace
	 */
	protected BibliographyAuthorException(String message, Throwable cause,
			boolean enableSupression, boolean writableStackTrace) {
		super(message, cause, enableSupression, writableStackTrace);
	}
}
