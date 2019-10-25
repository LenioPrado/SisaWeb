/*
 * File:	     RoleBean.java
 * Creation date: 09/07/2015
 * Role:        Rarvs
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
import br.edu.pcs.ifsulmg.sisa.exceptions.RoleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.RoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class RoleBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192734798825886839L;
	
	private Role _role;
	
	private List<Role> _rolesList;
	
	@Inject
	private RoleDelegate _roleDelegate;
	
	private Boolean _editRole;
	
	@PostConstruct
	public void init() {
		_role = new Role();
		loadRolesList();
	}
	
	public void save() {
		try {
			if (_editRole) {
				_roleDelegate.update(_role);
			}
			else {
				_roleDelegate.insert(_role);
			}
			loadRolesList();
			RequestContext.getCurrentInstance().execute("PF('saveRoleDialog').hide()");
			RequestContext.getCurrentInstance().update("formListRoles");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (RoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadRole(Role role) {
		_role = role;
		_editRole = true;
	}

	public void loadRolesList() {
		try {
			_rolesList = _roleDelegate.list();
		} catch (RoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ROLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Role role) {
		try {
			_roleDelegate.delete(role);
			loadRolesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (RoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_ROLE_PAGE_ROLE)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_ROLE_PAGE_ROLE;
			} 
			else if(constraint.contains(MsgConstants.CONSTRAINT_USER_ROLE)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_ROLE_USER;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanRole() {
		_role = new Role();
		_editRole = false;
	}

	public Role getRole() {
		return _role;
	}

	public void setRole(Role role) {
		_role = role;
	}

	public List<Role> getRolesList() {
		return _rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		_rolesList = rolesList;
	}

	public RoleDelegate getRoleDelegate() {
		return _roleDelegate;
	}

	public void setRoleDelegate(RoleDelegate roleDelegate) {
		_roleDelegate = roleDelegate;
	}

	public Boolean getEditRole() {
		return _editRole;
	}

	public void setEditRole(Boolean editRole) {
		_editRole = editRole;
	}	
}