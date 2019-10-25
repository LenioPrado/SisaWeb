/*
* File:	     PpcDAOTest.java
* Creation date: 02/07/2015 
* Ppc:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;

public class PpcDAOTest {

	PpcDAO ppcDAO;
	CourseDAO courseDAO;
	TraineeshipDAO traineeshipDAO; 
	
	Date date;
	Date dateUpdate;
	Date dateList;
	
	Course course;
	Course courseUpdate;
	Course courseList;
	Traineeship traineeship;
	Traineeship traineeshipUpdate;
	Traineeship traineeshipList;
	
	@Before
	public void setUp() throws Exception {
		try{
			ppcDAO = new PpcDAO();
			courseDAO = new CourseDAO();
			traineeshipDAO = new TraineeshipDAO();
			
			date = new GregorianCalendar(2015, 7, 2).getTime();
			dateUpdate = new GregorianCalendar(2015, 8, 2).getTime();
			dateList = new GregorianCalendar(2012, 2, 14).getTime();
			
			course = new Course(1, "Curso 1","CA1",7, 6);
			course.setCourseId(courseDAO.insert(course));

			courseUpdate = new Course(2, "Curso 2 Update","CA2U",99, 33);
			courseUpdate.setCourseId(courseDAO.insert(courseUpdate));

			courseList = new Course(3, "Curso 3 Update","CA3U",45, 21);
			courseList.setCourseId(courseDAO.insert(courseList));

			traineeship = new Traineeship(1, 80, "Descrição 1", "Descrição Abreviada 1");
			traineeship.setTraineeshipId(traineeshipDAO.insert(traineeship));
			
			traineeshipUpdate = new Traineeship(2, 90, "Descrição 2 Update", "Descrição Abreviada 2 Update");
			traineeshipUpdate.setTraineeshipId(traineeshipDAO.insert(traineeshipUpdate));
			
			traineeshipList = new Traineeship(3, 190, "Descrição 3 List", "Descrição Abreviada 3 List");
			traineeshipList.setTraineeshipId(traineeshipDAO.insert(traineeshipList));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try{
			courseDAO.delete(course.getCourseId());
			courseDAO.delete(courseUpdate.getCourseId());
			courseDAO.delete(courseList.getCourseId());
			traineeshipDAO.delete(traineeship.getTraineeshipId());
			traineeshipDAO.delete(traineeshipUpdate.getTraineeshipId());
			traineeshipDAO.delete(traineeshipList.getTraineeshipId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertPpc(){		
		System.out.println("Test Insert Ppc...");		

		try {
			assertEquals(0, ppcDAO.list().size());
			
			Ppc vo = new Ppc(1, date, "Local 1", 10, 4, course, traineeship);
			vo.setPpcId(ppcDAO.insert(vo));
			
			assertEquals(1, ppcDAO.list().size());
			
			Ppc retrieved = ppcDAO.getById(vo.getPpcId());

			assertEquals(vo.getDate(), retrieved.getDate());			
			assertEquals(vo.getPlace(), retrieved.getPlace());
			assertEquals(vo.getClassHour(), retrieved.getClassHour());
			assertEquals(vo.getWeeksNumber(), retrieved.getWeeksNumber());
			assertEquals(vo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			assertEquals(vo.getTraineeship().getTraineeshipId(), retrieved.getTraineeship().getTraineeshipId());
			
			ppcDAO.delete(vo.getPpcId());
			
			assertEquals(0, ppcDAO.list().size());
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Ppc finished...");		
	}

	@Test
	public void testUpdatePpc(){		
		System.out.println("Test Update Ppc...");		

		try {			
			assertEquals(0, ppcDAO.list().size());

			Ppc vo = new Ppc(1, date, "Local 1 Update", 10, 4, course, traineeship);
			vo.setPpcId(ppcDAO.insert(vo));		
			
			vo.setDate(dateUpdate);
			vo.setPlace("Local 2 Update");
			vo.setClassHour(20);
			vo.setWeeksNumber(9);
			vo.setCourse(courseUpdate);
			vo.setTraineeship(traineeshipUpdate);
			
			ppcDAO.update(vo);
			
			Ppc retrieved = ppcDAO.getById(vo.getPpcId());
			
			assertEquals(vo.getDate(), retrieved.getDate());			
			assertEquals(vo.getPlace(), retrieved.getPlace());
			assertEquals(vo.getClassHour(), retrieved.getClassHour());
			assertEquals(vo.getWeeksNumber(), retrieved.getWeeksNumber());
			assertEquals(vo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			assertEquals(vo.getTraineeship().getTraineeshipId(), retrieved.getTraineeship().getTraineeshipId());
			
			ppcDAO.delete(vo.getPpcId());
			
			assertEquals(0, ppcDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Ppc finished...");		
	}
	
	@Test
	public void testListAndGetById(){		
		System.out.println("Test Ppc List and GetById...");		

		try {
			assertEquals(0, ppcDAO.list().size());

			Ppc vo = new Ppc(1, date, "Local 1 List", 10, 4, course, traineeship);
			Ppc vo1 = new Ppc(2, dateList, "Local 2 List", 20, 5, courseList, traineeshipList);
			
			vo.setPpcId(ppcDAO.insert(vo));
			vo1.setPpcId(ppcDAO.insert(vo1));
			
			assertEquals(2, ppcDAO.list().size());
			
			Assert.assertNotNull(ppcDAO.getById(vo.getPpcId()));
			assertEquals(vo.getPlace(), ppcDAO.getById(vo.getPpcId()).getPlace());
			
			Assert.assertNotNull(ppcDAO.getById(vo1.getPpcId()));
			assertEquals(vo1.getPlace(), ppcDAO.getById(vo1.getPpcId()).getPlace());
			
			ppcDAO.delete(vo.getPpcId());
			ppcDAO.delete(vo1.getPpcId());
			
			assertEquals(0, ppcDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Ppc List and GetById finished...");		
	}
}