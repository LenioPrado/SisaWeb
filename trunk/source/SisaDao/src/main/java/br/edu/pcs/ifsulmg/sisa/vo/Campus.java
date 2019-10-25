/*
 * File:	     Campus.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Campus.
 */
public class Campus implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3843254098129719114L;
	
	/**
	 * Instantiates a new campus.
	 */
	public Campus(){
		
	}
	
	/**
	 * Instantiates a new campus.
	 *
	 * @param campusId the campus id
	 * @param name the name
	 * @param cnpj the cnpj
	 * @param rectory the rectory
	 */
	public Campus(int campusId, String name, String cnpj, Rectory rectory){
		_campusId = campusId;
		_name = name;
		_cnpj = cnpj;
		_rectory = rectory;
	}
	
	/** The _campus id. */
	private int _campusId;
	
	/** The _name. */
	private String _name;
	
	/** The _cnpj. */
	private String _cnpj;
	
	/** The _rectory. */
	private Rectory _rectory = new Rectory();
	
	/** The _courses. */
	private List<Course> _courses = new ArrayList<Course>();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _campusId;
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
		Campus other = (Campus) obj;
		if (_campusId != other._campusId)
			return false;
		return true;
	}
	
	/**
	 * Gets the campus id.
	 *
	 * @return the campus id
	 */
	public int getCampusId() {
		return _campusId;
	}

	/**
	 * Sets the campus id.
	 *
	 * @param campusId the new campus id
	 */
	public void setCampusId(int campusId) {
		_campusId = campusId;
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
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return _cnpj;
	}

	/**
	 * Sets the cnpj.
	 *
	 * @param cnpj the new cnpj
	 */
	public void setCnpj(String cnpj) {
		_cnpj = cnpj;
	}

	/**
	 * Gets the rectory.
	 *
	 * @return the rectory
	 */
	public Rectory getRectory() {
		return _rectory;
	}

	/**
	 * Sets the rectory.
	 *
	 * @param string the new rectory
	 */
	public void setRectory(Rectory string) {
		_rectory = string;
	}

	/**
	 * Gets the courses.
	 *
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return _courses;
	}

	/**
	 * Sets the courses.
	 *
	 * @param courses the new courses
	 */
	public void setCourses(List<Course> courses) {
		_courses = courses;
	}
}