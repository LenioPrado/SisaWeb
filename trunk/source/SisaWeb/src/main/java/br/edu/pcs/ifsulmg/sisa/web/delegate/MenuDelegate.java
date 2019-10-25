/*
 * File:	     MenuDelegate.java
 * Creation date: 13/04/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.MenuException;
import br.edu.pcs.ifsulmg.sisa.vo.Menu;

public class MenuDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1925307629175649371L;

	public int insert(Menu menu) throws MenuException, EntityConstraintViolationException {
		int id = getMenuDAO().insert(menu);
		return id;
	}

	public void update(Menu menu) throws MenuException, EntityConstraintViolationException {
		getMenuDAO().update(menu);
	}

	public List<Menu> list() throws MenuException {
		List<Menu> list = getMenuDAO().list();
		return list;
	}

	public void delete(Menu menu) throws MenuException, EntityConstraintViolationException {
		getMenuDAO().delete(menu.getMenuId());
	}
	
	public List<Menu> getParentMenusWithChildren() throws MenuException{
		return getMenuDAO().getParentMenusWithChildren();
	}
	
	public List<Menu> getParentMenusWithChildrenByRoleAndAction(int roleId, int actionId) throws MenuException{
		return getMenuDAO().getParentMenusWithChildrenByRoleAndAction(roleId, actionId);
	}
}