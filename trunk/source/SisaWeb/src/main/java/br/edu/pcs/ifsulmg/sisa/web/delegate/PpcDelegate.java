/*
 * File:	     PpcDelegate.java
 * Creation date: 19/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PpcException;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;

public class PpcDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5141395276187581860L;

	public int insert(Ppc ppc) throws PpcException, EntityConstraintViolationException {
		int id = getPpcDAO().insert(ppc);
		return id;
	}
	
	public void update(Ppc ppc) throws PpcException, EntityConstraintViolationException {
		getPpcDAO().update(ppc);
	}
	
	public List<Ppc> list() throws PpcException {
		List<Ppc> list = getPpcDAO().list();
		return list;
	}
	
	public List<Ppc> listByCourse(int courseId) throws PpcException {
		List<Ppc> list = getPpcDAO().listByCourse(courseId);
		return list;
	}

	public void delete(Ppc ppc) throws PpcException, EntityConstraintViolationException {
			getPpcDAO().delete(ppc.getPpcId());
	}
}
