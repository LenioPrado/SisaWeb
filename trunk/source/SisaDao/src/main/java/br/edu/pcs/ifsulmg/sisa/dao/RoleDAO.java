/*
* File:	     RoleDAO.java
* Creation date: 14/06/2015
* Author:        vinícius
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.RoleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Role;

/**
 * The Class RoleDAO.
 */
public class RoleDAO extends BaseDAOWithId<Role, RoleException> {

	@Override
	protected String getInsertSql() {
		return "insert into roles values(null,?,?)";
	}

	@Override
	protected String getUpdateSql(Role vo) {
		return "update roles set name=?, observation=? "
				+ "where role_id = " + vo.getRoleId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from roles where role_id = " + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where role_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from roles ";
	}

	@Override
	protected Object[] getData(Role vo) {
		Object[] objs = {vo.getName(), vo.getObservation()};
		return objs;
	}

	@Override
	protected Role fillEntityProperties(ResultSet rs)
			throws SQLException {
		Role vo = new Role();
		
		vo.setRoleId(rs.getInt("role_id"));
		vo.setName(rs.getString("name"));
		vo.setObservation(rs.getString("observation"));

		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
		
	@Override
	protected RoleException getEntityException(String message) {
		return new RoleException(message);
	}

	@Override
	protected RoleException getEntityException(Exception e) {
		return new RoleException(e);
	}	
}