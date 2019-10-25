/*
 * File:	     Author.java
 * Creation date: 08/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Author.
 */
public class Author implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5694681494313541646L;

	/**
	 * Instantiates a new author.
	 */
	public Author() {

	}

	/**
	 * Instantiates a new author.
	 *
	 * @param authorId the author id
	 * @param lastName the last name
	 * @param otherNames the other names
	 */
	public Author(int authorId, String lastName, String otherNames) {
		_authorId = authorId;
		_lastName = lastName;
		_otherNames = otherNames;
	}

	/** The _author id. */
	private int _authorId;

	/** The _last name. */
	private String _lastName;

	/** The _other names. */
	private String _otherNames;
	
	/** The _bibliographies. */
	private List<Bibliography> _bibliographies = new ArrayList<Bibliography>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _authorId;
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
		Author other = (Author) obj;
		if (_authorId != other._authorId)
			return false;
		return true;
	}

	/**
	 * Gets the author id.
	 *
	 * @return the author id
	 */
	public int getAuthorId() {
		return _authorId;
	}

	/**
	 * Sets the author id.
	 *
	 * @param authorId the new author id
	 */
	public void setAuthorId(int authorId) {
		_authorId = authorId;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return _lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	/**
	 * Gets the other names.
	 *
	 * @return the other names
	 */
	public String getOtherNames() {
		return _otherNames;
	}

	/**
	 * Sets the other names.
	 *
	 * @param otherNames the new other names
	 */
	public void setOtherNames(String otherNames) {
		_otherNames = otherNames;
	}

	public List<Bibliography> getBibliographies() {
		return _bibliographies;
	}

	public void setBibliographies(List<Bibliography> bibliographies) {
		_bibliographies = bibliographies;
	}
}