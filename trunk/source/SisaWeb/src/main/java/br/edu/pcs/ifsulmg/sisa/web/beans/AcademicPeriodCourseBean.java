/*
 * File:	     AcademicPeriodCourseBean.java
 * Creation date: 20/08/2015
 * AcademicPeriodCourse:       PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.AcademicPeriodCourseException;
import br.edu.pcs.ifsulmg.sisa.exceptions.CourseException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriodCourse;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.AcademicPeriodCourseDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.CourseDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class AcademicPeriodCourseBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192734776825886839L;
	
	@Inject
	private AcademicPeriodCourseDelegate _academicPeriodCourseDelegate;
	
	@Inject
	private CourseDelegate _courseDelegate;
	
	private Course _course;
	
	private List<AcademicPeriodCourse> _academicPeriodCoursesList;
	
	private List<Course> _courses;	
	
	private Boolean _editAcademicPeriodCourse;
	
	private List<AcademicPeriod> _selectedAcademicPeriods;
	
	private List<AcademicPeriod> _currentAcademicPeriods;
	
	@PostConstruct
	public void init() {
		_course = new Course();
		_selectedAcademicPeriods = new ArrayList<AcademicPeriod>();
		_currentAcademicPeriods = new ArrayList<AcademicPeriod>();
		loadAcademicPeriodCoursesList();
	}
	
	public void save() {
		try {
			if (_editAcademicPeriodCourse) 
				_academicPeriodCourseDelegate.delete(_course, getAcademicPeriodsToDelete());
				
			_academicPeriodCourseDelegate.insert(_course, getAcademicPeriodsToSave());
				
			loadAcademicPeriodCoursesList();
			RequestContext.getCurrentInstance().execute("PF('saveAcademicPeriodCourseDialog').hide()");
			RequestContext.getCurrentInstance().update("formListAcademicPeriodCourses");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_ACADEMIC_PERIOD_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} 
		catch (AcademicPeriodCourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_ACADEMIC_PERIOD_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_ACADEMIC_PERIOD_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
			
	private List<AcademicPeriod> getAcademicPeriodsToSave(){
		List<AcademicPeriod> toSave = new ArrayList<AcademicPeriod>();
		
		for (int i = 0; i < _selectedAcademicPeriods.size(); i++) {
			AcademicPeriod selectedAcademicPeriod = _selectedAcademicPeriods.get(i);
			if (!_currentAcademicPeriods.contains(selectedAcademicPeriod) && !toSave.contains(selectedAcademicPeriod)) {
				toSave.add(selectedAcademicPeriod);
			}
		}
		return toSave;
	}
	
	private List<AcademicPeriod> getAcademicPeriodsToDelete(){
		List<AcademicPeriod> toDelete = new ArrayList<AcademicPeriod>();
		
		for (int i = 0; i < _currentAcademicPeriods.size(); i++) {
			AcademicPeriod currentAcademicPeriod = _currentAcademicPeriods.get(i);
			if (!_selectedAcademicPeriods.contains(currentAcademicPeriod)&& !toDelete.contains(currentAcademicPeriod)) {
				toDelete.add(currentAcademicPeriod);
			}
		}
		return toDelete;
	}
	
	public void courseChange(){
		loadAcademicPeriodCourse(_course);
	}

	public void loadAcademicPeriodCourse(Course course) {
		_course = course;
		_editAcademicPeriodCourse = true;
		clearAcademicPeriodsLists();
		
		for (int i = 0; i < _academicPeriodCoursesList.size(); i++) {
			AcademicPeriodCourse current = _academicPeriodCoursesList.get(i);
			if (current.getCourse().getCourseId() == _course.getCourseId())
			{
				_currentAcademicPeriods.add(current.getAcademicPeriod());
				_selectedAcademicPeriods.add(current.getAcademicPeriod());
			}
		}		
	}
	
	public void loadAcademicPeriodCoursesList() {
		try {
			_courses = _courseDelegate.list();
			_academicPeriodCoursesList = _academicPeriodCourseDelegate.list();
			
			for (AcademicPeriodCourse academicPeriodCourse : _academicPeriodCoursesList) {
				Course course = academicPeriodCourse.getCourse();
				AcademicPeriod academicPeriod = academicPeriodCourse.getAcademicPeriod();
				
				Course existentCourse = _courses.get(_courses.indexOf(course));
				if (!existentCourse.getAcademicPeriods().contains(academicPeriod)) {
					existentCourse.getAcademicPeriods().add(academicPeriod);					
				}
			}			
		} catch (AcademicPeriodCourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ACADEMIC_PERIOD_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		} catch (CourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_COURSES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void delete(Course course, AcademicPeriod academicPeriod) {
		try {
			_academicPeriodCourseDelegate.delete(course, academicPeriod);
			clearAcademicPeriodsLists();
			loadAcademicPeriodCoursesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_ACADEMIC_PERIOD_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (AcademicPeriodCourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_ACADEMIC_PERIOD_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void cleanAcademicPeriodCourse() {
		_course = new Course();
		_editAcademicPeriodCourse = false;
		clearAcademicPeriodsLists();
	}
	
	private void clearAcademicPeriodsLists() {
		_currentAcademicPeriods.clear();
		_selectedAcademicPeriods.clear();
	}

	public List<AcademicPeriodCourse> getAcademicPeriodCoursesList() {
		return _academicPeriodCoursesList;
	}

	public void setAcademicPeriodCoursesList(List<AcademicPeriodCourse> academicPeriodCoursesList) {
		_academicPeriodCoursesList = academicPeriodCoursesList;
	}

	public AcademicPeriodCourseDelegate getAcademicPeriodCourseDelegate() {
		return _academicPeriodCourseDelegate;
	}

	public void setAcademicPeriodCourseDelegate(AcademicPeriodCourseDelegate academicPeriodCourseDelegate) {
		_academicPeriodCourseDelegate = academicPeriodCourseDelegate;
	}

	public Boolean getEditAcademicPeriodCourse() {
		return _editAcademicPeriodCourse;
	}

	public void setEditAcademicPeriodCourse(Boolean editAcademicPeriodCourse) {
		_editAcademicPeriodCourse = editAcademicPeriodCourse;
	}

	public List<Course> getCourses() {
		return _courses;
	}

	public void setCourses(List<Course> courses) {
		_courses = courses;
	}

	public Course getCourse() {
		return _course;
	}

	public void setCourse(Course course) {
		_course = course;
	}

	public List<AcademicPeriod> getSelectedAcademicPeriods() {
		return _selectedAcademicPeriods;
	}

	public void setSelectedAcademicPeriods(List<AcademicPeriod> selectedAcademicPeriods) {
		_selectedAcademicPeriods = selectedAcademicPeriods;
	}

	public List<AcademicPeriod> getCurrentAcademicPeriods() {
		return _currentAcademicPeriods;
	}

	public void setCurrentAcademicPeriods(List<AcademicPeriod> currentAcademicPeriods) {
		_currentAcademicPeriods = currentAcademicPeriods;
	}	
}