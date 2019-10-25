/*
* File:	     ActionDAOTest.java
* Creation date: 30/03/2015 
* Action:        Amanda
*/

package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.ActionDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Action;

public class ActionDAOTest {

	ActionDAO actionDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			actionDAO = new ActionDAO();		
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
	public void testInsertAction(){		
		System.out.println("Test Insert Action...");		

		try {
			assertEquals(0, actionDAO.list().size());
			
			Action vo = new Action(1, "Action 1");
			vo.setActionId(actionDAO.insert(vo));
			
			assertEquals(1, actionDAO.list().size());
			
			Action retrieved = actionDAO.getById(vo.getActionId());
			
			assertEquals(vo.getDescription(), retrieved.getDescription());
			
			actionDAO.delete(vo.getActionId());
			
			assertEquals(0, actionDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Action finished...");		
	}
	
	@Test
	public void testUpdateAction(){		
		System.out.println("Test Update Action...");		

		try {			
			assertEquals(0, actionDAO.list().size());
		
			Action vo = new Action(1, "Description Update");
			vo.setActionId(actionDAO.insert(vo));
			
			assertEquals(1, actionDAO.list().size());			
			
			vo.setDescription("Description Changed");
			
			actionDAO.update(vo);
			
			Action retrieved = actionDAO.getById(vo.getActionId());
			
			assertEquals(vo.getDescription(), retrieved.getDescription());

			actionDAO.delete(vo.getActionId());
			
			assertEquals(0, actionDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Action finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Action List and GetById...");		

		try {			
			assertEquals(0, actionDAO.list().size());
			
			Action vo = new Action(1, "Action Description 1");
			Action vo1 = new Action(2, "Action Description 2");
			
			vo.setActionId(actionDAO.insert(vo));
			vo1.setActionId(actionDAO.insert(vo1));
			
			assertEquals(2, actionDAO.list().size());

			Assert.assertNotNull(actionDAO.getById(vo.getActionId()));
			assertEquals(vo.getDescription(), actionDAO.getById(vo.getActionId()).getDescription());
		
			actionDAO.delete(vo.getActionId());		
			actionDAO.delete(vo1.getActionId());
			
			assertEquals(0, actionDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Action List and GetById finished...");		
	}
}