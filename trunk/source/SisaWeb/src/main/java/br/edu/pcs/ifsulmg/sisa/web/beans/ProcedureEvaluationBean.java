/*
 * File:	     ProcedureEvaluationBean.java
 * Creation date: 28/08/2015
 * Author:        Rarvs
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

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.ProcedureEvaluationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.ProcedureEvaluation;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ProcedureEvaluationDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class ProcedureEvaluationBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3356362057149272685L;
	
	protected ProcedureEvaluation _procedureEvaluation;
	
	protected List<ProcedureEvaluation> _procedureEvaluationsList;
	
	@Inject
	protected ProcedureEvaluationDelegate _procedureEvaluationDelegate;
	
	private Boolean _editProcedureEvaluation;
	
	protected boolean _teachingPlanIsVisible = true;
	
	@PostConstruct
	public void init() {
		_procedureEvaluation = new ProcedureEvaluation();
		loadProcedureEvaluationsList();
	}
	
	public void save() {
		try {
			if (_editProcedureEvaluation) {
				_procedureEvaluationDelegate.update(_procedureEvaluation);
			}
			else {
				_procedureEvaluationDelegate.insert(_procedureEvaluation);
			}
			loadList();
			RequestContext.getCurrentInstance().execute("PF('saveProcedureEvaluationDialog').hide()");
			RequestContext.getCurrentInstance().update("formListProcedureEvaluations");	
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_PROCEDURE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (ProcedureEvaluationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_PROCEDURE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected void loadList(){
		loadProcedureEvaluationsList();
	}

	public void loadProcedureEvaluation(ProcedureEvaluation procedureEvaluation) {
		_procedureEvaluation = procedureEvaluation;
		_editProcedureEvaluation = true;
	}

	public void loadProcedureEvaluationsList() {
		try {
			_procedureEvaluationsList = _procedureEvaluationDelegate.list();
		} catch (ProcedureEvaluationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PROCEDURE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(ProcedureEvaluation procedureEvaluation) {
		try {
			_procedureEvaluationDelegate.delete(procedureEvaluation);
			loadProcedureEvaluationsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_PROCEDURE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (ProcedureEvaluationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_PROCEDURE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}  catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_PROCEDURE_EVALUATION_TEACHING_PLAN)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PROCEDURE_EVALUATION_TEACHING_PLAN;
			} 
			else if(constraint.contains(MsgConstants.CONSTRAINT_PROCEDURE_EVALUATION_EVALUATION)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PROCEDURE_EVALUATION_EVALUATION;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}


	public void cleanProcedureEvaluation() {
		_procedureEvaluation = new ProcedureEvaluation();
		_editProcedureEvaluation = false;
	}

	public ProcedureEvaluation getProcedureEvaluation() {
		return _procedureEvaluation;
	}

	public void setProcedureEvaluation(ProcedureEvaluation procedureEvaluation) {
		_procedureEvaluation = procedureEvaluation;
	}

	public List<ProcedureEvaluation> getProcedureEvaluationsList() {
		return _procedureEvaluationsList;
	}

	public void setProcedureEvaluationsList(List<ProcedureEvaluation> procedureEvaluationsList) {
		_procedureEvaluationsList = procedureEvaluationsList;
	}

	public ProcedureEvaluationDelegate getProcedureEvaluationDelegate() {
		return _procedureEvaluationDelegate;
	}

	public void setProcedureEvaluationDelegate(ProcedureEvaluationDelegate procedureEvaluationDelegate) {
		_procedureEvaluationDelegate = procedureEvaluationDelegate;
	}

	public Boolean getEditProcedureEvaluation() {
		return _editProcedureEvaluation;
	}

	public void setEditProcedureEvaluation(Boolean editProcedureEvaluation) {
		_editProcedureEvaluation = editProcedureEvaluation;
	}

	public boolean isTeachingPlanIsVisible() {
		return _teachingPlanIsVisible;
	}

	public void setTeachingPlanIsVisible(boolean teachingPlanIsVisible) {
		_teachingPlanIsVisible = teachingPlanIsVisible;
	}
}