/*
* File:	     EvaluationDAO.java
* Creation date: 19/06/2015
* Author:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.EvaluationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Evaluation;

/**
 * The Class EvaluationDAO.
 */
public class EvaluationDAO extends BaseDAOWithId<Evaluation, EvaluationException> {

	@Override
	protected String getInsertSql() {
		return "insert into evaluations (evaluation_id, name, description) values (null,?,?)";
	}

	@Override
	protected String getUpdateSql(Evaluation vo) {
		return "update evaluations set name=?, description=? " +
				"where evaluation_id=" + vo.getEvaluationId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from evaluations where evaluation_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where evaluation_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from evaluations";
	}

	@Override
	protected Object[] getData(Evaluation vo) {
		Object[] objs = { vo.getName(), vo.getDescription()};
		return objs;
	}

	@Override
	protected Evaluation fillEntityProperties(ResultSet rs)
			throws SQLException {
		Evaluation vo = new Evaluation();
		
		vo.setEvaluationId(rs.getInt("evaluation_id"));
		vo.setName(rs.getString("name"));
		vo.setDescription (rs.getString("description"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected EvaluationException getEntityException(String message) {
		return new EvaluationException(message);
	}

	@Override
	protected EvaluationException getEntityException(Exception e) {
		return new EvaluationException(e);
	}
}
