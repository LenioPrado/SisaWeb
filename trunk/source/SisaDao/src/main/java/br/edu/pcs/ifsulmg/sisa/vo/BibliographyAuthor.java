/*
 * File:	     Bibliography.java
 * Creation date: 12/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class BibliographyAuthor.
 */
public class BibliographyAuthor implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7653035828565690867L;
	
	/**
	 * Instantiates a new bibliography author.
	 */
	public BibliographyAuthor(){		
		
	}
	
	/**
	 * Instantiates a new bibliography author.
	 *
	 * @param author the author
	 * @param bibliography the bibliography
	 */
	public BibliographyAuthor(Author author, Bibliography bibliography){
		_author = author;
		_bibliography = bibliography;
	}
	
	/** The _author. */
	private Author _author = new Author();
	
	/** The _bibliography. */
	private Bibliography _bibliography = new Bibliography();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _author.getAuthorId() + _bibliography.getBibliographyId();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Objec
	 * t#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibliographyAuthor other = (BibliographyAuthor) obj;
		if (_author.getAuthorId() != other.getAuthor().getAuthorId())
			return false;
		if (_bibliography.getBibliographyId() != other.getBibliography().getBibliographyId())
			return false;
		return true;
	}
		
	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public Author getAuthor() {
		return _author;
	}
	
	/**
	 * Gets the bibliography.
	 *
	 * @return the bibliography
	 */
	public Bibliography getBibliography() {
		return _bibliography;
	}
}