/*
 * File:	     TeachingPlanBaseBean.java
 * Creation date: 05/10/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.pcs.ifsulmg.sisa.exceptions.ClassProgrammingException;
import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineBibliographyException;
import br.edu.pcs.ifsulmg.sisa.exceptions.ProcedureEvaluationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.ClassProgramming;
import br.edu.pcs.ifsulmg.sisa.vo.DisciplineBibliography;
import br.edu.pcs.ifsulmg.sisa.vo.ProcedureEvaluation;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ClassProgrammingDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.DisciplineBibliographyDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ProcedureEvaluationDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.CreatePdfUtil;

@Named
@ViewScoped
public class TeachingPlanBaseBean extends CommonBean {
	
	@Inject
	private DisciplineBibliographyDelegate _disciplineBibliographyDelegate;
	
	@Inject
	private ClassProgrammingDelegate _classProgrammingDelegate;
	
	@Inject
	private ProcedureEvaluationDelegate _procedureEvaluationDelegate;
	
	protected static TeachingPlan _completeTeachingPlan;
	
	protected boolean _teachingPlanIsVisible = false;
	
	protected String _action = "";
		
	protected void defineTeachingPlanFromSession(){
		_completeTeachingPlan = (TeachingPlan)BeanUtil.getParamInSession("teachingPlan");
		if (_completeTeachingPlan != null) {
			loadTeachingPlan();			
		}
	}
	
	protected void loadTeachingPlan(){
		loadTeachingPlanClassesProgramming();
		loadTeachingPlanProceduresEvaluations();
		loadTeachingPlanBibliographies();		
	}
	
	private void loadTeachingPlanClassesProgramming(){		
		try {
			List<ClassProgramming> list = _classProgrammingDelegate.listByTeachingPlan(_completeTeachingPlan.getTeachingPlanId());
			_completeTeachingPlan.setClassesProgramming(list);
		} catch (ClassProgrammingException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_CLASS_PROGRAMMING, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	private void loadTeachingPlanProceduresEvaluations(){
		try {
			List<ProcedureEvaluation> list = _procedureEvaluationDelegate.listByTeachingPlan(_completeTeachingPlan.getTeachingPlanId());
			_completeTeachingPlan.setProceduresEvaluations(list);
		} catch (ProcedureEvaluationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PROCEDURE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	private void loadTeachingPlanBibliographies() {
		try {
			int disciplineId = _completeTeachingPlan.getPpcDiscipline().getDiscipline().getDisciplineId();
			List<DisciplineBibliography> disciplineBibliographies = _disciplineBibliographyDelegate.listByDiscipline(disciplineId);
			List<Bibliography> basic = new ArrayList<Bibliography>();
			List<Bibliography> complementary = new ArrayList<Bibliography>();
			
			for (DisciplineBibliography discBib : disciplineBibliographies) {
				if (discBib.isBasic()) {
					basic.add(discBib.getBibliography());
				} else {
					complementary.add(discBib.getBibliography());
				}
			}
			_completeTeachingPlan.getPpcDiscipline().getDiscipline().setBasicBibliographies(basic);
			_completeTeachingPlan.getPpcDiscipline().getDiscipline().setComplementaryBibliographies(complementary);
		} catch (DisciplineBibliographyException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	public void createPDF() {
	    CreatePdfUtil.createPDF(_completeTeachingPlan);    
	}	

	public boolean isTeachingPlanIsVisible() {
		return _teachingPlanIsVisible;
	}

	public void setTeachingPlanIsVisible(boolean teachingPlanIsVisible) {
		_teachingPlanIsVisible = teachingPlanIsVisible;
	}

	public TeachingPlan getCompleteTeachingPlan() {
		return _completeTeachingPlan;
	}

	public void setCompleteTeachingPlan(TeachingPlan completeTeachingPlan) {
		_completeTeachingPlan = completeTeachingPlan;
	}

	public String getAction() {
		return _action;
	}

	public void setAction(String action) {
		_action = action;
	}
}