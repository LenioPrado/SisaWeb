/*
* File:	   VerificationDAOTest.java
* Creation date: 06/07/2015 
* User:    Rarvs / Lênio
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
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
import br.edu.pcs.ifsulmg.sisa.dao.ResponsibleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.RoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TeachingPlanDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserRoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.VerificationDAO;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.vo.Responsible;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;
import br.edu.pcs.ifsulmg.sisa.vo.Verification;

public class VerificationDAOTest {

	VerificationDAO verificationDAO;
	TeachingPlanDAO teachingPlanDAO;
	UserDAO userDAO;
	RoleDAO roleDAO;
	UserRoleDAO userRoleDAO;
	DisciplineDAO disciplineDAO;
	CourseDAO courseDAO;
	TraineeshipDAO traineeshipDAO;
	AcademicPeriodDAO academicPeriodDAO;
	PpcDAO ppcDAO;
	PpcDisciplineDAO ppcDisciplineDAO;
	ResponsibleDAO responsibleDAO;
	
	Date startDate;
	Date endDate;
	
	Timestamp timeCreation;
	Timestamp timeCreationUpdate;
	Timestamp timeCreationList;
	Timestamp timeEvaluation;
	Timestamp timeEvaluationUpdate;
	Timestamp timeEvaluationList;
	Discipline discipline;
	User user;
	User userUpdate;
	User userList;
	Role role;
	UserRole userRole;
	TeachingPlan teachingPlan;
	Responsible responsible;
	Responsible responsibleUpdate;
	Responsible responsibleList;
	Course course;
	Traineeship traineeship;
	AcademicPeriod academicPeriod;
	Ppc ppc;
	PpcDiscipline ppcDiscipline;
	PpcDiscipline ppcDisciplineUpdate;
	
	@Before
	public void setUp() throws Exception {
		try{
			verificationDAO = new VerificationDAO();
			teachingPlanDAO = new TeachingPlanDAO();
			userDAO = new UserDAO();
			roleDAO = new RoleDAO();
			userRoleDAO = new UserRoleDAO();
			disciplineDAO = new DisciplineDAO();
			courseDAO = new CourseDAO();
			traineeshipDAO = new TraineeshipDAO();
			academicPeriodDAO = new AcademicPeriodDAO();
			ppcDAO = new PpcDAO();
			ppcDisciplineDAO = new PpcDisciplineDAO();
			responsibleDAO = new ResponsibleDAO();
			
			timeCreation = new Timestamp(new GregorianCalendar(2015, 7, 2, 14, 00).getTimeInMillis());
			timeCreation.setNanos(0);
			
			timeCreationUpdate = new Timestamp(new GregorianCalendar(2018, 4, 1, 7, 04).getTimeInMillis());
			timeCreationUpdate.setNanos(0);
			
			timeCreationList = new Timestamp(new GregorianCalendar(2011, 3, 5, 12, 00).getTimeInMillis());
			timeCreationList.setNanos(0);
			
			timeEvaluation = new Timestamp(new GregorianCalendar(2016, 1, 4, 17, 00).getTimeInMillis());
			timeEvaluation.setNanos(0);
			
			timeEvaluationUpdate = new Timestamp(new GregorianCalendar(2019, 5, 11, 6, 05).getTimeInMillis());
			timeEvaluationUpdate.setNanos(0);
			
			timeEvaluationList = new Timestamp(new GregorianCalendar(2014, 6, 13, 19, 00).getTimeInMillis());
			timeEvaluationList.setNanos(0);
			
			startDate = new GregorianCalendar(2016, 3, 7).getTime();
			endDate = new GregorianCalendar(2015, 7, 2).getTime();
			
			Date date = new GregorianCalendar(2015, 7, 2).getTime();
			
			discipline = new Discipline(1,1,"Menu","Name",1,"Tipo198");
			discipline.setDisciplineId(disciplineDAO.insert(discipline));
			
			user = new User(1,"Name","pass123","email");
			user.setUserId(userDAO.insert(user));
			
			userUpdate = new User(2,"Name1","pass321","email1");
			userUpdate.setUserId(userDAO.insert(userUpdate));
			
			userList = new User(3,"Name2","pass111","email2");
			userList.setUserId(userDAO.insert(userList));
			
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
			
			teachingPlan = new TeachingPlan(1, "Objetivo", "TeachPro", "Recov", "Observ", user, academicPeriod, ppcDiscipline);
			teachingPlan.setTeachingPlanId(teachingPlanDAO.insert(teachingPlan));
			
			role = new Role(1, "Role 1", "Observação 1");
			role.setRoleId(roleDAO.insert(role));
			
			userRole = new UserRole(1, startDate, endDate, role, user);
			userRole.setUserRoleId(userRoleDAO.insert(userRole));
			
			responsible = new Responsible(userRole, teachingPlan);
			responsible.setResponsibleId(responsibleDAO.insert(responsible));
			
			responsibleUpdate = new Responsible(userRole, teachingPlan);
			responsibleUpdate.setResponsibleId(responsibleDAO.insert(responsibleUpdate));
			
			responsibleList = new Responsible(userRole, teachingPlan);
			responsibleList.setResponsibleId(responsibleDAO.insert(responsibleUpdate));
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try{
			responsibleDAO.delete(responsible.getResponsibleId());
			responsibleDAO.delete(responsibleUpdate.getResponsibleId());
			responsibleDAO.delete(responsibleList.getResponsibleId());
			userRoleDAO.delete(userRole.getUserRoleId());
			roleDAO.delete(role.getRoleId());			
			teachingPlanDAO.delete(teachingPlan.getTeachingPlanId());
			academicPeriodDAO.delete(academicPeriod.getAcademicPeriodId());
			ppcDisciplineDAO.delete(ppcDiscipline.getPpcDisciplineId());
			disciplineDAO.delete(discipline.getDisciplineId());
			userDAO.delete(user.getUserId());
			userDAO.delete(userUpdate.getUserId());
			userDAO.delete(userList.getUserId());
			ppcDAO.delete(ppc.getPpcId());
			courseDAO.delete(course.getCourseId());
			traineeshipDAO.delete(traineeship.getTraineeshipId());
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}

	@Test
	public void testInsertVerification(){		
		System.out.println("Test Insert Verification...");		

		try {		
			assertEquals(0, verificationDAO.list().size());
			
			Verification vo = new Verification(1,timeCreation,timeEvaluation,"observation",1, responsible);
			vo.setVerificationId(verificationDAO.insert(vo));
			
			assertEquals(1, verificationDAO.list().size());
						
			Verification retrieved = verificationDAO.getById(vo.getVerificationId());
						
			assertEquals(vo.getCreationDate(), retrieved.getCreationDate());
			assertEquals(vo.getEvaluationDate(), retrieved.getEvaluationDate());
			assertEquals(vo.getObservation(), retrieved.getObservation());
			assertEquals(vo.getStatus(), retrieved.getStatus());	
			assertEquals(vo.getResponsible().getUserRole().getUser().getUserId(), retrieved.getResponsible().getUserRole().getUser().getUserId());			
			assertEquals(vo.getResponsible().getTeachingPlan().getTeachingPlanId(), retrieved.getResponsible().getTeachingPlan().getTeachingPlanId());
			
			verificationDAO.delete(vo.getVerificationId());
			
			assertEquals(0, verificationDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Verification finished...");		
	}
	
	@Test
	public void testUpdateVerification(){		
		System.out.println("Test Update Verification...");		

		try {			
			assertEquals(0, verificationDAO.list().size());
			
			Verification vo = new Verification(1,timeCreation,timeEvaluation,"observation",1, responsible);
			vo.setVerificationId(verificationDAO.insert(vo));			
			
			assertEquals(1, verificationDAO.list().size());			
			
			vo.setCreationDate(timeCreationUpdate);
			vo.setEvaluationDate(timeEvaluationUpdate);
			vo.setObservation("Observaion1");
			vo.setStatus(2);
			vo.setResponsible(responsibleUpdate);

			verificationDAO.update(vo);
			
			Verification retrieved = verificationDAO.getById(vo.getVerificationId());
			
			assertEquals(vo.getCreationDate(), retrieved.getCreationDate());
			assertEquals(vo.getEvaluationDate(), retrieved.getEvaluationDate());
			assertEquals(vo.getObservation(), retrieved.getObservation());
			assertEquals(vo.getStatus(), retrieved.getStatus());	
			assertEquals(vo.getResponsible().getUserRole().getUser().getUserId(), retrieved.getResponsible().getUserRole().getUser().getUserId());			
			assertEquals(vo.getResponsible().getTeachingPlan().getTeachingPlanId(), retrieved.getResponsible().getTeachingPlan().getTeachingPlanId());

			verificationDAO.delete(vo.getVerificationId());
			
			assertEquals(0, verificationDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Verification finished...");		
	}
	
	@Test
	public void testListAndGetById(){		
		System.out.println("Test Verification List and GetById...");		

		try {
			assertEquals(0, verificationDAO.list().size());
			
			Verification vo = new Verification(1,timeCreation,timeEvaluation,"observation 1 lst",1, responsible);
			vo.setVerificationId(verificationDAO.insert(vo));

			Verification vo1 = new Verification(2,timeCreationList,timeEvaluationList,"observation 2 lst",2, responsibleList);
			vo1.setVerificationId(verificationDAO.insert(vo1));
			
			assertEquals(2, verificationDAO.list().size());

			Assert.assertNotNull(verificationDAO.getById(vo.getVerificationId()));
			assertEquals(vo.getVerificationId(), 
					verificationDAO.getById(vo.getVerificationId()).getVerificationId());
			
			Assert.assertNotNull(verificationDAO.getById(vo1.getVerificationId()));
			assertEquals(vo1.getVerificationId(), 
					verificationDAO.getById(vo1.getVerificationId()).getVerificationId());
			
			verificationDAO.delete(vo.getVerificationId());
			verificationDAO.delete(vo1.getVerificationId());
			
			assertEquals(0, verificationDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Verification List and GetById finished...");		
	}
}