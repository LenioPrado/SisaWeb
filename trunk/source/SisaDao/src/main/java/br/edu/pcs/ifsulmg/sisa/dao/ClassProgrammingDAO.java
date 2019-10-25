/*
 * File:	     ClassProgrammingDAO.java
 * Creation date: 23/06/2015
 * ClassProgramming:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.ClassProgrammingException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.ClassProgramming;

/**
 * The Class ClassProgrammingDAO.
 */
public class ClassProgrammingDAO extends BaseDAOWithId<ClassProgramming, ClassProgrammingException> {

	@Override
	protected String getInsertSql() {
		return "insert into classes_programming (programming_id, content, classes_quantity, class_type, teaching_plan_id) values (null,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(ClassProgramming vo) {
		return "update classes_programming set content=?, classes_quantity=?, class_type=?, teaching_plan_id=? "+
				"where programming_id=" + vo.getProgrammingId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from classes_programming where programming_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where programming_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select cp.*, ap.semester, ap.year, c.name, d.name from classes_programming as cp " +
				" inner join teaching_plans as tp " +
				" on cp.teaching_plan_id = tp.teaching_plan_id " +
				" inner join ppcs_disciplines pd on tp.ppc_discipline_id = pd.ppc_discipline_id " +
				" inner join disciplines d on pd.discipline_id = d.discipline_id " +
				" inner join ppcs p on pd.ppc_id = p.ppc_id " +
				" inner join courses c on c.course_id = p.course_id " +
				" left outer join academics_periods ap on ap.academic_period_id = tp.academic_period_id ";
	}

	@Override
	protected Object[] getData(ClassProgramming vo) {
		return new Object[] { vo.getContent(), vo.getClassesQuantity(), vo.getClassType(),
				vo.getTeachingPlan().getTeachingPlanId()};
	}

	@Override
	protected ClassProgramming fillEntityProperties(ResultSet rs) throws SQLException {
		ClassProgramming vo = new ClassProgramming();

		vo.setProgrammingId(rs.getInt("programming_id"));
		vo.setContent(rs.getString("content"));
		vo.setClassesQuantity(rs.getInt("classes_quantity"));
		vo.setClassType(rs.getInt("class_type"));
		
		vo.getTeachingPlan().setTeachingPlanId(rs.getInt("cp.teaching_plan_id"));
		vo.getTeachingPlan().getAcademicPeriod().setSemester(rs.getInt("ap.semester"));
		vo.getTeachingPlan().getAcademicPeriod().setYear(rs.getInt("ap.year"));
		
		vo.getTeachingPlan().getPpcDiscipline().getPpc().getCourse().setName(rs.getString("c.name"));
		vo.getTeachingPlan().getPpcDiscipline().getDiscipline().setName(rs.getString("d.name"));
		
		return vo;
	}
	
	public List<ClassProgramming> listByTeachingPlan(int teachingPlanId) throws ClassProgrammingException {
		String sql = getListSql() + " " + getGroupBySql() + 
				" WHERE cp.teaching_plan_id = " + teachingPlanId + " " + getOrderBySql() ;
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
	protected ClassProgrammingException getEntityException(String message) {
		return new ClassProgrammingException(message);
	}

	@Override
	protected ClassProgrammingException getEntityException(Exception e) {
		return new ClassProgrammingException(e);
	}
}