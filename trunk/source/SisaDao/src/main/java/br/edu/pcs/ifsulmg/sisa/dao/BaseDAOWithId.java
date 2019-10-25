/*
 * File:	     BaseDAOWithId.java
 * Creation date: 	19/06/2015
 * Verification:     Lênio
 */
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;

/**
 * The Class BaseDAOWithId.
 *
 * @param <Entity> the generic type
 * @param <EntityException> the generic type
 */
public abstract class BaseDAOWithId<Entity, EntityException extends Exception> extends CommonDAO {
	
	/**
	 * Insert.
	 *
	 * @param vo the vo
	 * @return the int
	 * @throws EntityException the entity exception
	 * @throws EntityConstraintViolationException 
	 */
	public int insert(Entity vo) throws EntityException, EntityConstraintViolationException {
		int lastIdInsert = -1;
		ResultSet rs;
		String sql = getInsertSql();
		
		try {
			PreparedStatement stmt = prepareAndExecuteQuery(vo, sql);
			rs = stmt.getGeneratedKeys();
			lastIdInsert = getGeneratedId(rs, getEntityException("Impossível retornar a chave."));
			closeRsSt(rs, stmt);
		}
		catch (MySQLIntegrityConstraintViolationException e){
			String message = "Não é possível salvar os dados. Ocorreu algum problema com o relacionamento entre as entidades envolvidas.";
			EntityConstraintViolationException ex = new EntityConstraintViolationException(message, e);
			printLog(ex);
			throw ex;			
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
		return lastIdInsert;
	}
	
	/**
	 * Update.
	 *
	 * @param vo the vo
	 * @throws EntityException the entity exception
	 * @throws EntityConstraintViolationException 
	 */
	public void update(Entity vo) throws EntityException, EntityConstraintViolationException {
		String sql = getUpdateSql(vo);
		
		try {
			PreparedStatement stmt = prepareAndExecuteQuery(vo, sql);
			closeSt(stmt);
		} catch (MySQLIntegrityConstraintViolationException e){
			String message = "Não é possível atualizar os dados. Já existe uma associação com as opções informadas.";
			EntityConstraintViolationException ex = new EntityConstraintViolationException(message, e);
			printLog(ex);
			throw ex;			
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @throws EntityException the entity exception
	 * @throws MySQLIntegrityConstraintViolationException 
	 */
	public void delete(int id) throws EntityException, EntityConstraintViolationException {
		String sql = getDeleteSql(id);
		
		try {
			PreparedStatement stmt = getPreparedStatement(sql);
			stmt.executeUpdate();
			closeSt(stmt);
		} catch (MySQLIntegrityConstraintViolationException e){
			String message = "Não é possível excluir o registro pois está associado a outro cadastro.";
			EntityConstraintViolationException ex = new EntityConstraintViolationException(message, e);
			printLog(ex);
			throw ex;			
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
	}	

	/**
	 * Prepare and execute query.
	 *
	 * @param vo the vo
	 * @param sql the sql
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	private PreparedStatement prepareAndExecuteQuery(Entity vo, String sql)
			throws SQLException {
		PreparedStatement stmt = getPreparedStatementId(sql);
		Object[] objs = getData(vo);
		setPreparedStatement(objs, stmt);
		stmt.executeUpdate();
		return stmt;
	}
	
	/**
	 * List by sql.
	 *
	 * @param sql the sql
	 * @return the list
	 * @throws EntityException the entity exception
	 */
	public List<Entity> listBySql(String sql) throws EntityException {
		List<Entity> list = null;
		try {
			list = new ArrayList<Entity>();
			PreparedStatement stmt = getPreparedStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {				
				Entity vo = fillResultSet(rs);
				list.add(vo);
			}
			closeRsSt(rs, stmt);			
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
		return list;
	}

	/**
	 * Gets the by sql.
	 *
	 * @param sql the sql
	 * @return the by sql
	 * @throws EntityException the entity exception
	 */
	public Entity getBySql(String sql) throws EntityException {
		Entity vo = null;
		try {
			PreparedStatement stmt = getPreparedStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				vo = fillResultSet(rs);
			}
			closeRsSt(rs, stmt);
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
		return vo;
	}
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws EntityException the entity exception
	 */
	public Entity getById(int id) throws EntityException {
		String sql = getListSql() + " " + getWhereByIdSql(id) + " " + getGroupBySql() + " " + getOrderBySql();
		Entity vo = null;		
		try{
			vo = getBySql(sql);
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}		
		return vo;
	}

	/**
	 * List.
	 *
	 * @return the list
	 * @throws EntityException the entity exception
	 */
	public List<Entity> list() throws EntityException {
		String sql = getListSql() + " " + getGroupBySql() + " " + getOrderBySql();
		try {
			return listBySql(sql);
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
	}
	
	/**
	 * Fill result set.
	 *
	 * @param vo the vo
	 * @param rs the rs
	 * @throws EntityException the entity exception
	 */
	private Entity fillResultSet(ResultSet rs) throws EntityException {		
		Entity vo = null;
		try {
			vo = fillEntityProperties(rs);
		} catch (Exception e) {
			printLog(e);
			throw getEntityException(e);
		}
		return vo;
	}

	/**
	 * Print log.
	 *
	 * @param e the e
	 */
	protected void printLog(Exception e) {
		e.printStackTrace();
		LogSISA.log.error(e.getMessage());		
	}
	
	protected String getGroupBySql(){
		return "";
	}
	
	protected String getOrderBySql(){
		return "";
	}
	
	/**
	 * Gets the insert sql.
	 *
	 * @return the insert sql
	 */
	protected abstract String getInsertSql();
	
	/**
	 * Gets the update sql.
	 *
	 * @param vo the vo
	 * @return the update sql
	 */
	protected abstract String getUpdateSql(Entity vo);
	
	/**
	 * Gets the delete sql.
	 *
	 * @param id the id
	 * @return the delete sql
	 */
	protected abstract String getDeleteSql(int id);

	/**
	 * Gets the where by id sql.
	 *
	 * @param id the id
	 * @return the where by id sql
	 */
	protected abstract String getWhereByIdSql(int id);
	
	/**
	 * Gets the list sql.
	 *
	 * @return the list sql
	 */
	protected abstract String getListSql();
		
	/**
	 * Gets the data.
	 *
	 * @param vo the vo
	 * @return the data
	 */
	protected abstract Object[] getData(Entity vo);
	
	/**
	 * Fill entity properties.
	 *
	 * @param vo the vo
	 * @param rs the rs
	 * @throws SQLException the SQL exception
	 */
	protected abstract Entity fillEntityProperties(ResultSet rs) throws SQLException;
		
	/**
	 * Log sql.
	 */
	protected abstract void logSql(String sql);
	
	@Override
	protected PreparedStatement getPreparedStatement(String sql) throws SQLException{
		logSql(sql);
		return super.getPreparedStatement(sql);
	}
	
	@Override
	protected PreparedStatement getPreparedStatementId(String sql) throws SQLException {
		logSql(sql);
		return super.getPreparedStatementId(sql);
	}

	/**
	 * Gets the entity exception.
	 *
	 * @param message the message
	 * @return the entity exception
	 */
	protected abstract EntityException getEntityException(String message);
	
	/**
	 * Gets the entity exception.
	 *
	 * @param e the e
	 * @return the entity exception
	 */
	protected abstract EntityException getEntityException(Exception e);
}