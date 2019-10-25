/*
 * File:	     ClassProgrammingDelegate.java
 * Creation date: 03/09/2015
 * Author:        Amanda
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.ClassProgrammingException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.ClassProgramming;

public class ClassProgrammingDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3294577031667579553L;

	public int insert(ClassProgramming classProgramming) throws ClassProgrammingException, EntityConstraintViolationException {
		int id = getClassProgrammingDAO().insert(classProgramming);
		return id;
	}

	public void update(ClassProgramming classProgramming) throws ClassProgrammingException, EntityConstraintViolationException {
		getClassProgrammingDAO().update(classProgramming);
	}

	public List<ClassProgramming> list() throws ClassProgrammingException {
		List<ClassProgramming> list = getClassProgrammingDAO().list();
		return list;
	}
	
	public List<ClassProgramming> listByTeachingPlan(int teachingPlanId) throws ClassProgrammingException {
		List<ClassProgramming> list = getClassProgrammingDAO().listByTeachingPlan(teachingPlanId);
		return list;
	}

	public void delete(ClassProgramming classProgramming) throws ClassProgrammingException, EntityConstraintViolationException {
		getClassProgrammingDAO().delete(classProgramming.getProgrammingId());
	}
}