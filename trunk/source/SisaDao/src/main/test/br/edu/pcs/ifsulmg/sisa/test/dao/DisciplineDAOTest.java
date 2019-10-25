/*
 * File:	     DisciplineDAOTest.java
 * Creation date: 	03/07/2015
 * Verification:     Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.DisciplineDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;

public class DisciplineDAOTest {

	DisciplineDAO disciplineDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			disciplineDAO = new DisciplineDAO();
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
	public void testInsertDiscipline(){		
		System.out.println("Test Insert Discipline...");		

		try {
			assertEquals(0, disciplineDAO.list().size());
			
			Discipline vo = new Discipline(1, 2,"Menu1", "Disciplina1",3,"Tipo99");
			vo.setDisciplineId(disciplineDAO.insert(vo));
			
			assertEquals(1, disciplineDAO.list().size());
			
			Discipline retrieved = disciplineDAO.getById(vo.getDisciplineId());
			
			assertEquals(vo.getPeriodSeries(), retrieved.getPeriodSeries());
			assertEquals(vo.getMenu(), retrieved.getMenu());
			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getWeekClasses(), retrieved.getWeekClasses());
			assertEquals(vo.getType(), retrieved.getType());
			
			disciplineDAO.delete(vo.getDisciplineId());
			
			assertEquals(0, disciplineDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Insert Discipline finished...");		
	}

	@Test
	public void testUpdateDiscipline(){		
		System.out.println("Test Update Discipline...");		

		try {			
			assertEquals(0, disciplineDAO.list().size());
		
			Discipline vo = new Discipline(1, 2,"Menu1", "Disciplina1",5,"Tipo1590");
			vo.setDisciplineId(disciplineDAO.insert(vo));			
			
			assertEquals(1, disciplineDAO.list().size());			
			
			vo.setPeriodSeries(11);
			vo.setMenu("Menu1UP");
			vo.setName("Disciplina1UP");
			vo.setWeekClasses(22);
			vo.setType("Tipo1UP");
			
			disciplineDAO.update(vo);
			
			Discipline retrieved = disciplineDAO.getById(vo.getDisciplineId());
			
			assertEquals(vo.getPeriodSeries(), retrieved.getPeriodSeries());
			assertEquals(vo.getMenu(),retrieved.getMenu());
			assertEquals(vo.getName(), retrieved.getName());			
			assertEquals(vo.getType(),retrieved.getType());
			
			disciplineDAO.delete(vo.getDisciplineId());
			
			assertEquals(0, disciplineDAO.list().size());	
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Update Discipline finished...");		
	}

	@Test
	public void testListAndGetById(){		
		System.out.println("Test Discipline List and GetById...");		

		try {			
			assertEquals(0, disciplineDAO.list().size());
			
			Discipline vo1 = new Discipline(1, 31,"Menu1", "Disciplina1",61,"Tipo2861");
			Discipline vo2 = new Discipline(2, 32,"Menu2", "Disciplina2",62,"Tipo2");
			Discipline vo3 = new Discipline(3, 33,"Menu3", "Disciplina3",63,"Tipo3");
			Discipline vo4 = new Discipline(4, 34,"Menu4", "Disciplina4",64,"Tipo4");
			Discipline vo5 = new Discipline(5, 35,"Menu5", "Disciplina5",65,"Tipo5");
			Discipline vo6 = new Discipline(6, 36,"Menu6", "Disciplina6",66,"Tipo6");
			Discipline vo7 = new Discipline(7, 37,"Menu7", "Disciplina7",67,"Tipo7");
			
			vo1.setDisciplineId(disciplineDAO.insert(vo1));
			vo2.setDisciplineId(disciplineDAO.insert(vo2));
			vo3.setDisciplineId(disciplineDAO.insert(vo3));
			vo4.setDisciplineId(disciplineDAO.insert(vo4));
			vo5.setDisciplineId(disciplineDAO.insert(vo5));
			vo6.setDisciplineId(disciplineDAO.insert(vo6));
			vo7.setDisciplineId(disciplineDAO.insert(vo7));
			
			assertEquals(7, disciplineDAO.list().size());

			Assert.assertNotNull(disciplineDAO.getById(vo1.getDisciplineId()));
			assertEquals(vo1.getName(), disciplineDAO.getById(vo1.getDisciplineId()).getName());
			
			Assert.assertNotNull(disciplineDAO.getById(vo2.getDisciplineId()));
			assertEquals(vo2.getName(), disciplineDAO.getById(vo2.getDisciplineId()).getName());
			
			Assert.assertNotNull(disciplineDAO.getById(vo3.getDisciplineId()));
			assertEquals(vo3.getName(), disciplineDAO.getById(vo3.getDisciplineId()).getName());
			
			Assert.assertNotNull(disciplineDAO.getById(vo4.getDisciplineId()));
			assertEquals(vo4.getName(), disciplineDAO.getById(vo4.getDisciplineId()).getName());
			
			Assert.assertNotNull(disciplineDAO.getById(vo5.getDisciplineId()));
			assertEquals(vo5.getName(), disciplineDAO.getById(vo5.getDisciplineId()).getName());

			Assert.assertNotNull(disciplineDAO.getById(vo6.getDisciplineId()));
			assertEquals(vo6.getName(), disciplineDAO.getById(vo6.getDisciplineId()).getName());

			Assert.assertNotNull(disciplineDAO.getById(vo7.getDisciplineId()));
			assertEquals(vo7.getName(), disciplineDAO.getById(vo7.getDisciplineId()).getName());
			
			disciplineDAO.delete(vo1.getDisciplineId());
			disciplineDAO.delete(vo2.getDisciplineId());
			disciplineDAO.delete(vo3.getDisciplineId());
			disciplineDAO.delete(vo4.getDisciplineId());
			disciplineDAO.delete(vo5.getDisciplineId());
			disciplineDAO.delete(vo6.getDisciplineId());
			disciplineDAO.delete(vo7.getDisciplineId());
			
			assertEquals(0, disciplineDAO.list().size());			
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		System.out.println("Test Discipline List and GetById finished...");		
	}
}