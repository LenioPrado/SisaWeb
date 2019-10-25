/*
 * File:	     FillTeachingPlanBean.java
 * Creation date: 05/10/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.ResponsibleException;
import br.edu.pcs.ifsulmg.sisa.exceptions.VerificationException;
import br.edu.pcs.ifsulmg.sisa.util.DateUtil;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Responsible;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Verification;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ResponsibleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.VerificationDelegate;
import br.edu.pcs.ifsulmg.sisa.web.enums.VerificationStatus;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class FillTeachingPlanBean extends TeachingPlanBaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8306738123063195623L;
	
	@Inject
	private ResponsibleDelegate _responsibleDelegate;
	
	@Inject
	private VerificationDelegate _verificationDelegate;
	
	private List<Responsible> _responsiblesList;
	
	private List<Responsible> _selectedResponsibles;
	
	private boolean _hasPendentEvaluation = false;	
		
	@PostConstruct
	public void init() {
		_action = "fill";
		defineTeachingPlanFromSession();
		if (_completeTeachingPlan != null) {
			verifyPendentEvaluations();
		}
		
		_teachingPlanIsVisible = _completeTeachingPlan != null;
	}
	
	private void verifyPendentEvaluations(){
		_hasPendentEvaluation = false;
		try{
			List<Verification> verifications = _verificationDelegate.listByTeachingPlan(_completeTeachingPlan);			
			for (Verification verification : verifications) {
				if (verification.getStatus() == VerificationStatus.PENDING.getIndex()) {
					_hasPendentEvaluation = true;
					break;
				}
			}
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}	
	}
	
	public void loadTeachingPlanResponsibles(){		
		try {
			_selectedResponsibles = new ArrayList<Responsible>();					
			int teachingPlanId = _completeTeachingPlan.getTeachingPlanId();
			_responsiblesList = _responsibleDelegate.listByTeachingPlan(teachingPlanId);			
		} catch (ResponsibleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_RESPONSIBLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		} 
	}
	
	public void submitTeachingPlan(){
		try {
			for (Responsible responsible : _selectedResponsibles) {
				Timestamp now = DateUtil.nowTimeStamp();
				int status = VerificationStatus.PENDING.getIndex();
				Verification verification = new Verification(0, now, null,"", status, responsible);
				_verificationDelegate.insert(verification);
			}			
			RequestContext.getCurrentInstance().execute("PF('submitTeachingPlanDialog').hide()");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_SUBMIT_TEACHING_PLAN, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_SUBMIT_TEACHING_PLAN, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String editClassesProgramming(TeachingPlan teachingPlan){
		BeanUtil.putSession("teachingPlan", teachingPlan);
		
		LogSISA.log.info(" teaching plan to fill classes programming: " + teachingPlan.getTeachingPlanLabel());

		return WebConstants.PAGE_EDIT_CLASSES_PROGRAMMING;
	}
	
	public String editProceduresEvaluation(TeachingPlan teachingPlan){
		BeanUtil.putSession("teachingPlan", teachingPlan);
		
		LogSISA.log.info(" teaching plan to fill procedures evaluations: " + teachingPlan.getTeachingPlanLabel());

		return WebConstants.PAGE_EDIT_PROCEDURES_EVALUATION;
	}

	public List<Responsible> getResponsiblesList() {
		return _responsiblesList;
	}

	public void setResponsiblesList(List<Responsible> responsiblesList) {
		_responsiblesList = responsiblesList;
	}

	public List<Responsible> getSelectedResponsibles() {
		return _selectedResponsibles;
	}

	public void setSelectedResponsibles(List<Responsible> selectedResponsibles) {
		_selectedResponsibles = selectedResponsibles;
	}

	public boolean isHasPendentEvaluation() {
		return _hasPendentEvaluation;
	}

	public void setHasPendentEvaluation(boolean hasPendentEvaluation) {
		_hasPendentEvaluation = hasPendentEvaluation;
	}
}