/*
 * File:	    Ppc.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.util.DateUtil;

/**
 * The Class Ppc.
 */
public class Ppc implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5802941563443739079L;

	/**
	 * Instantiates a new ppc.
	 */
	public Ppc() {

	}
	
	public int getDisciplinesSize() { 
		return _disciplines.size(); 
	}
	
	public float getTotalClassesHour() {
		return (float) _classHour * _weeksNumber * _totalWeekClasses / 60;
	}

	/**
	 * Instantiates a new ppc.
	 *
	 * @param ppcId the ppc id
	 * @param date the date
	 * @param place the place
	 * @param classHour the class hour
	 * @param weeksNumber the weeks number
	 * @param course the course
	 * @param traineeship the traineeship
	 */
	public Ppc(int ppcId, Date date, String place, Integer classHour,
			Integer weeksNumber, Course course, Traineeship traineeship) {
		_ppcId = ppcId;
		_date = date;
		_place = place;
		_classHour = classHour;
		_weeksNumber = weeksNumber;
		_course = course;
		_traineeship = traineeship;
	}

	/** The _disciplines. */
	private List<Discipline> _disciplines = new ArrayList<Discipline>();
	
	/** The _ppc id. */
	private int _ppcId;
	
	/** The _date. */
	private Date _date;
	
	/** The _place. */
	private String _place;
	
	/** The _class hour. */
	private Integer _classHour;
	
	/** The _weeks number. */
	private Integer _weeksNumber;
	
	/** The _total week classes. */
	private Integer _totalWeekClasses;
	
	/** The _course. */
	private Course _course = new Course();
	
	/** The _traineeship. */
	private Traineeship _traineeship = new Traineeship();
	
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
		result = prime * result + _ppcId;
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
		Ppc other = (Ppc) obj;
		if (_ppcId != other._ppcId)
			return false;
		return true;
	}

	/**
	 * Gets the ppc id.
	 *
	 * @return the ppc id
	 */
	public int getPpcId() {
		return _ppcId;
	}

	/**
	 * Sets the ppc id.
	 *
	 * @param ppcId the new ppc id
	 */
	public void setPpcId(int ppcId) {
		_ppcId = ppcId;
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
	 * Gets the place.
	 *
	 * @return the place
	 */
	public String getPlace() {
		return _place;
	}

	/**
	 * Sets the place.
	 *
	 * @param place the new place
	 */
	public void setPlace(String place) {
		_place = place;
	}

	/**
	 * Gets the class hour.
	 *
	 * @return the class hour
	 */
	public Integer getClassHour() {
		return _classHour;
	}

	/**
	 * Sets the class hour.
	 *
	 * @param classHour the new class hour
	 */
	public void setClassHour(Integer classHour) {
		_classHour = classHour;
	}

	/**
	 * Gets the weeks number.
	 *
	 * @return the weeks number
	 */
	public Integer getWeeksNumber() {
		return _weeksNumber;
	}

	/**
	 * Sets the weeks number.
	 *
	 * @param weeksNumber the new weeks number
	 */
	public void setWeeksNumber(Integer weeksNumber) {
		_weeksNumber = weeksNumber;
	}

	/**
	 * Gets the course.
	 *
	 * @return the course
	 */
	public Course getCourse() {
		return _course;
	}

	/**
	 * Sets the course.
	 *
	 * @param course the new course
	 */
	public void setCourse(Course course) {
		_course = course;
	}

	/**
	 * Gets the traineeship.
	 *
	 * @return the traineeship
	 */
	public Traineeship getTraineeship() {
		return _traineeship;
	}

	/**
	 * Sets the traineeship.
	 *
	 * @param traineeship the new traineeship
	 */
	public void setTraineeship(Traineeship traineeship) {
		_traineeship = traineeship;
	}

	/**
	 * Gets the disciplines.
	 *
	 * @return the disciplines
	 */
	public List<Discipline> getDisciplines() {
		return _disciplines;
	}

	/**
	 * Sets the disciplines.
	 *
	 * @param disciplines the new disciplines
	 */
	public void setDisciplines(List<Discipline> disciplines) {
		_disciplines = disciplines;
	}

	/**
	 * Gets the total week classes.
	 *
	 * @return the total week classes
	 */
	public Integer getTotalWeekClasses() {
		return _totalWeekClasses;
	}

	/**
	 * Sets the total week classes.
	 *
	 * @param totalWeekClasses the new total week classes
	 */
	public void setTotalWeekClasses(Integer totalWeekClasses) {
		_totalWeekClasses = totalWeekClasses;
	}
}