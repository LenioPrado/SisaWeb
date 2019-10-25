/*
 * File:	     PageRoleBean.java
 * Creation date: 30/03/2016
 * Author:        Rarvs
 */

package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.ActionException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PageException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PageRoleException;
import br.edu.pcs.ifsulmg.sisa.exceptions.RoleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Action;
import br.edu.pcs.ifsulmg.sisa.vo.Page;
import br.edu.pcs.ifsulmg.sisa.vo.PageRole;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ActionDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PageDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PageRoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.RoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.ColumnModel;
import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.UserAccessUtils;

@Named
@ViewScoped
public class PageRoleBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192709236825886839L;
	
	@Inject
	private PageRoleDelegate _pageRoleDelegate;
	
	@Inject
	private PageDelegate _pageDelegate;
	
	@Inject
	private RoleDelegate _roleDelegate;
	
	@Inject
	private ActionDelegate _actionDelegate;
	
	private List<Action> _actions;
	
	private List<PageRole> _pageRolesList;
	
	private List<Page> _pages;

	private List<Role> _roles;	
	
	private List<ColumnModel> _actionColumns;
	
	private Role _role;
	
	private boolean _editPageRole;
	
	private Map<Page, List<Action>> _currentPagesActions;
	
	@PostConstruct
	public void init() {
		_currentPagesActions = new HashMap<Page, List<Action>>();
		loadPageRolesList();
		loadEntities();
	}	

	private void loadPageRolesList() {
		try {
			_roles = _roleDelegate.list();			
			_pages = _pageDelegate.list();
			_actions = _actionDelegate.list();					
			_actionColumns = new ArrayList<ColumnModel>();		
			
		} catch (PageException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PAGE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (ActionException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ACTIONS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (RoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_ROLES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void loadEntities(){			
		_currentPagesActions.clear();
		_actionColumns.clear();
		
		for (Page page : _pages) {
			for (Action action : _actions) {
				Action newAction = new Action(action.getActionId(), "");
				if (!page.getActions().contains(newAction)) {
					page.getActions().add(newAction);
				}				
			}
		}

		for (Action action : _actions) {
			_actionColumns.add(new ColumnModel(action.getDescription(), MessagesUtil.getValue("page_role_action_action_field_name")));
		}
		
		if (_roles.size() > 0) {
			_role = _roles.get(0);
			roleChanged();
		}
	}
	
	public void save() {
		try {
			for (Map.Entry<Page, List<Action>> toDelete : _currentPagesActions.entrySet()) {
				Page page = toDelete.getKey();
				_pageRoleDelegate.delete(_role, page, toDelete.getValue());
			}
			
			for (Map.Entry<Page, List<Action>> toInsert : getPagesActionsToSave().entrySet()) {
				Page page = toInsert.getKey();
				_pageRoleDelegate.insert(_role, page, toInsert.getValue());
			}
			
			Role role = UserAccessUtils.getInstance().getRoleInSession();
			UserAccessUtils.getInstance().loadAndSetPageRoles(role);
				
			loadPageRolesList();
			loadEntities();
			RequestContext.getCurrentInstance().update("formListPageRole");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_PAGE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} 
		catch (PageRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_PAGE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_PAGE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
			
	private Map<Page, List<Action>> getPagesActionsToSave(){
		Map<Page, List<Action>> toSave = new HashMap<Page, List<Action>>();
		
		for (Page page : _pages) {
			for (Action action : page.getActions()) {
				if (action.getIsChecked()) {
					if (!toSave.containsKey(page)) {
						toSave.put(page, new ArrayList<Action>());
					}
					toSave.get(page).add(action);
				}
			}
		}
		return toSave;
	}
	
	public void roleChanged(){
		for (Page page : _pages) {
			for (Action action : page.getActions()) {
				action.setIsChecked(false);
			}
		}
		
		try {
			_pageRolesList = _pageRoleDelegate.listByRole(_role);
		} catch (PageRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PAGE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
		
		for (PageRole pageRole : _pageRolesList) {
			Page page = pageRole.getPage();
			Action action = pageRole.getAction();

			Page newPage = _pages.get(_pages.indexOf(page));
			List<Action> actions = newPage.getActions();
			Action newAction = actions.get(actions.indexOf(action));
			newAction.setIsChecked(true);
			newAction.setDescription("Marcado");

			if (!_currentPagesActions.containsKey(newPage)) {
				_currentPagesActions.put(newPage, new ArrayList<Action>());
			}
			_currentPagesActions.get(newPage).add(newAction);
		}
	}
		
	public void enablePageRoleEdition() {
		_editPageRole = true;
		BeanUtil.setMessageGeneral("Edição ON", BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
		System.out.println("Edição ON");
	}
	
	public void disablePageRoleEdition() {
		_editPageRole = false;
		BeanUtil.setMessageGeneral("Edição OFF", BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
		System.out.println("Edição OFF");
	}

	public List<PageRole> getPageRolesList() {
		return _pageRolesList;
	}

	public void setPageRolesList(List<PageRole> pageRolesList) {
		_pageRolesList = pageRolesList;
	}

	public PageRoleDelegate getPageRoleDelegate() {
		return _pageRoleDelegate;
	}

	public void setPageRoleDelegate(PageRoleDelegate pageRoleDelegate) {
		_pageRoleDelegate = pageRoleDelegate;
	}

	public boolean getEditPageRole() {
		return _editPageRole;
	}

	public void setEditPageRole(boolean editPageRole) {
		_editPageRole = editPageRole;
	}

	public List<Role> getRoles() {
		return _roles;
	}

	public void setRoles(List<Role> roles) {
		_roles = roles;
	}

	public List<ColumnModel> getActionColumns() {
		return _actionColumns;
	}

	public void setActionColumns(List<ColumnModel> actionColumns) {
		_actionColumns = actionColumns;
	}

	public List<Page> getPages() {
		return _pages;
	}

	public void setPages(List<Page> pages) {
		_pages = pages;
	}

	public Role getRole() {
		return _role;
	}

	public void setRole(Role role) {
		_role = role;
	}
}