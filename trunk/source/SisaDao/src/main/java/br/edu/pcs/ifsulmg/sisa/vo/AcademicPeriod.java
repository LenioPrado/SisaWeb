/*
 * File:	     AcademicPeriod.java
 * Creation date: 12/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class AcademicPeriod.
 */
public class AcademicPeriod implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8215570576350615629L;
	
	/**
	 * Instantiates a new academic period.
	 */
	public AcademicPeriod(){
		
	}
	
	/**
	 * Instantiates a new academic period.
	 *
	 * @param academicPeriodId the academic period id
	 * @param year the year
	 * @param semester the semester
	 * @param type the type
	 */
	public AcademicPeriod(int academicPeriodId,  Integer year, Integer semester, String type){
		_academicPeriodId = academicPeriodId;
		_year = year;
		_semester = semester;
		_type = type;
	}
	
	/** The _academic period id. */
	private int _academicPeriodId;
	
	/** The _year. */
	private Integer _year;
	
	/** The _semester. */
	private Integer _semester;
	
	/** The _type. */
	private String _type;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _academicPeriodId;
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
		AcademicPeriod other = (AcademicPeriod) obj;
		if (_academicPeriodId != other._academicPeriodId)
			return false;
		return true;
	}
	
	/**
	 * Gets the academic period id.
	 *
	 * @return the academic period id
	 */
	public int getAcademicPeriodId() {
		return _academicPeriodId;
	}

	/**
	 * Sets the academic period id.
	 *
	 * @param academicPeriodId the new academic period id
	 */
	public void setAcademicPeriodId(int academicPeriodId) {
		_academicPeriodId = academicPeriodId;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return _year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		_year = year;
	}

	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public Integer getSemester() {
		return _semester;
	}

	/**
	 * Sets the semester.
	 *
	 * @param semester the new semester
	 */
	public void setSemester(Integer semester) {
		_semester = semester;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return _type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		_type = type;
	}
}