/*
* File:	     UserRoleException.java
* Creation date: 	19/06/2015
* Verification:     PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.exceptions;

public class UserRoleException extends Exception {
	
	private static final long serialVersionUID = -1792658158275026174L;;
	
	public UserRoleException(String message){
		super(message);
	}	
	public UserRoleException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserRoleException(Throwable cause) {
		super(cause);
	}
	
	protected UserRoleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	}

	
	

	
	}
