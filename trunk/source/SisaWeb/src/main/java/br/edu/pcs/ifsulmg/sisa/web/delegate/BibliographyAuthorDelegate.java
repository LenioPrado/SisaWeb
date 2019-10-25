/*
 * File:	     BibliographyAuthorDelegate.java
 * Creation date: 12/08/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.BibliographyAuthorException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Author;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.BibliographyAuthor;

public class BibliographyAuthorDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -931847540021335093L;

	public void insert(Author author, List<Bibliography> bibliographies) throws BibliographyAuthorException, EntityConstraintViolationException {
		for (Bibliography bibliography : bibliographies) {
			getBibliographyAuthorDAO().insert(new BibliographyAuthor(author, bibliography));
		}				
	}

	public List<BibliographyAuthor> list() throws BibliographyAuthorException {
		List<BibliographyAuthor> list = getBibliographyAuthorDAO().list();
		return list;
	}
	
	public void delete(BibliographyAuthor bibliographyAuthor) throws BibliographyAuthorException {
		getBibliographyAuthorDAO().delete(bibliographyAuthor);
	}

	public void delete(Author author, Bibliography bibliography) throws BibliographyAuthorException {
		delete(new BibliographyAuthor(author, bibliography));
	}
	
	public void delete(Author author, List<Bibliography> bibliographies) throws BibliographyAuthorException {
		for (Bibliography bibliography : bibliographies) {
			delete(author, bibliography);
		}
	}
}