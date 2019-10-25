package br.edu.pcs.ifsulmg.sisa.exceptions;

public class PpcException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8563829501648364530L;

	/**
	 * Instantiates a new Ppc Exception.
	 *
	 * @param message the message
	 */
	public PpcException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Ppc Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public PpcException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Ppc Exception.
	 *
	 * @param cause the cause
	 */
	public PpcException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Ppc Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected PpcException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
