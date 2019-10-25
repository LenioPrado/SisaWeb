/*
 * File:	     CampusBean.java
 * Creation date: 06/07/2015
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

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.CampusException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Campus;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.CampusDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class CampusBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8808675061235232362L;
	
	private Campus _campus;
	
	private List<Campus> _campusList;
	
	@Inject
	private CampusDelegate _campusDelegate;
	
	private Boolean _editCampus;
	
	@PostConstruct
	public void init() {
		_campus = new Campus();
		loadCampusList();
	}	
	
	public void save() {
		try {
			if (_editCampus) {
				_campusDelegate.update(_campus);
			}
			else {
				_campusDelegate.insert(_campus);
			}
			loadCampusList();
			RequestContext.getCurrentInstance().execute("PF('saveCampusDialog').hide()");
			RequestContext.getCurrentInstance().update("formListCampus");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_CAMPUS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (CampusException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_CAMPUS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadCampus(Campus campus) {
		_campus = campus;
		_editCampus = true;
	}

	public void loadCampusList() {
		try {
			_campusList = _campusDelegate.list();
		} catch (CampusException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Campus campus) {
		try {
			_campusDelegate.delete(campus);
			loadCampusList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_CAMPUS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (CampusException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CAMPUS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_CAMPUS_COURSE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanCampus() {
		_campus = new Campus();
		_editCampus = false;
	}

	public Campus getCampus() {
		return _campus;
	}

	public void setCampus(Campus campus) {
		_campus = campus;
	}

	public List<Campus> getCampusList() {
		return _campusList;
	}

	public void setCampusList(List<Campus> campusList) {
		_campusList = campusList;
	}

	public CampusDelegate getCampusDelegate() {
		return _campusDelegate;
	}

	public void setCampusDelegate(CampusDelegate campusDelegate) {
		_campusDelegate = campusDelegate;
	}

	public Boolean getEditCampus() {
		return _editCampus;
	}

	public void setEditCampus(Boolean editCampus) {
		_editCampus = editCampus;
	}	
}