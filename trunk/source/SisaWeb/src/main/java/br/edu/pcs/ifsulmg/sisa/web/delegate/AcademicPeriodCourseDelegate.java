/*
 * File:	     AcademicPeriodCourseDelegate.java
 * Creation date: 20/08/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.AcademicPeriodCourseException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriodCourse;
import br.edu.pcs.ifsulmg.sisa.vo.Course;


public class AcademicPeriodCourseDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975713493724229821L;

	public void insert(Course course, List<AcademicPeriod> academicPeriods) throws AcademicPeriodCourseException, EntityConstraintViolationException {
		for (AcademicPeriod academicPeriodToInsert : academicPeriods) {
			getAcademicPeriodCourseDAO().insert(new AcademicPeriodCourse(academicPeriodToInsert, course));
		}		
	}

	public List<AcademicPeriodCourse> list() throws AcademicPeriodCourseException {
		List<AcademicPeriodCourse> list = getAcademicPeriodCourseDAO().list();
		return list;
	}
	
	public void delete(AcademicPeriodCourse academicPeriodCourse) throws AcademicPeriodCourseException {
		getAcademicPeriodCourseDAO().delete(academicPeriodCourse);
	}

	public void delete(Course course, AcademicPeriod academicPeriod) throws AcademicPeriodCourseException {
		delete(new AcademicPeriodCourse(academicPeriod, course));
	}
	
	public void delete(Course course, List<AcademicPeriod> academicPeriods) throws AcademicPeriodCourseException {
		for (AcademicPeriod academicPeriodToDelete : academicPeriods) {
			delete(new AcademicPeriodCourse(academicPeriodToDelete, course));
		}		
	}
}