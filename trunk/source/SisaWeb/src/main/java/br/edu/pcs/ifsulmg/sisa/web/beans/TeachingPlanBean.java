/*
 * File:	     TeachingPlanBean.java
 * Creation date: 09/07/2015
 * Author:        Fabrício
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

import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PpcDisciplineException;
import br.edu.pcs.ifsulmg.sisa.exceptions.TeachingPlanException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.DisciplineDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PpcDisciplineDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.TeachingPlanDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class TeachingPlanBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -987349234742349893L;
	
	private TeachingPlan _teachingPlan;
	
	private List<TeachingPlan> _teachingPlansList;
	
	private List<Discipline> _disciplinesList;
	
	@Inject
	private TeachingPlanDelegate _teachingPlanDelegate;
	
	@Inject
	private DisciplineDelegate _disciplineDelegate;
	
	@Inject
	private PpcDisciplineDelegate _ppcDisciplineDelegate;
	
	private Boolean _editTeachingPlan;
	
	private Boolean _showSelectOptions = true;
	
	@PostConstruct
	public void init() {
		_teachingPlan = new TeachingPlan();
		loadTeachingPlansList();
	}
	
	public void save() {
		try {
			if (_editTeachingPlan) {
				_teachingPlanDelegate.update(_teachingPlan);
			}
			else {
				_teachingPlanDelegate.insert(_teachingPlan);
			}
			loadTeachingPlansList();
			RequestContext.getCurrentInstance().execute("PF('saveTeachingPlanDialog').hide()");
			RequestContext.getCurrentInstance().update("formListTeachingPlan");	
			RequestContext.getCurrentInstance().update("formCompleteTeachingPlan");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_TEACHING_PLAN, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_TEACHING_PLAN, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadTeachingPlan(TeachingPlan teachingPlan) {
		_teachingPlan = teachingPlan;
		_editTeachingPlan = true;
		ppcChanged();
	}
	
	public void fillTeachingPlan(TeachingPlan teachingPlan) {
		_teachingPlan = teachingPlan;
		_editTeachingPlan = true;
		_showSelectOptions = false;
	}	

	public void loadTeachingPlansList() {
		try {
			_teachingPlansList = _teachingPlanDelegate.list();
		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_TEACHING_PLANS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}	

	public void delete(TeachingPlan teachingPlan) {
		try {
			_teachingPlanDelegate.delete(teachingPlan);
			loadTeachingPlansList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_TEACHING_PLAN, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_TEACHING_PLAN, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_TEACHING_PLAN_CLASS_PROGRAMMING)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_CLASS_PROGRAMMING;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_TEACHING_PLAN_RESPONSIBLE)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_RESPONSIBLE;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_TEACHING_PLAN_VERIFICATION)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_VERIFICATION;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_TEACHING_PLAN_DISCIPLINE)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_DISCIPLINE;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_TEACHING_PLAN_PROCEDURE_EVALUATION)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_TEACHING_PLAN_PROCEDURE_EVALUATION;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanTeachingPlan() {
		_teachingPlan = new TeachingPlan();
		_editTeachingPlan = false;
	}
	
	public void ppcChanged(){
		try {
			Ppc ppc = _teachingPlan.getPpcDiscipline().getPpc(); 
			if (ppc != null) {
				_disciplinesList = _disciplineDelegate.listByPpc(ppc.getPpcId());
			} else {
				_disciplinesList = null;
			}
		} catch (DisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINES_BY_PPC, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void disciplineChanged(){
		try {
			Ppc ppc = _teachingPlan.getPpcDiscipline().getPpc();
			Discipline discipline = _teachingPlan.getPpcDiscipline().getDiscipline(); 
			
			PpcDiscipline ppcDiscipline = _ppcDisciplineDelegate.getByPpcAndDiscipline(ppc.getPpcId(), discipline.getDisciplineId());
			_teachingPlan.setPpcDiscipline(ppcDiscipline);
			
		} catch (PpcDisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINES_BY_PPC, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
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

	public TeachingPlanDelegate getTeachingPlanDelegate() {
		return _teachingPlanDelegate;
	}

	public void setTeachingPlanDelegate(TeachingPlanDelegate teachingPlanDelegate) {
		_teachingPlanDelegate = teachingPlanDelegate;
	}

	public Boolean getEditTeachingPlan() {
		return _editTeachingPlan;
	}

	public void setEditTeachingPlan(Boolean editTeachingPlan) {
		_editTeachingPlan = editTeachingPlan;
	}

	public List<Discipline> getDisciplinesList() {
		return _disciplinesList;
	}

	public void setDisciplinesList(List<Discipline> disciplinesList) {
		_disciplinesList = disciplinesList;
	}

	public Boolean getShowSelectOptions() {
		return _showSelectOptions;
	}

	public void setShowSelectOptions(Boolean showSelectOptions) {
		_showSelectOptions = showSelectOptions;
	}
}