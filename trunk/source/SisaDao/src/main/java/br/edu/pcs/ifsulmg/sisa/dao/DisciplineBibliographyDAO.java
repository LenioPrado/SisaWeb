/*
 * File:	     DisciplineBibliographyDAO.java
 * Creation date: 	23/06/2015
 * Verification:     Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineBibliographyException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.DisciplineBibliography;

/**
 * The Class DisciplineBibliographyDAO.
 */
public class DisciplineBibliographyDAO extends BaseDAOWithoutId<DisciplineBibliography, DisciplineBibliographyException> {

	@Override
	protected String getInsertSql() {
		return "insert into disciplines_bibliographies (bibliography_id, discipline_id, is_basic) " +
				" values(?,?,?)";
	}

	@Override
	protected String getUpdateSql() {
		return "update disciplines_bibliographies db set bibliography_id=?, discipline_id=? ";
	}

	@Override
	protected String getDeleteSql() {
		return "delete from db using disciplines_bibliographies db ";
	}

	@Override
	protected String getWhereByMembersIdsSql(DisciplineBibliography vo) {
		return " where db.discipline_id=" + vo.getDiscipline().getDisciplineId() +
				" and db.bibliography_id=" + vo.getBibliography().getBibliographyId();
	}

	@Override
	protected String getListSql() {
		return "select db.*, d.name, b.* from disciplines_bibliographies as db " +
				" inner join disciplines as d" +
				" on db.discipline_id = d.discipline_id" +
				" inner join bibliographies as b" +
				" on db.bibliography_id = b.bibliography_id";
	}
	
	@Override
	protected Object[] getData(DisciplineBibliography vo) {
		Object[] objs = { vo.getBibliography().getBibliographyId(), 
				vo.getDiscipline().getDisciplineId(), vo.isBasic() };
		return objs;
	}

	@Override
	protected DisciplineBibliography fillEntityProperties(ResultSet rs) throws SQLException {
		DisciplineBibliography vo = new DisciplineBibliography();

		vo.getBibliography().setBibliographyId(rs.getInt("db.bibliography_id"));
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
		
		vo.getDiscipline().setDisciplineId(rs.getInt("db.discipline_id"));
		vo.getDiscipline().setName(rs.getString("d.name"));
		
		vo.setBasic(rs.getBoolean("db.is_basic"));
		
		return vo;
	}
	
	public List<DisciplineBibliography> listByDiscipline(int disciplineId) throws DisciplineBibliographyException{
		String sql = getListSql() + " where db.discipline_id = " + disciplineId;
		return listBySql(sql);
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected DisciplineBibliographyException getEntityException(String message) {
		return new DisciplineBibliographyException(message);
	}

	@Override
	protected DisciplineBibliographyException getEntityException(Exception e) {
		return new DisciplineBibliographyException(e);
	}
}