/*
* File:	     CampusCourseDAOTest.java
* Creation date: 03/07/2015 
* Course:        PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.CampusCourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CampusDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.RectoryDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Campus;
import br.edu.pcs.ifsulmg.sisa.vo.CampusCourse;
import br.edu.pcs.ifsulmg.sisa.vo.Course;
import br.edu.pcs.ifsulmg.sisa.vo.Rectory;

public class CampusCourseDAOTest {

	CampusDAO campusDAO;
	CourseDAO courseDAO;
	RectoryDAO rectoryDAO;
	CampusCourseDAO campusCourseDAO;
	
	Course course;
	Course courseUpdate;
	Rectory rectory;
	Campus campus;
	Campus campusUpdate;
	Campus campusList;

	@Before
	public void setUp() throws Exception {
		try{
			campusDAO = new CampusDAO();
			courseDAO = new CourseDAO();
			rectoryDAO = new RectoryDAO();
			campusCourseDAO = new CampusCourseDAO();
			
			course = new Course(1,"Informatica","INF",5,4);
			course.setCourseId(courseDAO.insert(course));

			courseUpdate = new Course(2,"Eletrotécnica2","ELETRO2",3,5);
			courseUpdate.setCourseId(courseDAO.insert(courseUpdate));

			rectory = new Rectory(1,"1","Reitoria1");
			rectory.setRectoryId(rectoryDAO.insert(rectory));			
			
			campus = new Campus(1,"Curso1","1",rectory);
			campus.setCampusId(campusDAO.insert(campus));
			
			campusUpdate = new Campus(2,"Curso2","2",rectory);
			campusUpdate.setCampusId(campusDAO.insert(campusUpdate));
			
			campusList = new Campus(3,"Curso3","3",rectory);
			campusList.setCampusId(campusDAO.insert(campusList));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		try{
			courseDAO.delete(course.getCourseId());
			courseDAO.delete(courseUpdate.getCourseId());
			campusDAO.delete(campus.getCampusId());
			campusDAO.delete(campusUpdate.getCampusId());
			campusDAO.delete(campusList.getCampusId());
			rectoryDAO.delete(rectory.getRectoryId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertCampusCourse() {
		System.out.println("Test Insert Campus Course...");
		
		try{
			assertEquals(0, campusCourseDAO.list().size());
			
			CampusCourse vo = new CampusCourse(course,campus);
			campusCourseDAO.insert(vo);
			
			assertEquals(1, campusCourseDAO.list().size());
			
			CampusCourse retrieved = campusCourseDAO.getByMembersIds(vo);
			
			assertEquals(vo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			assertEquals(vo.getCampus().getCampusId(), retrieved.getCampus().getCampusId());
			
			campusCourseDAO.delete(vo);
						
			assertEquals(0, campusCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		System.out.println("Test Insert Campus Course finished...");		
	}
	
	@Test
	public void testCampusUpdateCampusCourse(){
		System.out.println("Test Campus Update Campus Course...");
		
		try{			
			assertEquals(0, campusCourseDAO.list().size());
			
			CampusCourse vo = new CampusCourse(course, campus);						
			campusCourseDAO.insert(vo);
			
			assertEquals(1, campusCourseDAO.list().size());
			
			CampusCourse newVo = new CampusCourse(course,campusUpdate);	
			campusCourseDAO.update(vo, newVo);
			
			CampusCourse retrieved = campusCourseDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			assertEquals(newVo.getCampus().getCampusId(), retrieved.getCampus().getCampusId());
			
			campusCourseDAO.delete(newVo);
			
			assertEquals(0, campusCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Campus Update Campus Course finished...");
	}
	
	@Test
	public void testCourseUpdateCampusCourse(){
		System.out.println("Test Course Update Campus Course...");
		
		try{			
			assertEquals(0, campusCourseDAO.list().size());
			
			CampusCourse vo = new CampusCourse(course, campus);						
			campusCourseDAO.insert(vo);
			
			assertEquals(1, campusCourseDAO.list().size());
			
			CampusCourse newVo = new CampusCourse(courseUpdate, campus);	
			campusCourseDAO.update(vo, newVo);
			
			CampusCourse retrieved = campusCourseDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			assertEquals(newVo.getCampus().getCampusId(), retrieved.getCampus().getCampusId());
			
			campusCourseDAO.delete(newVo);
			
			assertEquals(0, campusCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Course Update Campus Course finished...");
	}
	
	@Test
	public void testListAndGetById(){
		System.out.println("Test Campus Course List and GetById... ");
		
		try{
			assertEquals(0, campusCourseDAO.list().size());
			
			CampusCourse vo = new CampusCourse(course,campus);
			CampusCourse vo1 = new CampusCourse(course,campusList);
			
			campusCourseDAO.insert(vo);
			campusCourseDAO.insert(vo1);
			
			assertEquals(2, campusCourseDAO.list().size());
			
			Assert.assertNotNull(campusCourseDAO.getByMembersIds(vo));
			assertEquals(vo.getCourse().getCourseId(), 
					campusCourseDAO.getByMembersIds(vo).getCourse().getCourseId());
			
			Assert.assertNotNull(campusCourseDAO.getByMembersIds(vo1));
			assertEquals(vo1.getCourse().getCourseId(), 
					campusCourseDAO.getByMembersIds(vo1).getCourse().getCourseId());
			
			campusCourseDAO.delete(vo);
			campusCourseDAO.delete(vo1);
			
			assertEquals(0, campusCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Campus Course List and GetById finished...");
	}
}