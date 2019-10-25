/*
* File:	     PpcDisciplineDAOTest.java
* Creation date: 03/07/2015 
* Course:        PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PpcDisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;


public class PpcDisciplineDAOTest {

	PpcDAO ppcDAO;
	DisciplineDAO disciplineDAO;
	PpcDisciplineDAO ppcDisciplineDAO;
	CourseDAO courseDAO;
	TraineeshipDAO traineeshipDAO;
	
	Timestamp time;
	Timestamp timeUpdate;
	Timestamp timeList;
	
	Course course;
	Traineeship traineeship;
	Ppc ppc;
	Ppc ppcUpdate;
	Ppc ppcList;
	Discipline discipline;
	Discipline disciplineUpdate;
	Discipline disciplineList;

	@Before
	public void setUp() throws Exception {
		try{
			ppcDAO = new PpcDAO();
			disciplineDAO = new DisciplineDAO();
			ppcDisciplineDAO = new PpcDisciplineDAO();
			courseDAO = new CourseDAO();
			traineeshipDAO = new TraineeshipDAO();
			
			time = new Timestamp(new GregorianCalendar(2015, 7, 2, 14, 00).getTimeInMillis());
			time.setNanos(0);
			
			timeUpdate = new Timestamp(new GregorianCalendar(2015, 8, 2, 17, 00).getTimeInMillis());
			timeUpdate.setNanos(0);
			
			timeList = new Timestamp(new GregorianCalendar(2012, 1, 9, 21, 00).getTimeInMillis());
			timeList.setNanos(0);
			
			course = new Course(1, "Curso 1","CA1",98, 56);
			course.setCourseId(courseDAO.insert(course));
			
			traineeship = new Traineeship(1, 80, "Descrição 1", "Descrição Abreviada 1");
			traineeship.setTraineeshipId(traineeshipDAO.insert(traineeship));
			
			ppc = new Ppc(1,  time, "Local 1", 10, 4, course, traineeship);
			ppc.setPpcId(ppcDAO.insert(ppc));
			
			ppcUpdate = new Ppc(2, timeUpdate, "Local 2", 21, 7, course, traineeship);
			ppcUpdate.setPpcId(ppcDAO.insert(ppcUpdate));
			
			ppcList = new Ppc(3, timeList, "Local 1", 10, 4, course, traineeship);
			ppcList.setPpcId(ppcDAO.insert(ppcList));
			
			discipline = new Discipline(1,1,"Menu 1","Matemática",4,"Tipo 1");
			discipline.setDisciplineId(disciplineDAO.insert(discipline));

			disciplineUpdate = new Discipline(2,65,"Menu 43","Física 3",14,"Tipo 22");
			disciplineUpdate.setDisciplineId(disciplineDAO.insert(disciplineUpdate));

			disciplineList = new Discipline(3,43,"Menu 9","Matemática 1",10,"Tipo 42");
			disciplineList.setDisciplineId(disciplineDAO.insert(disciplineList));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		try{
			disciplineDAO.delete(discipline.getDisciplineId());
			disciplineDAO.delete(disciplineUpdate.getDisciplineId());
			disciplineDAO.delete(disciplineList.getDisciplineId());
			ppcDAO.delete(ppc.getPpcId());
			ppcDAO.delete(ppcUpdate.getPpcId());
			ppcDAO.delete(ppcList.getPpcId());
			traineeshipDAO.delete(traineeship.getTraineeshipId());
			courseDAO.delete(course.getCourseId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertPpcDiscipline() {
		System.out.println("Test Insert Ppc Discipline...");
		
		try{
			assertEquals(0, ppcDisciplineDAO.list().size());
			
			PpcDiscipline vo = new PpcDiscipline(1, discipline, ppc);
			vo.setPpcDisciplineId(ppcDisciplineDAO.insert(vo));
			
			assertEquals(1, ppcDisciplineDAO.list().size());
			
			PpcDiscipline retrieved = ppcDisciplineDAO.getById(vo.getPpcDisciplineId());
			
			assertEquals(vo.getDiscipline().getDisciplineId(), retrieved.getDiscipline().getDisciplineId());
			assertEquals(vo.getPpc().getPpcId(), retrieved.getPpc().getPpcId());
						
			ppcDisciplineDAO.delete(vo.getPpcDisciplineId());			
			
			assertEquals(0, ppcDisciplineDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		System.out.println("Test Insert Ppc Discipline finished...");		
	}
	
	@Test
	public void testPpcDisciplineUpdatePpcDiscipline(){
		System.out.println("Test Discipline Update Ppc Discipline...");
		
		try{			
			assertEquals(0, ppcDisciplineDAO.list().size());

			PpcDiscipline vo = new PpcDiscipline(1, discipline, ppc);
			vo.setPpcDisciplineId(ppcDisciplineDAO.insert(vo));
			
			vo.setDiscipline(disciplineUpdate);
			vo.setPpc(ppc);
			
			ppcDisciplineDAO.update(vo);
			
			assertEquals(1, ppcDisciplineDAO.list().size());
			
			PpcDiscipline retrieved = ppcDisciplineDAO.getById(vo.getPpcDisciplineId());
			
			assertEquals(vo.getDiscipline().getDisciplineId(), retrieved.getDiscipline().getDisciplineId());
			assertEquals(vo.getPpc().getPpcId(), retrieved.getPpc().getPpcId());
			
			ppcDisciplineDAO.delete(vo.getPpcDisciplineId());
						
			assertEquals(0, ppcDisciplineDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Discipline Update Ppc Discipline finished...");
	}
	
	@Test
	public void testListAndGetById(){
		System.out.println("Test Ppc Discipline List and GetById... ");
		
		try{
			assertEquals(0, ppcDisciplineDAO.list().size());
			
			PpcDiscipline vo = new PpcDiscipline(9,discipline,ppc);
			PpcDiscipline vo1 = new PpcDiscipline(10,disciplineList,ppcList);
			
			vo.setPpcDisciplineId(ppcDisciplineDAO.insert(vo));
			vo1.setPpcDisciplineId(ppcDisciplineDAO.insert(vo1));
			
			assertEquals(2, ppcDisciplineDAO.list().size());
			
			Assert.assertNotNull(ppcDisciplineDAO.getById(vo.getPpcDisciplineId()));
			assertEquals(vo.getPpc(), ppcDAO.getById(vo.getPpc().getPpcId()));
			
			Assert.assertNotNull(ppcDAO.getById(vo.getPpc().getPpcId()));
			assertEquals(vo.getDiscipline(), ppcDisciplineDAO.getById(vo.getPpcDisciplineId()).getDiscipline());

			Assert.assertNotNull(ppcDisciplineDAO.getById(vo1.getPpcDisciplineId()));
			assertEquals(vo1.getPpc(), ppcDAO.getById(vo1.getPpc().getPpcId()));

			Assert.assertNotNull(ppcDAO.getById(vo1.getPpc().getPpcId()));
			assertEquals(vo1.getDiscipline(), ppcDisciplineDAO.getById(vo1.getPpcDisciplineId()).getDiscipline());

			ppcDisciplineDAO.delete(vo.getPpcDisciplineId());
			ppcDisciplineDAO.delete(vo1.getPpcDisciplineId());
			
			assertEquals(0, ppcDisciplineDAO.list().size());	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Ppc Discipline List and GetById finished...");
	}
}