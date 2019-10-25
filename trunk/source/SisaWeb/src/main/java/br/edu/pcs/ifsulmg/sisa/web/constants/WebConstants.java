/*
* File:	     WebConstants.java
* Creation date: 06/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.constants;

/**
 * The Class WebConstants.
 */
public class WebConstants {

	/**
	 * Instantiates a new web constants.
	 */
	private WebConstants() { }
	
	/** The Constant PARAM_USER. */
	public static final String PARAM_USER = "userSession" ;
	
	/** The Constant PARAM_ROLE. */
	public static final String PARAM_ROLE = "roleSession" ;
	
	/** The Constant CURRENT_PAGE. */
	public static final String CURRENT_PAGE = "currentPage" ;
	
	public static final String PARAM_USER_ROLE_PAGES = "userRolePagesSession" ;
	
	/** The Constant PAGE_SUCCESS. */
	public static final String PAGE_SUCCESS = "/pages/success.xhtml";
	
	/** The Constant PAGE_404. Page not found*/
	public static final String PAGE_404 = "/pages/erro/404.xhtml";
	
	/** The Constant PAGE_500. Expired Session. */
	public static final String PAGE_500 = "/pages/erro/500.xhtml";
	
	public static final String PAGE_RECOVERY_PASSWORD = "/pages/recoveryPassword.xhtml";

	public static final String PAGE_FORGOT_PWD = "/pages/forgotPwd.xhtml";

	public static final String PAGE_LOGIN = "/pages/login.xhtml";
	
	public static final String PAGE_HOME = "/pages/home.xhtml?faces-redirect=true";
	
	public static final String PAGE_SELECT_ROLE = "/pages/selectRole.xhtml";
	
	public static final String PAGE_EVALUATE_TEACHING_PLAN = "/pages/listEvaluateTeachingPlan.xhtml?faces-redirect=true";
	
	public static final String PAGE_FILL_TEACHING_PLAN = "/pages/listFillTeachingPlan.xhtml?faces-redirect=true";
	
	public static final String PAGE_EDIT_CLASSES_PROGRAMMING = "/pages/listClassProgrammingByTeachingPlan.xhtml?faces-redirect=true";
	
	public static final String PAGE_EDIT_PROCEDURES_EVALUATION = "/pages/listProcedureEvaluationByTeachingPlan.xhtml?faces-redirect=true";
}
