/*
* File:	     PpcDAO.java
* Creation date: 18/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.PpcException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;

/**
 * The Class PpcDAO.
 */
public class PpcDAO extends BaseDAOWithId<Ppc, PpcException> {

	@Override
	protected String getInsertSql() {
		return "insert into ppcs values (null,?,?,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Ppc vo) {
		return "update ppcs set date=?, place=?, class_hour=?, weeks_number=?, " +
				" course_id=?, traineeship_id=? " +
				" where ppc_id=" + vo.getPpcId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from ppcs where ppc_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where p.ppc_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select distinct p.*, c.name, c.short_name, t.short_description, " +
				" (select sum(d.week_classes) from disciplines d " +
				" inner join ppcs_disciplines pd on d.discipline_id = pd.discipline_id " +
				" where pd.ppc_id = p.ppc_id) as total_week_classes " +
				" from ppcs p inner join courses c  on p.course_id = c.course_id " +
				" inner join traineeships t  on p.traineeship_id = t.traineeship_id " +
				" left outer join ppcs_disciplines pd on p.ppc_id = pd.ppc_id " +
				" left outer join disciplines d on pd.discipline_id = d.discipline_id ";
	}

	@Override
	protected Object[] getData(Ppc vo) {
		Object[] objs = { vo.getDate(), vo.getPlace(), 
				vo.getClassHour(), vo.getWeeksNumber(), vo.getCourse().getCourseId(), 
				vo.getTraineeship().getTraineeshipId()};
		return objs;
	}

	@Override
	protected Ppc fillEntityProperties(ResultSet rs)
			throws SQLException {
		Ppc vo = new Ppc();
		
		vo.setPpcId(rs.getInt("p.ppc_id"));
		vo.setDate(rs.getDate("p.date"));
		vo.setPlace(rs.getString("p.place"));
		vo.setClassHour(rs.getInt("p.class_hour"));
		vo.setWeeksNumber(rs.getInt("p.weeks_number"));
		vo.setTotalWeekClasses(rs.getInt("total_week_classes"));
		
		vo.getCourse().setCourseId(rs.getInt("p.course_id"));
		vo.getCourse().setName(rs.getString("c.name"));
		vo.getCourse().setShortName(rs.getString("c.short_name"));
		vo.getTraineeship().setTraineeshipId(rs.getInt("traineeship_id"));
		vo.getTraineeship().setShortDescription(rs.getString("t.short_description"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected PpcException getEntityException(String message) {
		return new PpcException(message);
	}

	@Override
	protected PpcException getEntityException(Exception e) {
		return new PpcException(e);
	}
	
	public List<Ppc> listByCourse(int courseId) throws PpcException{
		String sql = getListSql() + " where c.course_id = " + courseId;
		return listBySql(sql);
	}
}