/*
* File:	     VerificationDAO.java
* Creation date: 	12/06/2015
* Verification:     PagodeiroDoMal/Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.VerificationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.Verification;

/**
 * The Class VerificationDAO.
 */
public class VerificationDAO extends BaseDAOWithId<Verification, VerificationException> {

	@Override
	protected String getInsertSql() {
		return "insert into verifications values (null,?,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Verification vo) {
		return "update verifications set creation_date=?, evaluation_date=?, observation=?," +
				" status=?, responsible_id=?" + 
				" where verification_id=" + vo.getVerificationId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from verifications where verification_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where verification_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select v.*, ap.semester, ap.year, c.name, d.name, u.user_id, u.name, " +
				" ro.role_id, ro.name, tp.teaching_plan_id, users.name " +
				" from verifications v " +
				" inner join responsibles r on v.responsible_id = r.responsible_id " +
				" inner join teaching_plans tp on r.teaching_plan_id = tp.teaching_plan_id " + 
				" inner join users_roles ur on r.user_role_id = ur.user_role_id " +
				" inner join users u on ur.user_id = u.user_id " +
				" inner join roles ro on ur.role_id = ro.role_id " +
				" inner join ppcs_disciplines pd on pd.ppc_discipline_id = tp.ppc_discipline_id " +
				" inner join disciplines d on pd.discipline_id = d.discipline_id " +
				" inner join ppcs p on pd.ppc_id = p.ppc_id " +
				" inner join courses c on c.course_id = p.course_id " +
				" left outer join academics_periods ap on ap.academic_period_id = tp.academic_period_id " +
				" inner join users on users.user_id = tp.teacher_id ";		  
	}
	
	@Override
	protected String getOrderBySql() {
		return " ORDER BY creation_date, evaluation_date ";
	}

	@Override
	protected Object[] getData(Verification vo) {
		Object[] objs = { vo.getCreationDate(), vo.getEvaluationDate(), vo.getObservation(), 
				vo.getStatus(), vo.getResponsible().getResponsibleId()};
		return objs;
	}

	@Override
	protected Verification fillEntityProperties(ResultSet rs) throws SQLException {
		Verification vo = new Verification();
		
		vo.setVerificationId(rs.getInt("v.verification_id"));
		vo.setCreationDate(rs.getTimestamp("v.creation_date"));
		vo.setEvaluationDate(rs.getTimestamp("v.evaluation_date"));
		vo.setObservation(rs.getString("v.observation"));
		vo.setStatus(rs.getInt("v.status"));
		
		vo.getResponsible().setResponsibleId(rs.getInt("v.responsible_id"));
	
		vo.getResponsible().getUserRole().getUser().setUserId(rs.getInt("u.user_id"));
		vo.getResponsible().getUserRole().getUser().setName(rs.getString("u.name"));
		vo.getResponsible().getUserRole().getRole().setRoleId(rs.getInt("ro.role_id"));
		vo.getResponsible().getUserRole().getRole().setName(rs.getString("ro.name"));
		
		vo.getResponsible().getTeachingPlan().setTeachingPlanId(rs.getInt("tp.teaching_plan_id"));
		vo.getResponsible().getTeachingPlan().getAcademicPeriod().setSemester(rs.getInt("ap.semester"));
		vo.getResponsible().getTeachingPlan().getAcademicPeriod().setYear(rs.getInt("ap.year"));
		
		vo.getResponsible().getTeachingPlan().getPpcDiscipline().getPpc().getCourse().setName(rs.getString("c.name"));
		vo.getResponsible().getTeachingPlan().getPpcDiscipline().getDiscipline().setName(rs.getString("d.name"));
		
		vo.getResponsible().getTeachingPlan().getTeacher().setName(rs.getString("users.name"));
				
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected VerificationException getEntityException(String message) {
		return new VerificationException(message);
	}

	@Override
	protected VerificationException getEntityException(Exception e) {
		return new VerificationException(e);
	}
	
	public List<Verification> listByTeachingPlan(TeachingPlan teachingPlan) throws VerificationException{
		String sql = getListSql() + " WHERE tp.teaching_plan_id = " + teachingPlan.getTeachingPlanId() +
				getOrderBySql();
		return listBySql(sql);
	}

	public List<Verification> listByTeachingPlans(List<TeachingPlan> teachingPlans) throws VerificationException{
		String ids = "";
		for (TeachingPlan teachingPlan : teachingPlans) {
			ids += teachingPlan.getTeachingPlanId() + ",";
		}
		ids = ids.substring(0, ids.length() - 1);
		String sql = getListSql();
		sql += " WHERE tp.teaching_plan_id IN (" + ids + ") " + getOrderBySql();;
		return listBySql(sql);
	}
	
	public List<Verification> listByUserRole(User user, Role role) throws VerificationException{
		String sql = getListSql();
		sql += " WHERE u.user_id = " + user.getUserId() + 
				" AND ro.role_id = " + role.getRoleId() + " ";
		return listBySql(sql);
	}
}