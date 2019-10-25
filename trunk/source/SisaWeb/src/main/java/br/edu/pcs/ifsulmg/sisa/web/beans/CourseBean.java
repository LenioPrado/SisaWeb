/*
 * File:	     CourseBean.java
 * Creation date: 24/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.CourseException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.CourseDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class CourseBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -953721650362957154L;
	
	private Course _course;
	
	private List<Course> _coursesList;
	
	@Inject
	private CourseDelegate _courseDelegate;
	
	private Boolean _editCourse;	
		
	@PostConstruct
	public void init() {
		_course = new Course();
		loadCoursesList();
	}
	
	public void save() {
		try {
			if (_editCourse) {
				_courseDelegate.update(_course);
			}
			else {
				_courseDelegate.insert(_course);
			}
			loadCoursesList();
			RequestContext.getCurrentInstance().execute("PF('saveCourseDialog').hide()");
			RequestContext.getCurrentInstance().update("formListCourses");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (CourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.DUPLICATE_ENTRY) &&
					constraint.contains(MsgConstants.DUPLICATE_COURSE_NAME)) {
				message = MsgConstants.MSG_ERROR_SAVE_DUPLICATE_COURSE_NAME;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadCourse(Course course) {
		_course = course;
		_editCourse = true;
	}

	public void loadCoursesList() {
		try {
			_coursesList = _courseDelegate.list();
		} catch (CourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_COURSES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Course course) {
		try {
			_courseDelegate.delete(course);
			loadCoursesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (CourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_COURSE_ACADEMIC_PERIOD)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_COURSE_ACADEMIC_PERIOD;
			} 
			else if(constraint.contains(MsgConstants.CONSTRAINT_COURSE_CAMPUS)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_COURSE_CAMPUS;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_COURSE_PPC)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_COURSE_PPC;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanCourse() {
		_course = new Course();
		_editCourse = false;
	}

	public Course getCourse() {
		return _course;
	}

	public void setCourse(Course course) {
		_course = course;
	}

	public List<Course> getCoursesList() {
		return _coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		_coursesList = coursesList;
	}

	public CourseDelegate getCourseDelegate() {
		return _courseDelegate;
	}

	public void setCourseDelegate(CourseDelegate courseDelegate) {
		_courseDelegate = courseDelegate;
	}

	public Boolean getEditCourse() {
		return _editCourse;
	}

	public void setEditCourse(Boolean editCourse) {
		_editCourse = editCourse;
	}	
}