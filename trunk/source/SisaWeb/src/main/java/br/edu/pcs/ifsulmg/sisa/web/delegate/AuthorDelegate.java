/*
 * File:	     AuthorDelegate.java
 * Creation date: 08/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.AuthorException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Author;

public class AuthorDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975713493724229821L;

	public int insert(Author author) throws AuthorException, EntityConstraintViolationException {
		int id = getAuthorDAO().insert(author);
		return id;
	}

	public void update(Author author) throws AuthorException, EntityConstraintViolationException {
		getAuthorDAO().update(author);
	}

	public List<Author> list() throws AuthorException {
		List<Author> list = getAuthorDAO().list();
		return list;
	}

	public void delete(Author author) throws AuthorException, EntityConstraintViolationException {
		getAuthorDAO().delete(author.getAuthorId());
	}
}