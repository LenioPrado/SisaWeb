/*
 * File:	     CampusCourseBean.java
 * Creation date: 20/08/2015
 * Author:        Rarvs
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

import br.edu.pcs.ifsulmg.sisa.exceptions.CampusCourseException;
import br.edu.pcs.ifsulmg.sisa.exceptions.CampusException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Campus;
import br.edu.pcs.ifsulmg.sisa.vo.CampusCourse;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.CampusCourseDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.CampusDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class CampusCourseBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7881715734091040654L;
	
	@Inject
	private CampusCourseDelegate _campusCourseDelegate;
	
	@Inject
	private CampusDelegate _campusDelegate;
	
	private Campus _campus;
	
	private CampusCourse _campusCourse;
	
	private List<Campus> _campusList;
	
	private List<CampusCourse> _campusCoursesList;	
	
	private Boolean _editCampusCourse;
	
	private List<Course> _selectedCourses;
	
	private List<Course> _currentCourses;
	
	@PostConstruct
	public void init() {
		_campus = new Campus();
		_selectedCourses = new ArrayList<Course>();
		_currentCourses = new ArrayList<Course>();
		loadCampusCoursesList();
	}
	
	public void save() {
		try {			
			if (_editCampusCourse)
				_campusCourseDelegate.delete(_campus, getCoursesToDelete());

			_campusCourseDelegate.insert(_campus, getCoursesToSave());

			loadCampusCoursesList();
			RequestContext.getCurrentInstance().execute("PF('saveCampusCourseDialog').hide()");
			RequestContext.getCurrentInstance().update("formListCampusCourses");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_CAMPUS_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (CampusCourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_CAMPUS_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private List<Course> getCoursesToSave(){
		List<Course> toSave = new ArrayList<Course>();
		
		for (int i = 0; i < _selectedCourses.size(); i++) {
			Course selectedCourse = _selectedCourses.get(i);
			if (!_currentCourses.contains(selectedCourse) && !toSave.contains(selectedCourse)) {
				toSave.add(selectedCourse);
			}
		}
		return toSave;
	}
	
	private List<Course> getCoursesToDelete(){
		List<Course> toDelete = new ArrayList<Course>();
		
		for (int i = 0; i < _currentCourses.size(); i++) {
			Course currentCourse = _currentCourses.get(i);
			if (!_selectedCourses.contains(currentCourse)&& !toDelete.contains(currentCourse)) {
				toDelete.add(currentCourse);
			}
		}
		return toDelete;
	}

	public void campusChange(){
		loadCampusCourse(_campus);
	}
	
	public void loadCampusCourse(Campus campus) {
		_campus = campus;
		_editCampusCourse = true;
		clearCoursesLists();
		
		for (int i = 0; i < _campusCoursesList.size(); i++) {
			CampusCourse current = _campusCoursesList.get(i);
			if (current.getCampus().getCampusId() == _campus.getCampusId())
			{
				_currentCourses.add(current.getCourse());
				_selectedCourses.add(current.getCourse());
			}
		}
	}

	public void loadCampusCoursesList() {
		try {
			_campusCoursesList = _campusCourseDelegate.list();
			_campusList = _campusDelegate.list();
			
			for (CampusCourse campusCourse : _campusCoursesList) {
				Campus campus = campusCourse.getCampus();
				Course course = campusCourse.getCourse();

				Campus existentCampus = _campusList.get(_campusList.indexOf(campus));
				if(!existentCampus.getCourses().contains(course)){
					existentCampus.getCourses().add(course);
				}
			}
		} catch (CampusCourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_CAMPUS_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
		catch (CampusException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_CAMPUS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Campus campus, Course course) {
		try {
			_campusCourseDelegate.delete(campus, course);
			clearCoursesLists();
			loadCampusCoursesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_CAMPUS_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (CampusCourseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CAMPUS_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanCampusCourse() {
		_campus = new Campus();
		_editCampusCourse = false;
		clearCoursesLists();
	}
	
	private void clearCoursesLists() {
		_currentCourses.clear();
		_selectedCourses.clear();
	}

	public CampusCourse getCampusCourse() {
		return _campusCourse;
	}

	public void setCampusCourse(CampusCourse campusCourse) {
		_campusCourse = campusCourse;
	}

	public List<CampusCourse> getCampusCoursesList() {
		return _campusCoursesList;
	}

	public void setCampusCoursesList(
			List<CampusCourse> campusCoursesList) {
		_campusCoursesList = campusCoursesList;
	}

	public CampusCourseDelegate getCampusCourseDelegate() {
		return _campusCourseDelegate;
	}

	public void setCampusCourseDelegate(
			CampusCourseDelegate campusCourseDelegate) {
		_campusCourseDelegate = campusCourseDelegate;
	}

	public Boolean getEditCampusCourse() {
		return _editCampusCourse;
	}

	public void setEditCampusCourse(Boolean editCampusCourse) {
		_editCampusCourse = editCampusCourse;
	}

	public Campus getCampus() {
		return _campus;
	}

	public void setCampus(Campus campus) {
		_campus = campus;
	}

	public List<Campus> getCampusList() {
		return _campusList;
	}

	public void setCampusList(List<Campus> campusList) {
		_campusList = campusList;
	}

	public List<Course> getSelectedCourses() {
		return _selectedCourses;
	}

	public void setSelectedCourses(List<Course> selectedCourses) {
		_selectedCourses = selectedCourses;
	}

	public List<Course> getCurrentCourses() {
		return _currentCourses;
	}

	public void setCurrentCourses(List<Course> currentCourses) {
		_currentCourses = currentCourses;
	}
}