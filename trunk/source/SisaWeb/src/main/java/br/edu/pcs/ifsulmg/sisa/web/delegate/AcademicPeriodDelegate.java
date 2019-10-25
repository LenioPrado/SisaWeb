/*
 * File:	     AcademicPeriodDelegate.java
 * Creation date: 09/07/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.AcademicPeriodException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;


public class AcademicPeriodDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975713493724229821L;

	public int insert(AcademicPeriod academicPeriod) throws AcademicPeriodException, EntityConstraintViolationException {
		int id = getAcademicPeriodDAO().insert(academicPeriod);
		return id;
	}

	public void update(AcademicPeriod academicPeriod) throws AcademicPeriodException, EntityConstraintViolationException {
		getAcademicPeriodDAO().update(academicPeriod);
	}

	public List<AcademicPeriod> list() throws AcademicPeriodException {
		List<AcademicPeriod> list = getAcademicPeriodDAO().list();
		return list;
	}

	public void delete(AcademicPeriod academicPeriod) throws AcademicPeriodException, EntityConstraintViolationException {
		getAcademicPeriodDAO().delete(academicPeriod.getAcademicPeriodId());
	}
}