/*
 * File:	     RoleDAOTest.java
 * Creation date: 06/07/2015 
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.RoleDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Role;

public class RoleDAOTest {

	RoleDAO roleDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			roleDAO = new RoleDAO();
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
	public void testInsertRole() {
		System.out.println("Test Insert Role...");

		try {
			assertEquals(0, roleDAO.list().size());

			Role vo = new Role(1,"Nome1","Obser1");
			vo.setRoleId(roleDAO.insert(vo));

			assertEquals(1, roleDAO.list().size());

			Role retrieved = roleDAO.getById(vo.getRoleId());

			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getObservation(), retrieved.getObservation());

			roleDAO.delete(vo.getRoleId());

			assertEquals(0, roleDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Role finished...");
	}

	@Test
	public void testUpdateRole() {
		System.out.println("Test Update Role...");

		try {
			assertEquals(0, roleDAO.list().size());

			Role vo = new Role(1,"Nome2","Obser2");
			vo.setRoleId(roleDAO.insert(vo));

			assertEquals(1, roleDAO.list().size());

			vo.setName("Name1UP");
			vo.setObservation("Ober1Up");

			roleDAO.update(vo);

			Role retrieved = roleDAO.getById(vo.getRoleId());

			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getObservation(), retrieved.getObservation());
			
			roleDAO.delete(vo.getRoleId());

			assertEquals(0, roleDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Update Role finished...");
	}

	@Test
	public void testListAndGetById() {
		System.out.println("Test Role List and GetById...");

		try {
			assertEquals(0, roleDAO.list().size());

			Role vo = new Role(1,"Name1","Observa1");
			Role vo1 = new Role(2,"Name2","Observa2");

			vo.setRoleId(roleDAO.insert(vo));
			vo1.setRoleId(roleDAO.insert(vo1));
			
			assertEquals(2, roleDAO.list().size());
			
			Assert.assertNotNull(roleDAO.getById(vo.getRoleId()));
			assertEquals(vo.getName(),roleDAO.getById(vo.getRoleId()).getName());

			Assert.assertNotNull(roleDAO.getById(vo1.getRoleId()));
			assertEquals(vo1.getObservation(),roleDAO.getById(vo1.getRoleId()).getObservation());			

			roleDAO.delete(vo.getRoleId());
			roleDAO.delete(vo1.getRoleId());

			assertEquals(0, roleDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Role List and GetById finished...");
	}
}