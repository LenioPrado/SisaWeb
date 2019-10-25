/*
 * File:	     CompleteTeachingPlanBean.java
 * Creation date: 11/05/2016
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

import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PpcException;
import br.edu.pcs.ifsulmg.sisa.exceptions.TeachingPlanException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.DisciplineDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PpcDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.TeachingPlanDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class CompleteTeachingPlanBean extends TeachingPlanBaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1928374659174529602L;
	
	@Inject
	private PpcDelegate _ppcDelegate;
	
	@Inject
	private DisciplineDelegate _disciplineDelegate;
	
	@Inject
	private TeachingPlanDelegate _teachingPlanDelegate;
	
	private Course _course;
	
	private Ppc _ppc;
	
	private Discipline _discipline; 
	
	private TeachingPlan _teachingPlan;
	
	private List<Ppc> _ppcsList;	
	
	private List<Discipline> _disciplinesList;	
	
	private List<TeachingPlan> _teachingPlansList;
	
	@PostConstruct
	public void init() {
		_completeTeachingPlan = new TeachingPlan();
		_teachingPlanIsVisible = false;
	}
	
	public void courseChanged(){
		try {
			if (_course != null) {
				_ppcsList = _ppcDelegate.listByCourse(_course.getCourseId());
			} else {
				_ppcsList = null;
				_disciplinesList = null;
				_teachingPlansList = null;
				
				_ppc = null;
				_discipline = null;
				_teachingPlan = null;
				_completeTeachingPlan = null;
			}
			_teachingPlanIsVisible = false;
		} catch (PpcException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PPCS_BY_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void ppcChanged(){
		try {
			if (_ppc != null) {
				_disciplinesList = _disciplineDelegate.listByPpc(_ppc.getPpcId());
			} else {
				_disciplinesList = null;
				_teachingPlansList = null;
				
				_discipline = null;
				_teachingPlan = null;
				_completeTeachingPlan = null;
			}
			_teachingPlanIsVisible = false;
		} catch (DisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINES_BY_PPC, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void disciplineChanged(){
		try {
			if (_discipline != null) {
				_teachingPlansList = _teachingPlanDelegate.listByDiscipline(_discipline.getDisciplineId());
			} else {
				_teachingPlansList = null;

				_teachingPlan = null;
				_completeTeachingPlan = null;
			}
			_teachingPlanIsVisible = false;
		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_TEACHING_PLANS_BY_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void teachingPlanChanged(){
		try {
			if (_teachingPlan != null) {
				_completeTeachingPlan = _teachingPlanDelegate.getById(_course.getCourseId(), _ppc.getPpcId(), 
								_discipline.getDisciplineId(), _teachingPlan.getTeachingPlanId());
				loadTeachingPlan();
				_teachingPlanIsVisible = true;
			} else {
				_completeTeachingPlan = null;
				_teachingPlanIsVisible = false;
			}
			LogSISA.log.info("TeachingPlanIsVisible: " + _teachingPlanIsVisible);
		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_COMPLETE_TEACHING_PLANS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public TeachingPlan getTeachingPlan() {
		return _teachingPlan;
	}

	public void setTeachingPlan(TeachingPlan teachingPlan) {
		_teachingPlan = teachingPlan;
	}

	public List<TeachingPlan> getTeachingPlansList() {
		return _teachingPlansList;
	}

	public void setTeachingPlansList(List<TeachingPlan> teachingPlansList) {
		_teachingPlansList = teachingPlansList;
	}

	public Ppc getPpc() {
		return _ppc;
	}

	public void setPpc(Ppc ppc) {
		_ppc = ppc;
	}

	public Course getCourse() {
		return _course;
	}

	public void setCourse(Course course) {
		_course = course;
	}

	public List<Ppc> getPpcsList() {
		return _ppcsList;
	}

	public void setPpcsList(List<Ppc> ppcsList) {
		_ppcsList = ppcsList;
	}

	public Discipline getDiscipline() {
		return _discipline;
	}

	public void setDiscipline(Discipline discipline) {
		_discipline = discipline;
	}

	public boolean isTeachingPlanIsVisible() {
		return _teachingPlanIsVisible;
	}

	public void setTeachingPlanIsVisible(boolean teachingPlanIsVisible) {
		_teachingPlanIsVisible = teachingPlanIsVisible;		
	}

	public List<Discipline> getDisciplinesList() {
		return _disciplinesList;
	}

	public void setDisciplinesList(List<Discipline> disciplinesList) {
		_disciplinesList = disciplinesList;
	}
}