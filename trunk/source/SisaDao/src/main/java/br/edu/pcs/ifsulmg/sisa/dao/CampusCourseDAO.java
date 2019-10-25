/*
 * File:	     CampusCourseDAO.java
 * Creation date: 	23/06/2015
 * Verification:     Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.CampusCourseException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.CampusCourse;

/**
 * The Class CampusCourseDAO.
 */
public class CampusCourseDAO extends BaseDAOWithoutId<CampusCourse, CampusCourseException> {

	@Override
	protected String getInsertSql() {
		return "insert into campus_courses(course_id, campus_id) values(?,?)";
	}

	@Override
	protected String getUpdateSql() {
		return "update campus_courses cc set course_id=?, campus_id=? ";
	}

	@Override
	protected String getDeleteSql() {
		return "delete from cc using campus_courses cc ";
	}

	@Override
	protected String getWhereByMembersIdsSql(CampusCourse vo) {
		return " where cc.course_id=" + vo.getCourse().getCourseId() +
				" and cc.campus_id=" + vo.getCampus().getCampusId();
	}

	@Override
	protected String getListSql() {
		return "select cc.*, co.*, ca.name from campus_courses cc " +
				" inner join courses co on cc.course_id = co.course_id " +
				" inner join campus ca on cc.campus_id = ca.campus_id";
	}
	
	@Override
	protected Object[] getData(CampusCourse vo) {
		Object[] objs = { vo.getCourse().getCourseId(), vo.getCampus().getCampusId() };
		return objs;
	}

	@Override
	protected CampusCourse fillEntityProperties(ResultSet rs) throws SQLException {
		CampusCourse vo = new CampusCourse();

		vo.getCourse().setCourseId(rs.getInt("course_id"));
		vo.getCourse().setName(rs.getString("co.name"));
		vo.getCourse().setModality(rs.getInt("co.modality"));
		vo.getCourse().setShift(rs.getInt("co.shift"));
		vo.getCourse().setShortName(rs.getString("co.short_name"));
		
		vo.getCampus().setCampusId(rs.getInt("campus_id"));
		vo.getCampus().setName(rs.getString("ca.name"));
		
		return vo;
	}

	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected CampusCourseException getEntityException(String message) {
		return new CampusCourseException(message);
	}

	@Override
	protected CampusCourseException getEntityException(Exception e) {
		return new CampusCourseException(e);
	}
}