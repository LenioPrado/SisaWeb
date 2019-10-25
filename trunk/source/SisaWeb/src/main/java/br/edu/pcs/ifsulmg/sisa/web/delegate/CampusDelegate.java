/*
 * File:	     CampusDelegate.java
 * Creation date: 06/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.CampusException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Campus;

public class CampusDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3294577031667579553L;

	public int insert(Campus campus) throws CampusException, EntityConstraintViolationException {
		int id = getCampusDAO().insert(campus);
		return id;
	}

	public void update(Campus campus) throws CampusException, EntityConstraintViolationException {
		getCampusDAO().update(campus);
	}

	public List<Campus> list() throws CampusException {
		List<Campus> list = getCampusDAO().list();
		return list;
	}

	public void delete(Campus campus) throws CampusException, EntityConstraintViolationException {
		getCampusDAO().delete(campus.getCampusId());
	}
}