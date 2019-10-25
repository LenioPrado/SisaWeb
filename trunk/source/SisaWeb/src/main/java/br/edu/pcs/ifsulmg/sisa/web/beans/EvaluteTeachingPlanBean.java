/*
 * File:	     EvaluteTeachingPlanBean.java
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
import br.edu.pcs.ifsulmg.sisa.exceptions.TeachingPlanException;
import br.edu.pcs.ifsulmg.sisa.exceptions.VerificationException;
import br.edu.pcs.ifsulmg.sisa.util.DateUtil;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.Verification;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.TeachingPlanDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.VerificationDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.UserAccessUtils;

@Named
@ViewScoped
public class EvaluteTeachingPlanBean extends TeachingPlanBaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -18964726151946286L;
		
	@Inject
	private TeachingPlanDelegate _teachingPlanDelegate;
	
	@Inject
	private VerificationDelegate _verificationDelegate;
	
	private Verification _verification;
	
	private Verification _oldVerification = new Verification();
	
	private List<TeachingPlan> _teachingPlansToEvaluateList;
	
	private boolean _evaluatingTeachingPlan = false;
		
	@PostConstruct
	public void init() {		
		
	}
	
	public void loadTeachingPlanAndSetVerification(){		
		setVerificationFromSession();
	}
	
	private void setVerificationFromSession(){
		_verification = (Verification) BeanUtil.getParamInSession("verification");
		_teachingPlanIsVisible = _verification != null;
	}
	
	public void loadTeachingPlansToEvaluateList(){
		try {
			User loggedUser = UserAccessUtils.getInstance().getUserInSession();
			Role loggedRole = UserAccessUtils.getInstance().getRoleInSession();
			_teachingPlansToEvaluateList = new ArrayList<TeachingPlan>();
			List<Verification> verifications = _verificationDelegate.listByUserRole(loggedUser, loggedRole);
			
			for (Verification verification : verifications){
				TeachingPlan teachingPlan = verification.getResponsible().getTeachingPlan();
				
				if (!_teachingPlansToEvaluateList.contains(teachingPlan)) {
					_teachingPlansToEvaluateList.add(teachingPlan);					
				}
				teachingPlan = _teachingPlansToEvaluateList.get(_teachingPlansToEvaluateList.indexOf(teachingPlan));
				
				if(!teachingPlan.getVerifications().contains(verification)){
					teachingPlan.getVerifications().add(verification);			
				}
			}
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public String evaluateTeachingPlan(TeachingPlan teachingPlan, Verification verification) {		
		try {
			teachingPlan = _teachingPlanDelegate.getByTeachingPlanId(teachingPlan.getTeachingPlanId());
		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_TEACHING_PLANS,
					BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
		BeanUtil.putSession("teachingPlan", teachingPlan);
		BeanUtil.putSession("verification", verification);
		
		String info = String.format(" Evaluate teaching plan: %s, verification: %d", 
				teachingPlan.getTeachingPlanLabel(), verification.getVerificationId());
		LogSISA.log.info(info);

		return WebConstants.PAGE_EVALUATE_TEACHING_PLAN;
	}
	
	public void enableTeachingPlanVerification() {
		_evaluatingTeachingPlan = true;
		copyVerification(_verification, _oldVerification);
		String message = "Avaliação do Plano de Ensino Habilitada";
		BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		LogSISA.log.info(message);
		System.out.println(message);
	}
	
	public void disableTeachingPlanVerification() {
		_evaluatingTeachingPlan = false;
		copyVerification(_oldVerification, _verification);
		String message = "Avaliação do Plano de Ensino Desabilitada";
		BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		LogSISA.log.info(message);
		System.out.println(message);
	}
	
	private void copyVerification(Verification from, Verification to){
		to.setObservation(from.getObservation());
		to.setStatus(from.getStatus());
	}
	
	public void evaluateTeachingPlan(){
		try {
			Timestamp now = DateUtil.nowTimeStamp();
			_verification.setEvaluationDate(now);
			_verificationDelegate.update(_verification);
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
			RequestContext.getCurrentInstance().update("formCompleteTeachingPlan");
			_evaluatingTeachingPlan = false;
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_CONSTRAINT_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean isEvaluatingTeachingPlan() {
		return _evaluatingTeachingPlan;
	}

	public void setEvaluatingTeachingPlan(boolean evaluatingTeachingPlan) {
		_evaluatingTeachingPlan = evaluatingTeachingPlan;
	}

	public Verification getVerification() {
		return _verification;
	}

	public void setVerification(Verification verification) {
		_verification = verification;
	}

	public List<TeachingPlan> getTeachingPlansToEvaluateList() {
		return _teachingPlansToEvaluateList;
	}

	public void setTeachingPlansToEvaluateList(
			List<TeachingPlan> teachingPlansToEvaluateList) {
		_teachingPlansToEvaluateList = teachingPlansToEvaluateList;
	}
}