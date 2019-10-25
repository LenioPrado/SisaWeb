/*
 * File:	     RoleDelegate.java
 * Creation date: 09/07/2015
 * Role:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.RoleException;
import br.edu.pcs.ifsulmg.sisa.vo.Role;

public class RoleDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8775713493724229821L;

	public int insert(Role role) throws RoleException, EntityConstraintViolationException {
		int id = getRoleDAO().insert(role);
		return id;
	}

	public void update(Role role) throws RoleException, EntityConstraintViolationException {
		getRoleDAO().update(role);
	}

	public List<Role> list() throws RoleException {
		List<Role> list = getRoleDAO().list();
		return list;
	}

	public void delete(Role role) throws RoleException, EntityConstraintViolationException {
		getRoleDAO().delete(role.getRoleId());
	}
}