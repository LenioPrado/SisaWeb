/*
 * File:	     AuthorDAO.java
 * Creation date: 03/06/2015
 * Author:        Lênio
 */

package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.AuthorException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Author;

/**
 * The Class AuthorDAO.
 */
public class AuthorDAO extends BaseDAOWithId<Author, AuthorException> {

	@Override
	protected String getInsertSql() {
		return "insert into authors (author_id, last_name, other_names) values (null,?,?)";
	}

	@Override
	protected String getUpdateSql(Author vo) {
		return "update authors set last_name=?, other_names=? "
				+ " where author_id=" + vo.getAuthorId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from authors where author_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where author_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from authors ";
	}

	@Override
	protected Object[] getData(Author vo) {
		return new Object[] { vo.getLastName(), vo.getOtherNames() };
	}

	@Override
	protected Author fillEntityProperties(ResultSet rs) throws SQLException {
		Author vo = new Author();

		vo.setAuthorId(rs.getInt("author_id"));
		vo.setLastName(rs.getString("last_name"));
		vo.setOtherNames(rs.getString("other_names"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected AuthorException getEntityException(String message) {
		return new AuthorException(message);
	}

	@Override
	protected AuthorException getEntityException(Exception e) {
		return new AuthorException(e);
	}
}
