/*
 * File:	     TeachingPlanDAOTest.java
 * Creation date: 	06/07/2015
 * Verification:     Rarvs / Lênio
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
import br.edu.pcs.ifsulmg.sisa.dao.PpcDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TeachingPlanDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;
import br.edu.pcs.ifsulmg.sisa.vo.User;

public class TeachingPlanDAOTest {

	DisciplineDAO disciplineDAO;
	TeachingPlanDAO teachingPlanDAO;
	CourseDAO courseDAO;
	TraineeshipDAO traineeshipDAO;
	AcademicPeriodDAO academicPeriodDAO;
	PpcDAO ppcDAO;
	PpcDisciplineDAO ppcDisciplineDAO;
	UserDAO userDAO;
	
	Discipline discipline;
	Course course;
	Traineeship traineeship;
	AcademicPeriod academicPeriod;
	AcademicPeriod academicPeriodUpdate;
	Ppc ppc;
	PpcDiscipline ppcDiscipline;
	PpcDiscipline ppcDisciplineUpdate;
	User user;
	User userUpdate;
	
	@Before
	public void setUp() throws Exception {
		try {
			disciplineDAO =  new DisciplineDAO();
			teachingPlanDAO = new TeachingPlanDAO();
			courseDAO = new CourseDAO();
			traineeshipDAO = new TraineeshipDAO();
			academicPeriodDAO = new AcademicPeriodDAO();
			ppcDAO = new PpcDAO();
			ppcDisciplineDAO = new PpcDisciplineDAO();
			userDAO = new UserDAO();
			
			Date date = new GregorianCalendar(2015, 7, 2).getTime();
			
			discipline =  new Discipline(1, 2,"Menu1", "Disciplina1",5,"Tipo2");
			discipline.setDisciplineId(disciplineDAO.insert(discipline));
			
			course = new Course(1, "Curso 1","CA1",7, 6);
			course.setCourseId(courseDAO.insert(course));
			
			traineeship = new Traineeship(1, 80, "Descrição 1", "Descrição Abreviada 1");
			traineeship.setTraineeshipId(traineeshipDAO.insert(traineeship));		
			
			ppc = new Ppc(1, date, "Local 1", 10, 4, course, traineeship);
			ppc.setPpcId(ppcDAO.insert(ppc));
			
			ppcDiscipline = new PpcDiscipline(discipline, ppc);
			ppcDiscipline.setPpcDisciplineId(ppcDisciplineDAO.insert(ppcDiscipline));
			
			ppcDisciplineUpdate = new PpcDiscipline(discipline, ppc);
			ppcDisciplineUpdate.setPpcDisciplineId(ppcDisciplineDAO.insert(ppcDisciplineUpdate));
			
			academicPeriod = new AcademicPeriod(1, 2016, 1, "Bimestral");
			academicPeriod.setAcademicPeriodId(academicPeriodDAO.insert(academicPeriod));
			
			academicPeriodUpdate = new AcademicPeriod(2, 2017, 2, "Semestral");
			academicPeriodUpdate.setAcademicPeriodId(academicPeriodDAO.insert(academicPeriodUpdate));
			
			user = new User(1,"Name","pass123","email");
			user.setUserId(userDAO.insert(user));
			
			userUpdate = new User(2,"NameUpdate","pass123Update","emailUpdate");
			userUpdate.setUserId(userDAO.insert(userUpdate));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try {
			userDAO.delete(user.getUserId());
			userDAO.delete(userUpdate.getUserId());
			ppcDisciplineDAO.delete(ppcDiscipline.getPpcDisciplineId());
			ppcDisciplineDAO.delete(ppcDisciplineUpdate.getPpcDisciplineId());
			academicPeriodDAO.delete(academicPeriod.getAcademicPeriodId());
			academicPeriodDAO.delete(academicPeriodUpdate.getAcademicPeriodId());
			disciplineDAO.delete(discipline.getDisciplineId());
			ppcDAO.delete(ppc.getPpcId());
			traineeshipDAO.delete(traineeship.getTraineeshipId());
			courseDAO.delete(course.getCourseId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertTeachingPlan(){		
		System.out.println("Test Insert Teaching Plan...");		

		try {			
			assertEquals(0, teachingPlanDAO.list().size());
			
			TeachingPlan vo  = new TeachingPlan(1, "Objetivo", "TeachPro", "Recov", "Observ", user, academicPeriod, ppcDiscipline);
			vo.setTeachingPlanId(teachingPlanDAO.insert(vo));
			
			assertEquals(1, teachingPlanDAO.list().size());
			
			TeachingPlan retrieved = teachingPlanDAO.getById(vo.getTeachingPlanId());
			
			assertEquals(vo.getObjective(), retrieved.getObjective());	
			assertEquals(vo.getTeachingProcedure(), retrieved.getTeachingProcedure());			
			assertEquals(vo.getRecovery(), retrieved.getRecovery());			
			assertEquals(vo.getObservation(), retrieved.getObservation());
			assertEquals(vo.getTeacher().getUserId(), retrieved.getTeacher().getUserId());
			assertEquals(vo.getAcademicPeriod().getAcademicPeriodId(), retrieved.getAcademicPeriod().getAcademicPeriodId());
			assertEquals(vo.getPpcDiscipline().getDiscipline().getDisciplineId(), retrieved.getPpcDiscipline().getDiscipline().getDisciplineId());
									
			teachingPlanDAO.delete(vo.getTeachingPlanId());
			
			assertEquals(0, teachingPlanDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Teaching Plan finished...");		
	}

	@Test
	public void testUpdateTeachingPlan(){		
		System.out.println("Test Update Teaching Plan...");		

		try {			
			assertEquals(0, teachingPlanDAO.list().size());
			
			TeachingPlan vo = new TeachingPlan(1, "Objetivo", "TeachPro", "Recov", "Observ", user, academicPeriod, ppcDiscipline);
			vo.setTeachingPlanId(teachingPlanDAO.insert(vo));			
			
			assertEquals(1, teachingPlanDAO.list().size());	
			
			vo.setObjective("Objetivo Upd 1");
			vo.setTeachingProcedure("Teach Proced Upd 1");
			vo.setRecovery("Recov Upd 1");
			vo.setTeacher(userUpdate);
			vo.setAcademicPeriod(academicPeriodUpdate);
			vo.setPpcDiscipline(ppcDisciplineUpdate);
			
			teachingPlanDAO.update(vo);
			
			TeachingPlan retrieved = teachingPlanDAO.getById(vo.getTeachingPlanId());
			
			assertEquals(vo.getObjective(), retrieved.getObjective());			
			assertEquals(vo.getTeachingProcedure(), retrieved.getTeachingProcedure());			
			assertEquals(vo.getRecovery(), retrieved.getRecovery());			
			assertEquals(vo.getObservation(), retrieved.getObservation());
			assertEquals(vo.getTeacher().getUserId(), retrieved.getTeacher().getUserId());
			assertEquals(vo.getAcademicPeriod().getAcademicPeriodId(), retrieved.getAcademicPeriod().getAcademicPeriodId());
			assertEquals(vo.getPpcDiscipline().getPpcDisciplineId(), retrieved.getPpcDiscipline().getPpcDisciplineId());			

			teachingPlanDAO.delete(vo.getTeachingPlanId());
			
			assertEquals(0, teachingPlanDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Teaching Plan finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Teaching Plan List and GetById...");		

		try {			
			assertEquals(0, teachingPlanDAO.list().size());
			
			TeachingPlan vo  = new TeachingPlan(1, "Objetivo1", "TeachPro1", "Recov1", "Observ1", user, academicPeriod, ppcDiscipline);
			TeachingPlan vo1  = new TeachingPlan(2, "Objetivo2", "TeachPro2", "Recov2", "Observ2", user, academicPeriod, ppcDiscipline);
			TeachingPlan vo2  = new TeachingPlan(3, "Objetivo3", "TeachPro3", "Recov3", "Observ3", user, academicPeriod, ppcDiscipline);

			vo.setTeachingPlanId(teachingPlanDAO.insert(vo));
			vo1.setTeachingPlanId(teachingPlanDAO.insert(vo1));
			vo2.setTeachingPlanId(teachingPlanDAO.insert(vo2));
			
			assertEquals(3, teachingPlanDAO.list().size());
			
			Assert.assertNotNull(teachingPlanDAO.getById(vo.getTeachingPlanId()));
			assertEquals(vo.getObjective(),teachingPlanDAO.getById(vo.getTeachingPlanId()).getObjective());
			assertEquals(vo.getRecovery(),teachingPlanDAO.getById(vo.getTeachingPlanId()).getRecovery());
			assertEquals(vo.getTeacher().getUserId(),userDAO.getById(vo.getTeacher().getUserId()).getUserId());
			assertEquals(vo.getTeachingProcedure(),teachingPlanDAO.getById(vo.getTeachingPlanId()).getTeachingProcedure());
			assertEquals(vo.getAcademicPeriod().getAcademicPeriodId(),teachingPlanDAO.getById(vo.getTeachingPlanId()).getAcademicPeriod().getAcademicPeriodId());
			
			Assert.assertNotNull(teachingPlanDAO.getById(vo1.getTeachingPlanId()));
			assertEquals(vo1.getObjective(),teachingPlanDAO.getById(vo1.getTeachingPlanId()).getObjective());
			assertEquals(vo1.getRecovery(),teachingPlanDAO.getById(vo1.getTeachingPlanId()).getRecovery());
			assertEquals(vo1.getTeachingProcedure(),teachingPlanDAO.getById(vo1.getTeachingPlanId()).getTeachingProcedure());
			assertEquals(vo1.getAcademicPeriod().getAcademicPeriodId(),teachingPlanDAO.getById(vo1.getTeachingPlanId()).getAcademicPeriod().getAcademicPeriodId());

			Assert.assertNotNull(teachingPlanDAO.getById(vo2.getTeachingPlanId()));
			assertEquals(vo2.getObjective(),teachingPlanDAO.getById(vo2.getTeachingPlanId()).getObjective());
			assertEquals(vo2.getRecovery(),teachingPlanDAO.getById(vo2.getTeachingPlanId()).getRecovery());
			assertEquals(vo2.getTeachingProcedure(),teachingPlanDAO.getById(vo2.getTeachingPlanId()).getTeachingProcedure());
			assertEquals(vo2.getAcademicPeriod().getAcademicPeriodId(),teachingPlanDAO.getById(vo2.getTeachingPlanId()).getAcademicPeriod().getAcademicPeriodId());
			
			teachingPlanDAO.delete(vo.getTeachingPlanId());
			teachingPlanDAO.delete(vo1.getTeachingPlanId());
			teachingPlanDAO.delete(vo2.getTeachingPlanId());
			
			assertEquals(0, teachingPlanDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Teaching Plan List and GetById finished...");		
	}
}