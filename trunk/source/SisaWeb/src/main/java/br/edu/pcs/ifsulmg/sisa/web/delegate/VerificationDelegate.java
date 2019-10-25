/*
 * File:	     VerificationDelegate.java
 * Creation date: 28/08/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.VerificationException;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.Verification;

public class VerificationDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5141395276187581860L;

	public int insert(Verification verification) throws VerificationException, EntityConstraintViolationException {
		int id = getVerificationDAO().insert(verification);
		return id;
	}

	public void update(Verification verification) throws VerificationException, EntityConstraintViolationException {
		getVerificationDAO().update(verification);
	}

	public List<Verification> list() throws VerificationException {
		List<Verification> list = getVerificationDAO().list();
		return list;
	}

	public void delete(Verification verification) throws VerificationException, EntityConstraintViolationException {
		getVerificationDAO().delete(verification.getVerificationId());
	}
	
	public List<Verification> listByTeachingPlan(TeachingPlan teachingPlan) throws VerificationException {
		return getVerificationDAO().listByTeachingPlan(teachingPlan);
	}

	public List<Verification> listByTeachingPlans(List<TeachingPlan> teachingPlans) throws VerificationException {
		return getVerificationDAO().listByTeachingPlans(teachingPlans);
	}
	
	public List<Verification> listByUserRole(User user, Role role) throws VerificationException {
		return getVerificationDAO().listByUserRole(user, role);
	}
}
