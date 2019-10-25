/*
 * File:	     AcademicPeriodCourse.java
 * Creation date: 12/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class AcademicPeriodCourse.
 */
public class AcademicPeriodCourse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6465224366059026282L;
	
	/**
	 * Instantiates a new academic period course.
	 */
	public AcademicPeriodCourse(){
		
	}
	
	/**
	 * Instantiates a new academic period course.
	 *
	 * @param academicPeriod the academic period
	 * @param course the course
	 */
	public AcademicPeriodCourse(AcademicPeriod academicPeriod, Course course){
		_academicPeriod = academicPeriod;
		_course = course;
	}
	
	/** The _academic period. */
	private AcademicPeriod _academicPeriod = new AcademicPeriod();
	
	/** The _course. */
	private Course _course = new Course();
	
	/**
	 * Gets the academic period.
	 *
	 * @return the academic period
	 */
	public AcademicPeriod getAcademicPeriod() {
		return _academicPeriod;
	}
	
	/**
	 * Gets the course.
	 *
	 * @return the course
	 */
	public Course getCourse() {
		return _course;
	}
}
