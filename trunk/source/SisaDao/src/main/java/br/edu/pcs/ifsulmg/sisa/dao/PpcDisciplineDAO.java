/*
* File:	     PpcDisciplineDAO.java
* Creation date: 23/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.PpcDisciplineException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;

/**
 * The Class PpcDisciplineDAO.
 */
public class PpcDisciplineDAO extends BaseDAOWithId<PpcDiscipline, PpcDisciplineException> {

	@Override
	protected String getInsertSql() {
		return "insert into ppcs_disciplines (ppc_discipline_id, discipline_id, ppc_id) values (null,?,?)";
	}

	@Override
	protected String getUpdateSql(PpcDiscipline vo) {
		return "update ppcs_disciplines pd set discipline_id=?, ppc_id=? " +
				" where ppc_discipline_id=" + vo.getPpcDisciplineId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from pd using ppcs_disciplines pd where pd.ppc_discipline_id=" + id ;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where pd.ppc_discipline_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select pd.*, d.* from ppcs_disciplines pd " +
				" inner join ppcs as p " +
				" on pd.ppc_id = p.ppc_id " +
				" inner join disciplines as d" +
				" on pd.discipline_id = d.discipline_id";
	}

	@Override
	protected Object[] getData(PpcDiscipline vo) {
		Object[] objs = { vo.getDiscipline().getDisciplineId(), vo.getPpc().getPpcId() };
		return objs;
	}

	@Override
	protected PpcDiscipline fillEntityProperties(ResultSet rs)
			throws SQLException {
		PpcDiscipline vo = new PpcDiscipline();
		
		vo.setPpcDisciplineId(rs.getInt("pd.ppc_discipline_id"));
		
		vo.getDiscipline().setDisciplineId(rs.getInt("pd.discipline_id"));
		vo.getDiscipline().setName(rs.getString("d.name"));
		vo.getDiscipline().setPeriodSeries(rs.getInt("d.period_series"));
		vo.getDiscipline().setMenu(rs.getString("d.menu"));		
		vo.getDiscipline().setWeekClasses(rs.getInt("d.week_classes"));
		vo.getDiscipline().setType(rs.getString("d.type"));
		
		vo.getPpc().setPpcId(rs.getInt("pd.ppc_id"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	public PpcDiscipline getByPpcAndDiscipline(int ppcId, int disciplineId) throws PpcDisciplineException {
		String sql = "select pd.*, d.* from ppcs_disciplines pd " +
					" inner join disciplines d on pd.discipline_id = d.discipline_id " +
					" inner join ppcs p on pd.ppc_id = p.ppc_id " +
					" where pd.ppc_id = " + ppcId +
					" and pd.discipline_id = " + disciplineId;
		return getBySql(sql);
	}

	@Override
	protected PpcDisciplineException getEntityException(String message) {
		return new PpcDisciplineException(message);
	}

	@Override
	protected PpcDisciplineException getEntityException(Exception e) {
		return new PpcDisciplineException(e);
	}
}