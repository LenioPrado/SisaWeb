/*
 * File:	     PageDAO.java
 * Creation date: 30/03/2015
 * Page:        Amanda
 */

package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.PageException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Page;

/**
 * The Class PageDAO.
 */
public class PageDAO extends BaseDAOWithId<Page, PageException> {

	@Override
	protected String getInsertSql() {
		return "insert into pages (page_id, name, url) values (null,?,?)";
	}

	@Override
	protected String getUpdateSql(Page vo) {
		return "update pages set name=?, url=? "
				+ " where page_id=" + vo.getPageId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from pages where page_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where page_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from pages ";
	}
	
	@Override
	protected String getOrderBySql(){
		return " order by name ";
	}

	@Override
	protected Object[] getData(Page vo) {
		return new Object[] { vo.getName(), vo.getUrl() };
	}

	@Override
	protected Page fillEntityProperties(ResultSet rs) throws SQLException {
		Page vo = new Page();

		vo.setPageId(rs.getInt("page_id"));
		vo.setName(rs.getString("name"));
		vo.setUrl(rs.getString("url"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected PageException getEntityException(String message) {
		return new PageException(message);
	}

	@Override
	protected PageException getEntityException(Exception e) {
		return new PageException(e);
	}
}
