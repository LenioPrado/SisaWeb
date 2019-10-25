/*
 * File:	     RectoryDAO.java
 * Creation date: 23/06/2015
 * Rectory:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.RectoryException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Rectory;

/**
 * The Class RectoryDAO.
 */
public class RectoryDAO extends BaseDAOWithId<Rectory, RectoryException> {

	@Override
	protected String getInsertSql() {
		return "insert into rectories (rectory_id, cnpj, name) values (null,?,?)";
	}

	@Override
	protected String getUpdateSql(Rectory vo) {
		return "update rectories set cnpj=?, name=? "
				+ " where rectory_id=" + vo.getRectoryId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from rectories where rectory_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where rectory_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from rectories ";
	}

	@Override
	protected Object[] getData(Rectory vo) {
		return new Object[] { vo.getCnpj(), vo.getName() };
	}

	@Override
	protected Rectory fillEntityProperties(ResultSet rs) throws SQLException {
		Rectory vo = new Rectory();

		vo.setRectoryId(rs.getInt("rectory_id"));
		vo.setCnpj(rs.getString("cnpj"));
		vo.setName(rs.getString("name"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected RectoryException getEntityException(String message) {
		return new RectoryException(message);
	}

	@Override
	protected RectoryException getEntityException(Exception e) {
		return new RectoryException(e);
	}
}