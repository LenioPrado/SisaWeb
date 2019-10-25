/*
 * File:	     AcademicPeriodCourseDAO.java
 * Creation date: 	19/06/2015
 * Verification:     PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.AcademicPeriodCourseException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriodCourse;

/**
 * The Class AcademicPeriodCourseDAO.
 */
public class AcademicPeriodCourseDAO extends BaseDAOWithoutId<AcademicPeriodCourse, AcademicPeriodCourseException> {

	@Override
	protected String getInsertSql() {
		return "insert into academics_periods_courses (academic_period_id, course_id) values(?,?)";
	}

	@Override
	protected String getUpdateSql() {
		return "update academics_periods_courses apc set academic_period_id=?, course_id=? ";
	}

	@Override
	protected String getDeleteSql() {
		return "delete from apc using academics_periods_courses apc ";
	}

	@Override
	protected String getWhereByMembersIdsSql(AcademicPeriodCourse vo) {
		return " where apc.course_id=" + vo.getCourse().getCourseId() +
				" and apc.academic_period_id=" + vo.getAcademicPeriod().getAcademicPeriodId();
	}

	@Override
	protected String getListSql() {
		return "select apc.*, ap.*, c.name from academics_periods_courses as apc " +
				" inner join academics_periods as ap " +
				" on apc.academic_period_id = ap.academic_period_id " +
				" inner join courses as c " +
				" on apc.course_id = c.course_id ";
	}
	
	@Override
	protected Object[] getData(AcademicPeriodCourse vo) {
		Object[] objs = { vo.getAcademicPeriod().getAcademicPeriodId(), vo.getCourse().getCourseId() };
		return objs;
	}

	@Override
	protected AcademicPeriodCourse fillEntityProperties(ResultSet rs) throws SQLException {
		AcademicPeriodCourse vo = new AcademicPeriodCourse();

		vo.getAcademicPeriod().setAcademicPeriodId(rs.getInt("apc.academic_period_id"));
		vo.getAcademicPeriod().setYear(rs.getInt("ap.year"));
		vo.getAcademicPeriod().setSemester(rs.getInt("ap.semester"));
		vo.getAcademicPeriod().setType(rs.getString("ap.type"));
		
		vo.getCourse().setCourseId(rs.getInt("apc.course_id"));
		vo.getCourse().setName(rs.getString("c.name"));
		
		return vo;
	}

	@Override
	protected AcademicPeriodCourseException getEntityException(String message) {
		return new AcademicPeriodCourseException(message);
	}

	@Override
	protected AcademicPeriodCourseException getEntityException(Exception e) {
		return new AcademicPeriodCourseException(e);
	}

	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
}