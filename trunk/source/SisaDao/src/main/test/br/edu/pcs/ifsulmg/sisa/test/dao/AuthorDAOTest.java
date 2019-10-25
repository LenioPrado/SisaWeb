/*
* File:	     AuthorDAOTest.java
* Creation date: 03/06/2015 
* Author:        Lênio
*/

package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.AuthorDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Author;

public class AuthorDAOTest {

	AuthorDAO authorDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			authorDAO = new AuthorDAO();		
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
	public void testInsertAuthor(){		
		System.out.println("Test Insert Author...");		

		try {
			assertEquals(0, authorDAO.list().size());
			
			Author vo = new Author(1, "Autor 1", "Outros Autores 1");
			vo.setAuthorId(authorDAO.insert(vo));
			
			assertEquals(1, authorDAO.list().size());
			
			Author retrieved = authorDAO.getById(vo.getAuthorId());
			
			assertEquals(vo.getLastName(), retrieved.getLastName());			
			assertEquals(vo.getOtherNames(), retrieved.getOtherNames());
			
			authorDAO.delete(vo.getAuthorId());
			
			assertEquals(0, authorDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Author finished...");		
	}
	
	@Test
	public void testUpdateAuthor(){		
		System.out.println("Test Update Author...");		

		try {			
			assertEquals(0, authorDAO.list().size());
		
			Author vo = new Author(1, "Last Name Update", "Other Names Update");
			vo.setAuthorId(authorDAO.insert(vo));			
			
			assertEquals(1, authorDAO.list().size());			
			
			vo.setLastName("Last Name Changed 1");
			vo.setOtherNames("Other Names Changed1");
			
			authorDAO.update(vo);
			
			Author retrieved = authorDAO.getById(vo.getAuthorId());
			
			assertEquals(vo.getLastName(), retrieved.getLastName());			
			assertEquals(vo.getOtherNames(), retrieved.getOtherNames());

			authorDAO.delete(vo.getAuthorId());
			
			assertEquals(0, authorDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Author finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Author List and GetById...");		

		try {			
			assertEquals(0, authorDAO.list().size());
			
			Author vo = new Author(1, "AuthorLastName1", "AuthorOtherNames1");
			Author vo1 = new Author(2, "AuthorLastName2", "AuthorOtherNames2");
			
			vo.setAuthorId(authorDAO.insert(vo));			
			vo1.setAuthorId(authorDAO.insert(vo1));
			
			assertEquals(2, authorDAO.list().size());

			Assert.assertNotNull(authorDAO.getById(vo.getAuthorId()));
			assertEquals(vo.getLastName(), authorDAO.getById(vo.getAuthorId()).getLastName());
			
			Assert.assertNotNull(authorDAO.getById(vo1.getAuthorId()));
			assertEquals(vo1.getOtherNames(), authorDAO.getById(vo1.getAuthorId()).getOtherNames());
			
			authorDAO.delete(vo.getAuthorId());
			authorDAO.delete(vo1.getAuthorId());			
			
			assertEquals(0, authorDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Author List and GetById finished...");		
	}
}