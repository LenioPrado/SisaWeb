/*
 * File:	     CampusCourseDelegate.java
 * Creation date: 20/08/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.CampusCourseException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Campus;
import br.edu.pcs.ifsulmg.sisa.vo.CampusCourse;
import br.edu.pcs.ifsulmg.sisa.vo.Course;

public class CampusCourseDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -942747540021335093L;

	public void insert(Campus campus, List<Course> courses) throws CampusCourseException, EntityConstraintViolationException {
		for (Course course : courses) {
			getCampusCourseDAO().insert(new CampusCourse(course, campus));	
		}		
	}
	
	public List<CampusCourse> list() throws CampusCourseException {
		List<CampusCourse> list = getCampusCourseDAO().list();
		return list;
	}

	public void delete(CampusCourse campusCourse) throws CampusCourseException {
		getCampusCourseDAO().delete(campusCourse);
	}
	
	public void delete(Campus campus, Course course) throws CampusCourseException {
		delete(new CampusCourse(course, campus));
	}
	
	public void delete(Campus campus, List<Course> courses) throws CampusCourseException {
		for (Course course : courses) {
			delete(new CampusCourse(course, campus));			
		}
	}
}