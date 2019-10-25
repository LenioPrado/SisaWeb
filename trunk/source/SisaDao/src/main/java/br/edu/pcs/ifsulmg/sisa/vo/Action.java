/*
 * File:	     Action.java
 * Creation date: 30/03/2016
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class Action.
 */
public class Action implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6355875733456752161L;
	
	/**
	 * Instantiates a new action.
	 */
	public Action() {

	}
	
	/**
	 * Instantiates a new action.
	 *
	 * @param actionId the action id
	 * @param description the description
	 */
	public Action(int actionId, String description) {
		_actionId = actionId;
		_description = description;
	}
	
	/** The _action id. */
	private int _actionId;


	/** The _description. */
	private String _description;
	
	/** The _is checked. */
	private boolean _isChecked;

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _actionId;
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
		Action other = (Action) obj;
		if (_actionId != other._actionId)
			return false;
		return true;
	}

	
	/**
	 * Gets the action id.
	 *
	 * @return the action id
	 */
	public int getActionId() {
		return _actionId;
	}

	
	/**
	 * Sets the action id.
	 *
	 * @param actionId the new action id
	 */
	public void setActionId(int actionId) {
		_actionId = actionId;
	}


	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return _description;
	}

	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		_description = description;
	}


	public boolean getIsChecked() {
		return _isChecked;
	}


	public void setIsChecked(boolean isChecked) {
		_isChecked = isChecked;
	}
}