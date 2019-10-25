/*
 * File:	     ProcedureEvaluationDAO.java
 * Creation date: 23/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.ProcedureEvaluationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.ProcedureEvaluation;

/**
 * The Class ProcedureEvaluationDAO.
 */
public class ProcedureEvaluationDAO extends BaseDAOWithId<ProcedureEvaluation, ProcedureEvaluationException> {

	@Override
	protected String getInsertSql() {
		return "insert into procedures_evaluations (procedure_id, date, observation, teaching_plan_id, evaluation_id)" +
				" values (null,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(ProcedureEvaluation vo) {
		return "update procedures_evaluations set date=?, observation=? ,"+
				"teaching_plan_id=?, evaluation_id=? "+
				" where procedure_id=" + vo.getProcedureId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from procedures_evaluations where procedure_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where procedure_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select pe.*, ap.semester, ap.year, c.name, d.name, e.name from procedures_evaluations pe " +
				" inner join teaching_plans tp on pe.teaching_plan_id = tp.teaching_plan_id " +
				" inner join evaluations e on pe.evaluation_id = e.evaluation_id " +
				" inner join ppcs_disciplines pd on tp.ppc_discipline_id = pd.ppc_discipline_id " +
				" inner join disciplines d on pd.discipline_id = d.discipline_id " +
				" inner join ppcs p on pd.ppc_id = p.ppc_id " +
				" inner join courses c on c.course_id = p.course_id " +
				" left outer join academics_periods ap on ap.academic_period_id = tp.academic_period_id ";
	}

	@Override
	protected Object[] getData(ProcedureEvaluation vo) {
		return new Object[] { vo.getDate(), vo.getObservation(), 
				vo.getTeachingPlan().getTeachingPlanId(), vo.getEvaluation().getEvaluationId() };
	}

	@Override
	protected ProcedureEvaluation fillEntityProperties(ResultSet rs) throws SQLException {
		ProcedureEvaluation vo = new ProcedureEvaluation();

		vo.setProcedureId(rs.getInt("procedure_id"));
		vo.setDate(rs.getTimestamp("date"));
		vo.setObservation(rs.getString("observation"));

		vo.getEvaluation().setEvaluationId(rs.getInt("evaluation_id"));
		vo.getEvaluation().setName(rs.getString("e.name"));
		
		vo.getTeachingPlan().setTeachingPlanId(rs.getInt("pe.teaching_plan_id"));
		vo.getTeachingPlan().getAcademicPeriod().setSemester(rs.getInt("ap.semester"));
		vo.getTeachingPlan().getAcademicPeriod().setYear(rs.getInt("ap.year"));
		
		vo.getTeachingPlan().getPpcDiscipline().getPpc().getCourse().setName(rs.getString("c.name"));
		vo.getTeachingPlan().getPpcDiscipline().getDiscipline().setName(rs.getString("d.name"));
		
		return vo;
	}
	
	public List<ProcedureEvaluation> listByTeachingPlan(int teachingPlanId) throws ProcedureEvaluationException {
		String sql = getListSql() + " " + getGroupBySql() + 
				" WHERE tp.teaching_plan_id = " + teachingPlanId + " " + getOrderBySql() ;
		try {
			return listBySql(sql);
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
		
	@Override
	protected ProcedureEvaluationException getEntityException(String message) {
		return new ProcedureEvaluationException(message);
	}

	@Override
	protected ProcedureEvaluationException getEntityException(Exception e) {
		return new ProcedureEvaluationException(e);
	}
}