/*
 * File:	     TeachingPlan.java
 * Creation date: 12/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class TeachingPlan.
 */
public class TeachingPlan implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 185578788555399548L;

	/**
	 * Instantiates a new teaching plan.
	 */
	public TeachingPlan() {

	}
	
	public float getTotalLoadTimeHours() {
		return (float) _ppcDiscipline.getDiscipline().getWeekClasses() * _ppcDiscipline.getPpc().getClassHour() / 60;
	}
	
	public float getPracticeLoadTimeHours() {
		return (float) _practiceLoadTimeHoursClass * _ppcDiscipline.getPpc().getClassHour() / 60;
	}
	
	public float getTheoreticalLoadTimeHours() {
		return (float) _theoreticalLoadTimeHoursClass * _ppcDiscipline.getPpc().getClassHour() / 60;
	}
	
	public String getTeachingPlanLabel(){
		String label = String.format("%s/%s - %s - %s", 
				_academicPeriod.getSemester(),_academicPeriod.getYear(),
				_ppcDiscipline.getPpc().getCourse().getName(),
				_ppcDiscipline.getDiscipline().getName());
		return label;
	}

	/**
	 * Instantiates a new teaching plan.
	 *
	 * @param teachingplanId the teachingplan id
	 * @param objective the objective
	 * @param teachingProcedure the teaching procedure
	 * @param recovery the recovery
	 * @param observation the observation
	 * @param academicPeriod the academic period
	 * @param ppcDiscipline the ppc discipline
	 */
	public TeachingPlan(int teachingplanId, String objective, String teachingProcedure, String recovery, 
			String observation, User teacher, AcademicPeriod academicPeriod, PpcDiscipline ppcDiscipline) {
		_teachingPlanId = teachingplanId;
		_objective = objective;
		_teachingProcedure = teachingProcedure;
		_recovery = recovery;
		_observation = observation;
		_teacher = teacher;
		_academicPeriod = academicPeriod;
		_ppcDiscipline = ppcDiscipline;
	}

	/** The _teaching plan id. */
	private int _teachingPlanId;
	
	/** The _objective. */
	private String _objective;
	
	/** The _teaching procedure. */
	private String _teachingProcedure;
	
	/** The _recovery. */
	private String _recovery;
	
	/** The _observation. */
	private String _observation;
	
	/** The _practice load time hours class. */
	private Integer _practiceLoadTimeHoursClass;
	
	/** The _theoretical load time hours class. */
	private Integer _theoreticalLoadTimeHoursClass;	
	
	/** The _teacher. */
	private User _teacher = new User();
	
	/** The _user role. */
	private UserRole _userRole = new UserRole();

	/** The _ppc discipline. */
	private PpcDiscipline _ppcDiscipline = new PpcDiscipline();
	
	/** The _academic period. */
	private AcademicPeriod _academicPeriod = new AcademicPeriod();
	
	/** The _classes programming. */
	private List<ClassProgramming> _classesProgramming = new ArrayList<ClassProgramming>();
	
	/** The _procedures evaluations. */
	private List<ProcedureEvaluation> _proceduresEvaluations = new ArrayList<ProcedureEvaluation>();
		
	/** The _verifications. */
	private List<Verification> _verifications = new ArrayList<Verification>();

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _teachingPlanId;
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
		TeachingPlan other = (TeachingPlan) obj;
		if (_teachingPlanId != other._teachingPlanId)
			return false;
		return true;
	}
	
	/**
	 * Gets the teaching plan id.
	 *
	 * @return the teaching plan id
	 */
	public int getTeachingPlanId() {
		return _teachingPlanId;
	}

	/**
	 * Sets the teaching plan id.
	 *
	 * @param teachingplanId the new teaching plan id
	 */
	public void setTeachingPlanId(int teachingplanId) {
		_teachingPlanId = teachingplanId;
	}

	/**
	 * Gets the objective.
	 *
	 * @return the objective
	 */
	public String getObjective() {
		return _objective;
	}

	/**
	 * Sets the objective.
	 *
	 * @param objective the new objective
	 */
	public void setObjective(String objective) {
		_objective = objective;
	}
	
	/**
	 * Gets the teaching procedure.
	 *
	 * @return the teaching procedure
	 */
	public String getTeachingProcedure() {
		return _teachingProcedure;
	}

	/**
	 * Sets the teaching procedure.
	 *
	 * @param teachingProcedure the new teaching procedure
	 */
	public void setTeachingProcedure(String teachingProcedure) {
		_teachingProcedure = teachingProcedure;
	}

	/**
	 * Gets the recovery.
	 *
	 * @return the recovery
	 */
	public String getRecovery() {
		return _recovery;
	}

	/**
	 * Sets the recovery.
	 *
	 * @param recovery the new recovery
	 */
	public void setRecovery(String recovery) {
		_recovery = recovery;
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
	 * Gets the academic period.
	 *
	 * @return the academic period
	 */
	public AcademicPeriod getAcademicPeriod() {
		return _academicPeriod;
	}

	/**
	 * Sets the academic period.
	 *
	 * @param academicPeriod the new academic period
	 */
	public void setAcademicPeriod(AcademicPeriod academicPeriod) {
		_academicPeriod = academicPeriod;
	}

	/**
	 * Gets the classes programming.
	 *
	 * @return the classes programming
	 */
	public List<ClassProgramming> getClassesProgramming() {
		return _classesProgramming;
	}

	/**
	 * Sets the classes programming.
	 *
	 * @param classesProgramming the new classes programming
	 */
	public void setClassesProgramming(List<ClassProgramming> classesProgramming) {
		_classesProgramming = classesProgramming;
	}

	/**
	 * Gets the procedures evaluations.
	 *
	 * @return the procedures evaluations
	 */
	public List<ProcedureEvaluation> getProceduresEvaluations() {
		return _proceduresEvaluations;
	}

	/**
	 * Sets the procedures evaluations.
	 *
	 * @param proceduresEvaluations the new procedures evaluations
	 */
	public void setProceduresEvaluations(
			List<ProcedureEvaluation> proceduresEvaluations) {
		_proceduresEvaluations = proceduresEvaluations;
	}

	/**
	 * Gets the ppc discipline.
	 *
	 * @return the ppc discipline
	 */
	public PpcDiscipline getPpcDiscipline() {
		return _ppcDiscipline;
	}

	/**
	 * Sets the ppc discipline.
	 *
	 * @param ppcDiscipline the new ppc discipline
	 */
	public void setPpcDiscipline(PpcDiscipline ppcDiscipline) {
		_ppcDiscipline = ppcDiscipline;
	}

	/**
	 * Gets the theoretical load time hours class.
	 *
	 * @return the theoretical load time hours class
	 */
	public Integer getTheoreticalLoadTimeHoursClass() {
		return _theoreticalLoadTimeHoursClass;
	}

	/**
	 * Sets the theoretical load time hours class.
	 *
	 * @param theoreticalLoadTimeHoursClass the new theoretical load time hours class
	 */
	public void setTheoreticalLoadTimeHoursClass(
			Integer theoreticalLoadTimeHoursClass) {
		_theoreticalLoadTimeHoursClass = theoreticalLoadTimeHoursClass;
	}

	/**
	 * Gets the practice load time hours class.
	 *
	 * @return the practice load time hours class
	 */
	public Integer getPracticeLoadTimeHoursClass() {
		return _practiceLoadTimeHoursClass;
	}

	/**
	 * Sets the practice load time hours class.
	 *
	 * @param practiceLoadTimeHoursClass the new practice load time hours class
	 */
	public void setPracticeLoadTimeHoursClass(Integer practiceLoadTimeHoursClass) {
		_practiceLoadTimeHoursClass = practiceLoadTimeHoursClass;
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

	public List<Verification> getVerifications() {
		return _verifications;
	}

	public void setVerifications(List<Verification> verifications) {
		_verifications = verifications;
	}

	public User getTeacher() {
		return _teacher;
	}

	public void setTeacher(User teacher) {
		_teacher = teacher;
	}

	}