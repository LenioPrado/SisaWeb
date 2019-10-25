/*
 * File:	     PageRoleDAO.java
 * Creation date: 23/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.PageRoleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.PageRole;
import br.edu.pcs.ifsulmg.sisa.vo.Role;

/**
 * The Class PageRoleDAO.
 */
public class PageRoleDAO extends BaseDAOWithoutId<PageRole, PageRoleException> {

	@Override
	protected String getInsertSql() {
		return "insert into pages_roles values (?,?,?)";
	}

	@Override
	protected String getUpdateSql() {
		return "update pages_roles pr set page_id=?, role_id=?, action_id=?";
	}

	@Override
	protected String getDeleteSql() {
		return "delete from pr using pages_roles pr ";
	}

	@Override
	protected String getWhereByMembersIdsSql(PageRole vo) {
		return " where pr.page_id=" + vo.getPage().getPageId() +
				" and pr.role_id=" + vo.getRole().getRoleId() +
				" and pr.action_id=" + vo.getAction().getActionId();
	}

	@Override
	protected String getListSql() {
		return "select pr.*, p.name, p.url, r.name, r.observation, a.description from pages_roles pr " + 
				" inner join pages p on pr.page_id = p.page_id " +
				" inner join roles r on pr.role_id = r.role_id " + 
				" inner join actions a on pr.action_id = a.action_id";
	}

	@Override
	protected Object[] getData(PageRole vo) {
		return new Object[] { vo.getPage().getPageId(), vo.getRole().getRoleId(),
				vo.getAction().getActionId()};
	}

	@Override
	protected PageRole fillEntityProperties(ResultSet rs) throws SQLException {
		PageRole vo = new PageRole();

		vo.getPage().setPageId(rs.getInt("pr.page_id"));
		vo.getPage().setName(rs.getString("p.name"));
		vo.getPage().setUrl(rs.getString("p.url"));
		vo.getRole().setRoleId(rs.getInt("pr.role_id"));
		vo.getRole().setName(rs.getString("r.name"));
		vo.getAction().setActionId(rs.getInt("pr.action_id"));
		vo.getAction().setDescription(rs.getString("a.description"));
		return vo;
	}

	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	public List<PageRole> listByRole(Role role) throws PageRoleException {
		String sql = getListSql() + " where pr.role_id = " + role.getRoleId();
		return listBySql(sql);
	}


	@Override
	protected PageRoleException getEntityException(String message) {
		return new PageRoleException(message);
	}

	@Override
	protected PageRoleException getEntityException(Exception e) {
		return new PageRoleException(e);
	}
}