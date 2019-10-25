/*
 * File:	     AcademicPeriodBean.java
 * Creation date: 09/07/2015
 * Author:        PagodeiroDoMal
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

import br.edu.pcs.ifsulmg.sisa.exceptions.AcademicPeriodException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.AcademicPeriodDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class AcademicPeriodBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192734798825886839L;
	
	private AcademicPeriod _academicPeriod;
	
	private List<AcademicPeriod> _academicPeriodsList;
	
	@Inject
	private AcademicPeriodDelegate _academicPeriodDelegate;
	
	private Boolean _editAcademicPeriod;	
	
	@PostConstruct
	public void init() {
		_academicPeriod = new AcademicPeriod();
		loadAcademicPeriodsList();
	}
	
	public void save() {
		try {
			if (_editAcademicPeriod) {
				_academicPeriodDelegate.update(_academicPeriod);
			}
			else {
				_academicPeriodDelegate.insert(_academicPeriod);
			}
			loadAcademicPeriodsList();
			RequestContext.getCurrentInstance().execute("PF('saveAcademicPeriodDialog').hide()");
			RequestContext.getCurrentInstance().update("formListAcademicPeriods");		
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_ACADEMIC_PERIOD, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (AcademicPeriodException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_ACADEMIC_PERIOD, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadAcademicPeriod(AcademicPeriod author) {
		_academicPeriod = author;
		_editAcademicPeriod = true;
	}

	public void loadAcademicPeriodsList() {
		try {
			_academicPeriodsList = _academicPeriodDelegate.list();
		} catch (AcademicPeriodException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_AUTHORS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(AcademicPeriod author) {
		try {
			_academicPeriodDelegate.delete(author);
			loadAcademicPeriodsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_ACADEMIC_PERIOD, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (AcademicPeriodException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_ACADEMIC_PERIOD, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_ACADEMIC_PERIOD_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanAcademicPeriod() {
		_academicPeriod = new AcademicPeriod();
		_editAcademicPeriod = false;
	}

	public AcademicPeriod getAcademicPeriod() {
		return _academicPeriod;
	}

	public void setAcademicPeriod(AcademicPeriod author) {
		_academicPeriod = author;
	}

	public List<AcademicPeriod> getAcademicPeriodsList() {
		return _academicPeriodsList;
	}

	public void setAcademicPeriodsList(List<AcademicPeriod> authorsList) {
		_academicPeriodsList = authorsList;
	}

	public AcademicPeriodDelegate getAcademicPeriodDelegate() {
		return _academicPeriodDelegate;
	}

	public void setAcademicPeriodDelegate(AcademicPeriodDelegate academicPeriodDelegate) {
		_academicPeriodDelegate = academicPeriodDelegate;
	}

	public Boolean getEditAcademicPeriod() {
		return _editAcademicPeriod;
	}

	public void setEditAcademicPeriod(Boolean editAcademicPeriod) {
		_editAcademicPeriod = editAcademicPeriod;
	}
}