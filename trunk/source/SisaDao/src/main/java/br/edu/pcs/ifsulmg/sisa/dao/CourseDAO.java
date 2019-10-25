/*
* File:	     CourseDAO.java
* Creation date: 23/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.CourseException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Course;

/**
 * The Class CourseDAO.
 */
public class CourseDAO extends BaseDAOWithId<Course, CourseException> {

	@Override
	protected String getInsertSql() {
		return "insert into courses (course_id,name,modality,shift,short_name) values (null,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Course vo) {
		return "update courses set name=?, modality=?, shift=?, short_name=?"
				+ " where course_id=" + vo.getCourseId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from courses where course_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where course_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from courses ";
	}

	@Override
	protected Object[] getData(Course vo) {
		return new Object[] { vo.getName(), vo.getModality(), vo.getShift(), vo.getShortName() };
	}

	@Override
	protected Course fillEntityProperties(ResultSet rs) throws SQLException {
		Course vo = new Course();

		vo.setCourseId(rs.getInt("course_id"));
		vo.setName(rs.getString("name"));
		vo.setModality(rs.getInt("modality"));
		vo.setShift(rs.getInt("shift"));
		vo.setShortName(rs.getString("short_name"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected CourseException getEntityException(String message) {
		return new CourseException(message);
	}

	@Override
	protected CourseException getEntityException(Exception e) {
		return new CourseException(e);
	}
}