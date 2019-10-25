/*
 * File:	     DisciplineBibliography.java
 * Creation date: 12/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class DisciplineBibliography.
 */
public class DisciplineBibliography implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5496473025489552501L;
	
	/**
	 * Instantiates a new discipline bibliography.
	 */
	public DisciplineBibliography(){		
		
	}
	
	/**
	 * Instantiates a new discipline bibliography.
	 *
	 * @param bibliography the bibliography
	 * @param discipline the discipline
	 */
	public DisciplineBibliography(Bibliography bibliography , Discipline discipline, boolean isBasic){
		_bibliography = bibliography;
		_discipline = discipline;
		_isBasic = isBasic;
	}	
	
	/** The _is basic. */
	private boolean _isBasic;
	
	/** The _bibliography. */
	private Bibliography _bibliography = new Bibliography();
	
	/** The _discipline. */
	private Discipline _discipline = new Discipline();
	
	/**
	 * Gets the bibliography.
	 *
	 * @return the bibliography
	 */
	public Bibliography getBibliography() {
		return _bibliography;
	}
	
	/**
	 * Gets the discipline.
	 *
	 * @return the discipline
	 */
	public Discipline getDiscipline() {
		return _discipline;
	}
	
	/**
	 * Checks if is basic.
	 *
	 * @return true, if is basic
	 */
	public boolean isBasic() {
		return _isBasic;
	}

	/**
	 * Sets the basic.
	 *
	 * @param isBasic the new basic
	 */
	public void setBasic(boolean isBasic) {
		_isBasic = isBasic;
	}
}