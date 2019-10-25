/*
* File:	     EvaluationDAOTest.java
* Creation date: 03/07/2015
* TeachingPlan:     Amanda
*/
package br.edu.pcs.ifsulmg.sisa.test.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.pcs.ifsulmg.sisa.dao.EvaluationDAO;
import br.edu.pcs.ifsulmg.sisa.vo.Evaluation;

public class EvaluationDAOTest {

	EvaluationDAO evaluationDAO;
	
	@Before
	public void setUp() throws Exception {
		try{
			evaluationDAO = new EvaluationDAO();
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
	public void testInsertEvaluation() {
		System.out.println("Test Insert Evaluation...");

		try {
			assertEquals(0, evaluationDAO.list().size());
									
			Evaluation vo = new Evaluation(1, "Nome", "Descrição");
			vo.setEvaluationId(evaluationDAO.insert(vo));

			assertEquals(1, evaluationDAO.list().size());

			Evaluation retrieved = evaluationDAO.getById(vo.getEvaluationId());

			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getDescription(), retrieved.getDescription());
			
			evaluationDAO.delete(vo.getEvaluationId());

			assertEquals(0, evaluationDAO.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Test Insert Evaluation finished...");
	}
	
	@Test
	public void testUpdateEvaluation(){
		System.out.println ("Test Update Evaluation...");
		
		try {
			assertEquals (0, evaluationDAO.list().size());
			
			Evaluation vo = new Evaluation (1,"Nome","Descrição");
			vo.setEvaluationId(evaluationDAO.insert(vo));
			
			Evaluation vo1 = new Evaluation (2,"Nome2","Descrição2");
			vo1.setEvaluationId(evaluationDAO.insert(vo1));
			
			assertEquals (2, evaluationDAO.list().size());
			
			vo.setName("NomeUpdate");
			vo.setDescription("DescriçãoUpdate");			
			
			vo1.setName("NomeUpdate2");
			vo1.setDescription("DescriçãoUpdate2");
			
			evaluationDAO.update(vo);
			evaluationDAO.update(vo1);
			
			Evaluation retrieved = evaluationDAO.getById(vo.getEvaluationId());
			Evaluation retrieved1 = evaluationDAO.getById(vo1.getEvaluationId());
			
			assertEquals(vo.getName(), retrieved.getName());
			assertEquals(vo.getDescription(), retrieved.getDescription());
			
			assertEquals(vo1.getName(), retrieved1.getName());
			assertEquals(vo1.getDescription(), retrieved1.getDescription());
						
			evaluationDAO.delete(vo.getEvaluationId());
			evaluationDAO.delete(vo1.getEvaluationId());
			
			assertEquals (0, evaluationDAO.list().size());
		} catch (Exception e){
			e.printStackTrace();
			}
		System.out.println("Test Update Evaluation finished...");
		}
	
	@Test
	public void testListAndGetById(){
		 System.out.println("Test Evaluation List and GetById...");
		 
		 try{	 
			 assertEquals (0, evaluationDAO.list().size());	 
			 
			 Evaluation vo = new Evaluation (1, "Nome", "Descrição");
			 Evaluation vo1 = new Evaluation (2, "Nome2", "Descrição2");
			 Evaluation vo2 = new Evaluation (3, "Nome3", "Descrição3");
			
			 vo.setEvaluationId(evaluationDAO.insert(vo));
			 vo1.setEvaluationId(evaluationDAO.insert(vo1));
			 vo2.setEvaluationId(evaluationDAO.insert(vo2));
						 
			 assertEquals (3,evaluationDAO.list().size());
			 
			 Assert.assertNotNull(evaluationDAO.getById(vo.getEvaluationId()));
			 assertEquals (vo.getName(),evaluationDAO.getById(vo.getEvaluationId()).getName());
			 
			 Assert.assertNotNull(evaluationDAO.getById(vo1.getEvaluationId()));
			 assertEquals (vo1.getDescription(),evaluationDAO.getById(vo1.getEvaluationId()).getDescription());
			 	
			 Assert.assertNotNull(evaluationDAO.getById(vo2.getEvaluationId()));
			 assertEquals (vo2.getDescription(),evaluationDAO.getById(vo2.getEvaluationId()).getDescription());
			 
			 evaluationDAO.delete(vo.getEvaluationId());
			 evaluationDAO.delete(vo1.getEvaluationId());
			 evaluationDAO.delete(vo2.getEvaluationId());
			 			
			 assertEquals (0, evaluationDAO.list().size());					 
		 } catch (Exception e){
			 e.printStackTrace();
		 }
		 System.out.println("Test Evaluation List and GetById finished...");
	}
}