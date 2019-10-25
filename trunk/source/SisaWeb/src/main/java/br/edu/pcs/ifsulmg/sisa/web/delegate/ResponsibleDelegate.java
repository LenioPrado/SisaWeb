/*
 * File:	     ResponsibleDelegate.java
 * Creation date: 03/09/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.ResponsibleException;
import br.edu.pcs.ifsulmg.sisa.vo.Responsible;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;

public class ResponsibleDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5141399276187581860L;

	public void insert(UserRole userRole, List<TeachingPlan> teachingPlans) throws ResponsibleException, EntityConstraintViolationException {
		for (TeachingPlan teachingPlan : teachingPlans) {
			getResponsibleDAO().insert(new Responsible(userRole, teachingPlan));
		}		
	}
	
	public List<Responsible> list() throws ResponsibleException {
		List<Responsible> list = getResponsibleDAO().list();
		return list;
	}
	
	public List<Responsible> listByTeachingPlan(int teachingPlanId) throws ResponsibleException {
		List<Responsible> list = getResponsibleDAO().listByTeachingPlan(teachingPlanId);
		return list;
	}

	public void delete(Responsible responsible) throws ResponsibleException, EntityConstraintViolationException {
		getResponsibleDAO().delete(responsible.getResponsibleId());
	}
	
	public void delete(UserRole userRole, List<TeachingPlan> teachingPlans) throws ResponsibleException, EntityConstraintViolationException {
		for (TeachingPlan teachingPlanToDelete : teachingPlans) {
			delete(new Responsible(userRole, teachingPlanToDelete));	
		}		
	}
}
