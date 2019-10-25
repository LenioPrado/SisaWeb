/*
* File:	     BibliographyDAO.java
* Creation date: 19/06/2015
* Author:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.BibliographyException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;

/**
 * The Class BibliographyDAO.
 */
public class BibliographyDAO extends BaseDAOWithId<Bibliography, BibliographyException>{

	@Override
	protected String getInsertSql() {
		return "insert into bibliographies (bibliography_id,bibliography_type,title," +
				"subtitle,edition,city_place,publisher,year,pages,volume,series,isbn) " +
				"values (null,?,?,?,?,?,?,?,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Bibliography vo) {
		return "update bibliographies set bibliography_type=?, title=?, subtitle=?, edition=?, city_place=?, " +
				"publisher=?, year=?, pages=?, volume=?, series=?, isbn=? " +
				"where bibliography_id="+ vo.getBibliographyId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from bibliographies where bibliography_id="+ id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where b.bibliography_id="+ id;
	}

	@Override
	protected String getListSql() {
		return "select distinct b.* from bibliographies b " +
				" left join disciplines_bibliographies db " +
				" on b.bibliography_id = db.bibliography_id ";
	}

	@Override
	protected Object[] getData(Bibliography vo) {
		Object [] objs = {vo.getBibliographyType(), vo.getTitle(), vo.getSubtitle(),vo.getEdition(), 
				vo.getCityPlace(), vo.getPublisher(), vo.getYear(), vo.getPages(), vo.getVolume(), 
				vo.getSeries(), vo.getIsbn()};
		return objs;
	}

	@Override
	protected Bibliography fillEntityProperties(ResultSet rs) throws SQLException {
		Bibliography vo = new Bibliography();
		
		vo.setBibliographyId(rs.getInt("bibliography_id"));
		vo.setBibliographyType(rs.getInt("bibliography_type"));
		vo.setTitle (rs.getString("title"));
		vo.setSubtitle(rs.getString("subtitle"));
		vo.setEdition(rs.getInt("edition"));
		vo.setCityPlace(rs.getString("city_place"));
		vo.setPublisher(rs.getString("publisher"));
		vo.setYear(rs.getInt("year"));
		vo.setPages(rs.getInt("pages"));
		vo.setVolume(rs.getInt("volume"));
		vo.setSeries(rs.getString("series"));
		vo.setIsbn(rs.getString("isbn"));		
		
		return vo;
	}
	
	public List<Bibliography> listByDiscipline(int disciplineId) throws BibliographyException {
		String sql = getListSql() + " " + getGroupBySql() + 
				" WHERE db.discipline_id = " + disciplineId + getOrderBySql();
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
	protected BibliographyException getEntityException(String message) {
		return new BibliographyException(message);
	}

	@Override
	protected BibliographyException getEntityException(Exception e) {
		return new BibliographyException(e);
	}
}