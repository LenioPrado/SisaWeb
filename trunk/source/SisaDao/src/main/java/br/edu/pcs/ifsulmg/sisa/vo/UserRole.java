/*
 * File:	     UserRole.java
 * Creation date: 12/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.util.DateUtil;

/**
 * The Class UserRole.
 */
public class UserRole implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 636384486580375529L;

	/**
	 * Instantiates a new user role.
	 */
	public UserRole(){
		
	}

	/**
	 * Instantiates a new user role.
	 *
	 * @param userRoleId the user role id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param role the role
	 * @param user the user
	 */
	public UserRole(int userRoleId, Date startDate, Date endDate, Role role, User user){
		_userRoleId = userRoleId;
		_endDate = endDate;
		_startDate = startDate;
		_role = role;
		_user = user;
	}
	
	/** The _userRoleId. */
	private int _userRoleId;
	
	/** The _end date. */
	private Date _endDate;
	
	/** The _start date. */
	private Date _startDate;
	
	/** The _role. */
	private Role _role = new Role();
	
	/** The _user. */
	private User _user = new User();	
	
	/** The _teaching plans. */
	private List<TeachingPlan> _teachingPlans = new ArrayList<TeachingPlan>();
	
	/**
	 * Gets the start date title.
	 *
	 * @return the start date title
	 */
	public String getStartDateTitle() {
		return _startDate == null ? "" : DateUtil.createDateFormatyyyyMMdd(_startDate);
	}
	
	/**
	 * Gets the end date title.
	 *
	 * @return the end date title
	 */
	public String getEndDateTitle() {
		return _endDate == null ? "" : DateUtil.createDateFormatyyyyMMdd(_endDate);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _userRoleId;
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
		UserRole other = (UserRole) obj;
		if (_userRoleId != other._userRoleId)
			return false;
		return true;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return _endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return _startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return _role;
	}
	
	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		_role = role;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return _user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		_user = user;
	}
	
	/**
	 * Gets the userRole id.
	 *
	 * @return the userRole id
	 */
	public int getUserRoleId() {
		return _userRoleId;
	}

	/**
	 * Sets the userRole id.
	 *
	 * @param verificationId the new userRole id
	 */
	public void setUserRoleId(int userRoleId) {
		_userRoleId = userRoleId;
	}

	public List<TeachingPlan> getTeachingPlans() {
		return _teachingPlans;
	}

	public void setTeachingPlans(List<TeachingPlan> teachingPlans) {
		_teachingPlans = teachingPlans;
	}
}