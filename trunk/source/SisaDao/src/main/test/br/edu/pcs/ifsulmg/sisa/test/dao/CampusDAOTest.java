/*
 * File:	     CampusDAOTest.java
 * Creation date: 26/06/2015 
 * Author:        Amanda
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.CampusDAO;
import br.edu.pcs.ifsulmg.sisa.dao.RectoryDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Campus;
import br.edu.pcs.ifsulmg.sisa.vo.Rectory;

public class CampusDAOTest {

	RectoryDAO rectoryDAO;
	CampusDAO campusDAO;
	
	Rectory rectory;
	Rectory rectoryUpdate;
	Rectory rectoryList;
	Rectory rectoryList1;
	
	@Before
	public void setUp() throws Exception {
		try{
			rectoryDAO = new RectoryDAO();
			campusDAO = new CampusDAO();
			
			rectory = new Rectory(1, "123", "NomeInsert");
			rectory.setRectoryId(rectoryDAO.insert(rectory));
			
			rectoryUpdate = new Rectory(2, "456", "NomeUpd123");
			rectoryUpdate.setRectoryId(rectoryDAO.insert(rectoryUpdate));
			
			rectoryList = new Rectory(1, "213", "Nome3");
			rectoryList.setRectoryId(rectoryDAO.insert(rectoryList));
			
			rectoryList1 = new Rectory(3, "53", "NomeList");
			rectoryList1.setRectoryId(rectoryDAO.insert(rectoryList1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try{
			rectoryDAO.delete(rectory.getRectoryId());
			rectoryDAO.delete(rectoryUpdate.getRectoryId());
			rectoryDAO.delete(rectoryList.getRectoryId());
			rectoryDAO.delete(rectoryList1.getRectoryId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertCampus() {
		System.out.println("Test Insert Campus...");

		try {
			assertEquals(0, campusDAO.list().size());
			
			Campus vo = new Campus(1, "Nome 1", "Cnpj 1", rectory);
			vo.setCampusId(campusDAO.insert(vo));

			assertEquals(1, campusDAO.list().size());

			Campus retrieved = campusDAO.getById(vo.getCampusId());

			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getCnpj(), retrieved.getCnpj());
			assertEquals (vo.getRectory().getRectoryId(), retrieved.getRectory().getRectoryId());

			campusDAO.delete(vo.getCampusId());			

			assertEquals(0, campusDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Campus finished...");
	}
	
	@Test
	public void testUpdateCampus(){
		System.out.println ("Test Update Campus...");
		
		try {
			assertEquals (0, campusDAO.list().size());
			
			Campus vo = new Campus (1,"Name","CJPJ", rectory);
			vo.setCampusId(campusDAO.insert(vo));
			
			assertEquals (1, campusDAO.list().size());
			
			vo.setName("Name");
			vo.setCnpj("CNPJ");
			vo.setRectory(rectoryUpdate);
			
			campusDAO.update(vo);
			
			Campus retrieved = campusDAO.getById(vo.getCampusId());
			
			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getCnpj(), retrieved.getCnpj());
			assertEquals (vo.getRectory().getRectoryId(), retrieved.getRectory().getRectoryId());
						
			campusDAO.delete(vo.getCampusId());
			
			assertEquals (0, campusDAO.list().size());
		} catch (Exception e){
			e.printStackTrace();
			}
		System.out.println("Test Update Campus finished...");
		}
	
	@Test
	public void testListAndGetById(){
		 System.out.println("Test Campus List and GetById...");
		 
		 try{
			 assertEquals (0, campusDAO.list().size());		 
			 
			 Campus vo = new Campus (1,"CampusName1", "CampusCNPJ1", rectory);
			 Campus vo1 = new Campus (2,"CampusName2", "CampusCNPJ2", rectoryList);
			 Campus vo2 = new Campus (3,"CampusName3", "CampusCNPJ", rectoryList1);
			
			 vo.setCampusId(campusDAO.insert(vo));
			 vo1.setCampusId(campusDAO.insert(vo1));
			 vo2.setCampusId(campusDAO.insert(vo2));
						 
			 assertEquals (3,campusDAO.list().size());
			 
			 Assert.assertNotNull(campusDAO.getById(vo.getCampusId()));
			 assertEquals (vo.getName(),campusDAO.getById(vo.getCampusId()).getName());
			 
			 Assert.assertNotNull(campusDAO.getById(vo1.getCampusId()));
			 assertEquals (vo1.getCnpj(),campusDAO.getById(vo1.getCampusId()).getCnpj());
			 
			 Assert.assertNotNull(campusDAO.getById(vo2.getCampusId()));
			 assertEquals (vo2.getRectory().getRectoryId(),campusDAO.getById(vo2.getCampusId()).getRectory().getRectoryId());
			 					 
			 campusDAO.delete(vo.getCampusId());
			 campusDAO.delete(vo1.getCampusId());
			 campusDAO.delete(vo2.getCampusId());
			 			
			 assertEquals (0, campusDAO.list().size());					 
		 } catch (Exception e){
			 e.printStackTrace();
		 }
		 System.out.println("Test Campus List and GetById finished...");
	}
}