/*
 * File:	     LoginBean.java
 * Creation date: 04/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.pcs.ifsulmg.sisa.exceptions.UserException;
import br.edu.pcs.ifsulmg.sisa.exceptions.UserRoleException;
import br.edu.pcs.ifsulmg.sisa.util.DateUtil;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.UserDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.UserRoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.LdapLogin;
import br.edu.pcs.ifsulmg.sisa.web.util.UserAccessUtils;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -7862042677833389387L;
	
	private String _email = "";

	private String _pwd = "";
	
	private User _loggedUser;
	
	private List<Role> _rolesList;
	
	private Role _role;

	@Inject
	private UserDelegate _userDelegate;
	
	@Inject
	private UserRoleDelegate _userRoleDelegate;
	
	private boolean _useLdapAuthentication;
	
	private boolean _useLdapSSL;
	
	@PostConstruct
	public void init() {
		_useLdapAuthentication = LdapLogin.useLdapAuthentication();
		_useLdapSSL = LdapLogin.useLdapSSL();
	}

	public String logout() {
		BeanUtil.invalidateSession();
		LogSISA.log.info(" LOGOUT ");

		return WebConstants.PAGE_LOGIN;
	}

	public String validateLogin() {
		LogSISA.log.info("validateLogin ");
		String pageNav = WebConstants.PAGE_LOGIN;
		User user = null;
		try {
			user = _userDelegate.login(_email, _pwd);
		} catch (UserException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOGIN, "msg",FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
		if (user != null) {
			UserAccessUtils.getInstance().removeUserRolePagesFromSession();
			BeanUtil.invalidateSession();
			UserAccessUtils.getInstance().putUserInSession(user);
			pageNav = MsgConstants.SELECT_ROLE_PAGE;
		} else {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOGIN, "msg",FacesMessage.SEVERITY_ERROR);
		}
		LogSISA.log.info("validateLogin " + pageNav);
		return pageNav;
	}

	public String recoveryPassword() {
		return MsgConstants.RECOVERY_PASSWORD_PAGE;
	}
	
	public String recoveryPasswordLdap() {
		BeanUtil.setMessageGeneral(MsgConstants.MSG_INFO_LDAP_PASSWORDS, "msg", FacesMessage.SEVERITY_WARN);
		return WebConstants.PAGE_LOGIN;
	}

	public String sendPassword() {
		String ret = "";
		if (_email != null && !_email.isEmpty()) {
			User user;
			try {
				user = _userDelegate.getUserByEmail(_email);
				if (user == null) {
					BeanUtil.setMessageGeneral(
							MsgConstants.MSG_ERRO_EMAIL_NOT_EXIST,
							BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
				} else {
					_userDelegate.sendPassword(user);
					BeanUtil.setMessageGeneral(
							MsgConstants.MSG_SUCCESS_SEND_PWD, BeanUtil.ID_MSG,
							FacesMessage.SEVERITY_INFO);
					ret = WebConstants.PAGE_LOGIN;
				}
			} catch (UserException e) {
				e.printStackTrace();
				LogSISA.log.error(e.getMessage());
				BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SEND_PASSWORD,
						BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			}
		}
		return ret;
	}
	
	public void loadLoggedUser(){
		_loggedUser = UserAccessUtils.getInstance().getUserInSession();
		
		if (_loggedUser == null) {
			return;
		}
		try {
			Date date = DateUtil.getNow();
			_rolesList = _userRoleDelegate.listRolesByUser(_loggedUser, date);
		} catch (UserRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ROLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();	
		}
	}
	
	public String enter(){
		String ret = MsgConstants.SELECT_ROLE_PAGE;
		if (_role == null) {
			String message = MsgConstants.MSG_ERROR_USER_HAS_NO_ROLE;
			BeanUtil.setMessageGeneral(message, "msg", FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(message);
			return ret;
		} else {
			UserAccessUtils.getInstance().putRoleInSession(_role);
			UserAccessUtils.getInstance().loadAndSetPageRoles(_role);
			return MsgConstants.HOME_PAGE;
		}
	}
	
	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getPwd() {
		return _pwd;
	}

	public void setPwd(String pwd) {
		_pwd = pwd;
	}

	public User getLoggedUser() {
		return _loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		_loggedUser = loggedUser;
	}

	public List<Role> getRolesList() {
		return _rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		_rolesList = rolesList;
	}

	public Role getRole() {
		return _role;
	}

	public void setRole(Role role) {
		_role = role;
	}

	public boolean isUseLdapAuthentication() {
		return _useLdapAuthentication;
	}

	public void setUseLdapAuthentication(boolean useLdapAuthentication) {
		_useLdapAuthentication = useLdapAuthentication;
	}

	public boolean isUseLdapSSL() {
		return _useLdapSSL;
	}

	public void setUseLdapSSL(boolean useLdapSSL) {
		_useLdapSSL = useLdapSSL;
	}
}