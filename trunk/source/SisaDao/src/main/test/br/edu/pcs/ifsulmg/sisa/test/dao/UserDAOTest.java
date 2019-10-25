/*
 * File:	     UserDAOTest.java
 * Creation date: 06/07/2015 
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.vo.User;

public class UserDAOTest {

	UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			userDAO = new UserDAO();
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
	public void testInsertUser() {
		System.out.println("Test Insert User...");

		try {
			assertEquals(0, userDAO.list().size());

			User vo = new User(1,"Name","pass123","email");
			vo.setUserId(userDAO.insert(vo));

			assertEquals(1, userDAO.list().size());

			User retrieved = userDAO.getById(vo.getUserId());

			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getPassword(), retrieved.getPassword());
			assertEquals(vo.getEmail(), retrieved.getEmail());

			userDAO.delete(vo.getUserId());

			assertEquals(0, userDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert User finished...");
	}

	@Test
	public void testUpdateUser() {
		System.out.println("Test Update User...");

		try {
			assertEquals(0, userDAO.list().size());

			User vo = new User(1,"Name","pass123","email");
			vo.setUserId(userDAO.insert(vo));

			assertEquals(1, userDAO.list().size());

			vo.setName("Name1UP");
			vo.setEmail("emailUP");
			vo.setPassword("passwordUP");

			userDAO.update(vo);

			User retrieved = userDAO.getById(vo.getUserId());

			assertEquals(vo.getName(), retrieved.getName());

			userDAO.delete(vo.getUserId());

			assertEquals(0, userDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Update User finished...");
	}

	@Test
	public void testListAndGetById() {
		System.out.println("Test User List and GetById...");

		try {
			assertEquals(0, userDAO.list().size());

			User vo = new User(1,"Name","pass123","email");
			User vo1 = new User(2,"Name2","pass1232","email2");

			vo.setUserId(userDAO.insert(vo));
			vo1.setUserId(userDAO.insert(vo1));

			assertEquals(2, userDAO.list().size());

			Assert.assertNotNull(userDAO.getById(vo.getUserId()));
			assertEquals(vo.getName(), userDAO.getById(vo.getUserId()).getName());

			Assert.assertNotNull(userDAO.getById(vo1.getUserId()));
			assertEquals(vo1.getName(),userDAO.getById(vo1.getUserId()).getName());
						
			userDAO.delete(vo.getUserId());
			userDAO.delete(vo1.getUserId());

			assertEquals(0, userDAO.list().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test User List and GetById finished...");
	}
}