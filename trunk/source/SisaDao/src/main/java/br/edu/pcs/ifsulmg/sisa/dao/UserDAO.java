/*
* File:	     UserDAO.java
* Creation date: 04/06/2015
* User:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.pcs.ifsulmg.sisa.exceptions.UserException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.User;

/**
 * The Class UserDAO.
 */
public class UserDAO extends BaseDAOWithId<User, UserException> {

	@Override
	protected String getInsertSql() {
		return "insert into users values (null,?,?,?)";
	}

	@Override
	protected String getUpdateSql(User vo) {
		return "update users set name=?, password=?, email=?" +
				" where user_id="+ vo.getUserId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from users where user_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where user_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select * from users ";
	}

	@Override
	protected Object[] getData(User vo) {
		Object[] objs = { vo.getName(),	vo.getPassword(), vo.getEmail() };
		return objs;
	}

	@Override
	protected User fillEntityProperties(ResultSet rs)
			throws SQLException {
		User vo = new User();
		
		vo.setUserId(rs.getInt("user_id"));
		vo.setName(rs.getString("name"));
		vo.setPassword(rs.getString("password"));
		vo.setEmail(rs.getString("email"));

		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}

	@Override
	protected UserException getEntityException(String message) {
		return new UserException(message);
	}
	
	@Override
	protected UserException getEntityException(Exception e) {
		return new UserException(e);
	}

	/**
	 * Gets the by email.
	 *
	 * @param email the email
	 * @return the by email
	 * @throws UserException the user exception
	 */
	public User getByEmail(String email) throws UserException {
		String sql = "select * from users where email='" + email + "'" ;
		return getBySql(sql);
	}

	/**
	 * Update user password.
	 *
	 * @param vo the vo
	 * @throws UserException the user exception
	 */
	public void updateUserPassword(User vo) throws UserException {
		String sql = "update users set password=? where user_id="+ vo.getUserId();
		try {
			PreparedStatement stmt = getPreparedStatement(sql);
			Object[] objs = { vo.getPassword()};
			setPreparedStatement(objs, stmt);
			stmt.executeUpdate();
			closeSt(stmt);
		} catch (Exception e) {
			e.printStackTrace();
			LogSISA.log.error(e.getMessage());
			throw new UserException(e);
		}
	}
}