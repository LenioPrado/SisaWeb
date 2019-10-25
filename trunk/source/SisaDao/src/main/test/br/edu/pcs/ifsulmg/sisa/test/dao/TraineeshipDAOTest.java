/*
 * File:	     TraineeshipDAOTest.java
 * Creation date: 06/07/2015 
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.TraineeshipDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;

public class TraineeshipDAOTest {

	TraineeshipDAO traineeshipDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			traineeshipDAO = new TraineeshipDAO();
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
	public void testInsertTraineeship() {
		System.out.println("Test Insert Traineeship...");

		try {
			assertEquals(0, traineeshipDAO.list().size());

			Traineeship vo = new Traineeship(1,2,"desc","descrição abreviada");
			vo.setTraineeshipId(traineeshipDAO.insert(vo));

			assertEquals(1, traineeshipDAO.list().size());

			Traineeship retrieved = traineeshipDAO.getById(vo.getTraineeshipId());

			assertEquals(vo.getHourLoad(), retrieved.getHourLoad());
			assertEquals(vo.getDescription(), retrieved.getDescription());

			traineeshipDAO.delete(vo.getTraineeshipId());

			assertEquals(0, traineeshipDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Traineeship finished...");
	}

	@Test
	public void testUpdateTraineeship() {
		System.out.println("Test Update Traineeship...");

		try {
			assertEquals(0, traineeshipDAO.list().size());

			Traineeship vo = new Traineeship(1,2,"desc1","descrição abreviada 1");
			vo.setTraineeshipId(traineeshipDAO.insert(vo));

			assertEquals(1, traineeshipDAO.list().size());

			vo.setHourLoad(12);
			vo.setDescription("desc1Up");

			traineeshipDAO.update(vo);

			Traineeship retrieved = traineeshipDAO.getById(vo.getTraineeshipId());

			assertEquals(vo.getHourLoad(), retrieved.getHourLoad());
			assertEquals(vo.getDescription(), retrieved.getDescription());
			
			traineeshipDAO.delete(vo.getTraineeshipId());

			assertEquals(0, traineeshipDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Update Traineeship finished...");
	}

	@Test
	public void testListAndGetById() {
		System.out.println("Test Traineeship List and GetById...");

		try {
			assertEquals(0, traineeshipDAO.list().size());

			Traineeship vo = new Traineeship(1,3,"desc2","descrição abreviada 2");
			Traineeship vo1 = new Traineeship(2,4,"desc4","descrição abreviada 4");

			vo.setTraineeshipId(traineeshipDAO.insert(vo));
			vo1.setTraineeshipId(traineeshipDAO.insert(vo1));
			
			assertEquals(2, traineeshipDAO.list().size());
			
			Assert.assertNotNull(traineeshipDAO.getById(vo.getTraineeshipId()));
			assertEquals(vo.getHourLoad(),traineeshipDAO.getById(vo.getTraineeshipId()).getHourLoad());

			Assert.assertNotNull(traineeshipDAO.getById(vo1.getTraineeshipId()));
			assertEquals(vo1.getDescription(),traineeshipDAO.getById(vo1.getTraineeshipId()).getDescription());
			
			traineeshipDAO.delete(vo.getTraineeshipId());
			traineeshipDAO.delete(vo1.getTraineeshipId());

			assertEquals(0, traineeshipDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Traineeship List and GetById finished...");
	}
}