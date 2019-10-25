/*
 * File:	     Verification.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */

package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import br.edu.pcs.ifsulmg.sisa.util.DateUtil;

/**
 * The Class Verification.
 */
public class Verification implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2924464962125795792L;

	/**
	 * Instantiates a new verification.
	 */
	public Verification() {

	}

	/**
	 * Instantiates a new verification.
	 *
	 * @param verificationId the verification id
	 * @param creationDate the creation date
	 * @param evaluationDate the evaluation date
	 * @param observation the observation
	 * @param status the status
	 * @param responsible the responsible
	 */
	public Verification(int verificationId, Timestamp creationDate, Timestamp evaluationDate, 
			String observation, int status, Responsible responsible) {
		_verificationId = verificationId;
		_creationDate = creationDate;
		_evaluationDate = evaluationDate;
		_observation = observation;
		_status = status;
		_responsible = responsible;
	}

	/** The _verification id. */
	private int _verificationId;

	/** The _observation. */
	private String _observation;

	/** The _status. */
	private int _status;

	/** The _creation date. */
	private Timestamp _creationDate;
	
	/** The _evaluation date. */
	private Timestamp _evaluationDate;
	
	/** The _responsible. */
	private Responsible _responsible = new Responsible();
	
	/**
	 * Gets the creation date title.
	 *
	 * @return the creation date title
	 */
	public String getCreationDateTitle() {
		return _creationDate == null ? "" : DateUtil.createDateFormatFull(_creationDate);
	}
	
	/**
	 * Gets the evaluation date title.
	 *
	 * @return the evaluation date title
	 */
	public String getEvaluationDateTitle() {
		return _evaluationDate == null ? "" : DateUtil.createDateFormatFull(_evaluationDate);
	}

	/**
	 * Gets the verification id.
	 *
	 * @return the verification id
	 */
	public int getVerificationId() {
		return _verificationId;
	}

	/**
	 * Sets the verification id.
	 *
	 * @param verificationId the new verification id
	 */
	public void setVerificationId(int verificationId) {
		_verificationId = verificationId;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return _status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		_status = status;
	}

	/**
	 * Gets the responsible.
	 *
	 * @return the responsible
	 */
	public Responsible getResponsible() {
		return _responsible;
	}

	/**
	 * Sets the responsible.
	 *
	 * @param responsible the new responsible
	 */
	public void setResponsible(Responsible responsible) {
		_responsible = responsible;
	}

	public Timestamp getCreationDate() {
		return _creationDate;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param creationDate the new creation date
	 */
	public void setCreationDate(Timestamp creationDate) {
		_creationDate = creationDate;
	}

	/**
	 * Gets the evaluation date.
	 *
	 * @return the evaluation date
	 */
	public Timestamp getEvaluationDate() {
		return _evaluationDate;
	}

	/**
	 * Sets the evaluation date.
	 *
	 * @param evaluationDate the new evaluation date
	 */
	public void setEvaluationDate(Timestamp evaluationDate) {
		_evaluationDate = evaluationDate;
	}
}