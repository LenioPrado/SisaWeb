/*
 * File:	     DisciplineBibliographyDelegate.java
 * Creation date: 09/09/2015
 * Author:        Amanda
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineBibliographyException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.DisciplineBibliography;

public class DisciplineBibliographyDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3294577031667579553L;

	public void insert(Discipline discipline, List<Bibliography> bibliographies) throws DisciplineBibliographyException, EntityConstraintViolationException {
		for (Bibliography bibliographyToInsert : bibliographies) {
			getDisciplineBibliographyDAO().insert(
					new DisciplineBibliography(bibliographyToInsert, discipline, bibliographyToInsert.isBasicView()));
		}		
	}

	public List<DisciplineBibliography> list() throws DisciplineBibliographyException {
		List<DisciplineBibliography> list = getDisciplineBibliographyDAO().list();
		return list;
	}
	
	public List<DisciplineBibliography> listByDiscipline(int disciplineId) throws DisciplineBibliographyException {
		List<DisciplineBibliography> list = getDisciplineBibliographyDAO().listByDiscipline(disciplineId);
		return list;
	}
	
	public void delete(DisciplineBibliography disciplineBibliography) throws DisciplineBibliographyException {
		getDisciplineBibliographyDAO().delete(disciplineBibliography);
	}

	public void delete(Discipline discipline, Bibliography bibliography) throws DisciplineBibliographyException {
		delete(new DisciplineBibliography(bibliography, discipline, true));
	}
	
	public void delete(Discipline discipline, List<Bibliography> bibliographies) throws DisciplineBibliographyException {
		for (Bibliography bibliographyToDelete : bibliographies) {
			delete(new DisciplineBibliography(bibliographyToDelete, discipline, true));
		}		
	}
}
