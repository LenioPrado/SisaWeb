/*
 * File:	     CourseDelegate.java
 * Creation date: 24/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.CourseException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Course;

public class CourseDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3294577031667579553L;

	public int insert(Course course) throws CourseException, EntityConstraintViolationException {
		int id = getCourseDAO().insert(course);
		return id;
	}

	public void update(Course course) throws CourseException, EntityConstraintViolationException {
		getCourseDAO().update(course);
	}

	public List<Course> list() throws CourseException {
		List<Course> list = getCourseDAO().list();
		return list;
	}

	public void delete(Course course) throws CourseException, EntityConstraintViolationException {
		getCourseDAO().delete(course.getCourseId());
	}
}