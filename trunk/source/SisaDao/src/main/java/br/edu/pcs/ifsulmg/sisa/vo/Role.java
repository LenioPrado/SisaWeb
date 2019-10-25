/*
* File:	     Role.java
* Creation date: 08/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Role.
 */
public class Role implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 349606690008744555L;

	/**
	 * Instantiates a new role.
	 */
	public Role() {

	}

	/**
	 * Instantiates a new role.
	 *
	 * @param roleId the role id
	 * @param name the name
	 * @param observation the observation
	 */
	public Role(int roleId, String name, String observation) {
		_roleId = roleId;
		_name = name;
		_observation = observation;
	}

	/** The _role id. */
	private int _roleId;

	/** The _name. */
	private String _name;

	/** The _observation. */
	private String _observation;
	
	/** The _pages. */
	private List<Page> _pages = new ArrayList<Page>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _roleId;
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
		Role other = (Role) obj;
		if (_roleId != other._roleId)
			return false;
		return true;
	}
	
	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return _roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(int roleId) {
		_roleId = roleId;
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
	 * Gets the observation.
	 *
	 * @return the observation
	 */
	public String getObservation() {
		return _observation;
	}

	/**
	 * Sets the observation.
	 *
	 * @param observation the new observation
	 */
	public void setObservation(String observation) {
		_observation = observation;
	}

	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public List<Page> getPages() {
		return _pages;
	}

	/**
	 * Sets the pages.
	 *
	 * @param pages the new pages
	 */
	public void setPages(List<Page> pages) {
		_pages = pages;
	}
}