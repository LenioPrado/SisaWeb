/*
 * File:	     EvaluationDelegate.java
 * Creation date: 09/07/2015
 * Evaluation:        Amanda
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EvaluationException;
import br.edu.pcs.ifsulmg.sisa.vo.Evaluation;

public class EvaluationDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6119419611841191919L;

	public int insert(Evaluation evaluation) throws EvaluationException, EntityConstraintViolationException {
		int id = getEvaluationDAO().insert(evaluation);
		return id;
	}

	public void update(Evaluation evaluation) throws EvaluationException, EntityConstraintViolationException {
		getEvaluationDAO().update(evaluation);
	}

	public List<Evaluation> list() throws EvaluationException {
		List<Evaluation> list = getEvaluationDAO().list();
		return list;
	}

	public void delete(Evaluation evaluation) throws EvaluationException, EntityConstraintViolationException {
		getEvaluationDAO().delete(evaluation.getEvaluationId());
	}
}