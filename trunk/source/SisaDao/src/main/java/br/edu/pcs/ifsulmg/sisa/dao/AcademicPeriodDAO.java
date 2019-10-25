/*
 * File:	     AcademicPeriodDAO.java
 * Creation date: 19/06/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.AcademicPeriodException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;

/**
 * The Class AcademicPeriodDAO.
 */
public class AcademicPeriodDAO extends BaseDAOWithId<AcademicPeriod, AcademicPeriodException> {

	@Override
	protected String getInsertSql() {
		return "insert into academics_periods (academic_period_id, year, semester, type) values (null,?,?,?)";
	}

	@Override
	protected String getUpdateSql(AcademicPeriod vo) {
		return "update academics_periods set year=?, semester=?, type=?"
				+ " where academic_period_id=" + vo.getAcademicPeriodId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from academics_periods where academic_period_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where academic_period_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from academics_periods";
	}

	@Override
	protected Object[] getData(AcademicPeriod vo) {
		Object[] objs = { vo.getYear(), vo.getSemester(), vo.getType() };
		return objs;
	}

	@Override
	protected AcademicPeriod fillEntityProperties(ResultSet rs)
			throws SQLException {
		AcademicPeriod vo = new AcademicPeriod();

		vo.setAcademicPeriodId(rs.getInt("academic_period_id"));
		vo.setYear(rs.getInt("year"));
		vo.setSemester(rs.getInt("semester"));
		vo.setType(rs.getString("type"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected AcademicPeriodException getEntityException(String message) {
		return new AcademicPeriodException(message);
	}

	@Override
	protected AcademicPeriodException getEntityException(Exception e) {
		return new AcademicPeriodException(e);
	}
}
