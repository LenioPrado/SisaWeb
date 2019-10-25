
/*
 * File:	     ClassProgramming.java
 * Creation date: 10/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class ClassProgramming.
 */
public class ClassProgramming implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8546022418554063449L;

	/**
	 * Instantiates a new class programming.
	 */
	public ClassProgramming() {

	}

	/**
	 * Instantiates a new class programming.
	 *
	 * @param programmingId the programming id
	 * @param content the content
	 * @param classesQuantity the classes quantity
	 * @param classType the class type
	 * @param teachingPlan the teaching plan
	 */
	public ClassProgramming(int programmingId, String content,Integer classesQuantity, int classType, TeachingPlan teachingPlan) {
		_programmingId = programmingId;
		_content = content;
		_classesQuantity = classesQuantity;
		_classType = classType;
		_teachingPlan = teachingPlan;
	}

	/** The _programming id. */
	private int _programmingId;
	
	/** The _content. */
	private String _content;
	
	/** The _classes quantity. */
	private Integer _classesQuantity;
	
	/** The _class type. */
	private int _classType;
	
	/** The _teaching plan. */
	private TeachingPlan _teachingPlan = new TeachingPlan();

	/**
	 * Gets the programming id.
	 *
	 * @return the programming id
	 */
	public int getProgrammingId() {
		return _programmingId;
	}

	/**
	 * Sets the programming id.
	 *
	 * @param programmingId the new programming id
	 */
	public void setProgrammingId(int programmingId) {
		_programmingId = programmingId;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return _content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		_content = content;
	}

	/**
	 * Gets the classes quantity.
	 *
	 * @return the classes quantity
	 */
	public Integer getClassesQuantity() {
		return _classesQuantity;
	}

	/**
	 * Sets the classes quantity.
	 *
	 * @param classesQuantity the new classes quantity
	 */
	public void setClassesQuantity(Integer classesQuantity) {
		_classesQuantity = classesQuantity;
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

	public int getClassType() {
		return _classType;
	}

	public void setClassType(int classType) {
		_classType = classType;
	}
}