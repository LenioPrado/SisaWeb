/*
 * File:	     Discipline.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Discipline.
 */
public class Discipline implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6291659362816502859L;

	/**
	 * Instantiates a new discipline.
	 */
	public Discipline() {

	}

	/**
	 * Instantiates a new discipline.
	 *
	 * @param disciplineId the discipline id
	 * @param periodSeries the period series
	 * @param menu the menu
	 * @param name the name
	 * @param weekClasses the load time hours class
	 * @param type the type
	 */
	public Discipline(int disciplineId, Integer periodSeries, String menu,
			String name, Integer weekClasses, String type) {
		_disciplineId = disciplineId;
		_periodSeries = periodSeries;
		_menu = menu;
		_name = name;
		_weekClasses = weekClasses;
		_type = type;
	}

	/** The _discipline id. */
	private int _disciplineId;
	
	/** The _period series. */
	private Integer _periodSeries;
	
	/** The _menu. */
	private String _menu;
	
	/** The _name. */
	private String _name;
	
	/** The _week classes. */
	private Integer _weekClasses;
	
	/** The _type. */
	private String _type;
	
	private List<Bibliography> _allBibliographies = new ArrayList<Bibliography>();
	
	/** The _basic bibliographies. */
	private List<Bibliography> _basicBibliographies = new ArrayList<Bibliography>();
	
	/** The _complementary bibliographies. */
	private List<Bibliography> _complementaryBibliographies = new ArrayList<Bibliography>();
	
	/** The _classes quantity sum. */
	private int _classesQuantitySum;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _disciplineId;
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
		Discipline other = (Discipline) obj;
		if (_disciplineId != other._disciplineId)
			return false;
		return true;
	}

	/**
	 * Gets the discipline id.
	 *
	 * @return the discipline id
	 */
	public int getDisciplineId() {
		return _disciplineId;
	}

	/**
	 * Sets the discipline id.
	 *
	 * @param disciplineId the new discipline id
	 */
	public void setDisciplineId(int disciplineId) {
		_disciplineId = disciplineId;
	}

	/**
	 * Gets the period series.
	 *
	 * @return the period series
	 */
	public Integer getPeriodSeries() {
		return _periodSeries;
	}

	/**
	 * Sets the period series.
	 *
	 * @param periodSeries the new period series
	 */
	public void setPeriodSeries(Integer periodSeries) {
		_periodSeries = periodSeries;
	}

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	public String getMenu() {
		return _menu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu the new menu
	 */
	public void setMenu(String menu) {
		_menu = menu;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return _type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		_type = type;
	}

	/**
	 * Gets the classes quantity sum.
	 *
	 * @return the classes quantity sum
	 */
	public int getClassesQuantitySum() {
		return _classesQuantitySum;
	}

	/**
	 * Sets the classes quantity sum.
	 *
	 * @param classesQuantitySum the new classes quantity sum
	 */
	public void setClassesQuantitySum(int classesQuantitySum) {
		_classesQuantitySum = classesQuantitySum;
	}
	
	public Integer getWeekClasses() {
		return _weekClasses;
	}

	public void setWeekClasses(Integer weekClasses) {
		_weekClasses = weekClasses;
	}

	public List<Bibliography> getBasicBibliographies() {
		return _basicBibliographies;
	}

	public void setBasicBibliographies(List<Bibliography> basicBibliographies) {
		_basicBibliographies = basicBibliographies;
	}

	public List<Bibliography> getComplementaryBibliographies() {
		return _complementaryBibliographies;
	}

	public void setComplementaryBibliographies(List<Bibliography> complementaryBibliographies) {
		_complementaryBibliographies = complementaryBibliographies;
	}

	public List<Bibliography> getAllBibliographies() {
		return _allBibliographies;
	}

	public void setAllBibliographies(List<Bibliography> allBibliographies) {
		_allBibliographies = allBibliographies;
	}
}