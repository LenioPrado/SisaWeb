/*
 * File:	     Traineeship.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class Traineeship.
 */
public class Traineeship implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1503859063841870319L;

	/**
	 * Instantiates a new traineeship.
	 */
	public Traineeship() {

	}

	/**
	 * Instantiates a new traineeship.
	 *
	 * @param traineeshipId the traineeship id
	 * @param hourLoad the hour load
	 * @param description the description
	 * @param shortDescription the short description
	 */
	public Traineeship(int traineeshipId, Integer hourLoad, String description, String shortDescription) {
		_traineeshipId = traineeshipId;
		_hourLoad = hourLoad;
		_description = description;
		_shortDescription = shortDescription;
	}

	/** The _traineeship id. */
	private int _traineeshipId;
	
	/** The _hour load. */
	private Integer _hourLoad;
	
	/** The _description. */
	private String _description;
	
	/** The _short description. */
	private String _shortDescription;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _traineeshipId;
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
		Traineeship other = (Traineeship) obj;
		if (_traineeshipId != other._traineeshipId)
			return false;
		return true;
	}
	
	/**
	 * Gets the traineeship id.
	 *
	 * @return the traineeship id
	 */
	public int getTraineeshipId() {
		return _traineeshipId;
	}

	/**
	 * Sets the traineeship id.
	 *
	 * @param traineeshipId the new traineeship id
	 */
	public void setTraineeshipId(int traineeshipId) {
		_traineeshipId = traineeshipId;
	}

	/**
	 * Gets the hour load.
	 *
	 * @return the hour load
	 */
	public Integer getHourLoad() {
		return _hourLoad;
	}

	/**
	 * Sets the hour load.
	 *
	 * @param hourLoad the new hour load
	 */
	public void setHourLoad(Integer hourLoad) {
		_hourLoad = hourLoad;
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

	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	public String getShortDescription() {
		return _shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription the new short description
	 */
	public void setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}
}