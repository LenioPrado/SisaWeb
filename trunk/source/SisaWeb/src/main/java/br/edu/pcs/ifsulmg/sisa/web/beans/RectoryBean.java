/*
 * File:	     RectoryBean.java
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

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.RectoryException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Rectory;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.RectoryDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class RectoryBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1840786566702317878L;
	
	private Rectory _rectory;
	
	private List<Rectory> _rectoriesList;
	
	@Inject
	private RectoryDelegate _rectoryDelegate;
	
	private Boolean _editRectory;
	
	@PostConstruct
	public void init() {
		_rectory = new Rectory();
		loadRectoryList();
	}
	
	public void save() {
		try {
			if (_editRectory) {
				_rectoryDelegate.update(_rectory);
			}
			else {
				_rectoryDelegate.insert(_rectory);
			}
			loadRectoryList();
			RequestContext.getCurrentInstance().execute("PF('saveRectoryDialog').hide()");
			RequestContext.getCurrentInstance().update("formListRectories");	
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_RECTORY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (RectoryException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_RECTORY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadRectory(Rectory rectory) {
		_rectory = rectory;
		_editRectory = true;
	}

	public void loadRectoryList() {
		try {
			_rectoriesList = _rectoryDelegate.list();
		} catch (RectoryException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_RECTORIES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Rectory rectory) {
		try {
			_rectoryDelegate.delete(rectory);
			loadRectoryList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_RECTORY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (RectoryException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_RECTORY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}   catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_RECTORY_CAMPUS)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_RECTORY_CAMPUS;
			} 
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanRectory() {
		_rectory = new Rectory();
		_editRectory = false;
	}

	public Rectory getRectory() {
		return _rectory;
	}

	public void setRectory(Rectory rectory) {
		_rectory = rectory;
	}

	public List<Rectory> getRectoriesList() {
		return _rectoriesList;
	}

	public void setRectoriesList(List<Rectory> rectoriesList) {
		_rectoriesList = rectoriesList;
	}

	public RectoryDelegate getRectoryDelegate() {
		return _rectoryDelegate;
	}

	public void setRectoryDelegate(RectoryDelegate rectoryDelegate) {
		_rectoryDelegate = rectoryDelegate;
	}

	public Boolean getEditRectory() {
		return _editRectory;
	}

	public void setEditRectory(Boolean editRectory) {
		_editRectory = editRectory;
	}	
}