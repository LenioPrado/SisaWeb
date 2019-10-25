/*
* File:	     User.java
* Creation date: 08/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class User.
 */
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3383313478433788627L;

	/**
	 * Instantiates a new user.
	 */
	public User(){
		
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userId the user id
	 * @param function the function
	 * @param name the name
	 * @param password the password
	 * @param email the email
	 */
	public User(int userId, String name, String password, String email){
		_userId = userId;
		_name = name;
		_password = password;
		_email = email;
	}
	
	/** The _user id. */
	private int _userId;
	
	/** The _name. */
	private String _name;
	
	/** The _password. */
	private String _password;
	
	/** The _view password. */
	private String _viewPassword;
	
	/** The _current password. */
	private String _currentPassword;

	/** The _email. */
	private String _email;
	
	/** The _user roles. */
	private List<UserRole> _userRoles = new ArrayList<UserRole>(); 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _userId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (_userId != other._userId)
			return false;
		return true;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return _userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		_userId = userId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return _password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		_password = password;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		_email = email;
	}
	
	/**
	 * Gets the view password.
	 *
	 * @return the view password
	 */
	public String getViewPassword() {
		return _viewPassword;
	}

	/**
	 * Sets the view password.
	 *
	 * @param viewPassword the new view password
	 */
	public void setViewPassword(String viewPassword) {
		_viewPassword = viewPassword;
	}

	/**
	 * Gets the user roles.
	 *
	 * @return the user roles
	 */
	public List<UserRole> getUserRoles() {
		return _userRoles;
	}

	/**
	 * Sets the user roles.
	 *
	 * @param userRoles the new user roles
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		_userRoles = userRoles;
	}

	/**
	 * Gets the current password.
	 *
	 * @return the current password
	 */
	public String getCurrentPassword() {
		return _currentPassword;
	}

	/**
	 * Sets the current password.
	 *
	 * @param currentPassword the new current password
	 */
	public void setCurrentPassword(String currentPassword) {
		_currentPassword = currentPassword;
	}
}