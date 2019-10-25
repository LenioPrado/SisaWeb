/*
* File:	     AuthorDAOTest.java
* Creation date: 26/06/2015 
* Author:       PagodeiroDoMal
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.AcademicPeriodCourseDAO;
import br.edu.pcs.ifsulmg.sisa.dao.AcademicPeriodDAO;
import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriodCourse;
import br.edu.pcs.ifsulmg.sisa.vo.Course;

public class AcademicPeriodCourseDAOTest {
	
	AcademicPeriodDAO academicPeriodDAO;
	CourseDAO courseDAO;
	AcademicPeriodCourseDAO academicPeriodCourseDAO;
	
	AcademicPeriod academicPeriod;
	AcademicPeriod academicPeriodUpdate;
	AcademicPeriod academicPeriodList;
	Course course;
	Course courseUpdate;
	Course courseList;
	
	@Before
	public void setUp() throws Exception {
		try{
			academicPeriodDAO = new AcademicPeriodDAO();
			courseDAO = new CourseDAO();
			academicPeriodCourseDAO = new AcademicPeriodCourseDAO();
			
			academicPeriod = new AcademicPeriod(1,2105,2,"Noturno");
			academicPeriod.setAcademicPeriodId(academicPeriodDAO.insert(academicPeriod));
			
			academicPeriodUpdate = new AcademicPeriod(2, 2016, 1, "Diurno");
			academicPeriodUpdate.setAcademicPeriodId(academicPeriodDAO.insert(academicPeriodUpdate));
			
			academicPeriodList = new AcademicPeriod(3,2100,88,"Vespertino");
			academicPeriodList.setAcademicPeriodId(academicPeriodDAO.insert(academicPeriodList));
			
			course = new Course(1,"Curso 1","Curso abreviado1",2,3);
			course.setCourseId(courseDAO.insert(course));
			
			courseUpdate = new Course(2,"Curso 2","Curso Abreviado 2",66,77);
			courseUpdate.setCourseId(courseDAO.insert(courseUpdate));
			
			courseList = new Course(3,"Curso 3","Curso Abreviado 3",99,103);
			courseList.setCourseId(courseDAO.insert(courseList));
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
			academicPeriodDAO.delete(academicPeriod.getAcademicPeriodId());
			academicPeriodDAO.delete(academicPeriodUpdate.getAcademicPeriodId());
			academicPeriodDAO.delete(academicPeriodList.getAcademicPeriodId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertAcademicPeriodCourse() {
		System.out.println("Test Insert Academic Period Course...");
		
		try{			
			assertEquals(0, academicPeriodCourseDAO.list().size());			
			
			AcademicPeriodCourse vo = new AcademicPeriodCourse(academicPeriod, course);
			academicPeriodCourseDAO.insert(vo);
			
			assertEquals(1, academicPeriodCourseDAO.list().size());
									
			AcademicPeriodCourse retrieved = academicPeriodCourseDAO.getByMembersIds(vo);
			
			assertEquals(vo.getAcademicPeriod().getAcademicPeriodId(), retrieved.getAcademicPeriod().getAcademicPeriodId());
			assertEquals(vo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			
			academicPeriodCourseDAO.delete(vo);
			
			assertEquals(0, academicPeriodCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		System.out.println("Test Insert Academic Period Course finished...");		
	}
	
	@Test
	public void testAcademicPeriodUpdateAcademicPeriodCourse(){
		System.out.println("Test Academic Period Update Academic Period Course...");
		
		try{			
			assertEquals(0, academicPeriodCourseDAO.list().size());
			
			AcademicPeriodCourse vo = new AcademicPeriodCourse(academicPeriod, course);
			
			academicPeriodCourseDAO.insert(vo);
			
			assertEquals(1, academicPeriodCourseDAO.list().size());
			
			AcademicPeriodCourse newVo = new AcademicPeriodCourse(academicPeriodUpdate, course);			
			academicPeriodCourseDAO.update(vo, newVo);
			
			AcademicPeriodCourse retrieved = academicPeriodCourseDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			assertEquals(newVo.getAcademicPeriod().getAcademicPeriodId(), retrieved.getAcademicPeriod().getAcademicPeriodId());
			
			academicPeriodCourseDAO.delete(newVo);
			
			assertEquals(0, academicPeriodCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Academic Period Update Academic Period Course finished...");
	}
	
	@Test
	public void testCourseUpdateAcademicPeriodCourse(){
		System.out.println("Test Course Update Academic Period Course...");
		
		try{			
			assertEquals(0, academicPeriodCourseDAO.list().size());
			
			AcademicPeriodCourse vo = new AcademicPeriodCourse(academicPeriod, course);			
			academicPeriodCourseDAO.insert(vo);
			
			assertEquals(1, academicPeriodCourseDAO.list().size());
			
			AcademicPeriodCourse newVo = new AcademicPeriodCourse(academicPeriod, courseUpdate);
			
			academicPeriodCourseDAO.update(vo, newVo);
			
			AcademicPeriodCourse retrieved = academicPeriodCourseDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getCourse().getCourseId(), retrieved.getCourse().getCourseId());
			assertEquals(newVo.getAcademicPeriod().getAcademicPeriodId(), retrieved.getAcademicPeriod().getAcademicPeriodId());
			
			academicPeriodCourseDAO.delete(newVo);
		
			assertEquals(0, academicPeriodCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Course Update Academic Period Course finished...");
	}
	
	@Test
	public void testListAndGetById(){
		System.out.println("Test Academic Period Course List and GetById... ");
		
		try{
			assertEquals(0, academicPeriodCourseDAO.list().size());
						
			AcademicPeriodCourse vo = new AcademicPeriodCourse (academicPeriod, course);
			AcademicPeriodCourse vo1 = new AcademicPeriodCourse(academicPeriodList, courseList);
						
			academicPeriodCourseDAO.insert(vo);		
			academicPeriodCourseDAO.insert(vo1);
			
			assertEquals(2, academicPeriodCourseDAO.list().size());
			
			Assert.assertNotNull(academicPeriodCourseDAO.getByMembersIds(vo));
			assertEquals(vo.getCourse().getCourseId(), 
					academicPeriodCourseDAO.getByMembersIds(vo).getCourse().getCourseId());
			
			Assert.assertNotNull(academicPeriodCourseDAO.getByMembersIds(vo1));
			assertEquals(vo1.getCourse().getCourseId(), 
					academicPeriodCourseDAO.getByMembersIds(vo1).getCourse().getCourseId());
			
			academicPeriodCourseDAO.delete(vo);
			academicPeriodCourseDAO.delete(vo1);
			
			assertEquals(0, academicPeriodCourseDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Academic Period Course List and GetById finished...");
	}
}