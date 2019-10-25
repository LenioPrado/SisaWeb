/*
 * File:	     BibliographyDelegate.java
 * Creation date: 08/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.BibliographyException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;

public class BibliographyDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4364874233195299015L;

	public int insert(Bibliography bibliography) throws BibliographyException, EntityConstraintViolationException {
		int id = getBibliographyDAO().insert(bibliography);
		return id;
	}

	public void update(Bibliography bibliography) throws BibliographyException, EntityConstraintViolationException {
		getBibliographyDAO().update(bibliography);
	}

	public List<Bibliography> list() throws BibliographyException {
		List<Bibliography> list = getBibliographyDAO().list();
		return list;
	}

	public void delete(Bibliography bibliography) throws BibliographyException, EntityConstraintViolationException {
		getBibliographyDAO().delete(bibliography.getBibliographyId());
	}

	public List<Bibliography> listByDiscipline(int disciplineId) throws BibliographyException {
		List<Bibliography> list = getBibliographyDAO().listByDiscipline(disciplineId);
		return list;
	}
}