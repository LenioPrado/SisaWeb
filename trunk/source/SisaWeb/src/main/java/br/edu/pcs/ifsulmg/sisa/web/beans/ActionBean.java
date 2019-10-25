/*
 * File:	     ActionBean.java
 * Creation date: 30/03/2016
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

import br.edu.pcs.ifsulmg.sisa.exceptions.ActionException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Action;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ActionDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class ActionBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192734798825886839L;
	
	private Action _action;
	
	private List<Action> _actionsList;
	
	@Inject
	private ActionDelegate _actionDelegate;
	
	private Boolean _editAction;
	
	@PostConstruct
	public void init() {
		_action = new Action();
		loadActionsList();
	}
	
	public void save() {
		try {
			if (_editAction) {
				_actionDelegate.update(_action);
			}
			else {
				_actionDelegate.insert(_action);
			}
			loadActionsList();
			RequestContext.getCurrentInstance().execute("PF('saveActionDialog').hide()");
			RequestContext.getCurrentInstance().update("formListActions");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_ACTION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (ActionException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_ACTION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadAction(Action action) {
		_action = action;
		_editAction = true;
	}

	public void loadActionsList() {
		try {
			_actionsList = _actionDelegate.list();
		} catch (ActionException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ACTIONS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Action action) {
		try {
			_actionDelegate.delete(action);
			loadActionsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_ACTION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (ActionException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_ACTION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_ACTION_PAGE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanAction() {
		_action = new Action();
		_editAction = false;
	}

	public Action getAction() {
		return _action;
	}

	public void setAction(Action action) {
		_action = action;
	}

	public List<Action> getActionsList() {
		return _actionsList;
	}

	public void setActionsList(List<Action> actionsList) {
		_actionsList = actionsList;
	}

	public ActionDelegate getActionDelegate() {
		return _actionDelegate;
	}

	public void setActionDelegate(ActionDelegate actionDelegate) {
		_actionDelegate = actionDelegate;
	}

	public Boolean getEditAction() {
		return _editAction;
	}

	public void setEditAction(Boolean editAction) {
		_editAction = editAction;
	}	
}