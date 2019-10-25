/*
 * File:	ProcedureEvaluationDAOTest.java
 * Creation date: 06/07/2015
 * ProcedureEvaluation: Amanda
 */

package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.AcademicPeriodDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.EvaluationDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.ProcedureEvaluationDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TeachingPlanDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Evaluation;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.vo.ProcedureEvaluation;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;
import br.edu.pcs.ifsulmg.sisa.vo.User;

public class ProcedureEvaluationDAOTest {
	
	DisciplineDAO disciplineDAO;
	EvaluationDAO evaluationDAO;
	TeachingPlanDAO teachingPlanDAO;
	ProcedureEvaluationDAO procedureEvaluationDAO;
	CourseDAO courseDAO;
	TraineeshipDAO traineeshipDAO;
	AcademicPeriodDAO academicPeriodDAO;
	PpcDAO ppcDAO;
	PpcDisciplineDAO ppcDisciplineDAO;
	UserDAO userDAO;
	
	Date time;
	Date timeUpdate;
	Date timeList;
	TeachingPlan teachingPlan;
	TeachingPlan teachingPlanUpdate;
	TeachingPlan teachingPlanList;
	Evaluation evaluation;
	Evaluation evaluationUpdate;
	Evaluation evaluationList;
	Discipline discipline;	
	Course course;
	Traineeship traineeship;
	AcademicPeriod academicPeriod;
	Ppc ppc;
	PpcDiscipline ppcDiscipline;
	User user;
	
	@Before
	public void setUp() throws Exception {
		try{		
			disciplineDAO = new DisciplineDAO();
			evaluationDAO = new EvaluationDAO();
			teachingPlanDAO = new TeachingPlanDAO();
			procedureEvaluationDAO = new ProcedureEvaluationDAO();
			courseDAO = new CourseDAO();
			traineeshipDAO = new TraineeshipDAO();
			academicPeriodDAO = new AcademicPeriodDAO();
			ppcDAO = new PpcDAO();
			ppcDisciplineDAO = new PpcDisciplineDAO();
			userDAO = new UserDAO();
			
			Date date = new GregorianCalendar(2015, 7, 2).getTime();
			time = new GregorianCalendar(2015, 12, 15).getTime();
			timeUpdate = new GregorianCalendar(2011, 5, 10).getTime();
			timeList = new GregorianCalendar(2003, 8, 20).getTime();
			
			discipline = new Discipline(1,1,"Menu","Nome",1,"Tipo4");
			discipline.setDisciplineId(disciplineDAO.insert(discipline));
			
			evaluation = new Evaluation(1,"Nome", "Descrição");
			evaluation.setEvaluationId(evaluationDAO.insert(evaluation));
			
			evaluationUpdate = new Evaluation(2,"Nome2", "Descrição2");
			evaluationUpdate.setEvaluationId(evaluationDAO.insert(evaluationUpdate));
			
			evaluationList = new Evaluation(3,"Nome3", "Descrição3");
			evaluationList.setEvaluationId(evaluationDAO.insert(evaluationList));
			
			course = new Course(1, "Curso 1","CA1",7, 6);
			course.setCourseId(courseDAO.insert(course));
			
			traineeship = new Traineeship(1, 80, "Descrição 1", "Descrição Abreviada 1");
			traineeship.setTraineeshipId(traineeshipDAO.insert(traineeship));
					
			ppc = new Ppc(1, date, "Local 1", 10, 4, course, traineeship);
			ppc.setPpcId(ppcDAO.insert(ppc));
			
			ppcDiscipline = new PpcDiscipline(discipline, ppc);
			ppcDiscipline.setPpcDisciplineId(ppcDisciplineDAO.insert(ppcDiscipline));
			
			academicPeriod = new AcademicPeriod(1, 2016, 1, "Bimestral");
			academicPeriod.setAcademicPeriodId(academicPeriodDAO.insert(academicPeriod));
			
			user = new User(1,"Name","pass123","email");
			user.setUserId(userDAO.insert(user));
	
			teachingPlan = new TeachingPlan(1,"Objetivo","Produção","Recupeação","Observação", user, academicPeriod,ppcDiscipline);
			teachingPlan.setTeachingPlanId(teachingPlanDAO.insert(teachingPlan));
			
			teachingPlanUpdate = new TeachingPlan(2,"Objetivo2","Produção2","Recupeação2","Observação2", user, academicPeriod,ppcDiscipline);
			teachingPlanUpdate.setTeachingPlanId(teachingPlanDAO.insert(teachingPlanUpdate));
			
			teachingPlanList = new TeachingPlan(3,"Objetivo3","Produção3","Recupeação3","Observação3", user, academicPeriod,ppcDiscipline);
			teachingPlanList.setTeachingPlanId(teachingPlanDAO.insert(teachingPlanList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {		
		try{
			teachingPlanDAO.delete(teachingPlan.getTeachingPlanId());
			teachingPlanDAO.delete(teachingPlanUpdate.getTeachingPlanId());
			teachingPlanDAO.delete(teachingPlanList.getTeachingPlanId());
			userDAO.delete(user.getUserId());
			academicPeriodDAO.delete(academicPeriod.getAcademicPeriodId());
			ppcDisciplineDAO.delete(ppcDiscipline.getPpcDisciplineId());
			ppcDAO.delete(ppc.getPpcId());
			traineeshipDAO.delete(traineeship.getTraineeshipId());
			courseDAO.delete(course.getCourseId());		
			disciplineDAO.delete(discipline.getDisciplineId());
			evaluationDAO.delete(evaluation.getEvaluationId());
			evaluationDAO.delete(evaluationUpdate.getEvaluationId());
			evaluationDAO.delete(evaluationList.getEvaluationId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertProcedureEvaluation() {
		System.out.println("Test Insert Procedure Evaluation...");
		try {
			assertEquals(0, procedureEvaluationDAO.list().size());			
			
			ProcedureEvaluation vo = new ProcedureEvaluation(1, time, "Observação 1", teachingPlan, evaluation);
			vo.setProcedureId(procedureEvaluationDAO.insert(vo));

			assertEquals(1, procedureEvaluationDAO.list().size());

			ProcedureEvaluation retrieved = procedureEvaluationDAO.getById(vo.getProcedureId());

			assertEquals(vo.getDate(), retrieved.getDate());
			assertEquals(vo.getObservation(), retrieved.getObservation());
			assertEquals(vo.getEvaluation().getEvaluationId(), retrieved.getEvaluation().getEvaluationId());
			assertEquals (vo.getTeachingPlan().getTeachingPlanId(), retrieved.getTeachingPlan().getTeachingPlanId());

			procedureEvaluationDAO.delete(vo.getProcedureId());
			
			assertEquals(0, procedureEvaluationDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test Insert Procedure Evaluation finished...");
	}

	@Test
	public void testUpdateProcedureEvaluation(){
		System.out.println ("Test Update Procedure Evaluation...");
		
		try {
			assertEquals (0, procedureEvaluationDAO.list().size());
			
			ProcedureEvaluation vo = new ProcedureEvaluation (1,time,"Observação", teachingPlan, evaluation);
			vo.setProcedureId(procedureEvaluationDAO.insert(vo));
			
			assertEquals (1, procedureEvaluationDAO.list().size());
			
			vo.setDate(timeUpdate);
			vo.setObservation("ObservaçãoUpdate");
			vo.setTeachingPlan(teachingPlanUpdate);
			vo.setEvaluation(evaluationUpdate);
			
			procedureEvaluationDAO.update(vo);
			
			ProcedureEvaluation retrieved = procedureEvaluationDAO.getById(vo.getProcedureId());
			
			assertEquals(vo.getDate(), retrieved.getDate());
			assertEquals(vo.getObservation(), retrieved.getObservation());
			assertEquals(vo.getEvaluation().getEvaluationId(), retrieved.getEvaluation().getEvaluationId());			
			assertEquals (vo.getTeachingPlan().getTeachingPlanId(), retrieved.getTeachingPlan().getTeachingPlanId());
									
			procedureEvaluationDAO.delete(vo.getProcedureId());
			
			assertEquals (0, procedureEvaluationDAO.list().size());
		} catch (Exception e){
			e.printStackTrace();
			}
		System.out.println("Test Update Procedure Evaluation finished...");
		}

	@Test
	public void testListAndGetById(){
		System.out.println("Test Procedure Evaluation List and GetById...");
		 
	 try{
		 assertEquals (0, procedureEvaluationDAO.list().size());
	 
		 ProcedureEvaluation vo = new ProcedureEvaluation (1,time,"Observation1", teachingPlan, evaluation);
		 ProcedureEvaluation vo1 = new ProcedureEvaluation (2,timeUpdate,"Observation2", teachingPlanUpdate, evaluationList);
		 ProcedureEvaluation vo2 = new ProcedureEvaluation (3,timeList,"Observation3", teachingPlanUpdate, evaluationList);
		
		 vo.setProcedureId(procedureEvaluationDAO.insert(vo));
		 vo1.setProcedureId(procedureEvaluationDAO.insert(vo1));
		 vo2.setProcedureId(procedureEvaluationDAO.insert(vo2));
					 
		 assertEquals(3,procedureEvaluationDAO.list().size());
		 
		 Assert.assertNotNull(procedureEvaluationDAO.getById(vo.getProcedureId()));
		 assertEquals(vo.getDate(),procedureEvaluationDAO.getById(vo.getProcedureId()).getDate());
		 assertEquals (vo.getObservation(),procedureEvaluationDAO.getById(vo.getProcedureId()).getObservation());
		 assertEquals (vo.getTeachingPlan().getTeachingPlanId(),procedureEvaluationDAO.getById(vo.getProcedureId()).getTeachingPlan().getTeachingPlanId());

		 Assert.assertNotNull(procedureEvaluationDAO.getById(vo1.getProcedureId()));
		 assertEquals(vo1.getDate(),procedureEvaluationDAO.getById(vo1.getProcedureId()).getDate());
		 assertEquals (vo1.getObservation(),procedureEvaluationDAO.getById(vo1.getProcedureId()).getObservation());
		 assertEquals (vo1.getTeachingPlan().getTeachingPlanId(),procedureEvaluationDAO.getById(vo1.getProcedureId()).getTeachingPlan().getTeachingPlanId());

		 Assert.assertNotNull(procedureEvaluationDAO.getById(vo2.getProcedureId()));
		 assertEquals(vo2.getDate(),procedureEvaluationDAO.getById(vo2.getProcedureId()).getDate());
		 assertEquals (vo2.getObservation(),procedureEvaluationDAO.getById(vo2.getProcedureId()).getObservation());
		 assertEquals (vo2.getTeachingPlan().getTeachingPlanId(),procedureEvaluationDAO.getById(vo2.getProcedureId()).getTeachingPlan().getTeachingPlanId());

		 procedureEvaluationDAO.delete(vo.getProcedureId());
		 procedureEvaluationDAO.delete(vo1.getProcedureId());
		 procedureEvaluationDAO.delete(vo2.getProcedureId());
		 			
		 assertEquals (0, procedureEvaluationDAO.list().size());					 
	 } catch (Exception e){
		 e.printStackTrace();
	 }
	 System.out.println("Test Procedure Evaluation List and GetById finished...");
	}	
}