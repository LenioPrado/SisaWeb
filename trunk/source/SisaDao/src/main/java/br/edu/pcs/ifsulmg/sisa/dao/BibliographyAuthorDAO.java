/*
* File:	     BibliographyAuthorDAO.java
* Creation date: 20/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.BibliographyAuthorException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.BibliographyAuthor;

/**
 * The Class BibliographyAuthorDAO.
 */
public class BibliographyAuthorDAO extends BaseDAOWithoutId<BibliographyAuthor, BibliographyAuthorException> {

	@Override
	protected String getInsertSql() {
		return "insert into bibliographies_authors (bibliography_id, author_id) values (?,?)";
	}

	@Override
	protected String getUpdateSql() {
		return "update bibliographies_authors ba set bibliography_id=?, author_id=? ";
	}

	@Override
	protected String getDeleteSql() {
		return "delete from ba using bibliographies_authors ba ";
	}

	@Override
	protected String getWhereByMembersIdsSql(BibliographyAuthor vo) {
		return " where ba.bibliography_id=" + vo.getBibliography().getBibliographyId() +
				" and ba.author_id=" + vo.getAuthor().getAuthorId();
	}

	@Override
	protected String getListSql() {
		return "select ba.*, a.last_name, b.* from bibliographies_authors ba " +
				" inner join authors a on ba.author_id = a.author_id " +
				" inner join bibliographies b on ba.bibliography_id = b.bibliography_id";
	}

	@Override
	protected Object[] getData(BibliographyAuthor vo) {
		Object [] objs = {vo.getBibliography().getBibliographyId(), vo.getAuthor().getAuthorId()};
		return objs;
	}

	@Override
	protected BibliographyAuthor fillEntityProperties(ResultSet rs)
			throws SQLException {
		BibliographyAuthor vo = new BibliographyAuthor();
		
		vo.getBibliography().setBibliographyId(rs.getInt("ba.bibliography_id"));
		vo.getBibliography().setBibliographyType(rs.getInt("b.bibliography_type"));
		vo.getBibliography().setTitle (rs.getString("b.title"));
		vo.getBibliography().setSubtitle(rs.getString("b.subtitle"));
		vo.getBibliography().setEdition(rs.getInt("b.edition"));
		vo.getBibliography().setCityPlace(rs.getString("b.city_place"));
		vo.getBibliography().setPublisher(rs.getString("b.publisher"));
		vo.getBibliography().setYear(rs.getInt("b.year"));
		vo.getBibliography().setPages(rs.getInt("b.pages"));
		vo.getBibliography().setVolume(rs.getInt("b.volume"));
		vo.getBibliography().setSeries(rs.getString("b.series"));
		vo.getBibliography().setIsbn(rs.getString("b.isbn"));
	
		vo.getAuthor().setAuthorId(rs.getInt("ba.author_id"));
		vo.getAuthor().setLastName(rs.getString("a.last_name"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected BibliographyAuthorException getEntityException(String message) {
		return new BibliographyAuthorException(message);
	}

	@Override
	protected BibliographyAuthorException getEntityException(Exception e) {
		return new BibliographyAuthorException(e);
	}
}