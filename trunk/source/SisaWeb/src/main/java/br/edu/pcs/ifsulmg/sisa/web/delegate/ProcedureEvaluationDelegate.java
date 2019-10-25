/*
 * File:	     ProcedureEvaluationDelegate.java
 * Creation date: 27/08/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.ProcedureEvaluationException;
import br.edu.pcs.ifsulmg.sisa.vo.ProcedureEvaluation;

public class ProcedureEvaluationDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5879395276187581860L;

	public int insert(ProcedureEvaluation procedureEvaluation) throws ProcedureEvaluationException, EntityConstraintViolationException {
		int id = getProcedureEvaluationDAO().insert(procedureEvaluation);
		return id;
	}

	public void update(ProcedureEvaluation procedureEvaluation) throws ProcedureEvaluationException, EntityConstraintViolationException {
		getProcedureEvaluationDAO().update(procedureEvaluation);
	}

	public List<ProcedureEvaluation> list() throws ProcedureEvaluationException {
		List<ProcedureEvaluation> list = getProcedureEvaluationDAO().list();
		return list;
	}

	public void delete(ProcedureEvaluation procedureEvaluation) throws ProcedureEvaluationException, EntityConstraintViolationException {
		getProcedureEvaluationDAO().delete(procedureEvaluation.getProcedureId());
	}

	public List<ProcedureEvaluation> listByTeachingPlan(int teachingPlanId) throws ProcedureEvaluationException {
		List<ProcedureEvaluation> list = getProcedureEvaluationDAO().listByTeachingPlan(teachingPlanId);
		return list;
	}
}