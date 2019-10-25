/*
* File:	     MenuDAOTest.java
* Creation date: 13/04/2016 
* Menu:        Lênio
*/

package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.MenuDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PageDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Menu;
import br.edu.pcs.ifsulmg.sisa.vo.Page;

public class MenuDAOTest {

	MenuDAO menuDAO;
	PageDAO pageDAO;
	
	Page page;
	Page pageUpdate;
	
	@Before
	public void setUp() throws Exception {
		try{
			menuDAO = new MenuDAO();
			pageDAO = new PageDAO();
			
			page = new Page(1, "PageList1", "Url List 1");
			page.setPageId(pageDAO.insert(page));
			
			pageUpdate = new Page(2, "PageUpdate2", "Url Update 2");
			pageUpdate.setPageId(pageDAO.insert(pageUpdate));
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try{
			pageDAO.delete(page.getPageId());
			pageDAO.delete(pageUpdate.getPageId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertMenu(){		
		System.out.println("Test Insert Menu...");		

		try {
			assertEquals(0, menuDAO.list().size());
			
			Menu voParent = new Menu(1, null, "Menu Pai 1", 1, null);
			Menu voChild = new Menu(2, voParent, "Menu Filho 1", 1, page);
			
			voParent.setMenuId(menuDAO.insert(voParent));
			voChild.setMenuId(menuDAO.insert(voChild));
			
			assertEquals(2, menuDAO.list().size());
			
			Menu retrieved = menuDAO.getById(voChild.getMenuId());
			
			assertEquals(voChild.getDescription(), retrieved.getDescription());
			assertEquals(voChild.getParent().getMenuId(), voParent.getMenuId());
			
			menuDAO.delete(voChild.getMenuId());
			menuDAO.delete(voParent.getMenuId());			
			
			assertEquals(0, menuDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Menu finished...");		
	}
	
	@Test
	public void testUpdateMenu(){		
		System.out.println("Test Update Menu...");		

		try {			
			assertEquals(0, menuDAO.list().size());
		
			Menu voParent = new Menu(1, null, "Menu Pai Upd 1", 1, null);
			Menu voChild = new Menu(2, voParent, "Menu Filho Upd 1", 1, page);
			
			voParent.setMenuId(menuDAO.insert(voParent));
			voChild.setMenuId(menuDAO.insert(voChild));
			
			assertEquals(2, menuDAO.list().size());			
			
			Menu voParentUpd = new Menu(3, null, "Menu Pai Upd 2", 1, null);

			voParentUpd.setMenuId(menuDAO.insert(voParentUpd));
			
			voChild.setParent(voParentUpd);
			voChild.setDescription("Page Changed");
			voChild.setMenuOrder(5);
			voChild.setPage(pageUpdate);
			
			menuDAO.update(voChild);
			
			Menu retrieved = menuDAO.getById(voChild.getMenuId());
			
			assertEquals(voChild.getParent().getMenuId(), retrieved.getParent().getMenuId());
			assertEquals(voChild.getDescription(), retrieved.getDescription());
			assertEquals(voChild.getMenuOrder(), retrieved.getMenuOrder());
			assertEquals(voChild.getPage().getPageId(), retrieved.getPage().getPageId());
			
			menuDAO.delete(voChild.getMenuId());
			menuDAO.delete(voParent.getMenuId());
			menuDAO.delete(voParentUpd.getMenuId());
			
			assertEquals(0, menuDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Menu finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Menu List and GetById...");		

		try {			
			assertEquals(0, menuDAO.list().size());
			
			Menu voParent = new Menu(1, null, "Menu Pai List 1", 1, null);
			Menu voChild = new Menu(2, voParent, "Menu Filho List 1", 1, page);
			
			voParent.setMenuId(menuDAO.insert(voParent));
			voChild.setMenuId(menuDAO.insert(voChild));
			
			assertEquals(2, menuDAO.list().size());

			Assert.assertNotNull(menuDAO.getById(voParent.getMenuId()));
			Assert.assertNotNull(menuDAO.getById(voChild.getMenuId()));
			
			assertEquals(voParent.getDescription(), menuDAO.getById(voParent.getMenuId()).getDescription());
			assertEquals(voChild.getDescription(), menuDAO.getById(voChild.getMenuId()).getDescription());
					
			menuDAO.delete(voChild.getMenuId());
			menuDAO.delete(voParent.getMenuId());
			
			assertEquals(0, menuDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Menu List and GetById finished...");		
	}
}