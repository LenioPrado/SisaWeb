/*
 * File:	     ResponsibleBean.java
 * Creation date: 19/06/2015
 * Author:        Lênio
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
import br.edu.pcs.ifsulmg.sisa.exceptions.ResponsibleException;
import br.edu.pcs.ifsulmg.sisa.exceptions.RoleException;
import br.edu.pcs.ifsulmg.sisa.exceptions.TeachingPlanException;
import br.edu.pcs.ifsulmg.sisa.exceptions.UserRoleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Responsible;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ResponsibleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.RoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.TeachingPlanDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.UserRoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class ResponsibleBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4306389957149272685L;
	
	@Inject
	private ResponsibleDelegate _responsibleDelegate;
	
	@Inject
	private UserRoleDelegate _userRoleDelegate;
	
	@Inject
	private RoleDelegate _roleDelegate;
	
	@Inject
	private TeachingPlanDelegate _teachingPlanDelegate;
	
	private UserRole _userRole;
	
	private List<UserRole> _usersRolesList;
	
	private List<Responsible> _responsiblesList;
	
	private List<User> _usersList;
	
	private List<Role> _rolesList;
	
	private List<TeachingPlan> _completeTeachingPlansList;
	
	private List<TeachingPlan> _teachingPlansList;
	
	private Boolean _editResponsible;
	
	private List<TeachingPlan> _selectedTeachingPlans;
	
	@PostConstruct
	public void init() {
		_userRole = new UserRole();
		_rolesList = new ArrayList<Role>();
		_usersList = new ArrayList<User>();
		
		_selectedTeachingPlans = new ArrayList<TeachingPlan>();
		loadResponsiblesList();
		loadAllTeachingPlansList();
	}	
	
	public void loadResponsiblesList() {
		loadUsersRolesList();
		try {
			_responsiblesList = _responsibleDelegate.list();			
			
			for (Responsible responsible : _responsiblesList) {
				UserRole userRole = responsible.getUserRole();
				TeachingPlan teachingPlan = responsible.getTeachingPlan();

				UserRole existentUserRole = _usersRolesList.get(_usersRolesList.indexOf(userRole));
				if(!existentUserRole.getTeachingPlans().contains(teachingPlan)){
					existentUserRole.getTeachingPlans().add(teachingPlan);
				}
			}
		} catch (ResponsibleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_RESPONSIBLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	public void loadUsersRolesList(){
		try {
			_usersRolesList = _userRoleDelegate.list();
			_rolesList = _roleDelegate.list();
			
			for (UserRole userRole : _usersRolesList) {
				User user = userRole.getUser();				

				if (!_usersList.contains(user)) {
					_usersList.add(user);					
				}
			}
		} catch (UserRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_USER_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		} catch (RoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ROLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	private void loadAllTeachingPlansList(){
		try {
			_completeTeachingPlansList = _teachingPlanDelegate.list();
		} catch (TeachingPlanException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_TEACHING_PLANS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	public void save() {
		try {
			_responsibleDelegate.insert(_userRole, _selectedTeachingPlans);
			
			loadResponsiblesList();
			RequestContext.getCurrentInstance().execute("PF('saveResponsibleDialog').hide()");
			RequestContext.getCurrentInstance().update("formListResponsibles");		
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_RESPONSIBLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (ResponsibleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_RESPONSIBLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}  catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadResponsible(UserRole userRole) {
		_userRole = userRole;
		_editResponsible = true;		
		
		clearTeachingPlansLists();
		loadUsers();
		loadTeachingPlansList();
	}
	
	private void loadTeachingPlansList(){
		_teachingPlansList = new ArrayList<TeachingPlan>(_completeTeachingPlansList);
		
		for (int i = 0; i < _responsiblesList.size(); i++) {
			Responsible current = _responsiblesList.get(i);
			if (current.getUserRole().equals(_userRole))
			{
				_teachingPlansList.remove(current.getTeachingPlan());
			}
		}
	}

	public void roleChange(){
		loadUsers();
	}
	
	private void loadUsers(){
		_usersList = new ArrayList<User>();
		for (UserRole current : _usersRolesList) {
			if (current.getRole().equals(_userRole.getRole()) && !_usersList.contains(current.getUser())) {
				_usersList.add(current.getUser());
			}
		}	
	}
	
	public void userChange(){
		loadTeachingPlansList();	
	}

	public void delete(UserRole userRole, TeachingPlan teachingPlan) {
		try {			
			_responsibleDelegate.delete(getResponsible(userRole, teachingPlan));
		
			clearTeachingPlansLists();
			loadResponsiblesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_RESPONSIBLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (ResponsibleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_RESPONSIBLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_RESPONSIBLE_VERIFICATION)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_RESPONSIBLE_VERIFICATION;
			} 
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Responsible getResponsible(UserRole userRole, TeachingPlan teachingPlan){
		Responsible responsible = new Responsible(userRole, teachingPlan); 
		return _responsiblesList.get(_responsiblesList.indexOf(responsible));
	}
	
	private void clearTeachingPlansLists() {
		_selectedTeachingPlans.clear();
	}

	public Boolean getEditResponsible() {
		return _editResponsible;
	}

	public void setEditResponsible(Boolean editResponsible) {
		_editResponsible = editResponsible;
	}

	public List<TeachingPlan> getSelectedTeachingPlans() {
		return _selectedTeachingPlans;
	}

	public void setSelectedTeachingPlans(List<TeachingPlan> selectedTeachingPlans) {
		_selectedTeachingPlans = selectedTeachingPlans;
	}

	public UserRole getUserRole() {
		return _userRole;
	}

	public void setUserRole(UserRole userRole) {
		_userRole = userRole;
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

	public List<UserRole> getUsersRolesList() {
		return _usersRolesList;
	}

	public void setUsersRolesList(List<UserRole> usersRolesList) {
		_usersRolesList = usersRolesList;
	}

	public List<Responsible> getResponsiblesList() {
		return _responsiblesList;
	}

	public void setResponsiblesList(List<Responsible> responsiblesList) {
		_responsiblesList = responsiblesList;
	}

	public List<TeachingPlan> getTeachingPlansList() {
		return _teachingPlansList;
	}

	public void setTeachingPlansList(List<TeachingPlan> teachingPlansList) {
		_teachingPlansList = teachingPlansList;
	}
}