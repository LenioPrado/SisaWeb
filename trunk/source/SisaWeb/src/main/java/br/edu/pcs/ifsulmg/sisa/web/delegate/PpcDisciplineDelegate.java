/*
 * File:	     PpcDisciplineDelegate.java
 * Creation date: 27/08/2015
 * PpcDiscipline:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PpcDisciplineException;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;


public class PpcDisciplineDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975713493724229821L;

	public void insert(List<PpcDiscipline> ppcsDisciplines) throws PpcDisciplineException, EntityConstraintViolationException {
		for (PpcDiscipline ppcDisciplineToInsert : ppcsDisciplines) {
			getPpcDisciplineDAO().insert(ppcDisciplineToInsert);
		}		
	}

	public List<PpcDiscipline> list() throws PpcDisciplineException {
		List<PpcDiscipline> list = getPpcDisciplineDAO().list();
		return list;
	}
	
	public PpcDiscipline getByPpcAndDiscipline(int ppcId, int disciplineId) throws PpcDisciplineException {
		PpcDiscipline entity = getPpcDisciplineDAO().getByPpcAndDiscipline(ppcId,disciplineId);
		return entity;
	}
	
	public void delete(PpcDiscipline ppcDiscipline) throws PpcDisciplineException, EntityConstraintViolationException {
		getPpcDisciplineDAO().delete(ppcDiscipline.getPpcDisciplineId());
	}
	
	public void delete(List<PpcDiscipline> ppcDisciplines) throws PpcDisciplineException, EntityConstraintViolationException {
		for (PpcDiscipline ppcDisciplineToDelete : ppcDisciplines) {
			delete(ppcDisciplineToDelete);
		}		
	}
}