/*
 * File:	     TeachingPlanDelegate.java
 * Creation date: 09/07/2015
 * Author:        Fabrício
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.TeachingPlanException;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.User;

public class TeachingPlanDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3897592387492874329L;

	public int insert(TeachingPlan teachingPlan) throws TeachingPlanException, EntityConstraintViolationException {
		int id = getTeachingPlanDAO().insert(teachingPlan);
		return id;
	}

	public void update(TeachingPlan teachingPlan) throws TeachingPlanException, EntityConstraintViolationException {
		getTeachingPlanDAO().update(teachingPlan);
	}

	public List<TeachingPlan> list() throws TeachingPlanException {
		List<TeachingPlan> list = getTeachingPlanDAO().list();
		return list;
	}
	
	public List<TeachingPlan> listByDiscipline(int disciplineId) throws TeachingPlanException {
		List<TeachingPlan> list = getTeachingPlanDAO().listByDiscipline(disciplineId);
		return list;
	}
	
	public List<TeachingPlan> listByTeacher(User user) throws TeachingPlanException {
		List<TeachingPlan> list = getTeachingPlanDAO().listByTeacher(user);
		return list;
	}
	
	public TeachingPlan getById(int courseId, int ppcId, int disciplineId, int teachingPlanId) 
			throws TeachingPlanException {
		TeachingPlan teachingPlan = getTeachingPlanDAO().
				getFiltered(courseId, ppcId, disciplineId, teachingPlanId);
		return teachingPlan;
	}
	
	public TeachingPlan getByTeachingPlanId(int teachingPlanId) throws TeachingPlanException {
		TeachingPlan teachingPlan = getTeachingPlanDAO().getById(teachingPlanId);
		return teachingPlan;
	}
	
	public void delete(TeachingPlan teachingPlan) throws TeachingPlanException, EntityConstraintViolationException {
		getTeachingPlanDAO().delete(teachingPlan.getTeachingPlanId());
	}
}