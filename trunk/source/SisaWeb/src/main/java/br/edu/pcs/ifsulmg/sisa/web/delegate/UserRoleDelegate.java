/*
 * File:	     UserRoleDelegate.java
 * Creation date: 03/09/2015
 * UserRole:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.UserRoleException;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;


public class UserRoleDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975713493724229821L;

	public void insert(UserRole userRole) throws UserRoleException, EntityConstraintViolationException {
		getUserRoleDAO().insert(userRole);
	}

	public List<UserRole> list() throws UserRoleException {
		List<UserRole> list = getUserRoleDAO().list();
		return list;
	}
	
	public void update(UserRole userRole) throws UserRoleException, EntityConstraintViolationException {
		getUserRoleDAO().update(userRole);
	}

	public void delete(UserRole userRole) throws UserRoleException, EntityConstraintViolationException {
		getUserRoleDAO().delete(userRole.getUserRoleId());
	}

	public List<Role> listRolesByUser(User loggedUser, Date actualDate) throws UserRoleException {
		return getUserRoleDAO().listRolesByUser(loggedUser, actualDate);
	}
}