/*
 * File:	      PpcDiscipline.java
 * Creation date: 12/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PpcDiscipline.
 */
public class PpcDiscipline implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3864830435611377494L;
	
	/**
	 * Instantiates a new ppc discipline.
	 */
	public PpcDiscipline(){
		
	}
	
	public int getTotalHourLoad(){
		return _discipline.getWeekClasses() * _ppc.getClassHour() / 60;
	}
	
	public int getClassNumber() {
		return _discipline.getWeekClasses() * _ppc.getWeeksNumber();
	}
	
	/**
	 * Instantiates a new ppc discipline.
	 *
	 * @param ppcDisciplineId the ppc discipline id
	 * @param discipline the discipline
	 * @param ppc the ppc
	 */
	public PpcDiscipline(int ppcDisciplineId, Discipline discipline, Ppc ppc){
		this(discipline, ppc);
		_ppcDisciplineId = ppcDisciplineId;		
	}
	
	/**
	 * Instantiates a new ppc discipline.
	 *
	 * @param discipline the discipline
	 * @param ppc the ppc
	 */
	public PpcDiscipline(Discipline discipline, Ppc ppc){
		_discipline = discipline;
		_ppc = ppc;
	}
	
	/** The _ppc discipline id. */
	private int _ppcDisciplineId;
	
	/** The _discipline. */
	private Discipline _discipline = new Discipline();
	
	/** The _ppc. */
	private Ppc _ppc = new Ppc();
	
	/** The _disciplines. */
	private List<Discipline> _disciplines = new ArrayList<Discipline>(); 
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _ppcDisciplineId + _ppc.getPpcId() + _discipline.getDisciplineId();
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
		PpcDiscipline other = (PpcDiscipline) obj;
		if(_ppc.getPpcId() != other._ppc.getPpcId())
			return false;
		if(_discipline.getDisciplineId() != other._discipline.getDisciplineId())
			return false;		
		return true;
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
	 * Sets the discipline.
	 *
	 * @param discipline the new discipline
	 */
	public void setDiscipline(Discipline discipline) {
		_discipline = discipline;
	}

	/**
	 * Gets the ppc.
	 *
	 * @return the ppc
	 */
	public Ppc getPpc() {
		return _ppc;
	}
	
	/**
	 * Sets the ppc.
	 *
	 * @param ppc the new ppc
	 */
	public void setPpc(Ppc ppc) {
		_ppc = ppc;
	}

	/**
	 * Gets the disciplines.
	 *
	 * @return the disciplines
	 */
	public List<Discipline> getDisciplines() {
		return _disciplines;
	}

	/**
	 * Gets the ppc discipline id.
	 *
	 * @return the ppc discipline id
	 */
	public int getPpcDisciplineId() {
		return _ppcDisciplineId;
	}

	/**
	 * Sets the ppc discipline id.
	 *
	 * @param ppcDisciplineId the new ppc discipline id
	 */
	public void setPpcDisciplineId(int ppcDisciplineId) {
		_ppcDisciplineId = ppcDisciplineId;
	}
}