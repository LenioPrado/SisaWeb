/*
* File:	     DisciplineBibliographyException.java
* Creation date: 19/06/2015
* Discipline:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class DisciplineBibliographyException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8457613427946342170L;

	/**
	 * Instantiates a new DisciplineBibliography Exception.
	 *
	 * @param message the message
	 */
	public DisciplineBibliographyException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new DisciplineBibliography Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DisciplineBibliographyException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new DisciplineBibliography Exception.
	 *
	 * @param cause the cause
	 */
	public DisciplineBibliographyException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new DisciplineBibliography Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected DisciplineBibliographyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}