package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.ActionException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Action;


public class ActionDAO extends BaseDAOWithId<Action, ActionException> {

	@Override
	protected String getInsertSql() {
		return "insert into actions (action_id, description) values (null,?)";
	}

	@Override
	protected String getUpdateSql(Action vo) {
		return "update actions set description=? "
				+ " where action_id=" + vo.getActionId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from actions where action_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where action_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from actions ";
	}

	@Override
	protected Object[] getData(Action vo) {
		return new Object[] { vo.getDescription() };
	}

	@Override
	protected Action fillEntityProperties(ResultSet rs) throws SQLException {
		Action vo = new Action();

		vo.setActionId(rs.getInt("action_id"));
		vo.setDescription(rs.getString("description"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected ActionException getEntityException(String message) {
		return new ActionException(message);
	}

	@Override
	protected ActionException getEntityException(Exception e) {
		return new ActionException(e);
	}
}
