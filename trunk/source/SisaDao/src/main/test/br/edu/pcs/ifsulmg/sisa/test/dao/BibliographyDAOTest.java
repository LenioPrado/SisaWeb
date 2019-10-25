/*
 * File:	     BibliographyDAOTest.java
 * Creation date: 24/06/2015 
 * Author:        Amanda
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
import br.edu.pcs.ifsulmg.sisa.dao.BibliographyDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;

public class BibliographyDAOTest {

	BibliographyDAO bibliographyDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			bibliographyDAO = new BibliographyDAO();

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
	public void testInsertBibliography() {
		System.out.println("Test Insert Bibliography...");

		try {
			assertEquals(0, bibliographyDAO.list().size());

			Bibliography vo = new Bibliography(1, 1, "Título 1", "Subtítulo 1", 2,
					"Cidade 1", "Editor 1", 3, 4, 5, "Série", "ISBN1");
			vo.setBibliographyId(bibliographyDAO.insert(vo));

			assertEquals(1, bibliographyDAO.list().size());

			Bibliography retrieved = bibliographyDAO.getById(vo.getBibliographyId());

			assertEquals(vo.getBibliographyType(), retrieved.getBibliographyType());
			assertEquals(vo.getTitle(), retrieved.getTitle());
			assertEquals(vo.getSubtitle(), retrieved.getSubtitle());
			assertEquals(vo.getEdition(), retrieved.getEdition());
			assertEquals(vo.getCityPlace(), retrieved.getCityPlace());
			assertEquals(vo.getPublisher(), retrieved.getPublisher());
			assertEquals(vo.getYear(), retrieved.getYear());
			assertEquals(vo.getPages(), retrieved.getPages());
			assertEquals(vo.getVolume(), retrieved.getVolume());
			assertEquals(vo.getSeries(), retrieved.getSeries());
			assertEquals(vo.getIsbn(), retrieved.getIsbn());

			bibliographyDAO.delete(vo.getBibliographyId());

			assertEquals(0, bibliographyDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Bibliography finished...");
	}

	@Test
	public void testUpdateBibliography() {
		System.out.println("Test Update Bibliography...");

		try {
			assertEquals(0, bibliographyDAO.list().size());

			Bibliography vo = new Bibliography(1, 2, "Title Update","Subtitle Update", 2, "City Place Update",
					"Publisher Update", 3, 4, 5, "Series Update", "ISBNUpdate1");
			vo.setBibliographyId(bibliographyDAO.insert(vo));

			assertEquals(1, bibliographyDAO.list().size());

			vo.setBibliographyType(3);
			vo.setTitle("Title Changed1");
			vo.setSubtitle("Subtitle Changed1");
			vo.setEdition(11);
			vo.setCityPlace("City Place Changed1");
			vo.setPublisher("Pubilsher Changed1");
			vo.setYear(22);
			vo.setPages(33);
			vo.setVolume(44);
			vo.setSeries("Series Changed1");
			vo.setIsbn("ISBN Changed1");

			bibliographyDAO.update(vo);

			Bibliography retrieved = bibliographyDAO.getById(vo.getBibliographyId());

			assertEquals(vo.getBibliographyType(), retrieved.getBibliographyType());
			assertEquals(vo.getTitle(), retrieved.getTitle());
			assertEquals(vo.getSubtitle(), retrieved.getSubtitle());
			assertEquals(vo.getEdition(), retrieved.getEdition());
			assertEquals(vo.getCityPlace(), retrieved.getCityPlace());
			assertEquals(vo.getPublisher(), retrieved.getPublisher());
			assertEquals(vo.getYear(), retrieved.getYear());
			assertEquals(vo.getPages(), retrieved.getPages());
			assertEquals(vo.getVolume(), retrieved.getVolume());
			assertEquals(vo.getSeries(), retrieved.getSeries());
			assertEquals(vo.getIsbn(), retrieved.getIsbn());

			bibliographyDAO.delete(vo.getBibliographyId());

			assertEquals(0, bibliographyDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test Update Bibliography finished...");
	}

	@Test
	public void testListAndGetById() {
		System.out.println("Test Bibliography List and GetById...");

		try {
			assertEquals(0, bibliographyDAO.list().size());

			Bibliography vo = new Bibliography(21, 1, "BibliographyTitle1",
					"BibliographySubtitle1", 32, "BibliographyCityPlace1",
					"BibliographyPublisher1", 23, 24, 25, "BibliographySeries1", "Bib1ISBN1");
			Bibliography vo1 = new Bibliography(42, 2, "BibliographyTitle2",
					"BibliographySubtitle2", 43, "BibliographyCityPlace2",
					"BibliographyPublisher2", 44, 45, 46, "BibliographySeries2", "Bib2ISBN2");
			Bibliography vo2 = new Bibliography(77, 3, "BibliographyTitle3",
					"BibliographySubtitle3", 78, "BibliographyCityPlace3",
					"BibliographyPublisher3", 79, 80, 81, "BibliographySeries3", "Bib3ISBN3");
			Bibliography vo3 = new Bibliography(64, 4, "BibliographyTitle4",
					"BibliographySubtitle4", 65, "BibliographyCityPlace4",
					"BibliographyPublisher4", 66, 67, 68, "BibliographySeries4", "Bib4ISBN4");
			Bibliography vo4 = new Bibliography(33, 1, "BibliographyTitle5",
					"BibliographySubtitle5", 34, "BibliographyCityPlace5",
					"BibliographyPublisher5", 35, 36, 37, "BibliographySeries5", "Bib5ISBN5");

			vo.setBibliographyId(bibliographyDAO.insert(vo));
			vo1.setBibliographyId(bibliographyDAO.insert(vo1));
			vo2.setBibliographyId(bibliographyDAO.insert(vo2));
			vo3.setBibliographyId(bibliographyDAO.insert(vo3));
			vo4.setBibliographyId(bibliographyDAO.insert(vo4));

			assertEquals(5, bibliographyDAO.list().size());

			Assert.assertNotNull(bibliographyDAO.getById(vo.getBibliographyId()));
			assertEquals(vo.getTitle(),bibliographyDAO.getById(vo.getBibliographyId()).getTitle());

			Assert.assertNotNull(bibliographyDAO.getById(vo1.getBibliographyId()));
			assertEquals(vo1.getSubtitle(),bibliographyDAO.getById(vo1.getBibliographyId()).getSubtitle());

			Assert.assertNotNull(bibliographyDAO.getById(vo2.getBibliographyId()));
			assertEquals(vo2.getEdition(),bibliographyDAO.getById(vo2.getBibliographyId()).getEdition());

			Assert.assertNotNull(bibliographyDAO.getById(vo3.getBibliographyId()));
			assertEquals(vo3.getCityPlace(),bibliographyDAO.getById(vo3.getBibliographyId()).getCityPlace());

			Assert.assertNotNull(bibliographyDAO.getById(vo4.getBibliographyId()));
			assertEquals(vo4.getPublisher(),bibliographyDAO.getById(vo4.getBibliographyId()).getPublisher());

			bibliographyDAO.delete(vo.getBibliographyId());
			bibliographyDAO.delete(vo1.getBibliographyId());
			bibliographyDAO.delete(vo2.getBibliographyId());
			bibliographyDAO.delete(vo3.getBibliographyId());
			bibliographyDAO.delete(vo4.getBibliographyId());

			assertEquals(0, bibliographyDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test Bibliography List and GetById finished...");
	}
}