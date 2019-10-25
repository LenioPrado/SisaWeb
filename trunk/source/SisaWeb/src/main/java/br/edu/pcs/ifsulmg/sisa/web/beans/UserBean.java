/*
 * File:	     UserBean.java
 * Creation date: 08/06/2015
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
import br.edu.pcs.ifsulmg.sisa.exceptions.UserException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.UserDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.UserAccessUtils;

@Named
@ViewScoped
public class UserBean extends CommonBean implements Serializable {

	private static final long serialVersionUID = -4306398560083692125L;

	private User _user;
	
	private List<User> _usersList;
	
	private List<User> _filteredUsersList;

	@Inject
	private UserDelegate _userDelegate;

	private boolean _editUser;

	@PostConstruct
	public void init() {
		_user = new User();
		loadUsersList();
	}

	public void save() {
		try {
			if (_editUser) {
				_userDelegate.update(_user);
			}
			else {
				_userDelegate.insert(_user);
			}
			loadUsersList();
			RequestContext.getCurrentInstance().execute("PF('saveUserDialog').hide()");
			RequestContext.getCurrentInstance().update("formListUser");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_USER, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (UserException e) {
			String message = MsgConstants.MSG_ERROR_SAVE_USER;
			if (e.getMessage().equals(MsgConstants.MSG_CURRENT_PASSWORD_NOT_MATCH)) {
				message = e.getMessage();
			}
			
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG_USER, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadUser(User user) {
		_user = user;
		_editUser = true;
	}

	public void loadUser() {
		try {
			_user = _userDelegate.getUserByEmail(UserAccessUtils.getInstance().getUserInSession().getEmail());
			_editUser = true;
			RequestContext.getCurrentInstance().execute("PF('saveUserDialog').show()");
		} catch (UserException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOAD_USER, BeanUtil.ID_MSG_USER, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadUsersList() {
		try {
			_usersList = _userDelegate.list();
		} catch (Exception e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOAD_USERS_LIST, BeanUtil.ID_MSG_USER, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
			
		}
	}

	public void delete(User user) {
		try {
			_userDelegate.delete(user);
			loadUsersList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_USER, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (UserException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_USER, BeanUtil.ID_MSG_USER, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}  catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_USER_RESPONSIBLE)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_USER_RESPONSIBLE;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_USER_VERIFICATION)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_USER_VERIFICATION;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_USER_ROLE)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_USER_ROLE;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanUser() {
		_user = new User();
		_editUser = false;
	}

	public void changePassword(String currentPassword) {
		try {
			_userDelegate.updatePassword(UserAccessUtils.getInstance().getUserInSession().getEmail(), currentPassword, _user.getPassword());
			RequestContext.getCurrentInstance().execute("dialogEditPwd.hide()");
		} catch (UserException e) {
			BeanUtil.setMessageGeneral(MessagesUtil.getValue("msg_update_password_fail"), BeanUtil.ID_MSG_PASSWORD, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public User getUser() {
		return _user;
	}

	public void setUser(User user) {
		_user = user;
	}

	public List<User> getUsersList() {
		return _usersList;
	}

	public void setUsersList(List<User> _usersList) {
		this._usersList = _usersList;
	}

	public UserDelegate getUserDelegate() {
		return _userDelegate;
	}

	public boolean isEditUser() {
		return _editUser;
	}

	public void setEditUser(boolean editUser) {
		_editUser = editUser;
	}

	public List<User> getFilteredUsersList() {
		return _filteredUsersList;
	}

	public void setFilteredUsersList(List<User> filteredUsersList) {
		this._filteredUsersList = filteredUsersList;
	}
}