/*
 * File:	     PageRole.java
 * Creation date: 30/03/2016
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class PageRole.
 */
public class PageRole implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7103729571230764935L;

	/**
	 * Instantiates a new user role.
	 */
	public PageRole(){
		
	}

	/**
	 * Instantiates a new user role.
	 *
	 * @param page the page
	 * @param role the role
	 * @param action the action
	 */
	public PageRole(Page page, Role role, Action action){
		_page = page;
		_role = role;
		_action = action;		
	}
	
	/** The _role. */
	private Role _role = new Role();
	
	/** The _page. */
	private Page _page = new Page();
	
	/** The _action. */
	private Action _action = new Action();

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return _role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		_role = role;
	}

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public Page getPage() {
		return _page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(Page page) {
		_page = page;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public Action getAction() {
		return _action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(Action action) {
		_action = action;
	}	
}