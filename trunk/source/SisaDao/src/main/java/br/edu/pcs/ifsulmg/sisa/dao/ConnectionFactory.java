/*
* File:	     ConnectionFactory.java
* Creation date: 03/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import br.edu.pcs.ifsulmg.sisa.exceptions.ConnectionFactoryException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.util.PropsUtil;

/**
 * A factory for creating Connection objects.
 */
public class ConnectionFactory {
	
	/** The connections. */
	private static HashMap<String, Connection>  _connections = new HashMap<>();
	
	/** The file name. */
	private static String _fileName = "/db.properties";
	
	/** The _db url. */
	private static String _dbUrl = "connection.url";
	
	/** The _db user name. */
	private static String _dbUserName = "connection.username";
	
	/** The _db pwd. */
	private static String _dbPwd = "connection.password";
	
	/** The _db driver. */
	private static String _dbDriver = "connection.driver";
 
	/** The _con. */
	private static Connection _con = null;
	
	/** The Constant WAIT_TIMEOUT. 28800 seconds/8hours */
	private static final int WAIT_TIMEOUT = 28800;
	
	/**
	 * Instantiates a new connection factory.
	 */
	private ConnectionFactory() {
	}
	
	/**
	 * Gets the single instance of ConnectionFactory.
	 *
	 * @return single instance of ConnectionFactory
	 */
	public static Connection getInstance() {
			try {
				if(_con == null){
					LogSISA.log.info(" con is null ");
					_con = _connections.get(_fileName);
					if(_con == null){
						LogSISA.log.info(" con is null in props ");
						createConnection();
					}else{
						checkCloseTimeOut();
					}
				}else{
					checkCloseTimeOut();
				}
			} catch (SQLException | ConnectionFactoryException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				LogSISA.log.error( e.getMessage(), e);
				e.printStackTrace();
			} 
		return _con;
	}

	/**
	 * Check close time out.
	 *
	 * @throws SQLException the sQL exception
	 * @throws ConnectionFactoryException the connection factory exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	private static void checkCloseTimeOut() throws SQLException,
			ConnectionFactoryException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		if(_con.isClosed()){
			LogSISA.log.info(" con is closed ");
			createConnection();	
		}else{
			if(!_con.isValid(WAIT_TIMEOUT)){
				LogSISA.log.info(" con wait timeout is NOT valid ");
				_con.close();
				createConnection();
			}else{
				//LogSISA.log.info(" con wait timeout is valid ");	
			}
		}
	}

	/**
	 * Creates a new Connection object.
	 *
	 * @throws ConnectionFactoryException the connection factory exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	private static void createConnection() throws ConnectionFactoryException,
			ClassNotFoundException, SQLException, InstantiationException,
			IllegalAccessException {
		Class<?> driverClass;
		Properties properties = PropsUtil.getInstance(_fileName);
		String url = properties.getProperty(_dbUrl);
		String usr = properties.getProperty(_dbUserName);
		String pwd = properties.getProperty(_dbPwd);
		String driver = properties.getProperty(_dbDriver);
		driverClass = Class.forName(driver);
		DriverManager.registerDriver((Driver) driverClass.newInstance());
		_con = DriverManager.getConnection(url,usr,pwd);
		_connections.put(_fileName, _con);
	}
}