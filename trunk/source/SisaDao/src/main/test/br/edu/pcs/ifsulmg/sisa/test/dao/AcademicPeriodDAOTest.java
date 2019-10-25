/*
* File:	     AcademicPeriodDAOTest.java
* Creation date: 26/06/2015 
* Author:        Rarvs
*/

package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.AcademicPeriodDAO;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;

public class AcademicPeriodDAOTest {

	AcademicPeriodDAO academicPeriodDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			academicPeriodDAO = new AcademicPeriodDAO();
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
	public void testInsertAcademicPeriod() {
		System.out.println("Test Insert Academic Period...");

		try {
			assertEquals(0, academicPeriodDAO.list().size());

			AcademicPeriod vo = new AcademicPeriod(1, 1, 1, "Type 1");
			vo.setAcademicPeriodId(academicPeriodDAO.insert(vo));

			assertEquals(1, academicPeriodDAO.list().size());

			AcademicPeriod retrieved = academicPeriodDAO.getById(vo.getAcademicPeriodId());

			assertEquals(vo.getYear(), retrieved.getYear());
			assertEquals(vo.getSemester(), retrieved.getSemester());
			assertEquals(vo.getType(), retrieved.getType());

			academicPeriodDAO.delete(vo.getAcademicPeriodId());

			assertEquals(0, academicPeriodDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Academic Period finished...");
	}

	@Test
	public void testUpdateAcademicPeriod() {
		System.out.println("Test Update Academic Period...");

		try {
			assertEquals(0, academicPeriodDAO.list().size());

			AcademicPeriod vo = new AcademicPeriod(1, 11, 11,"Type 1Up");
			vo.setAcademicPeriodId(academicPeriodDAO.insert(vo));

			assertEquals(1, academicPeriodDAO.list().size());

			vo.setYear(11);
			vo.setSemester(11);
			vo.setType("Type 1Up");

			academicPeriodDAO.update(vo);

			AcademicPeriod retrieved = academicPeriodDAO.getById(vo.getAcademicPeriodId());

			assertEquals(vo.getYear(), retrieved.getYear());
			assertEquals(vo.getSemester(), retrieved.getSemester());
			assertEquals(vo.getType(),retrieved.getType());

			academicPeriodDAO.delete(vo.getAcademicPeriodId());

			assertEquals(0, academicPeriodDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Update Academic Period finished...");
	}

	@Test
	public void testListAndGetById() {
		System.out.println("Test Academic Period List and GetById...");

		try {
			assertEquals(0, academicPeriodDAO.list().size());

			AcademicPeriod vo = new AcademicPeriod(1, 1, 1, "Type 1");
			AcademicPeriod vo1 = new AcademicPeriod(2, 2, 2,"Type 2");
			AcademicPeriod vo2 = new AcademicPeriod(3, 3, 3,"Type 3");

			vo.setAcademicPeriodId(academicPeriodDAO.insert(vo));
			vo1.setAcademicPeriodId(academicPeriodDAO.insert(vo1));
			vo2.setAcademicPeriodId(academicPeriodDAO.insert(vo2));

			assertEquals(3, academicPeriodDAO.list().size());

			Assert.assertNotNull(academicPeriodDAO.getById(vo.getAcademicPeriodId()));
			assertEquals(vo.getYear(), academicPeriodDAO.getById(vo.getAcademicPeriodId()).getYear());

			Assert.assertNotNull(academicPeriodDAO.getById(vo1.getAcademicPeriodId()));
			assertEquals(vo1.getSemester(), academicPeriodDAO.getById(vo1.getAcademicPeriodId()).getSemester());
			
			Assert.assertNotNull(academicPeriodDAO.getById(vo2.getAcademicPeriodId()));
			assertEquals(vo2.getType(), academicPeriodDAO.getById(vo2.getAcademicPeriodId()).getType());

			academicPeriodDAO.delete(vo.getAcademicPeriodId());
			academicPeriodDAO.delete(vo1.getAcademicPeriodId());
			academicPeriodDAO.delete(vo2.getAcademicPeriodId());

			assertEquals(0, academicPeriodDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Academic Period List and GetById finished...");
	}
}