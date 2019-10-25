/*
* File:	     BibliographyAuthorDAOTest.java
* Creation date: 26/06/2015 
* Author:        Rarvs
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.AuthorDAO;
import br.edu.pcs.ifsulmg.sisa.dao.BibliographyAuthorDAO;
import br.edu.pcs.ifsulmg.sisa.dao.BibliographyDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Author;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.BibliographyAuthor;

public class BibliographyAuthorDAOTest {

	BibliographyDAO bibliographyDAO;
	AuthorDAO authorDAO;
	BibliographyAuthorDAO bibliographyAuthorDAO;
	
	Bibliography bibliography;
	Author author;
	Author authorUpdate;
	Author authorList;
	Author authorList1;
	Bibliography bibliographyUpdate;
	Bibliography bibliographyList;	
	Bibliography bibliographyList1;
	
	@Before
	public void setUp() throws Exception {
		try{
			bibliographyDAO =  new BibliographyDAO();
			authorDAO =  new AuthorDAO();
			bibliographyAuthorDAO = new BibliographyAuthorDAO();
			
			bibliography = new Bibliography(1, 1, "Title1", "Subtitle 1", 1, "CityPlace1", "Publisher1", 1, 1, 1, "Serie1", "ISBN1");
			bibliography.setBibliographyId(bibliographyDAO.insert(bibliography));
			
			bibliographyUpdate = new Bibliography(2, 2,"Title2Up", "Subtitle2Up", 22, "CityPlace2Up", "Publisher2Up", 22, 23, 24,"Series2Up", "ISBNUpd1");
			bibliographyUpdate.setBibliographyId(bibliographyDAO.insert(bibliographyUpdate));
			
			bibliographyList = new Bibliography(2, 3,"Title2", "Subtitle 2", 2, "CityPlace2", "Publisher2", 2, 2, 2, "Serie2", "ISBN2");
			bibliographyList.setBibliographyId(bibliographyDAO.insert(bibliographyList));
			
			bibliographyList1 = new Bibliography(3, 4,"Title3", "Subtitle 3", 3, "CityPlace2", "Publisher2", 3, 3, 3, "Serie3", "ISBN3");
			bibliographyList1.setBibliographyId(bibliographyDAO.insert(bibliographyList1));
			
			author =  new Author(1,"Autor 1", "Outros Autores 1");
			author.setAuthorId(authorDAO.insert(author));
			
			authorUpdate =  new Author(2, "Author22Up", "OtherAuthors22Up");
			authorUpdate.setAuthorId(authorDAO.insert(authorUpdate));

			authorList =  new Author(2,"Autor 2", "Outros Autores 2");
			authorList.setAuthorId(authorDAO.insert(authorList));

			authorList1 =  new Author(3,"Autor 3", "Outros Autores 3");
			authorList1.setAuthorId(authorDAO.insert(authorList1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		try{
			bibliographyDAO.delete(bibliography.getBibliographyId());
			bibliographyDAO.delete(bibliographyUpdate.getBibliographyId());
			bibliographyDAO.delete(bibliographyList.getBibliographyId());
			bibliographyDAO.delete(bibliographyList1.getBibliographyId());
			authorDAO.delete(author.getAuthorId());
			authorDAO.delete(authorUpdate.getAuthorId());
			authorDAO.delete(authorList.getAuthorId());
			authorDAO.delete(authorList1.getAuthorId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertBibliographyAuthor(){		
		System.out.println("Test Insert Bibliography Author...");		

		try {
			assertEquals(0, bibliographyAuthorDAO.list().size());
			
			BibliographyAuthor vo  = new BibliographyAuthor(author, bibliography);
			bibliographyAuthorDAO.insert(vo);
			
			assertEquals(1, bibliographyAuthorDAO.list().size());
			
			BibliographyAuthor retrieved = bibliographyAuthorDAO.getByMembersIds(vo);
			
			assertEquals(vo.getAuthor().getAuthorId(), retrieved.getAuthor().getAuthorId());			
			assertEquals(vo.getBibliography().getBibliographyId(), retrieved.getBibliography().getBibliographyId());
						
			bibliographyAuthorDAO.delete(vo);			
			
			assertEquals(0, bibliographyAuthorDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Bibliography Author finished...");		
	}

	@Test
	public void testAuthorUpdateBibliographyAuthor(){		
		System.out.println("Test Author Update Bibliography Author...");		

		try {			
			assertEquals(0, bibliographyAuthorDAO.list().size());
		
			BibliographyAuthor vo = new BibliographyAuthor(author, bibliography);
			bibliographyAuthorDAO.insert(vo);			
			
			assertEquals(1, bibliographyAuthorDAO.list().size());			

			BibliographyAuthor newVo = new BibliographyAuthor(authorUpdate, bibliography);
			
			bibliographyAuthorDAO.update(vo, newVo);
			
			BibliographyAuthor retrieved = bibliographyAuthorDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getAuthor().getAuthorId(), retrieved.getAuthor().getAuthorId());			
			assertEquals(newVo.getBibliography().getBibliographyId(), retrieved.getBibliography().getBibliographyId());

			bibliographyAuthorDAO.delete(newVo);
			
			assertEquals(0, bibliographyAuthorDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Author Update Bibliography Author finished...");		
	}
	
	@Test
	public void testBibliographyUpdateBibliographyAuthor(){		
		System.out.println("Test Bibliography Update Bibliography Author...");		

		try {			
			assertEquals(0, bibliographyAuthorDAO.list().size());
		
			BibliographyAuthor vo = new BibliographyAuthor(author, bibliography);
			bibliographyAuthorDAO.insert(vo);			
			
			assertEquals(1, bibliographyAuthorDAO.list().size());			
		
			BibliographyAuthor newVo = new BibliographyAuthor(author, bibliographyUpdate);
			
			bibliographyAuthorDAO.update(vo, newVo);
			
			BibliographyAuthor retrieved = bibliographyAuthorDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getAuthor().getAuthorId(), retrieved.getAuthor().getAuthorId());			
			assertEquals(newVo.getBibliography().getBibliographyId(), retrieved.getBibliography().getBibliographyId());

			bibliographyAuthorDAO.delete(newVo);
			
			assertEquals(0, bibliographyAuthorDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Bibliography Update Bibliography Author finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Bibliography Author List and GetById...");		

		try {			
			assertEquals(0, bibliographyAuthorDAO.list().size());
			
			BibliographyAuthor vo  = new BibliographyAuthor(author, bibliography);
			BibliographyAuthor vo1 = new BibliographyAuthor(authorList, bibliographyList);
			BibliographyAuthor vo2 = new BibliographyAuthor(authorList1, bibliographyList1);

			bibliographyAuthorDAO.insert(vo);
			bibliographyAuthorDAO.insert(vo1);			
			bibliographyAuthorDAO.insert(vo2);
			
			assertEquals(3, bibliographyAuthorDAO.list().size());

			Assert.assertNotNull(bibliographyAuthorDAO.getByMembersIds(vo));
			assertEquals(vo1.getBibliography().getBibliographyId(), 
					bibliographyAuthorDAO.getByMembersIds(vo1).getBibliography().getBibliographyId());
			
			Assert.assertNotNull(bibliographyAuthorDAO.getByMembersIds(vo2));
			assertEquals(vo2.getBibliography().getBibliographyId(), 
					bibliographyAuthorDAO.getByMembersIds(vo2).getBibliography().getBibliographyId());
			
			bibliographyAuthorDAO.delete(vo);
			bibliographyAuthorDAO.delete(vo1);
			bibliographyAuthorDAO.delete(vo2);

			assertEquals(0, bibliographyAuthorDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Bibliography Author List and GetById finished...");		
	}
}