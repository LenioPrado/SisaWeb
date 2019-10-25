/*
 * File:	     HomeBean.java
 * Creation date: 23/08/2016
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
public class HomeBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -18978676453423123L;
	
	private List<TeachingPlan> _myTeachingPlansList;
	
	@Inject
	private TeachingPlanDelegate _teachingPlanDelegate;
	
	@Inject
	private VerificationDelegate _verificationDelegate;
	
	@PostConstruct
	public void init() {
		loadMyTeachingPlansList();
	}	
	
	public void loadMyTeachingPlansList(){
		try {
			User loggedUser = UserAccessUtils.getInstance().getUserInSession();
			_myTeachingPlansList = _teachingPlanDelegate.listByTeacher(loggedUser);
			
			if (_myTeachingPlansList.size() > 0) {
				List<Verification> verifications = _verificationDelegate.listByTeachingPlans(_myTeachingPlansList);
				for (Verification verification : verifications) {
					int index = _myTeachingPlansList.indexOf(verification.getResponsible().getTeachingPlan());
					TeachingPlan teachingPlan = _myTeachingPlansList.get(index);
					if (!teachingPlan.getVerifications().contains(verification)) {
						teachingPlan.getVerifications().add(verification);
					}

				}
			}
		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_TEACHING_PLANS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	public String fillTeachingPlan(TeachingPlan teachingPlan) {
		BeanUtil.putSession("teachingPlan", teachingPlan);
				
		LogSISA.log.info(" Fill teaching plan: " + teachingPlan.getTeachingPlanLabel());

		return WebConstants.PAGE_FILL_TEACHING_PLAN;
	}

	public List<TeachingPlan> getMyTeachingPlansList() {
		return _myTeachingPlansList;
	}

	public void setMyTeachingPlansList(List<TeachingPlan> myTeachingPlansList) {
		_myTeachingPlansList = myTeachingPlansList;
	}	
}