/*
 * File:	     TraineeshipDAO.java
 * Creation date: 23/06/2015
 * Traineeship:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.TraineeshipException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;

/**
 * The Class TraineeshipDAO.
 */
public class TraineeshipDAO extends BaseDAOWithId<Traineeship, TraineeshipException> {

	@Override
	protected String getInsertSql() {
		return "insert into traineeships values (null,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Traineeship vo) {
		return "update traineeships set hour_load=?, description=?, short_description=? "
				+ " where traineeship_id=" + vo.getTraineeshipId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from traineeships where traineeship_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where traineeship_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from traineeships ";
	}

	@Override
	protected Object[] getData(Traineeship vo) {
		return new Object[] { vo.getHourLoad(), vo.getDescription(), vo.getShortDescription() };
	}

	@Override
	protected Traineeship fillEntityProperties(ResultSet rs) throws SQLException {
		Traineeship vo = new Traineeship();

		vo.setTraineeshipId(rs.getInt("traineeship_id"));
		vo.setHourLoad(rs.getInt("hour_load"));
		vo.setDescription(rs.getString("description"));
		vo.setShortDescription(rs.getString("short_description"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected TraineeshipException getEntityException(String message) {
		return new TraineeshipException(message);
	}

	@Override
	protected TraineeshipException getEntityException(Exception e) {
		return new TraineeshipException(e);
	}
}