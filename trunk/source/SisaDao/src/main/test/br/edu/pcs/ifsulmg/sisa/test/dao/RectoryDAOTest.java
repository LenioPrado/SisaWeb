/*
 * File:	     RectoryDAOTest.java
 * Creation date: 03/07/2015 
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.RectoryDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Rectory;

public class RectoryDAOTest {

	RectoryDAO rectoryDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			rectoryDAO = new RectoryDAO();
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
	public void testInsertRectory() {
		System.out.println("Test Insert Rectory...");

		try {
			assertEquals(0, rectoryDAO.list().size());

			Rectory vo = new Rectory(1,"CNPJ","Nome1");
			vo.setRectoryId(rectoryDAO.insert(vo));

			assertEquals(1, rectoryDAO.list().size());

			Rectory retrieved = rectoryDAO.getById(vo.getRectoryId());

			assertEquals(vo.getCnpj(), retrieved.getCnpj());
			assertEquals(vo.getName(), retrieved.getName());

			rectoryDAO.delete(vo.getRectoryId());

			assertEquals(0, rectoryDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Rectory finished...");
	}

	@Test
	public void testUpdateRectory() {
		System.out.println("Test Update Rectory...");

		try {
			assertEquals(0, rectoryDAO.list().size());

			Rectory vo = new Rectory(1,"CNPJ","Name1");
			vo.setRectoryId(rectoryDAO.insert(vo));

			assertEquals(1, rectoryDAO.list().size());

			vo.setCnpj("CnpjUP");
			vo.setName("Name1UP");

			rectoryDAO.update(vo);

			Rectory retrieved = rectoryDAO.getById(vo.getRectoryId());

			assertEquals(vo.getCnpj(), retrieved.getCnpj());
			assertEquals(vo.getName(), retrieved.getName());

			rectoryDAO.delete(vo.getRectoryId());

			assertEquals(0, rectoryDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Update Rectory finished...");
	}

	@Test
	public void testListAndGetById() {
		System.out.println("Test Rectory List and GetById...");

		try {
			assertEquals(0, rectoryDAO.list().size());

			Rectory vo = new Rectory(1,"Name1","CNPJ1");
			Rectory vo1 = new Rectory(1,"Name2","CNPJ2");

			vo.setRectoryId(rectoryDAO.insert(vo));
			vo1.setRectoryId(rectoryDAO.insert(vo1));

			assertEquals(2, rectoryDAO.list().size());

			Assert.assertNotNull(rectoryDAO.getById(vo.getRectoryId()));
			assertEquals(vo.getCnpj(), rectoryDAO.getById(vo.getRectoryId()).getCnpj());

			Assert.assertNotNull(rectoryDAO.getById(vo1.getRectoryId()));
			assertEquals(vo1.getName(),rectoryDAO.getById(vo1.getRectoryId()).getName());			

			rectoryDAO.delete(vo.getRectoryId());
			rectoryDAO.delete(vo1.getRectoryId());

			assertEquals(0, rectoryDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Rectory List and GetById finished...");
	}
}