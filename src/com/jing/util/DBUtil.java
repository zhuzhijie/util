package com.jing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * 连接数据库的综合类。commons.dbcp-1.4，commons.dbutils-1.3，commons.pool-1.5.4包需要。
 * @author 朱志杰 QQ：695520848
 * Jul 16, 2013 10:15:00 AM
 */
public class DBUtil {
	
	private String dri = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private String poolName = null;	//连接池名称
	private ObjectPool connectionPool = null; //连接池
	//取出时检查连接是否有效。
	private boolean testOnBorrow=true;
	
	/**
	 * 功能：获取一个DBUtil对象。
	 * @author 朱志杰 QQ：695520848
	 * Jul 16, 2013 10:24:00 AM
	 * @param dri 驱动全类名，例如：com.mysql.jdbc.Driver。
	 * @param url 数据库url连接，例如："jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"
	 * @param userName 数据库用户名，例如：root
	 * @param password 数据库密码，例如：abc
	 * @param poolName 创建的数据库连接池的名称，例如mypool，注意一个web容器此名称不能重复。
	 */
	public static DBUtil getInstance(String dri,String url,String userName,String password,String poolName) {
		return new DBUtil( dri, url, userName, password, poolName);
	}
	
	
	/**
	 * 功能：构造函数
	 * @author 朱志杰 QQ：695520848
	 * Jul 16, 2013 10:24:00 AM
	 * @param dri 驱动全类名，例如：com.mysql.jdbc.Driver。
	 * @param url 数据库url连接，例如："jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"
	 * @param userName 数据库用户名，例如：root
	 * @param password 数据库密码，例如：abc
	 * @param poolName 创建的数据库连接池的名称，例如mypool，注意一个web容器此名称不能重复。
	 */
	private  DBUtil(String dri,String url,String userName,String password,String poolName){
		this.dri=dri;
		this.url=url;
		this.username=userName;
		this.password=password;
		this.poolName=poolName;
	}
	
	/**
	 * 执行sql。
	 * @param conn 连接
	 * @param pstm PreparedStatement
	 * @return int 执行sql对应的影响行。
	 * @throws SQLException 
	 */
	public int execute(Connection conn,PreparedStatement pstm) throws SQLException {
		try {
			return pstm.executeUpdate();
		}  finally {
			Close(conn);
		}
	}
	
	/**
	 * 查询sql。
	 * @param conn 连接
	 * @param pstm PreparedStatement
	 * @return List<Map<String,Object>> 查询的结果集
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> query(Connection conn,PreparedStatement pstm) throws SQLException {
		try {
			return resultSetToList(pstm.executeQuery());
		}  finally {
			Close(conn);
		}
	}
	
	/**
	 * 功能：ResultSet 转为List<Map<String,Object>>
	 * @author 朱志杰 QQ：862990787
	 * 2014-3-7 下午08:55:11
	 * @param rs ResultSet 原始数据集
	 * @return List<Map<String,Object>>
	 * @throws java.sql.SQLException
	 */
	private List<Map<String,Object>> resultSetToList(ResultSet rs) throws java.sql.SQLException {   
		if (rs == null)
			return Collections.EMPTY_LIST;

		ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> rowData = new HashMap<String, Object>();
		while (rs.next()) {
			rowData = new HashMap<String, Object>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}

	/**
	 * 查询sql语句。
	 * @param sql 被执行的sql语句
	 * @return List<Map<String,Object>>
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> query(String sql) throws SQLException {
		List<Map<String,Object>> results = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qr = new QueryRunner();
			results =qr.query(conn, sql, new MapListHandler());
		}  finally {
			Close(conn);
		}
		return results;
	}
	
	/**
	 * 根据参数查询sql语句
	 * @param sql sql语句
	 * @param param 参数
	 * @return List<Map<String,Object>>
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> query(String sql, Object param) throws SQLException {
		List<Map<String,Object>> results = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qr = new QueryRunner();
			results = (List<Map<String,Object>>) qr.query(conn, sql, param, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(conn);
		}
		return results;
	}
	
	/**
	 * 根据参数查询sql语句
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return List<Map<String,Object>>
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> query(String sql, Object... param) throws SQLException {
		List<Map<String,Object>> results = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qr = new QueryRunner();
			results = (List<Map<String,Object>>) qr.query(conn, sql, param, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(conn);
		}
		return results;
	}

	/**
	 * 执行sql语句
	 * @param sql 被执行的sql语句
	 * @return 受影响的行
	 * @throws Exception
	 */
	public int execute(String sql) throws Exception {
		Connection conn = getConnection();
		int rows = 0;
		try {
			QueryRunner qr = new QueryRunner();
			rows = qr.update(conn, sql);
		} finally {
			Close(conn);
		}
		return rows;
	}

	/**
	 * 执行含参数的sql语句
	 * @param sql 被执行的sql语句
	 * @param params 参数
	 * @return 返回受影响的行
	 * @throws Exception
	 */
	public int execute(String sql, Object[] params) throws Exception {
		Connection conn = getConnection();
		int rows = 0;
		try {
			QueryRunner qr = new QueryRunner();
			rows = qr.update(conn, sql, params);
		} finally {
			Close(conn);
		}
		return rows;
	}

	/**
	 * 关闭连接
	 * @param conn
	 * @throws SQLException 
	 */
	public void Close(Connection conn) throws SQLException {
		if(conn!=null){
			conn.close();
		}
		DbUtils.closeQuietly(conn);
	}
	
	/**
	 * 启动连接池
	 */
	private void StartPool() {
		try {
			Class.forName(dri);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (connectionPool != null) {
			ShutdownPool();
		}
		try {
			GenericObjectPool.Config config = new GenericObjectPool.Config();  
			//取出时检查连接是否有效。
			config.testOnBorrow=testOnBorrow;
	        
			connectionPool = new GenericObjectPool(null,config);
			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
					url, username, password);
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
					connectionFactory, connectionPool, null, "SELECT 1",
					false, true);
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager
					.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool(poolName, poolableConnectionFactory.getPool());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接池
	 */
	private void ShutdownPool() {
		try {
			PoolingDriver driver = (PoolingDriver) DriverManager
					.getDriver("jdbc:apache:commons:dbcp:");
			driver.closePool(poolName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到一个连接
	 * @return
	 */
	public synchronized Connection getConnection() {
		Connection conn = null;
		try {
			if (connectionPool == null)
				StartPool();
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+poolName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 取出时检查连接是否有效。
	 * @return
	 */
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}


	/**
	 * 设置取出时检查连接是否有效。
	 * @param testOnBorrow true 检测，false 不检测。
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
}

