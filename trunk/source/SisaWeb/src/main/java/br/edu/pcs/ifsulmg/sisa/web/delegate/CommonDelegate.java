/*
* File:	     CommonDelegate.java
* Creation date: 08/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import javax.inject.Inject;

import br.edu.pcs.ifsulmg.sisa.dao.AcademicPeriodCourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.AcademicPeriodDAO;
import br.edu.pcs.ifsulmg.sisa.dao.ActionDAO;
import br.edu.pcs.ifsulmg.sisa.dao.AuthorDAO;
import br.edu.pcs.ifsulmg.sisa.dao.BibliographyAuthorDAO;
import br.edu.pcs.ifsulmg.sisa.dao.BibliographyDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CampusCourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CampusDAO;
import br.edu.pcs.ifsulmg.sisa.dao.ClassProgrammingDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineBibliographyDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.EvaluationDAO;
import br.edu.pcs.ifsulmg.sisa.dao.MenuDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PageDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PageRoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.ProcedureEvaluationDAO;
import br.edu.pcs.ifsulmg.sisa.dao.RectoryDAO;
import br.edu.pcs.ifsulmg.sisa.dao.ResponsibleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.RoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TeachingPlanDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserRoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.VerificationDAO;

/**
 * The Class CommonDelegate.
 */
public class CommonDelegate {

	/**
	 * Instantiates a new common delegate.
	 */
	public CommonDelegate(){		
	}
	
	/** The _action dao. */
	@Inject
	private ActionDAO _actionDAO;
	
	/** The _user dao. */
	@Inject
	private UserDAO _userDAO;
	
	/** The _ppc dao. */
	@Inject
	private PpcDAO _ppcDAO;
	
	/** The _course dao. */
	@Inject
	private CourseDAO _courseDAO;
	
	/** The _traineeship dao. */
	@Inject
	private TraineeshipDAO _traineeshipDAO;
	
	/** The _discipline dao. */
	@Inject
	private DisciplineDAO _disciplineDAO;
	
	/** The _campus dao. */
	@Inject
	private CampusDAO _campusDAO;
	
	/** The _rectory dao. */
	@Inject
	private RectoryDAO _rectoryDAO;
	
	/** The _bibliography dao. */
	@Inject
	private BibliographyDAO _bibliographyDAO;
	
	/** The _bibliography author dao. */
	@Inject
	private BibliographyAuthorDAO _bibliographyAuthorDAO;
	
	/** The _author dao. */
	@Inject
	private AuthorDAO _authorDAO;
	
	/** The _academicPeriod dao. */
	@Inject
	private AcademicPeriodDAO _academicPeriodDAO;

	/** The _evaluation dao. */
	@Inject
	private EvaluationDAO _evaluationDAO;
	
	/** The _role dao. */
	@Inject
	private RoleDAO _roleDAO;
	
	/** The _teaching plan dao. */
	@Inject
	private TeachingPlanDAO _teachingPlanDAO;
	
	/** The _academic period course dao. */
	@Inject
	private AcademicPeriodCourseDAO _academicPeriodCourseDAO;
	
	/** The _campus course dao. */
	@Inject
	private CampusCourseDAO _campusCourseDAO;
	
	/** The _ppc discipline dao. */
	@Inject
	private PpcDisciplineDAO _ppcDisciplineDAO;

	/** The _precedure evaluation dao. */
	@Inject
	private ProcedureEvaluationDAO _procedureEvaluationDAO;
	
	/** The _verification dao. */
	@Inject
	private VerificationDAO _verificationDAO;
	
	/** The _responsible dao. */
	@Inject
	private ResponsibleDAO _responsibleDAO;
	
	/** The _classProgramming dao. */
	@Inject
	private ClassProgrammingDAO _classProgrammingDAO;
	
	/** The _page dao. */
	@Inject
	private PageDAO _pageDAO;
	
	/** The _userRole dao. */
	@Inject
	private UserRoleDAO _userRoleDAO;
	
	/** The _disciplineBibliography dao. */
	@Inject
	private DisciplineBibliographyDAO _disciplineBibliographyDAO;
	
	/** The _pageRole dao. */
	@Inject
	private PageRoleDAO _pageRoleDAO;
	
	/** The _menu dao. */
	@Inject
	private MenuDAO _menuDAO;
	
	/**	 
	 * Gets the user dao.
	 *
	 * @return the user dao
	 */
	protected UserDAO getUserDAO() {
		return _userDAO;
	}

	/**
	 * Gets the ppc dao.
	 *
	 * @return the ppc dao
	 */
	protected PpcDAO getPpcDAO() {
		return _ppcDAO;
	}
	
	/**
	 * Gets the course dao.
	 *
	 * @return the course dao
	 */
	protected CourseDAO getCourseDAO() {
		return _courseDAO;
	}

	/**
	 * Gets the traineeship dao.
	 *
	 * @return the traineeship dao
	 */
	protected TraineeshipDAO getTraineeshipDAO() {
		return _traineeshipDAO;
	}

	/**
	 * Gets the discipline dao.
	 *
	 * @return the discipline dao
	 */
	protected DisciplineDAO getDisciplineDAO() {
		return _disciplineDAO;
	}

	/**
	 * Gets the campus dao.
	 *
	 * @return the campus dao
	 */
	protected CampusDAO getCampusDAO() {
		return _campusDAO;
	}

	/**
	 * Gets the rectory dao.
	 *
	 * @return the rectory dao
	 */
	protected RectoryDAO getRectoryDAO() {
		return _rectoryDAO;
	}

	/**
	 * Gets the bibliography dao.
	 *
	 * @return the bibliography dao
	 */
	protected BibliographyDAO getBibliographyDAO() {
		return _bibliographyDAO;
	}
	
	/**
	 * Gets the bibliography author dao.
	 *
	 * @return the bibliography author dao
	 */
	protected BibliographyAuthorDAO getBibliographyAuthorDAO() {
		return _bibliographyAuthorDAO;
	}

	/**
	 * Gets the author dao.
	 *
	 * @return the author dao
	 */
	protected AuthorDAO getAuthorDAO() {
		return _authorDAO;
	}

	/**
	 * Gets the academic period dao.
	 *
	 * @return the academic period dao
	 */
	protected AcademicPeriodDAO getAcademicPeriodDAO() {
		return _academicPeriodDAO;
	}

	/**
	 * Gets the evaluation dao.
	 *
	 * @return the evaluation dao
	 */
	protected EvaluationDAO getEvaluationDAO() {
		return _evaluationDAO;
	}
	
	/**
	 * Gets the role dao.
	 *
	 * @return the role dao
	 */
	protected RoleDAO getRoleDAO(){
		return _roleDAO;
	}

	/**
	 * Gets the teaching plan dao.
	 *
	 * @return the teaching plan dao
	 */
	protected TeachingPlanDAO getTeachingPlanDAO() {
		return _teachingPlanDAO;
	}

	/**
	 * Gets the academic period course dao.
	 *
	 * @return the academic period course dao
	 */
	protected AcademicPeriodCourseDAO getAcademicPeriodCourseDAO() {
		return _academicPeriodCourseDAO;
	}
	
	/**
	 * Gets the campus course dao.
	 *
	 * @return the campus course dao
	 */
	protected CampusCourseDAO getCampusCourseDAO() {
		return _campusCourseDAO;
	}
	
	/**
	 * Gets the procedure evaluation dao.
	 *
	 * @return the procedure evaluation dao
	 */
	protected ProcedureEvaluationDAO getProcedureEvaluationDAO() {
		return _procedureEvaluationDAO;
	}

	/**
	 * Gets the ppc discipline dao.
	 *
	 * @return the ppc discipline dao
	 */
	protected PpcDisciplineDAO getPpcDisciplineDAO() {
		return _ppcDisciplineDAO;
	}
	/**
	 * Gets the verification dao.
	 *
	 * @return the verification dao
	 */
	protected VerificationDAO getVerificationDAO() {
		return _verificationDAO;
	}
	
	/**
	 * Gets the responsible dao.
	 *
	 * @return the responsible dao
	 */
	protected ResponsibleDAO getResponsibleDAO() {
		return _responsibleDAO;
	}
	
	/**
	 * Gets the class programming dao.
	 *
	 * @return the class programming dao
	 */

	protected ClassProgrammingDAO getClassProgrammingDAO() {
		return _classProgrammingDAO;
	}

	/**
	 * Gets the user role dao.
	 *
	 * @return the user role dao
	 */
	
	protected UserRoleDAO getUserRoleDAO() {
		return _userRoleDAO;
	}
	
	/**
	 * Gets the discipline bibliography dao.
	 *
	 * @return the discipline bibliography dao
	 */
	
	protected DisciplineBibliographyDAO getDisciplineBibliographyDAO() {
		return _disciplineBibliographyDAO;
	}
	
	/**
	 * Gets the page dao.
	 *
	 * @return the page dao
	 */
	
	protected PageDAO getPageDAO() {
		return _pageDAO;
	}
	
	/**
	 * Gets the page role dao.
	 *
	 * @return the page role dao
	 */
	
	protected PageRoleDAO getPageRoleDAO() {
		return _pageRoleDAO;
	}

	/**
	 * Gets the action dao.
	 *
	 * @return the action dao
	 */
	protected ActionDAO getActionDAO() {
		return _actionDAO;
	}

	/**
	 * Gets the menu dao.
	 *
	 * @return the menu dao
	 */
	protected MenuDAO getMenuDAO() {
		return _menuDAO;
	}
}