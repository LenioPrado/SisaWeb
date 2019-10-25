/*
 * File:	     DisciplineDelegate.java
 * Creation date: 06/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;

public class DisciplineDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4745791551534250410L;

	public int insert(Discipline discipline) throws DisciplineException, EntityConstraintViolationException {
		int id = getDisciplineDAO().insert(discipline);
		return id;
	}

	public void update(Discipline discipline) throws DisciplineException, EntityConstraintViolationException {
		getDisciplineDAO().update(discipline);
	}

	public List<Discipline> list() throws DisciplineException {
		List<Discipline> list = getDisciplineDAO().list();
		return list;
	}
	
	public List<Discipline> listByPpc(int ppcId) throws DisciplineException {
		List<Discipline> list = getDisciplineDAO().listByPpc(ppcId);
		return list;
	}

	public void delete(Discipline discipline) throws DisciplineException, EntityConstraintViolationException {
		getDisciplineDAO().delete(discipline.getDisciplineId());
	}
}