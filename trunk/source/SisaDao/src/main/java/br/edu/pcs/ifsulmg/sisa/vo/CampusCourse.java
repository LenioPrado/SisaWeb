/*
 * File:	     CampusCourse.java
 * Creation date: 12/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class CampusCourse.
 */
public class CampusCourse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2277530393447857387L;
	
	/**
	 * Instantiates a new campus course.
	 */
	public CampusCourse(){
		
	}

	/**
	 * Instantiates a new campus course.
	 *
	 * @param course the course
	 * @param campus the campus
	 */
	public CampusCourse(Course course, Campus campus){
		_course = course;
		_campus = campus;	
	}
	
	/** The _course. */
	private Course _course = new Course(); 
	
	/** The _campus. */
	private Campus _campus = new Campus();
	
	/**
	 * Gets the course.
	 *
	 * @return the course
	 */
	public Course getCourse() {
		return _course;
	}

	/**
	 * Gets the campus.
	 *
	 * @return the campus
	 */
	public Campus getCampus() {
		return _campus;
	}
}