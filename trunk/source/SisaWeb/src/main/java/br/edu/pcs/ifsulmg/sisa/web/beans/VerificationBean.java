/*
 * File:	     VerificationBean.java
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
import br.edu.pcs.ifsulmg.sisa.exceptions.ResponsibleException;
import br.edu.pcs.ifsulmg.sisa.exceptions.VerificationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Responsible;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Verification;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ResponsibleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.VerificationDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class VerificationBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3942362015149272685L;
	
	private Verification _verification;
	
	private List<Verification> _verificationsList;
	
	private List<Responsible> _responsiblesList;
	
	@Inject
	private ResponsibleDelegate _responsibleDelegate;
	
	@Inject
	private VerificationDelegate _verificationDelegate;
	
	private Boolean _editVerification;

	@PostConstruct
	public void init() {
		_verification = new Verification();
		loadVerificationsList();
		loadResponsiblesList();
	}
	
	public void save() {
		try {
			if (_editVerification) {
				_verificationDelegate.update(_verification);
			}
			else {
				_verificationDelegate.insert(_verification);
			}
			loadVerificationsList();
			RequestContext.getCurrentInstance().execute("PF('saveVerificationDialog').hide()");
			RequestContext.getCurrentInstance().update("formListVerifications");	
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadVerification(Verification verification) {
		_verification = verification;
		_editVerification = true;
		
		if (!_responsiblesList.contains(verification.getResponsible())) {
			_responsiblesList.add(verification.getResponsible());
		}
	}

	public void loadVerificationsList() {
		try {
			_verificationsList = _verificationDelegate.list();
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	public void loadResponsiblesList() {
		try {
			_responsiblesList = _responsibleDelegate.list();
		} catch (ResponsibleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_RESPONSIBLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}	

	public void delete(Verification verification) {
		try {
			_verificationDelegate.delete(verification);
			loadVerificationsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (VerificationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_VERIFICATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_VERIFICATION_TEACHING_PLAN)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_TEACHING_PLAN;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_VERIFICATION_ROLE)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_ROLE;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_VERIFICATION_USER)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_VERIFICATION_USER;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanVerification() {
		_verification = new Verification();
		_editVerification = false;
	}
	
	public void teachingPlanChanged(){
		try {
			TeachingPlan teachingPlan = _verification.getResponsible().getTeachingPlan(); 
			if (teachingPlan != null) {
				_responsiblesList = _responsibleDelegate.listByTeachingPlan(teachingPlan.getTeachingPlanId());
			} else {
				_responsiblesList = null;
			}
		} catch (ResponsibleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_RESPONSIBLES_BY_TEACHING_PLAN, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public Verification getVerification() {
		return _verification;
	}

	public void setVerification(Verification verification) {
		_verification = verification;
	}

	public List<Verification> getVerificationsList() {
		return _verificationsList;
	}

	public void setVerificationsList(List<Verification> verificationsList) {
		_verificationsList = verificationsList;
	}

	public VerificationDelegate getVerificationDelegate() {
		return _verificationDelegate;
	}

	public void setVerificationDelegate(VerificationDelegate verificationDelegate) {
		_verificationDelegate = verificationDelegate;
	}

	public Boolean getEditVerification() {
		return _editVerification;
	}

	public void setEditVerification(Boolean editVerification) {
		_editVerification = editVerification;
	}

	public List<Responsible> getResponsiblesList() {
		return _responsiblesList;
	}

	public void setResponsiblesList(List<Responsible> responsiblesList) {
		_responsiblesList = responsiblesList;
	}
}