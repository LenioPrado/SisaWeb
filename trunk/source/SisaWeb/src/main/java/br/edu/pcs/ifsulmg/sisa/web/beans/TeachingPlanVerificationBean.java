/*
 * File:	     TeachingPlanVerificationBean.java
 * Creation date: 22/08/2016
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

import br.edu.pcs.ifsulmg.sisa.exceptions.TeachingPlanException;
import br.edu.pcs.ifsulmg.sisa.exceptions.VerificationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Verification;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.TeachingPlanDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.VerificationDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class TeachingPlanVerificationBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2086411234076482946L;
	
	private List<TeachingPlan> _teachingPlansList;
	
	private List<Verification> _verificationsList;
	
	@Inject
	private TeachingPlanDelegate _teachingPlanDelegate;
	
	@Inject
	private VerificationDelegate _verificationDelegate;
	
	@PostConstruct
	public void init() {
		loadTeachingPlansList();
		loadVerifications();
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
	
	public void loadVerifications() {
		try {
			_verificationsList = _verificationDelegate.list();
			for (Verification verification : _verificationsList) {
				int index = _teachingPlansList.indexOf(verification.getResponsible().getTeachingPlan());					
				TeachingPlan teachingPlan = _teachingPlansList.get(index);
				teachingPlan.getVerifications().add(verification);
			}				
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public List<TeachingPlan> getTeachingPlansList() {
		return _teachingPlansList;
	}

	public void setTeachingPlansList(List<TeachingPlan> teachingPlansList) {
		_teachingPlansList = teachingPlansList;
	}

	public List<Verification> getVerificationsList() {
		return _verificationsList;
	}

	public void setVerificationsList(List<Verification> verificationsList) {
		_verificationsList = verificationsList;
	}
}