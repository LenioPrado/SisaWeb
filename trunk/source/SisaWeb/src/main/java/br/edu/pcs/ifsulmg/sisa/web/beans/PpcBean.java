/*
 * File:	     PpcBean.java
 * Creation date: 19/06/2015
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

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PpcException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PpcDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class PpcBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4306362057149272685L;
	
	private Ppc _ppc;
	
	private List<Ppc> _ppcsList;

	@Inject
	private PpcDelegate _ppcDelegate;
	
	private Boolean _editPpc;
	
	@PostConstruct
	public void init() {
		_ppc = new Ppc();
		loadPpcsList();
	}
	
	public void save() {
		try {
			if (_editPpc) {
				_ppcDelegate.update(_ppc);
			}
			else {
				_ppcDelegate.insert(_ppc);
			}
			loadPpcsList();
			RequestContext.getCurrentInstance().execute("PF('savePpcDialog').hide()");
			RequestContext.getCurrentInstance().update("formListPpcs");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_PPC, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (PpcException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_PPC, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadPpc(Ppc ppc) {
		_ppc = ppc;
		_editPpc = true;
	}

	public void loadPpcsList() {
		try {
			_ppcsList = _ppcDelegate.list();
		} catch (PpcException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PPCS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Ppc ppc) {
		try {
			_ppcDelegate.delete(ppc);
			loadPpcsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_PPC, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (PpcException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_PPC, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}  catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_PPC_DISCIPLINE)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PPC_DISCIPLINE;
			} 
			else if(constraint.contains(MsgConstants.CONSTRAINT_PPC_COURSE)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PPC_COURSE;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_PPC_TRAINEESHIP)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PPC_TRAINEESHIP;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanPpc() {
		_ppc = new Ppc();
		_editPpc = false;
	}
	
	public Ppc getPpc() {
		return _ppc;
	}

	public void setPpc(Ppc ppc) {
		_ppc = ppc;
	}

	public List<Ppc> getPpcsList() {
		return _ppcsList;
	}

	public void setPpcsList(List<Ppc> ppcsList) {
		_ppcsList = ppcsList;
	}

	public PpcDelegate getPpcDelegate() {
		return _ppcDelegate;
	}

	public void setPpcDelegate(PpcDelegate ppcDelegate) {
		_ppcDelegate = ppcDelegate;
	}

	public Boolean getEditPpc() {
		return _editPpc;
	}

	public void setEditPpc(Boolean editPpc) {
		_editPpc = editPpc;
	}
}