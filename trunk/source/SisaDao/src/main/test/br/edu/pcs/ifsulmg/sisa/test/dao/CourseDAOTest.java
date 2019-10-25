/*
* File:	     CourseDAOTest.java
* Creation date: 24/06/2015 
* Course:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.CourseDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Course;

public class CourseDAOTest {

	CourseDAO courseDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			courseDAO = new CourseDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try{
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertCourse(){		
		System.out.println("Test Insert Course...");		

		try {
			assertEquals(0, courseDAO.list().size());
			
			Course vo = new Course(1, "Curso 1","CA1",5, 3);
			vo.setCourseId(courseDAO.insert(vo));
			
			assertEquals(1, courseDAO.list().size());
			
			Course retrieved = courseDAO.getById(vo.getCourseId());
			
			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getModality(), retrieved.getModality());
			assertEquals(vo.getShift(), retrieved.getShift());
			
			courseDAO.delete(vo.getCourseId());
			
			assertEquals(0, courseDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Course finished...");		
	}

	@Test
	public void testUpdateCourse(){		
		System.out.println("Test Update Course...");		

		try {			
			assertEquals(0, courseDAO.list().size());
		
			Course vo = new Course(1, "Curso 1","CA1",2, 3);
			vo.setCourseId(courseDAO.insert(vo));			
			
			assertEquals(1, courseDAO.list().size());			
			
			vo.setName("Curso 1 Alterado");
			vo.setModality(66);
			vo.setShift(77);
			
			courseDAO.update(vo);
			
			Course retrieved = courseDAO.getById(vo.getCourseId());
			
			assertEquals(vo.getName(), retrieved.getName());			
			assertEquals(vo.getModality(), retrieved.getModality());
			assertEquals(vo.getShift(), retrieved.getShift());

			courseDAO.delete(vo.getCourseId());
			
			assertEquals(0, courseDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Course finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Course List and GetById...");		

		try {			
			assertEquals(0, courseDAO.list().size());
			
			Course vo = new Course(1, "Curso 1","CA1",3, 4);
			Course vo1 = new Course(2, "Curso 2","CA2",5, 6);
			
			vo.setCourseId(courseDAO.insert(vo));			
			vo1.setCourseId(courseDAO.insert(vo1));
			
			assertEquals(2, courseDAO.list().size());

			Assert.assertNotNull(courseDAO.getById(vo.getCourseId()));
			assertEquals(vo.getName(), courseDAO.getById(vo.getCourseId()).getName());
			
			Assert.assertNotNull(courseDAO.getById(vo1.getCourseId()));
			assertEquals(vo1.getName(), courseDAO.getById(vo1.getCourseId()).getName());
			
			courseDAO.delete(vo.getCourseId());
			courseDAO.delete(vo1.getCourseId());			
			
			assertEquals(0, courseDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Course List and GetById finished...");		
	}
}