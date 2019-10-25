/*
 * File:	     PageDelegate.java
 * Creation date: 30/03/2016
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.PageException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Page;

public class PageDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975709823724229821L;

	public int insert(Page page) throws PageException, EntityConstraintViolationException {
		int id = getPageDAO().insert(page);
		return id;
	}

	public void update(Page page) throws PageException, EntityConstraintViolationException {
		getPageDAO().update(page);
	}

	public List<Page> list() throws PageException {
		List<Page> list = getPageDAO().list();
		return list;
	}

	public void delete(Page page) throws PageException, EntityConstraintViolationException {
		getPageDAO().delete(page.getPageId());
	}
}