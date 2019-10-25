/*
 * File:	     UserRoleDAOTest.java
 * Creation date: 06/07/2015 
 * Author:        Amanda / Lênio
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.RoleDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserDAO;
import br.edu.pcs.ifsulmg.sisa.dao.UserRoleDAO;
import br.edu.pcs.ifsulmg.sisa.util.DateUtil;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;

public class UserRoleDAOTest {
	
	RoleDAO roleDAO;
	UserDAO userDAO;
	UserRoleDAO userRoleDAO;

	Date startDate;
	Date startDateUpdate;
	Date startDateList;
	Date endDate;
	Date endDateUpdate;
	Date endDateList;

	Role role;
	Role roleUpdate;
	Role roleList;
	User user;
	User userUpdate;
	User userList;
	
	@Before
	public void setUp() throws Exception {
		try{
			roleDAO = new RoleDAO();
			userDAO = new UserDAO();
			userRoleDAO = new UserRoleDAO();
			
			startDate = new GregorianCalendar(2015, 3, 7).getTime();
			endDate = new GregorianCalendar(2016, 7, 2).getTime();
						
			startDateUpdate = new GregorianCalendar(2011, 6, 9).getTime();
			endDateUpdate = new GregorianCalendar(2012, 3, 11).getTime();
			
			startDateList = new GregorianCalendar(2001, 9, 3).getTime();
			endDateList = new GregorianCalendar(2004, 5, 1).getTime();
			
			role = new Role(1, "Role 1", "Observação 1");
			role.setRoleId(roleDAO.insert(role));

			roleUpdate = new Role(1, "RoleUpdate2", "ObservaçãoUpdate 4");
			roleUpdate.setRoleId(roleDAO.insert(roleUpdate));
			
			roleList = new Role(3, "Role List 1", "ObservaçãoList1");
			roleList.setRoleId(roleDAO.insert(roleList));
			
			user = new User (1,"Nome","Senha","Email");
			user.setUserId(userDAO.insert(user));
			
			userUpdate = new User (2,"NomeUpdate2","SenhaUpdate2","EmailUpdate2");
			userUpdate.setUserId(userDAO.insert(userUpdate));
			
			userList = new User (1,"NomeList","SenhaList","EmailList");
			userList.setUserId(userDAO.insert(userList));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try{
			roleDAO.delete(role.getRoleId());
			userDAO.delete(user.getUserId());			
			roleDAO.delete(roleUpdate.getRoleId());			
			userDAO.delete(userUpdate.getUserId());
			roleDAO.delete(roleList.getRoleId());
			userDAO.delete(userList.getUserId());			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUserRole() {
		System.out.println("Test Insert User Role...");

		try {
			assertEquals(0, userRoleDAO.list().size());
						
			UserRole vo = new UserRole(1, startDate, endDate, role, user);
			vo.setUserRoleId(userRoleDAO.insert(vo));

			assertEquals(1, userRoleDAO.list().size());

			UserRole retrieved = userRoleDAO.getById(vo.getUserRoleId());

			assertEquals(vo.getStartDate(), retrieved.getStartDate());
			assertEquals(vo.getEndDate(), retrieved.getEndDate());
			assertEquals (vo.getUser().getUserId(), retrieved.getUser().getUserId());
			assertEquals (vo.getRole().getRoleId(), retrieved.getRole().getRoleId());

			userRoleDAO.delete(vo.getUserRoleId());

			assertEquals(0, userRoleDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert User Role finished...");
	}
	
	@Test
	public void testUpdateUserRole(){
		System.out.println("Test Update User Role...");
		
		try{			
			assertEquals(0, userRoleDAO.list().size());

			UserRole vo = new UserRole(1, startDate, endDate, role, user);
			vo.setUserRoleId(userRoleDAO.insert(vo));
			
			assertEquals(1, userRoleDAO.list().size());

			vo.setStartDate(startDateUpdate);
			vo.setEndDate(endDateUpdate);
			vo.setRole(roleUpdate);
			vo.setUser(userUpdate);
			
			userRoleDAO.update(vo);
			
			UserRole retrieved = userRoleDAO.getById(vo.getUserRoleId());
			
			assertEquals (vo.getStartDate(), retrieved.getStartDate());
			assertEquals (vo.getEndDate(), retrieved.getEndDate());
			assertEquals (vo.getUser().getUserId(), retrieved.getUser().getUserId());
			assertEquals (vo.getRole().getRoleId(), retrieved.getRole().getRoleId());
			
			userRoleDAO.delete(vo.getUserRoleId());				
		
			assertEquals(0, userRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test Update User Role finished...");
	}
	
	@Test
	public void testListAndGetById(){
		System.out.println("Test User Role List and GetById... ");
		
		try{
			assertEquals(0, userRoleDAO.list().size());
			
			UserRole vo = new UserRole(1, startDate, endDate, role, user);
			vo.setUserRoleId(userRoleDAO.insert(vo));
			
			UserRole vo1 = new UserRole(2, startDateList, endDateList, roleList, userList);
			vo1.setUserRoleId(userRoleDAO.insert(vo1));
			
			assertEquals(2, userRoleDAO.list().size());
			
			Assert.assertNotNull(userRoleDAO.getById(vo.getUserRoleId()));
			assertEquals(vo.getUserRoleId(), userRoleDAO.getById(vo.getUserRoleId()).getUserRoleId());
			
			Assert.assertNotNull(userRoleDAO.getById(vo1.getUserRoleId()));
			assertEquals(vo1.getUserRoleId(), userRoleDAO.getById(vo1.getUserRoleId()).getUserRoleId());
			
			userRoleDAO.delete(vo.getUserRoleId());
			userRoleDAO.delete(vo1.getUserRoleId());
			
			assertEquals(0, userRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test User Role List and GetById finished...");
	}
	
	@Test
	public void testListRolesByUser(){
		System.out.println("Test User Role List Roles by User... ");
		
		try{
			assertEquals(0, userRoleDAO.list().size());
			
			//startDate = 2015-3-7
			//endDate = 2016-7-2
			
			//startDateList = 2001-9-3
			//endDateList = 2004-5-1
			
			UserRole vo = new UserRole(1, startDate, endDate, role, user);
			vo.setUserRoleId(userRoleDAO.insert(vo));
			
			UserRole vo1 = new UserRole(2, startDateList, endDateList, roleList, userList);
			vo1.setUserRoleId(userRoleDAO.insert(vo1));			
			
			Date dateToQuery = new GregorianCalendar(2015, 5, 13).getTime();
			List<Role> rolesList = userRoleDAO.listRolesByUser(user, dateToQuery);
			
			assertEquals(1, rolesList.size());
			assertEquals(role.getRoleId(), rolesList.get(0).getRoleId());
			
			dateToQuery = new GregorianCalendar(2003, 9, 22).getTime();
			rolesList = userRoleDAO.listRolesByUser(userList, dateToQuery);
			
			assertEquals(1, rolesList.size());
			assertEquals(roleList.getRoleId(), rolesList.get(0).getRoleId());
			
			dateToQuery = DateUtil.getNow();
			rolesList = userRoleDAO.listRolesByUser(userList, dateToQuery);
			assertEquals(0, rolesList.size());
			
			Assert.assertNotNull(userRoleDAO.getById(vo.getUserRoleId()));
			assertEquals(vo.getUserRoleId(), userRoleDAO.getById(vo.getUserRoleId()).getUserRoleId());
			
			Assert.assertNotNull(userRoleDAO.getById(vo1.getUserRoleId()));
			assertEquals(vo1.getUserRoleId(), userRoleDAO.getById(vo1.getUserRoleId()).getUserRoleId());
			
			userRoleDAO.delete(vo.getUserRoleId());
			userRoleDAO.delete(vo1.getUserRoleId());
			
			assertEquals(0, userRoleDAO.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Test User Role List Roles by User finished...");
	}
}