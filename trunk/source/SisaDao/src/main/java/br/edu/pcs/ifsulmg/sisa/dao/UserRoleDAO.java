/*
 * File:	     UserRoleDAO.java
 * Creation date: 23/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.UserRoleException;
import br.edu.pcs.ifsulmg.sisa.util.DateUtil;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.vo.UserRole;

/**
 * The Class UserRoleDAO.
 */
public class UserRoleDAO extends BaseDAOWithId<UserRole, UserRoleException> {

	@Override
	protected String getInsertSql() {
		return "insert into users_roles values (null,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(UserRole vo) {
		return "update users_roles set user_id=?, role_id=?, "+
				"start_date=?, end_date=? " +
				"where user_role_id="+ vo.getUserRoleId() ;
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from users_roles where user_role_id="+id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where user_role_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select ur.*, r.name, r.observation, u.name from users_roles ur " +
				" inner join roles r on ur.role_id = r.role_id " +
				" inner join users u on ur.user_id = u.user_id";
	}

	@Override
	protected Object[] getData(UserRole vo) {
		return new Object[] { vo.getUser().getUserId(), vo.getRole().getRoleId(),
				vo.getStartDate(), vo.getEndDate()};
	}

	@Override
	protected UserRole fillEntityProperties(ResultSet rs) throws SQLException {
		UserRole vo = new UserRole();

		vo.setUserRoleId(rs.getInt("user_role_id"));
		vo.setStartDate(rs.getDate("start_date"));
		vo.setEndDate(rs.getDate("end_date"));
		vo.getUser().setUserId(rs.getInt("ur.user_id"));
		vo.getUser().setName(rs.getString("u.name"));		
		vo.getRole().setRoleId(rs.getInt("ur.role_id"));
		vo.getRole().setName(rs.getString("r.name"));
		vo.getRole().setObservation(rs.getString("r.observation"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected UserRoleException getEntityException(String message) {
		return new UserRoleException(message);
	}

	@Override
	protected UserRoleException getEntityException(Exception e) {
		return new UserRoleException(e);
	}

	public List<Role> listRolesByUser(User user, Date actualDate) throws UserRoleException {
		String date = DateUtil.createDateFormatMySql(actualDate);
		String sql = getListSql() + " WHERE u.user_id = " + user.getUserId() + 
				" AND start_date <= '" + date + "'" +
				" AND end_date >= '" + date + "' ";
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> usersRoles = listBySql(sql);
		
		for (UserRole userRole : usersRoles) {
			Role role = userRole.getRole();
			if (!roles.contains(role)) {
				roles.add(role);
			}
		}		
		return roles;
	}
}