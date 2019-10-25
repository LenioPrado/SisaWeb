/*
 * File:	     PageRoleDelegate.java
 * Creation date: 30/03/2016
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PageRoleException;
import br.edu.pcs.ifsulmg.sisa.vo.Action;
import br.edu.pcs.ifsulmg.sisa.vo.Page;
import br.edu.pcs.ifsulmg.sisa.vo.PageRole;
import br.edu.pcs.ifsulmg.sisa.vo.Role;


public class PageRoleDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975713427454229821L;

	public void insert(Role role, Page page, List<Action> actions) throws PageRoleException, EntityConstraintViolationException {
		for (Action action: actions) {
			getPageRoleDAO().insert(new PageRole(page, role, action));	
		}		
	}

	public List<PageRole> list() throws PageRoleException {
		List<PageRole> list = getPageRoleDAO().list();
		return list;
	}
	
	public List<PageRole> listByRole(Role role) throws PageRoleException {
		List<PageRole> list = getPageRoleDAO().listByRole(role);
		return list;
	}
	
	public void delete(PageRole pageRole) throws PageRoleException {
		getPageRoleDAO().delete(pageRole);
	}
	
	public void delete(Role role, Page page, List<Action> actions) throws PageRoleException {
		for (Action action : actions) {
			delete(new PageRole(page, role, action));
		}		
	}
}
