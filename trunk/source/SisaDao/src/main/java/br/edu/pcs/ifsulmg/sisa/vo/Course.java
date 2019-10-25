/*
 * File:	     Course.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */

package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Course.
 */
public class Course implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4835799369411089259L;

	/**
	 * Instantiates a new course.
	 */
	public Course() {

	}

	/**
	 * Instantiates a new course.
	 *
	 * @param name the name
	 * @param shortname the short name
	 * @param modality the modality
	 * @param courseId the course id
	 * @param shift the shift
	 */
	public Course(int courseId, String name, String shortName, int modality, int shift) {
		_name = name;
		_modality = modality;
		_courseId = courseId;
		_shift = shift;
		_shortName = shortName;
	}

	/** The _name. */
	private String _name;
	
	/** The _modality. */
	private int _modality;
	
	/** The _course id. */
	private int _courseId;
	
	/** The _shift. */
	private int _shift;
	
	/** The _shortName. */
	private String _shortName;
	
	/** The _academic periods. */
	private List<AcademicPeriod> _academicPeriods = new ArrayList<AcademicPeriod>();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _courseId;
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
		Course other = (Course) obj;
		if (_courseId != other._courseId)
			return false;
		return true;
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
	 * Gets the modality.
	 *
	 * @return the modality
	 */
	public int getModality() {
		return _modality;
	}

	/**
	 * Sets the modality.
	 *
	 * @param modality the new modality
	 */
	public void setModality(int modality) {
		_modality = modality;
	}

	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public int getCourseId() {
		return _courseId;
	}

	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setCourseId(int courseId) {
		_courseId = courseId;
	}

	/**
	 * Gets the shift.
	 *
	 * @return the shift
	 */
	public int getShift() {
		return _shift;
	}

	/**
	 * Sets the shift.
	 *
	 * @param shift the new shift
	 */
	public void setShift(int shift) {
		_shift = shift;
	}

	/**
	 * Gets the academic periods.
	 *
	 * @return the academic periods
	 */
	public List<AcademicPeriod> getAcademicPeriods() {
		return _academicPeriods;
	}

	/**
	 * Sets the academic periods.
	 *
	 * @param academicPeriods the new academic periods
	 */
	public void setAcademicPeriods(List<AcademicPeriod> academicPeriods) {
		_academicPeriods = academicPeriods;
	}

	public String getShortName() {
		return _shortName;
	}

	public void setShortName(String shortName) {
		_shortName = shortName;
	}
}