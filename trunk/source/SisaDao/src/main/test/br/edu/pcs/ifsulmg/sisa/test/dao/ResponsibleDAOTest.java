/*
 * File:	     ResponsibleDAOTest.java
 * Creation date: 08/07/2015 
 * Author:        Lênio
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
import br.edu.pcs.ifsulmg.sisa.dao.PpcDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.ResponsibleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.RoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TeachingPlanDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserRoleDAO;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.vo.Responsible;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;

public class ResponsibleDAOTest {

	ResponsibleDAO responsibleDAO;
	UserDAO userDAO;
	RoleDAO roleDAO;
	DisciplineDAO disciplineDAO;
	TeachingPlanDAO teachingPlanDAO;
	UserRoleDAO userRoleDAO;
	CourseDAO courseDAO;
	TraineeshipDAO traineeshipDAO;
	PpcDAO ppcDAO;
	PpcDisciplineDAO ppcDisciplineDAO;
	AcademicPeriodDAO academicPeriodDAO;
	
	Date date;
	Date startDate;
	Date startDateUpdate;
	Date startDateList;
	Date endDate;
	Date endDateUpdate;
	Date endDateList;
	User user;
	User userUpdate;
	User userList;
	Role role;
	Role roleUpdate;
	Role roleList;
	Discipline discipline;
	UserRole userRole;
	UserRole userRoleUpdate;
	UserRole userRoleList;
	Course course;
	Traineeship traineeship;	
	Ppc ppc;
	AcademicPeriod academicPeriod;
	PpcDiscipline ppcDiscipline;
	TeachingPlan teachingPlan;	
	TeachingPlan teachingPlanUpdate;
	TeachingPlan teachingPlanList;
	
	@Before
	public void setUp() throws Exception {
		try{
			responsibleDAO = new ResponsibleDAO();
			userDAO = new UserDAO();
			roleDAO = new RoleDAO();
			disciplineDAO =  new DisciplineDAO();
			teachingPlanDAO = new TeachingPlanDAO();
			userRoleDAO = new UserRoleDAO();
			courseDAO = new CourseDAO();
			traineeshipDAO = new TraineeshipDAO();
			academicPeriodDAO = new AcademicPeriodDAO();
			ppcDAO = new PpcDAO();			
			ppcDisciplineDAO = new PpcDisciplineDAO();
			
			date = new GregorianCalendar(2015, 7, 2).getTime();
			
			endDate = new GregorianCalendar(2015, 7, 2).getTime();			
			startDate = new GregorianCalendar(2016, 3, 7).getTime();

			endDateUpdate = new GregorianCalendar(2011, 3, 11).getTime();
			startDateUpdate = new GregorianCalendar(2012, 6, 9).getTime();
			
			endDateList = new GregorianCalendar(2001, 1, 21).getTime();
			startDateList = new GregorianCalendar(2001, 3, 8).getTime();
			
			user = new User(1, "Name11", "Pass11", "Email11");
			user.setUserId(userDAO.insert(user));
			
			userUpdate = new User(2, "NameUpd2", "PassUpd2", "EmailUpd2");
			userUpdate.setUserId(userDAO.insert(userUpdate));
			
			userList = new User(3, "Name33", "Pass33", "Email33");
			userList.setUserId(userDAO.insert(userList));
	
			role = new Role(1, "Role 1", "Observação 1");
			role.setRoleId(roleDAO.insert(role));
			
			roleUpdate = new Role(2, "RoleUpd 2", "ObservaçãoUpd 2");
			roleUpdate.setRoleId(roleDAO.insert(roleUpdate));
			
			roleList = new Role(3, "Role 3", "Observação 3");
			roleList.setRoleId(roleDAO.insert(roleList));
			
			discipline = new Discipline(1, 5,"Menu1", "Disciplina11",98,"Tipo147");
			discipline.setDisciplineId(disciplineDAO.insert(discipline));
						
			userRole = new UserRole(1, startDate, endDate, role, user);
			userRole.setUserRoleId(userRoleDAO.insert(userRole));
			
			userRoleUpdate = new UserRole(2, startDateUpdate, endDateUpdate, roleUpdate, userUpdate);
			userRoleUpdate.setUserRoleId(userRoleDAO.insert(userRoleUpdate));
			
			userRoleList = new UserRole(3, startDateList, endDateList, roleList, userList);
			userRoleList.setUserRoleId(userRoleDAO.insert(userRoleList));
			
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
			
			teachingPlan = new TeachingPlan(1, "Objetivo1", "TeachPro1", "Recov1", "Observ1", user, academicPeriod, ppcDiscipline);
			teachingPlan.setTeachingPlanId(teachingPlanDAO.insert(teachingPlan));
			
			teachingPlanUpdate = new TeachingPlan(2, "Objetivo2", "TeachPro2", "Recov2", "Observ2", user, academicPeriod, ppcDiscipline);
			teachingPlanUpdate.setTeachingPlanId(teachingPlanDAO.insert(teachingPlanUpdate));
			
			teachingPlanList = new TeachingPlan(3, "Objetivo3", "TeachPro3", "Recov3", "Observ3", user, academicPeriod, ppcDiscipline);
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
			academicPeriodDAO.delete(academicPeriod.getAcademicPeriodId());
			ppcDisciplineDAO.delete(ppcDiscipline.getPpcDisciplineId());
			ppcDAO.delete(ppc.getPpcId());
			traineeshipDAO.delete(traineeship.getTraineeshipId());
			courseDAO.delete(course.getCourseId());
			userRoleDAO.delete(userRole.getUserRoleId());
			userRoleDAO.delete(userRoleUpdate.getUserRoleId());
			userRoleDAO.delete(userRoleList.getUserRoleId());
			userDAO.delete(user.getUserId());
			userDAO.delete(userUpdate.getUserId());
			userDAO.delete(userList.getUserId());
			roleDAO.delete(role.getRoleId());
			roleDAO.delete(roleUpdate.getRoleId());
			roleDAO.delete(roleList.getRoleId());
			disciplineDAO.delete(discipline.getDisciplineId());

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertResponsible() {
		System.out.println("Test Insert Responsible...");

		try {
			assertEquals(0, responsibleDAO.list().size());
			
			Responsible vo = new Responsible(1, userRole,teachingPlan);
			vo.setResponsibleId(responsibleDAO.insert(vo));

			assertEquals(1, responsibleDAO.list().size());

			Responsible retrieved = responsibleDAO.getById(vo.getResponsibleId());

			assertEquals(vo.getUserRole().getUserRoleId(), retrieved.getUserRole().getUserRoleId());
			assertEquals(vo.getTeachingPlan().getTeachingPlanId(), retrieved.getTeachingPlan().getTeachingPlanId());
			
			responsibleDAO.delete(vo.getResponsibleId());
			
			assertEquals(0, responsibleDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Responsible finished...");
	}

	@Test
	public void testUpdateResponsible() {
		System.out.println("Test Update Responsible...");

		try {
			assertEquals(0, responsibleDAO.list().size());

			Responsible vo = new Responsible(1, userRole,teachingPlan);
			vo.setResponsibleId(responsibleDAO.insert(vo));

			assertEquals(1, responsibleDAO.list().size());
			
			vo.setUserRole(userRoleUpdate);
			vo.setTeachingPlan(teachingPlanUpdate);
			
			responsibleDAO.update(vo);

			Responsible retrieved = responsibleDAO.getById(vo.getResponsibleId());

			assertEquals(vo.getUserRole().getUserRoleId(), retrieved.getUserRole().getUserRoleId());
			assertEquals(vo.getTeachingPlan().getTeachingPlanId(), retrieved.getTeachingPlan().getTeachingPlanId());

			responsibleDAO.delete(vo.getResponsibleId());
			
			assertEquals(0, responsibleDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Update Responsible finished...");
	}	

	@Test
	public void testListAndGetById() {
		System.out.println("Test Responsible List and GetById...");

		try {
			assertEquals(0, responsibleDAO.list().size());

			Responsible vo = new Responsible(userRole, teachingPlan);
			vo.setResponsibleId(responsibleDAO.insert(vo));

			Responsible vo1 = new Responsible(userRoleList, teachingPlanList);
			vo1.setResponsibleId(responsibleDAO.insert(vo1));

			assertEquals(2, responsibleDAO.list().size());

			Assert.assertNotNull(responsibleDAO.getById(vo.getResponsibleId()));
			assertEquals(vo.getUserRole().getUserRoleId(), 
					responsibleDAO.getById(vo.getResponsibleId()).getUserRole().getUserRoleId());
			assertEquals(vo.getTeachingPlan().getTeachingPlanId(), 
					responsibleDAO.getById(vo.getResponsibleId()).getTeachingPlan().getTeachingPlanId());
			
			Assert.assertNotNull(responsibleDAO.getById(vo.getResponsibleId()));
			assertEquals(vo1.getUserRole().getUserRoleId(), 
					responsibleDAO.getById(vo1.getResponsibleId()).getUserRole().getUserRoleId());
			assertEquals(vo1.getTeachingPlan().getTeachingPlanId(), 
					responsibleDAO.getById(vo1.getResponsibleId()).getTeachingPlan().getTeachingPlanId());			

			responsibleDAO.delete(vo.getResponsibleId());
			responsibleDAO.delete(vo1.getResponsibleId());
			
			assertEquals(0, responsibleDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Responsible List and GetById finished...");
	}
}