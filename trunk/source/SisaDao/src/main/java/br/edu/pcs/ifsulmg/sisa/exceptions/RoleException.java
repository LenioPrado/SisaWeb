package br.edu.pcs.ifsulmg.sisa.exceptions;

public class RoleException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4170852154762875314L;

	/**
	 * Instantiates a new Role Exception.
	 *
	 * @param message the message
	 */
	public RoleException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Role Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public RoleException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Role Exception.
	 *
	 * @param cause the cause
	 */
	public RoleException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new Role Exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	protected RoleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
