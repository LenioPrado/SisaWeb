/*
 * File:	     UserRoleBean.java
 * Creation date: 03/09/2015
 * UserRole:       PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.RoleException;
import br.edu.pcs.ifsulmg.sisa.exceptions.UserException;
import br.edu.pcs.ifsulmg.sisa.exceptions.UserRoleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.RoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.UserDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.UserRoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class UserRoleBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192734798825886839L;
	
	private static final int DEFAULT_USER_ROLE_ID = 1;
	
	@Inject
	private UserRoleDelegate _userRoleDelegate;
	
	@Inject
	private UserDelegate _userDelegate;
	
	@Inject
	private RoleDelegate _roleDelegate;
	
	private UserRole _userRole;
	
	private List<Role> _rolesList;
	
	private List<User> _usersList;
	
	private List<UserRole> _userRolesList;	
	
	private Boolean _editUserRole;
	
	@PostConstruct
	public void init() {
		_userRole = new UserRole();
		_rolesList = new ArrayList<Role>();
		loadUserRolesList();
	}
	
	public void save() {
		try {
			if (_editUserRole) {			
				_userRoleDelegate.update(_userRole);
			} else {			
				_userRoleDelegate.insert(_userRole);
			}
			
			loadUserRolesList();
			RequestContext.getCurrentInstance().execute("PF('saveUserRoleDialog').hide()");
			RequestContext.getCurrentInstance().update("formListUserRoles");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (UserRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {			
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_CONSTRAINT_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadUserRole(UserRole userRole) {		
		_userRole = userRole;	
		
		if (_rolesList.size() == 0) {
			_rolesList.add(_userRole.getRole());
		}
		
		_editUserRole = true;
	}
	
	public void loadUnsetRoles(User user) {
		try {
			cleanUserRole();
			_userRole.setUser(user);
			_rolesList = _roleDelegate.list();
			for (UserRole userRole : user.getUserRoles()) {
				if (_rolesList.contains(userRole.getRole())) {
					_rolesList.remove(userRole.getRole());
				}
			}
		} catch (RoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ROLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
		_editUserRole = false;
	}

	public void loadUserRolesList() {
		try {			
			_usersList = _userDelegate.list();
			_userRolesList = _userRoleDelegate.list();
			
			for (UserRole userRole : _userRolesList) {
				User user = userRole.getUser();
				
				User existentUser = _usersList.get(_usersList.indexOf(user));				
				if (!existentUser.getUserRoles().contains(userRole)) {
					existentUser.getUserRoles().add(userRole);					
				}								
			}	
		} catch (UserRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		} catch (UserException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_USERS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(UserRole userRole) {
		try {
			if (userRole.getUserRoleId() == DEFAULT_USER_ROLE_ID) {
				BeanUtil.setMessageGeneral(MsgConstants.MSG_ERRO_DEFAULT_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_WARN);
			} else {
				_userRoleDelegate.delete(userRole);
				loadUserRolesList();
				BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
			}			
		} catch (UserRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace(); 
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_USER_ROLE_RESPONSIBLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanUserRole() {
		_userRole = new UserRole();	
		_editUserRole = false;
	}

	public List<UserRole> getUserRolesList() {
		return _userRolesList;
	}

	public void setUserRolesList(List<UserRole> userRoleList) {
		_userRolesList = userRoleList;
	}

	public Boolean getEditUserRole() {
		return _editUserRole;
	}

	public void setEditUserRole(Boolean editUserRole) {
		_editUserRole = editUserRole;
	}

	public List<User> getUsersList() {
		return _usersList;
	}

	public void setUsersList(List<User> usersList) {
		_usersList = usersList;
	}

	public List<Role> getRolesList() {
		return _rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		_rolesList = rolesList;
	}

	public UserRole getUserRole() {
		return _userRole;
	}

	public void setUserRole(UserRole userRole) {
		_userRole = userRole;
	}	
}