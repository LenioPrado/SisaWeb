/*
* File:	     TeachingPlanDAO.java
* Creation date: 23/06/2015
* TeachingPlan:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.TeachingPlanException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.vo.User;

/**
 * The Class TeachingPlanDAO.
 */
public class TeachingPlanDAO extends BaseDAOWithId<TeachingPlan, TeachingPlanException> {

	@Override
	protected String getInsertSql() {
		return "insert into teaching_plans (teaching_plan_id, ppc_discipline_id, academic_period_id, " +
				" teacher_id, objective, teaching_procedure, recovery, observation) " +
				" values (null,?,?,?,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(TeachingPlan vo) {
		return "update teaching_plans set ppc_discipline_id=?, academic_period_id=?, teacher_id=?, " +
				" objective=?, teaching_procedure=?, recovery=?, observation=? " +
				" where teaching_plan_id="+ vo.getTeachingPlanId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from teaching_plans where teaching_plan_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where tp.teaching_plan_id=" + id;
	}

	@Override
	protected String getListSql() {
		return " select tp.*, p.ppc_id, p.weeks_number, p.class_hour, " +
				" d.discipline_id, d.name, d.menu, d.period_series, d.week_classes, " +
				" c.course_id, c.name, c.modality, c.shift, ap.*, u.name, " +
				" (select sum(cp.classes_quantity) from classes_programming cp " + 
				" where tp.teaching_plan_id = cp.teaching_plan_id and cp.class_type = 2) " +
				" as theoretical_load_time_hours_class, " +
				" (select sum(cp.classes_quantity) from classes_programming cp " + 
				" where tp.teaching_plan_id = cp.teaching_plan_id and cp.class_type = 1) " +
				" as practice_load_time_hours_class " +
				" from teaching_plans tp " + 
				" left outer join classes_programming cp on tp.teaching_plan_id = cp.teaching_plan_id " +
				" inner join ppcs_disciplines pd on tp.ppc_discipline_id = pd.ppc_discipline_id " +
				" inner join disciplines d on pd.discipline_id = d.discipline_id " +
				" inner join ppcs p on pd.ppc_id = p.ppc_id " +
				" inner join courses c on c.course_id = p.course_id " +
				" inner join users u on u.user_id = tp.teacher_id " +
				" left outer join academics_periods ap on ap.academic_period_id = tp.academic_period_id " +
				" left outer join responsibles r on r.teaching_plan_id = tp.teaching_plan_id " +
				" left outer join users_roles ur on ur.user_role_id = r.user_role_id ";
	}
	
	@Override
	protected String getOrderBySql() {
		return " ORDER BY tp.objective ";
	}
	
	@Override
	protected String getGroupBySql() {
		return " GROUP BY teaching_plan_id ";
	}	
	
	public List<TeachingPlan> listByDiscipline(int disciplineId) throws TeachingPlanException{
		String sql = getListSql() + " " + getGroupBySql() + " HAVING d.discipline_id = " + disciplineId + " " + getOrderBySql();
		return listBySql(sql);
	}
	
	public List<TeachingPlan> listByTeacher(User user) throws TeachingPlanException {
		String sql = getListSql() + " WHERE tp.teacher_id = " + user.getUserId() + 
				getGroupBySql() + getOrderBySql();
		return listBySql(sql);
	}

	public TeachingPlan getFiltered(int courseId, int ppcId, int disciplineId, int teachingPlanId) 
			throws TeachingPlanException{
		String sql = getListSql();
		String where = " WHERE c.course_id = " + courseId +				
			" AND p.ppc_id = " + ppcId + 
			" AND d.discipline_id = " + disciplineId + 
			" AND tp.teaching_plan_id = " + teachingPlanId;
		
		return getBySql(sql + where);
	}
	
	@Override
	protected Object[] getData(TeachingPlan vo) {
		Object[] objs = { vo.getPpcDiscipline().getPpcDisciplineId(), 
				vo.getAcademicPeriod().getAcademicPeriodId(),
				vo.getTeacher().getUserId(), vo.getObjective(),vo.getTeachingProcedure(), 
				vo.getRecovery(), vo.getObservation(), 
				 };
		return objs;
	}

	@Override
	protected TeachingPlan fillEntityProperties(ResultSet rs)
			throws SQLException {
		TeachingPlan vo = new TeachingPlan();

		vo.setTeachingPlanId(rs.getInt("tp.teaching_plan_id"));
		vo.setObjective(rs.getString("tp.objective"));
		vo.setTeachingProcedure(rs.getString("tp.teaching_procedure"));
		vo.setRecovery(rs.getString("tp.recovery"));
		vo.setObservation(rs.getString("tp.observation"));
		
		vo.setPracticeLoadTimeHoursClass(rs.getInt("practice_load_time_hours_class"));
		vo.setTheoreticalLoadTimeHoursClass(rs.getInt("theoretical_load_time_hours_class"));
		
		vo.getTeacher().setUserId(rs.getInt("tp.teacher_id"));
		vo.getTeacher().setName(rs.getString("u.name"));
		
		vo.getPpcDiscipline().setPpcDisciplineId(rs.getInt("tp.ppc_discipline_id"));
		
		vo.getPpcDiscipline().getPpc().setPpcId(rs.getInt("p.ppc_id"));
		vo.getPpcDiscipline().getPpc().setClassHour(rs.getInt("p.class_hour"));
		vo.getPpcDiscipline().getPpc().setWeeksNumber(rs.getInt("p.weeks_number"));
		
		vo.getPpcDiscipline().getPpc().getCourse().setCourseId(rs.getInt("c.course_id"));
		vo.getPpcDiscipline().getPpc().getCourse().setName(rs.getString("c.name"));
		vo.getPpcDiscipline().getPpc().getCourse().setModality(rs.getInt("c.modality"));
		vo.getPpcDiscipline().getPpc().getCourse().setShift(rs.getInt("c.shift"));
		
		vo.getPpcDiscipline().getDiscipline().setDisciplineId(rs.getInt("d.discipline_id"));
		vo.getPpcDiscipline().getDiscipline().setName(rs.getString("d.name"));
		vo.getPpcDiscipline().getDiscipline().setMenu(rs.getString("d.menu"));
		vo.getPpcDiscipline().getDiscipline().setPeriodSeries(rs.getInt("d.period_series"));
		vo.getPpcDiscipline().getDiscipline().setWeekClasses(rs.getInt("d.week_classes"));
		
		vo.getAcademicPeriod().setAcademicPeriodId(rs.getInt("ap.academic_period_id"));
		vo.getAcademicPeriod().setYear(rs.getInt("ap.year"));
		vo.getAcademicPeriod().setSemester(rs.getInt("ap.semester"));
		vo.getAcademicPeriod().setType(rs.getString("ap.type"));

		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	@Override
	protected TeachingPlanException getEntityException(String message) {
		return new TeachingPlanException(message);
	}

	@Override
	protected TeachingPlanException getEntityException(Exception e) {
		return new TeachingPlanException(e);
	}
}