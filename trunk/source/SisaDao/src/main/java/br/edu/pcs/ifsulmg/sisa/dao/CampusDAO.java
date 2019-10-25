/*
 * File:	     CampusDAO.java
 * Creation date: 23/06/2015
 * Campus:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.CampusException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Campus;

/**
 * The Class CampusDAO.
 */
public class CampusDAO extends BaseDAOWithId<Campus, CampusException> {

	@Override
	protected String getInsertSql() {
		return "insert into campus values (null,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Campus vo) {
		return "update campus set name=?, cnpj=?, rectory_id=? "
				+ " where campus_id=" + vo.getCampusId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from campus where campus_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where c.campus_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select c.*,r.name from campus c "+
				" inner join rectories r "+
				" on c.rectory_id = r.rectory_id ";
	}

	@Override
	protected Object[] getData(Campus vo) {
		return new Object[] { vo.getName(), vo.getCnpj(), vo.getRectory().getRectoryId() };
	}

	@Override
	protected Campus fillEntityProperties(ResultSet rs) throws SQLException {
		Campus vo = new Campus();

		vo.setCampusId(rs.getInt("c.campus_id"));
		vo.setName(rs.getString("c.name"));
		vo.setCnpj(rs.getString("c.cnpj"));
		vo.getRectory().setRectoryId(rs.getInt("c.rectory_id"));
		vo.getRectory().setName(rs.getString("r.name"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected CampusException getEntityException(String message) {
		return new CampusException(message);
	}

	@Override
	protected CampusException getEntityException(Exception e) {
		return new CampusException(e);
	}
}