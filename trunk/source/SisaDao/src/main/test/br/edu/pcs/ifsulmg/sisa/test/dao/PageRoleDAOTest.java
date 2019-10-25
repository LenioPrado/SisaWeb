/*
* File:	     PageDAOTest.java
* Creation date: 06/04/2015 
* Page:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.ActionDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PageDAO;
import br.edu.pcs.ifsulmg.sisa.dao.PageRoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.RoleDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Action;
import br.edu.pcs.ifsulmg.sisa.vo.Page;
import br.edu.pcs.ifsulmg.sisa.vo.PageRole;
import br.edu.pcs.ifsulmg.sisa.vo.Role;

public class PageRoleDAOTest {

	PageDAO pageDAO;
	RoleDAO roleDAO;
	ActionDAO actionDAO;
	PageRoleDAO pageRoleDAO;
	
	Page page;
	Page pageUpdate;
	Page pageList;
	Role role;
	Role roleUpdate;
	Role roleList;
	Action action;
	Action actionUpdate;
	Action actionList;

	@Before
	public void setUp() throws Exception {
		try{
			pageDAO = new PageDAO();
			roleDAO = new RoleDAO();
			actionDAO = new ActionDAO();
			pageRoleDAO = new PageRoleDAO();
			
			page = new Page(1,"Página 1", "Url 1");
			page.setPageId(pageDAO.insert(page));
			
			pageUpdate = new Page(2,"Página 2", "Url Update 1");
			pageUpdate.setPageId(pageDAO.insert(pageUpdate));
			
			pageList = new Page(2,"Página 2", "Url List 2");
			pageList.setPageId(pageDAO.insert(pageList));
			
			role = new Role(1, "Nome 1", "Observação 1");
			role.setRoleId(roleDAO.insert(role));			
			
			roleUpdate = new Role(2, "Nome 2", "Observação 2");
			roleUpdate.setRoleId(roleDAO.insert(roleUpdate));
			
			roleList = new Role(3, "Nome 3", "Observação 3");
			roleList.setRoleId(roleDAO.insert(roleList));
			
			action = new Action(1, "Descrição 1");
			action.setActionId(actionDAO.insert(action));
			
			actionUpdate = new Action(2,"Descrição 2");
			actionUpdate.setActionId(actionDAO.insert(actionUpdate));
			
			actionList = new Action(3, "Descrição 3");
			actionList.setActionId(actionDAO.insert(actionList));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		try{
			pageDAO.delete(page.getPageId());
			pageDAO.delete(pageUpdate.getPageId());
			pageDAO.delete(pageList.getPageId());
			roleDAO.delete(role.getRoleId());
			roleDAO.delete(roleUpdate.getRoleId());
			roleDAO.delete(roleList.getRoleId());
			actionDAO.delete(action.getActionId());
			actionDAO.delete(actionUpdate.getActionId());
			actionDAO.delete(actionList.getActionId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertPageRole() {
		System.out.println("Test Insert Page Role Action...");
		
		try{
			assertEquals(0, pageRoleDAO.list().size());
			
			PageRole vo = new PageRole(page,role,action);
			pageRoleDAO.insert(vo);
			
			assertEquals(1, pageRoleDAO.list().size());
			
			PageRole retrieved = pageRoleDAO.getByMembersIds(vo);
			
			assertEquals(vo.getPage().getPageId(), retrieved.getPage().getPageId());
			assertEquals(vo.getRole().getRoleId(), retrieved.getRole().getRoleId());
			assertEquals(vo.getAction().getActionId(), retrieved.getAction().getActionId());
			
			pageRoleDAO.delete(vo);
									
			assertEquals(0, pageRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		System.out.println("Test Insert Page Role finished...");		
	}
	
	@Test
	public void testPageUpdatePageRole(){
		System.out.println("Test Page Update Page Role...");
		
		try{			
			assertEquals(0, pageRoleDAO.list().size());
			
			PageRole vo = new PageRole(page, role, action);						
			pageRoleDAO.insert(vo);
			
			assertEquals(1, pageRoleDAO.list().size());
			
			PageRole newVo = new PageRole(pageUpdate, role, action);	
			pageRoleDAO.update(vo, newVo);
			
			PageRole retrieved = pageRoleDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getPage().getPageId(), retrieved.getPage().getPageId());
			assertEquals(newVo.getRole().getRoleId(), retrieved.getRole().getRoleId());
			assertEquals(newVo.getAction().getActionId(), retrieved.getAction().getActionId());
			
			pageRoleDAO.delete(newVo);
					
			assertEquals(0, pageRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Page Update Page Role finished...");
	}
	
	@Test
	public void testActionUpdatePageRole(){
		System.out.println("Test Action Update Page Role...");
		
		try{			
			assertEquals(0, pageRoleDAO.list().size());
			
			PageRole vo = new PageRole(page, role, action);						
			pageRoleDAO.insert(vo);
			
			assertEquals(1, pageRoleDAO.list().size());
			
			PageRole newVo = new PageRole(page,role,actionUpdate);	
			pageRoleDAO.update(vo, newVo);
			
			PageRole retrieved = pageRoleDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getPage().getPageId(), retrieved.getPage().getPageId());
			assertEquals(newVo.getRole().getRoleId(), retrieved.getRole().getRoleId());
			assertEquals(newVo.getAction().getActionId(), retrieved.getAction().getActionId());
			
			pageRoleDAO.delete(newVo);
			
			assertEquals(0, pageRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Action Update Page Role finished...");
	}

	@Test
	public void testRoleUpdatePageRole(){
		System.out.println("Test Role Update Page Role...");
		
		try{			
			assertEquals(0, pageRoleDAO.list().size());
			
			PageRole vo = new PageRole(page, role, action);						
			pageRoleDAO.insert(vo);
			
			assertEquals(1, pageRoleDAO.list().size());
			
			PageRole newVo = new PageRole(page,roleUpdate,action);	
			pageRoleDAO.update(vo, newVo);
			
			PageRole retrieved = pageRoleDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getPage().getPageId(), retrieved.getPage().getPageId());
			assertEquals(newVo.getRole().getRoleId(), retrieved.getRole().getRoleId());
			assertEquals(newVo.getAction().getActionId(), retrieved.getAction().getActionId());
			
			pageRoleDAO.delete(newVo);
			
			assertEquals(0, pageRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Role Update Page Role finished...");
	}
	
	@Test
	public void testListAndGetById(){
		System.out.println("Test Page Role List and GetById... ");
		
		try{
			assertEquals(0, pageRoleDAO.list().size());
			
			PageRole vo = new PageRole(page, role, action);
			PageRole vo1 = new PageRole(pageList, roleList, actionList);
			
			pageRoleDAO.insert(vo);
			pageRoleDAO.insert(vo1);
			
			assertEquals(2, pageRoleDAO.list().size());
			
			Assert.assertNotNull(pageRoleDAO.getByMembersIds(vo));
			assertEquals(vo.getPage().getPageId(), 
					pageRoleDAO.getByMembersIds(vo).getPage().getPageId());
			
			Assert.assertNotNull(pageRoleDAO.getByMembersIds(vo1));
			assertEquals(vo1.getPage().getPageId(), 
					pageRoleDAO.getByMembersIds(vo1).getPage().getPageId());
			
			pageRoleDAO.delete(vo);
			pageRoleDAO.delete(vo1);
			
			assertEquals(0, pageRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Page Role List and GetById finished...");
	}
}