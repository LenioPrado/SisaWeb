/*
 * File:	     ProcedureEvaluationTeachingPlanBean.java
 * Creation date: 18/10/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.pcs.ifsulmg.sisa.exceptions.ProcedureEvaluationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.ProcedureEvaluation;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class ProcedureEvaluationTeachingPlanBean extends ProcedureEvaluationBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4984924394298502511L;
	
	TeachingPlan selectedTeachingPlan;
	
	@PostConstruct
	public void init() {
		defineTeachingPlanFromSession();
		if (selectedTeachingPlan != null) {
			listProcedureEvaluationsByTeachingPlan();
			_procedureEvaluation = new ProcedureEvaluation();
		}	
	}
	
	private void defineTeachingPlanFromSession(){
		selectedTeachingPlan = (TeachingPlan)BeanUtil.getParamInSession("teachingPlan");				
		_teachingPlanIsVisible = false;
	}
	
	private void listProcedureEvaluationsByTeachingPlan() {
		try {
			int teachingPlanId = selectedTeachingPlan.getTeachingPlanId();
			_procedureEvaluationsList = _procedureEvaluationDelegate.listByTeachingPlan(teachingPlanId);
		} catch (ProcedureEvaluationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PROCEDURE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	public void save(){
		_procedureEvaluation.setTeachingPlan(selectedTeachingPlan);
		super.save();
	}
	
	@Override
	protected void loadList(){
		listProcedureEvaluationsByTeachingPlan();
	}
	
	public String backToTeachingPlanFill(){
		return WebConstants.PAGE_FILL_TEACHING_PLAN;
	}
}