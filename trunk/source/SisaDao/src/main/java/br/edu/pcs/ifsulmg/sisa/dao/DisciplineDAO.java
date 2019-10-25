/*
 * File:	     DisciplineDAO.java
 * Creation date: 23/06/2015
 * Discipline:        Lênio
 */

package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;

public class DisciplineDAO extends BaseDAOWithId<Discipline, DisciplineException> {

	@Override
	protected String getInsertSql() {
		return "insert into disciplines (discipline_id,period_series,menu,name,week_classes,type) " +
				" values (null,?,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Discipline vo) {
		return "update disciplines set period_series=?, menu=?, name=?, week_classes=?, type=? " +
				"where discipline_id=" + vo.getDisciplineId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from disciplines where discipline_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " WHERE d.discipline_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select distinct d.*, " +
				" (select sum(cp.classes_quantity) classes_quantity_sum from classes_programming cp " +  
				" inner join teaching_plans tp  on cp.teaching_plan_id = tp.teaching_plan_id " +
				" inner join ppcs_disciplines pd  on tp.ppc_discipline_id = pd.ppc_discipline_id " +
				" right outer join disciplines d  on pd.discipline_id = d.discipline_id " +
				" HAVING classes_quantity_sum IS NOT NULL) classes_quantity_sum " +
				" from disciplines d " +
				" left outer join ppcs_disciplines pd on pd.discipline_id = d.discipline_id ";
	}

	@Override
	protected Object[] getData(Discipline vo) {
		return new Object[] { vo.getPeriodSeries(), vo.getMenu(), 
				vo.getName(), vo.getWeekClasses(), vo.getType()};
	}

	@Override
	protected Discipline fillEntityProperties(ResultSet rs) throws SQLException {
		Discipline vo = new Discipline();

		vo.setDisciplineId(rs.getInt("discipline_id"));
		vo.setPeriodSeries(rs.getInt("period_series"));
		vo.setMenu(rs.getString("menu"));
		vo.setName(rs.getString("name"));		
		vo.setWeekClasses(rs.getInt("week_classes"));
		vo.setType(rs.getString("type"));
		
		return vo;
	}
	
	public List<Discipline> listByPpc(int ppcId) throws DisciplineException {
		String sql = getListSql() + " where pd.ppc_id = " + ppcId;
		return listBySql(sql);
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected DisciplineException getEntityException(String message) {
		return new DisciplineException(message);
	}

	@Override
	protected DisciplineException getEntityException(Exception e) {
		return new DisciplineException(e);
	}
}