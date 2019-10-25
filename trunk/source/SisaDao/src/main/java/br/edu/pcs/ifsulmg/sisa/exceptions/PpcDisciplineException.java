/*
* File:	    PpcDisciplineException.java
* Creation date: 19/06/2015
* Author:        Rarvs
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class PpcDisciplineException.
 */
public class PpcDisciplineException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1456237895421306578L;
	
	/**
	 * Instantiates a new ppc discipline exception.
	 *
	 * @param message the message
	 */
	public PpcDisciplineException (String message){
		super(message);
	}
	
	/**
	 * Instantiates a new ppc discipline exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public PpcDisciplineException (String message, Throwable cause){
		super(message, cause);
	}
	
	/**
	 * Instantiates a new ppc discipline exception.
	 *
	 * @param cause the cause
	 */
	public PpcDisciplineException (Throwable cause){
		super(cause);
	}
	
	/**
	 * Instantiates a new ppc discipline exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSupression the enable supression
	 * @param writableStackTrace the writable stack trace
	 */
	public PpcDisciplineException (String message, Throwable cause, boolean enableSupression, boolean writableStackTrace){
		super(message, cause, enableSupression, writableStackTrace);
	}
}
