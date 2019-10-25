/*
 * File:	     ProcedureEvaluation.java
 * Creation date: 12/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.Date;

import br.edu.pcs.ifsulmg.sisa.util.DateUtil;

/**
 * The Class ProcedureEvaluation.
 */
public class ProcedureEvaluation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 916297593538621669L;
	
	/**
	 * Instantiates a new procedure evaluation.
	 */
	public ProcedureEvaluation(){
		
	}
	
	public String getDateLabel(){
		return DateUtil.createDateFormatyyyyMMdd(_date);
	}
	
	/**
	 * Instantiates a new procedure evaluation.
	 *
	 * @param procedureId the procedure id
	 * @param date the date
	 * @param observation the observation
	 * @param teachingPlan the teaching plan
	 * @param evaluation the evaluation
	 */
	public ProcedureEvaluation(int procedureId, Date date, String observation,
			TeachingPlan teachingPlan, Evaluation evaluation ){
		_procedureId = procedureId;
		_date = date;
		_observation = observation;
		_teachingPlan = teachingPlan;
		_evaluation = evaluation;
	}
	
	/** The _procedure id. */
	private int _procedureId;
	
	/** The _date. */
	private Date _date;
	
	/** The _observation. */
	private String _observation;
	
	/** The _teaching plan. */
	private TeachingPlan _teachingPlan = new TeachingPlan();	
	
	/** The _evaluation. */
	private Evaluation _evaluation = new Evaluation();
	
	public String getDateTitle() {
		return DateUtil.createDateFormatyyyyMMdd(_date);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _procedureId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcedureEvaluation other = (ProcedureEvaluation) obj;
		if (_procedureId != other._procedureId)
			return false;
		return true;
	}
	
	/**
	 * Gets the procedure id.
	 *
	 * @return the procedure id
	 */
	public int getProcedureId() {
		return _procedureId;
	}

	/**
	 * Sets the procedure id.
	 *
	 * @param procedureId the new procedure id
	 */
	public void setProcedureId(int procedureId) {
		_procedureId = procedureId;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return _date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		_date = date;
	}

	/**
	 * Gets the observation.
	 *
	 * @return the observation
	 */
	public String getObservation() {
		return _observation;
	}

	/**
	 * Sets the observation.
	 *
	 * @param observation the new observation
	 */
	public void setObservation(String observation) {
		_observation = observation;
	}

	/**
	 * Gets the teaching plan.
	 *
	 * @return the teaching plan
	 */
	public TeachingPlan getTeachingPlan() {
		return _teachingPlan;
	}

	/**
	 * Sets the teaching plan.
	 *
	 * @param teachingPlan the new teaching plan
	 */
	public void setTeachingPlan(TeachingPlan teachingPlan) {
		_teachingPlan = teachingPlan;
	}

	/**
	 * Gets the evaluation.
	 *
	 * @return the evaluation
	 */
	public Evaluation getEvaluation() {
		return _evaluation;
	}

	/**
	 * Sets the evaluation.
	 *
	 * @param evaluation the new evaluation
	 */
	public void setEvaluation(Evaluation evaluation) {
		_evaluation = evaluation;
	}
}