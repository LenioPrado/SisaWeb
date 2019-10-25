/*
 * File:	     ResponsibleDAO.java
 * Creation date: 	23/06/2015
 * Verification:     Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.ResponsibleException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Responsible;

/**
 * The Class ResponsibleDAO.
 */
public class ResponsibleDAO extends BaseDAOWithId<Responsible, ResponsibleException> {

	@Override
	protected String getInsertSql() {
		return "insert into responsibles values(null,?,?)";
	}

	@Override
	protected String getUpdateSql(Responsible vo) {
		return "update responsibles re set user_role_id=?, teaching_plan_id=? where responsible_id=" + vo.getResponsibleId();
	}

	@Override
	protected String getDeleteSql(int id) {
	 return "delete from responsibles where responsible_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where responsible_id=" +id;
	}

	@Override
	protected String getListSql() {
		return "select re.*, ur.user_id, u.name, u.email, r.role_id, r.name, d.name, " +
				" ap.semester, ap.year, c.name, users.name from responsibles re " +  
				" inner join users_roles ur on re.user_role_id = ur.user_role_id " +
				" inner join roles r on ur.role_id = r.role_id " +
				" inner join users u on ur.user_id = u.user_id " +
				" inner join teaching_plans tp on re.teaching_plan_id = tp.teaching_plan_id " +
				" inner join ppcs_disciplines pd on pd.ppc_discipline_id = tp.ppc_discipline_id " +
				" inner join disciplines d on pd.discipline_id = d.discipline_id " +
				" inner join ppcs p on pd.ppc_id = p.ppc_id " +
				" inner join courses c on c.course_id = p.course_id " +
				" left outer join academics_periods ap on ap.academic_period_id = tp.academic_period_id " +
				" inner join users on users.user_id = tp.teacher_id ";
	}
	
	@Override
	protected Object[] getData(Responsible vo) {
		Object[] objs = { vo.getUserRole().getUserRoleId(), vo.getTeachingPlan().getTeachingPlanId() };
		return objs;
	}

	@Override
	protected Responsible fillEntityProperties(ResultSet rs) throws SQLException {
		Responsible vo = new Responsible();
		
		vo.setResponsibleId(rs.getInt("responsible_id"));
		
		vo.getUserRole().setUserRoleId(rs.getInt("re.user_role_id"));
		vo.getUserRole().getUser().setUserId(rs.getInt("ur.user_id"));
		vo.getUserRole().getUser().setName(rs.getString("u.name"));
		vo.getUserRole().getUser().setEmail(rs.getString("u.email"));

		vo.getUserRole().getRole().setRoleId(rs.getInt("r.role_id"));
		vo.getUserRole().getRole().setName(rs.getString("r.name"));
		
		vo.getTeachingPlan().setTeachingPlanId(rs.getInt("re.teaching_plan_id"));
		
		vo.getTeachingPlan().getAcademicPeriod().setSemester(rs.getInt("ap.semester"));
		vo.getTeachingPlan().getAcademicPeriod().setYear(rs.getInt("ap.year"));
		
		vo.getTeachingPlan().getPpcDiscipline().getPpc().getCourse().setName(rs.getString("c.name"));
		vo.getTeachingPlan().getPpcDiscipline().getDiscipline().setName(rs.getString("d.name"));
		
		vo.getTeachingPlan().getTeacher().setName(rs.getString("users.name"));		
		
		return vo;
	}
	
	public List<Responsible> listByTeachingPlan(int teachingPlanId) throws ResponsibleException {
		String sql = getListSql() + " " + getGroupBySql() + 
				" WHERE re.teaching_plan_id = " + teachingPlanId + getOrderBySql();
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
	protected ResponsibleException getEntityException(String message) {
		return new ResponsibleException(message);
	}

	@Override
	protected ResponsibleException getEntityException(Exception e) {
		return new ResponsibleException(e);
	}
}