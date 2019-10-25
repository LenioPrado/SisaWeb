/*
 * File:	     DisciplineBibliographyDAOTest.java
 * Creation date: 	03/07/2015
 * Verification:     Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import br.edu.pcs.ifsulmg.sisa.dao.BibliographyDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineBibliographyDAO;
import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.DisciplineBibliography;

public class DisciplineBibliographyDAOTest {

	BibliographyDAO bibliographyDAO;
	DisciplineDAO disciplineDAO;
	DisciplineBibliographyDAO disciplineBibliographyDAO;
	
	Bibliography bibliography;	
	Bibliography bibliographyUpdate;
	Bibliography bibliographyList;
	Bibliography bibliographyList1;
	Discipline discipline;
	Discipline disciplineUpdate;
	Discipline disciplineList;	
	Discipline disciplineList1;	
	
	@Before
	public void setUp() throws Exception {
		try{
			bibliographyDAO =  new BibliographyDAO();
			disciplineDAO =  new DisciplineDAO();
			disciplineBibliographyDAO = new DisciplineBibliographyDAO();
			
			bibliography = new Bibliography(1,1,"Title1","Subtitle 1",1,"CityPlace1","Publisher1",1,1,1,"Serie1","Isbn1");
			bibliography.setBibliographyId(bibliographyDAO.insert(bibliography));

			bibliographyUpdate =  new Bibliography(2,2,"Title2Up","Subtitle2Up",22,"CityPlace2Up","Publisher2Up",22,23,24,"Series2Up","Isbn2Up");
			bibliographyUpdate.setBibliographyId(bibliographyDAO.insert(bibliographyUpdate));
			
			bibliographyList = new Bibliography(2,3,"Title2","Subtitle 2",2,"CityPlace2","Publisher2",2,2,2,"Serie2","IsbnList1");
			bibliographyList.setBibliographyId(bibliographyDAO.insert(bibliographyList));
			
			bibliographyList1 = new Bibliography(3,4,"Title3","Subtitle 3",3,"CityPlace2","Publisher2",3,3,3,"Serie3","IsbnUpd1");
			bibliographyList1.setBibliographyId(bibliographyDAO.insert(bibliographyList1));
			
			discipline =  new Discipline(1, 1,"Menu1", "Disciplina1",1,"Tipo32");
			discipline.setDisciplineId(disciplineDAO.insert(discipline));
			
			disciplineUpdate =  new Discipline(2, 2,"Menu2", "Disciplina2",2,"Tipo87");
			disciplineUpdate.setDisciplineId(disciplineDAO.insert(disciplineUpdate));
			
			disciplineList =  new Discipline(3, 3,"Menu3", "Disciplina3",3,"Tipo3");
			disciplineList.setDisciplineId(disciplineDAO.insert(disciplineList));
			
			disciplineList1 =  new Discipline(4, 4,"Menu4", "Disciplina4",4,"Tipo4");
			disciplineList1.setDisciplineId(disciplineDAO.insert(disciplineList1));
			
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
			disciplineDAO.delete(discipline.getDisciplineId());
			disciplineDAO.delete(disciplineUpdate.getDisciplineId());
			disciplineDAO.delete(disciplineList.getDisciplineId());
			disciplineDAO.delete(disciplineList1.getDisciplineId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertDisciplineBibliography(){		
		System.out.println("Test Insert Discipline Bibliography...");		

		try {
			assertEquals(0, disciplineBibliographyDAO.list().size());
			
			DisciplineBibliography vo  = new DisciplineBibliography( bibliography, discipline, false);
			disciplineBibliographyDAO.insert(vo);
			
			assertEquals(1, disciplineBibliographyDAO.list().size());
			
			DisciplineBibliography retrieved = disciplineBibliographyDAO.getByMembersIds(vo);
			
			assertEquals(vo.getDiscipline().getDisciplineId(), retrieved.getDiscipline().getDisciplineId());			
			assertEquals(vo.getBibliography().getBibliographyId(), retrieved.getBibliography().getBibliographyId());
			assertEquals(vo.isBasic(), retrieved.isBasic());
						
			disciplineBibliographyDAO.delete(vo);
						
			assertEquals(0, disciplineBibliographyDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Discipline Bibliography finished...");		
	}

	@Test
	public void testBibliographyUpdateDisciplineBibliography(){		
		System.out.println("Test Bibliography Update Discipline Bibliography...");		

		try {			
			assertEquals(0, disciplineBibliographyDAO.list().size());
		
			DisciplineBibliography vo = new DisciplineBibliography(bibliography, discipline, false);
			disciplineBibliographyDAO.insert(vo);			
			
			assertEquals(1, disciplineBibliographyDAO.list().size());			

			DisciplineBibliography newVo = new DisciplineBibliography(bibliographyUpdate, discipline, true);
			
			disciplineBibliographyDAO.update(vo, newVo);
			
			DisciplineBibliography retrieved = disciplineBibliographyDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getDiscipline().getDisciplineId(), retrieved.getDiscipline().getDisciplineId());			
			assertEquals(newVo.getBibliography().getBibliographyId(), retrieved.getBibliography().getBibliographyId());
			assertEquals(vo.isBasic(), retrieved.isBasic());

			disciplineBibliographyDAO.delete(newVo);
			
			assertEquals(0, disciplineBibliographyDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Bibliography Update Discipline Bibliography finished...");		
	}
	
	@Test
	public void testDisciplineUpdateDisciplineBibliography(){		
		System.out.println("Test Discipline Update Discipline Bibliography...");		

		try {			
			assertEquals(0, disciplineBibliographyDAO.list().size());
		
			DisciplineBibliography vo = new DisciplineBibliography(bibliography, discipline, true);
			disciplineBibliographyDAO.insert(vo);			
			
			assertEquals(1, disciplineBibliographyDAO.list().size());			

			DisciplineBibliography newVo = new DisciplineBibliography(bibliography, disciplineUpdate, false);
			
			disciplineBibliographyDAO.update(vo, newVo);
			
			DisciplineBibliography retrieved = disciplineBibliographyDAO.getByMembersIds(newVo);
			
			assertEquals(newVo.getDiscipline().getDisciplineId(), retrieved.getDiscipline().getDisciplineId());			
			assertEquals(newVo.getBibliography().getBibliographyId(), retrieved.getBibliography().getBibliographyId());
			assertEquals(vo.isBasic(), retrieved.isBasic());

			disciplineBibliographyDAO.delete(newVo);
			
			assertEquals(0, disciplineBibliographyDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Discipline Update Discipline Bibliography finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Discipline Bibliography List and GetById...");		

		try {			
			assertEquals(0, disciplineBibliographyDAO.list().size());
			
			DisciplineBibliography vo  = new DisciplineBibliography(bibliography, discipline, true);
			DisciplineBibliography vo1 = new DisciplineBibliography(bibliographyList, disciplineList, false);
			DisciplineBibliography vo2 = new DisciplineBibliography(bibliographyList1, disciplineList1, true);

			disciplineBibliographyDAO.insert(vo);
			disciplineBibliographyDAO.insert(vo1);			
			disciplineBibliographyDAO.insert(vo2);
			
			assertEquals(3, disciplineBibliographyDAO.list().size());
			
			Assert.assertNotNull(disciplineBibliographyDAO.getByMembersIds(vo));
			assertEquals(vo.getBibliography().getBibliographyId(), 
					disciplineBibliographyDAO.getByMembersIds(vo)
					.getBibliography().getBibliographyId());
			
			Assert.assertNotNull(disciplineBibliographyDAO.getByMembersIds(vo1));
			assertEquals(vo1.getBibliography().getBibliographyId(), 
					disciplineBibliographyDAO.getByMembersIds(vo1)
					.getBibliography().getBibliographyId());
			
			Assert.assertNotNull(disciplineBibliographyDAO.getByMembersIds(vo2));
			assertEquals(vo2.getBibliography().getBibliographyId(), 
					disciplineBibliographyDAO.getByMembersIds(vo2)
					.getBibliography().getBibliographyId());
			
			disciplineBibliographyDAO.delete(vo);
			disciplineBibliographyDAO.delete(vo1);
			disciplineBibliographyDAO.delete(vo2);
			
			assertEquals(0, disciplineBibliographyDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Discipline Bibliography List and GetById finished...");		
	}
}