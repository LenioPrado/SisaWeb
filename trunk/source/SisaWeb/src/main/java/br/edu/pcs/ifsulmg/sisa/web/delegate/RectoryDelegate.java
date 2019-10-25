/*
 * File:	     RectoryDelegate.java
 * Creation date: 06/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.RectoryException;
import br.edu.pcs.ifsulmg.sisa.vo.Rectory;

public class RectoryDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2677813160855841310L;

	public int insert(Rectory rectory) throws RectoryException, EntityConstraintViolationException {
		int id = getRectoryDAO().insert(rectory);
		return id;
	}

	public void update(Rectory rectory) throws RectoryException, EntityConstraintViolationException {
		getRectoryDAO().update(rectory);
	}

	public List<Rectory> list() throws RectoryException {
		List<Rectory> list = getRectoryDAO().list();
		return list;
	}

	public void delete(Rectory rectory) throws RectoryException, EntityConstraintViolationException {
		getRectoryDAO().delete(rectory.getRectoryId());
	}
}