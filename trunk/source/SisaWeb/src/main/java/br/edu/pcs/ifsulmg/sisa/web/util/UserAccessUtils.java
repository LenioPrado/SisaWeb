/*
* File:	     UserAccessUtils.java
* Creation date: 15/06/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.pcs.ifsulmg.sisa.exceptions.PageRoleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Action;
import br.edu.pcs.ifsulmg.sisa.vo.Page;
import br.edu.pcs.ifsulmg.sisa.vo.PageRole;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PageRoleDelegate;
import br.edu.pcs.ifsulmg.sisa.web.enums.ActionEnum;

@ManagedBean
@Named
public class UserAccessUtils implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1029384756152846209L;

	private static UserAccessUtils _instance;
	
	@Inject
	private PageRoleDelegate _pageRoleDelegate;
	
	public UserAccessUtils(){
		//
	}

	public static UserAccessUtils getInstance(){
		if (_instance == null) {
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();	
			_instance = (UserAccessUtils) elContext.getELResolver().getValue(elContext, null, "userAccessUtils");
		}
		return _instance;
	}
	
	public boolean hasPageListAccess(String pageName){
		Page page = getPageWithActions(pageName);
		
		if (page != null) {
			for (Action action : page.getActions()) {
				if (action.getActionId() == ActionEnum.LIST.getIndex()) {
					return true;
				}
			}
		} else {
			System.out.println(String.format("Página: %s - %s", pageName, 
					MessagesUtil.getValue("lbl_user_has_not_access")));		
		}
		return false;
	}
	
	private Page getPageWithActions(String pageName){
		pageName = pageName.substring(pageName.lastIndexOf("/") + 1, pageName.length());
		
		@SuppressWarnings("unchecked")
		List<Page> pagesList = (List<Page>)BeanUtil.getParamInSession(WebConstants.PARAM_USER_ROLE_PAGES);
		for (Page page : pagesList) {
			if (page.getUrl().toLowerCase().equals(pageName.toLowerCase())) {
				return page;
			}
		}
		return null;
	}
	
	public void loadAndSetPageRoles(Role role) {

		List<PageRole> pageRolesList = loadPagesRoles(role);
		
		List<Page> pages = new ArrayList<Page>();
		
		for (PageRole pageRole : pageRolesList) {
			Action action = pageRole.getAction();
			
			if (!pages.contains(pageRole.getPage())) {
				pages.add(pageRole.getPage());
			}
			Page page = pages.get(pages.indexOf(pageRole.getPage()));
			if (!page.getActions().contains(action)) {
				page.getActions().add(action);
			}			
		}
		BeanUtil.putSession(WebConstants.PARAM_USER_ROLE_PAGES, pages);
	}
	
	private List<PageRole> loadPagesRoles(Role role){
		List<PageRole> pageRolesList = new ArrayList<PageRole>();
		try {
			pageRolesList = _pageRoleDelegate.listByRole(role);
		} catch (PageRoleException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PAGE_ROLE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return pageRolesList;
	}

	public void removeUserRolePagesFromSession() {
		BeanUtil.removeFromSession(WebConstants.PARAM_USER_ROLE_PAGES);
	}
	
	public Page getCurrentPageFromSession() {
		return (Page)BeanUtil.getParamInSession(WebConstants.CURRENT_PAGE);
	}
	
	public void setCurrentPageInSession(String pageName){
		Page current = getPageWithActions(pageName);
		BeanUtil.putSession(WebConstants.CURRENT_PAGE, current);		
	}

	public User getUserInSession() {
		User u = (User) BeanUtil.getParamInSession(WebConstants.PARAM_USER);
		return u;
	}

	public void putUserInSession(User u) {
		BeanUtil.putSession(WebConstants.PARAM_USER, u);
	}

	public Role getRoleInSession() {
		Role role = (Role)BeanUtil.getParamInSession(WebConstants.PARAM_ROLE);
		return role;
	}

	public void putRoleInSession(Role role) {
		BeanUtil.putSession(WebConstants.PARAM_ROLE, role);
	}
}
