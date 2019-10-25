/*
 * File:	     Evaluation.java
 * Creation date: 11/06/2015
 * Author:        Rarvs
 */

package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class Evaluation.
 */
public class Evaluation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4038415565123696307L;

	/**
	 * Instantiates a new evaluation.
	 */
	public Evaluation() {

	}
	
	/**
	 * Instantiates a new evaluation.
	 *
	 * @param evaluationId the evaluation id
	 * @param name the name
	 * @param description the description
	 */
	public Evaluation(int evaluationId, String name, String description) {
		_evaluationId = evaluationId;
		_name = name;
		_description = description;
	}

	/** The _evaluation id. */
	private int _evaluationId;
	
	/** The _name. */
	private String _name;
	
	/** The _description. */
	private String _description;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _evaluationId;
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
		Evaluation other = (Evaluation) obj;
		if (_evaluationId != other._evaluationId)
			return false;
		return true;
	}
	
	/**
	 * Gets the evaluation id.
	 *
	 * @return the evaluation id
	 */
	public int getEvaluationId() {
		return _evaluationId;
	}

	/**
	 * Sets the evaluation id.
	 *
	 * @param evaluationId the new evaluation id
	 */
	public void setEvaluationId(int evaluationId) {
		_evaluationId = evaluationId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		_description = description;
	}
}