/*
 * File:	     ClassProgrammingDAOTest.java
 * Creation date: 03/07/2015 
 * Author:        Amanda
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
import br.edu.pcs.ifsulmg.sisa.dao.ClassProgrammingDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TeachingPlanDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.ClassProgramming;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;
import br.edu.pcs.ifsulmg.sisa.vo.User;

public class ClassProgrammingDAOTest {

	DisciplineDAO disciplineDAO;
	TeachingPlanDAO teachingPlanDAO;
	ClassProgrammingDAO classProgrammingDAO;
	CourseDAO courseDAO;
	TraineeshipDAO traineeshipDAO;
	AcademicPeriodDAO academicPeriodDAO;
	PpcDAO ppcDAO;
	PpcDisciplineDAO ppcDisciplineDAO;
	UserDAO userDAO;
	
	Date date;
	
	Course course;
	Traineeship traineeship;
	Ppc ppc;
	AcademicPeriod academicPeriod;
	Discipline discipline;
	PpcDiscipline ppcDiscipline;
	TeachingPlan teachingPlan;
	TeachingPlan teachingPlanUpdate;
	TeachingPlan teachingPlanList;
	User user;
	
	@Before
	public void setUp() throws Exception {
		try{
			disciplineDAO = new DisciplineDAO();
			teachingPlanDAO = new TeachingPlanDAO();
			classProgrammingDAO = new ClassProgrammingDAO();
			courseDAO = new CourseDAO();
			traineeshipDAO = new TraineeshipDAO();
			academicPeriodDAO = new AcademicPeriodDAO();
			ppcDAO = new PpcDAO();
			ppcDisciplineDAO = new PpcDisciplineDAO();
			userDAO = new UserDAO();
			
			date = new GregorianCalendar(2015, 7, 2).getTime();
			
			discipline = new Discipline(1,1,"Menu","Nome77",1,"Tipo7");
			discipline.setDisciplineId(disciplineDAO.insert(discipline));

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
						
			teachingPlanList = new TeachingPlan(2,"Objetivo2", "Produção2","Recuperação2","Observação2", user, academicPeriod,ppcDiscipline);
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertClassProgramming() {
		System.out.println("Test Insert Class Programming...");

		try {
			assertEquals(0, classProgrammingDAO.list().size());
						
			ClassProgramming vo = new ClassProgramming(1,"Conteúdo",1, 1,teachingPlan);
			vo.setProgrammingId(classProgrammingDAO.insert(vo));

			assertEquals(1, classProgrammingDAO.list().size());

			ClassProgramming retrieved = classProgrammingDAO.getById(vo.getProgrammingId());

			assertEquals(vo.getContent(), retrieved.getContent());
			assertEquals(vo.getClassesQuantity(), retrieved.getClassesQuantity());
			assertEquals(vo.getClassType(), retrieved.getClassType());
			assertEquals (vo.getTeachingPlan().getTeachingPlanId(), retrieved.getTeachingPlan().getTeachingPlanId());

			classProgrammingDAO.delete(vo.getProgrammingId());		

			assertEquals(0, classProgrammingDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Class Programming finished...");
	}
	
	@Test
	public void testUpdateClassProgramming(){
		System.out.println ("Test Update Class Programming...");
		
		try {
			assertEquals (0, classProgrammingDAO.list().size());
			
			ClassProgramming vo = new ClassProgramming (1,"Conteúdo",2, 1, teachingPlan);
			vo.setProgrammingId(classProgrammingDAO.insert(vo));
			
			assertEquals (1, classProgrammingDAO.list().size());
			
			vo.setContent("ConteúdoUpdate");
			vo.setClassesQuantity(2);
			vo.setTeachingPlan(teachingPlanUpdate);
		
			classProgrammingDAO.update(vo);
			
			ClassProgramming retrieved = classProgrammingDAO.getById(vo.getProgrammingId());
			
			assertEquals(vo.getContent(), retrieved.getContent());
			assertEquals(vo.getClassesQuantity(), retrieved.getClassesQuantity());
			assertEquals (vo.getTeachingPlan().getTeachingPlanId(), retrieved.getTeachingPlan().getTeachingPlanId());
						
			classProgrammingDAO.delete(vo.getProgrammingId());
			
			assertEquals (0, classProgrammingDAO.list().size());
		} catch (Exception e){
			e.printStackTrace();
			}
		System.out.println("Test Update Class Programming finished...");
		}
	
	@Test
	public void testListAndGetById(){
		 System.out.println("Test Class Programming List and GetById...");
		 
		 try{
			assertEquals (0, classProgrammingDAO.list().size());		 
			 
			ClassProgramming vo = new ClassProgramming (1,"Conteúdo",2, 1, teachingPlan);
			ClassProgramming vo1 = new ClassProgramming (2,"Conteúdo2",1, 2, teachingPlanUpdate);
			 
			vo.setProgrammingId(classProgrammingDAO.insert(vo));	
			vo1.setProgrammingId(classProgrammingDAO.insert(vo1));
						 
			assertEquals (2,classProgrammingDAO.list().size());
			 
			Assert.assertNotNull(classProgrammingDAO.getById(vo.getProgrammingId()));
			assertEquals (vo.getContent(),classProgrammingDAO.getById(vo.getProgrammingId()).getContent());
			 
			Assert.assertNotNull(classProgrammingDAO.getById(vo1.getProgrammingId()));
			assertEquals (vo1.getContent(),classProgrammingDAO.getById(vo1.getProgrammingId()).getContent());
			 		
			classProgrammingDAO.delete(vo.getProgrammingId());
			classProgrammingDAO.delete(vo1.getProgrammingId());
			 			
			assertEquals (0, classProgrammingDAO.list().size());
			
		 } catch (Exception e){
			 e.printStackTrace();
		 }
		 System.out.println("Test Class Programming List and GetById finished...");
	}	
}