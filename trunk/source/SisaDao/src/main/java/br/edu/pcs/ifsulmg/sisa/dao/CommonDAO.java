/*
 * File:	     CommonDAO.java
 * Creation date: 03/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.pcs.ifsulmg.sisa.dao.ConnectionFactory;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;

public class CommonDAO {

	/** The _connection. */
	private Connection _connection = null;

	/**
	 * Instantiates a new common dao.
	 */
	public CommonDAO() {
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		try {
			if (_connection == null || _connection.isClosed()) {
				_connection = ConnectionFactory.getInstance();
			}
		} catch (SQLException e) {
			LogSISA.log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return _connection;
	}

	/**
	 * Gets the prepared statement.
	 *
	 * @param sql the sql
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	protected PreparedStatement getPreparedStatement(String sql)
			throws SQLException {
		sql = sql.toLowerCase();
		
		return getConnection().prepareStatement(sql);
	}

	/**
	 * Gets the prepared statement id.
	 *
	 * @param sql the sql
	 * @return the prepared statement id
	 * @throws SQLException the SQL exception
	 */
	protected PreparedStatement getPreparedStatementId(String sql)
			throws SQLException {		

		sql = sql.toLowerCase();
		
		return getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 * Sets the prepared statement.
	 *
	 * @param objs the objs
	 * @param prepareStatement the prepare statement
	 * @throws SQLException the SQL exception
	 */
	public void setPreparedStatement(Object[] objs, PreparedStatement prepareStatement) throws SQLException {
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] != null) {
				prepareStatement.setObject(i + 1, objs[i]);
			} else {
				prepareStatement.setNull(i + 1, java.sql.Types.NULL);
			}
		}
	}

	/**
	 * Close rs st.
	 *
	 * @param rs the rs
	 * @param st the st
	 */
	public void closeRsSt(ResultSet rs, Statement st) {
		closeRs(rs);
		closeSt(st);
	}

	/**
	 * Close st.
	 *
	 * @param st the st
	 */
	public void closeSt(Statement st) {
		closeConnection(_connection);
		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				LogSISA.log.error(e.getMessage(), e);
				e.printStackTrace();
			}
	}

	/**
	 * Close rs.
	 *
	 * @param rs the rs
	 */
	public void closeRs(ResultSet rs) {
		closeConnection(_connection);
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				LogSISA.log.error(e.getMessage(), e);
				e.printStackTrace();
			}
	}

	/**
	 * Gets the generated id.
	 *
	 * @param rs the rs
	 * @param e the e
	 * @return the generated id
	 * @throws Exception the exception
	 */
	protected int getGeneratedId(ResultSet rs, Exception e) throws Exception {
		int ret = -1;
		if (rs.next()) {
			ret = rs.getInt(1);
		} else {
			throw e;
		}
		return ret;
	}

	/**
	 * Close connection.
	 *
	 * @param con the con
	 */
	public void closeConnection(Connection con) {
		//
	}
}