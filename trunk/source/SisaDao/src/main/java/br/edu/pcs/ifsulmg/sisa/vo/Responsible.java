/*
 * File:	     Responsible.java
 * Creation date: 12/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class Responsible.
 */
public class Responsible implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4310371122211014048L;

	/**
	 * Instantiates a new responsible.
	 */
	public Responsible(){
		
	}
	
	/**
	 * Instantiates a new responsible.
	 *
	 *@param responsibleid the responsibleid
	 * @param userrole the userrole
	 * @param teachingplan the teachingplan
	 */
	public Responsible(int responsibleId, UserRole userRole, TeachingPlan teachingplan){
		this(userRole, teachingplan);
		_responsibleId = responsibleId;		
	}
	
	/**
	 * Instantiates a new responsible.
	 *
	 * @param userRole the user role
	 * @param teachingplan the teachingplan
	 */
	public Responsible(UserRole userRole, TeachingPlan teachingplan){
		_userRole = userRole;
		_teachingPlan = teachingplan;		
	}
	
	/** The _responsbileId. */
	private int _responsibleId;
	
	/** The _userrole. */
	private UserRole _userRole =  new UserRole();
	
	/** The _teachingplan. */
	private TeachingPlan _teachingPlan = new TeachingPlan();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _userRole.getUserRoleId() + _teachingPlan.getTeachingPlanId();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Objec
	 * t#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Responsible other = (Responsible) obj;
		if (_userRole.getUserRoleId() != other.getUserRole().getUserRoleId())
			return false;
		if (_teachingPlan.getTeachingPlanId() != other.getTeachingPlan().getTeachingPlanId())
			return false;
		return true;
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
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	public UserRole getUserRole() {
		return _userRole;
	}

	/**
	 * Sets the user role.
	 *
	 * @param userRole the new user role
	 */
	public void setUserRole(UserRole userRole) {
		_userRole = userRole;
	}

	/**
	 * Gets the responsible id.
	 *
	 * @return the responsible id
	 */
	public int getResponsibleId() {
		return _responsibleId;
	}

	/**
	 * Sets the responsible id.
	 *
	 * @param responsibleId the new responsible id
	 */
	public void setResponsibleId(int responsibleId) {
		_responsibleId = responsibleId;
	}
}