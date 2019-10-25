/*
* File:	     PageDAOTest.java
* Creation date: 30/03/2016
* Page:        Amanda
*/

package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.PageDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Page;

public class PageDAOTest {

	PageDAO pageDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			pageDAO = new PageDAO();
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
	public void testInsertPage(){		
		System.out.println("Test Insert Page...");		

		try {
			assertEquals(0, pageDAO.list().size());
			
			Page vo = new Page(1, "Página 1", "Url Insert 1");
			vo.setPageId(pageDAO.insert(vo));
			
			assertEquals(1, pageDAO.list().size());
			
			Page retrieved = pageDAO.getById(vo.getPageId());
			
			assertEquals(vo.getName(), retrieved.getName());
			
			pageDAO.delete(vo.getPageId());
			
			assertEquals(0, pageDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Page finished...");		
	}
	
	@Test
	public void testUpdatePage(){		
		System.out.println("Test Update Page...");		

		try {			
			assertEquals(0, pageDAO.list().size());
		
			Page vo = new Page(1, "Name Update", "Url Update 2");
			vo.setPageId(pageDAO.insert(vo));
			
			assertEquals(1, pageDAO.list().size());			
			
			vo.setName("Name Changed");
			
			pageDAO.update(vo);
			
			Page retrieved = pageDAO.getById(vo.getPageId());
			
			assertEquals(vo.getName(), retrieved.getName());

			pageDAO.delete(vo.getPageId());
			
			assertEquals(0, pageDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Page finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Page List and GetById...");		

		try {			
			assertEquals(0, pageDAO.list().size());
			
			Page vo = new Page(1, "PageName1", "Url List 1");
			Page vo1 = new Page(2, "PageName2", "Url List 2");
			
			vo.setPageId(pageDAO.insert(vo));
			vo1.setPageId(pageDAO.insert(vo1));
			
			assertEquals(2, pageDAO.list().size());

			Assert.assertNotNull(pageDAO.getById(vo.getPageId()));
			assertEquals(vo.getName(), pageDAO.getById(vo.getPageId()).getName());
		
			pageDAO.delete(vo.getPageId());		
			pageDAO.delete(vo1.getPageId());
			
			assertEquals(0, pageDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Page List and GetById finished...");		
	}
}