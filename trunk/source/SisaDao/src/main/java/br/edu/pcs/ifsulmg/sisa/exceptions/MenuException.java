package br.edu.pcs.ifsulmg.sisa.exceptions;

/**
 * The Class MenuException.
 */
public class MenuException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8563854895016483653L;

	/**
	 * Instantiates a new Menu Exception.
	 *
	 * @param message the message
	 */
	public MenuException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Menu Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public MenuException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Menu Exception.
	 *
	 * @param cause the cause
	 */
	public MenuException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Menu Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected MenuException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
