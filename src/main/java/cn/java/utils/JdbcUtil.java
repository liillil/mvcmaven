package cn.java.utils;



import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * jdbc工具类
 * @author Administrator
 * 
 */
public class JdbcUtil {
	//数据库连接池，c3p0 
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}

	/**
	 * @deprecated:获取数据库maysql连接对象conn<br/>
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	 
	
	/**
	 * @deprecated:释放数据路资源<br/>
	 * @param Connection conn 
	 * @param Statemen st
	 * @param ResultSet rs
	 * @throws Exception
	 */
	public static void jdbcClose(Connection conn,Statement st,ResultSet rs )  {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * @param conn
	 */
	public static void rollbackTransation(Connection conn) {
		
		if(conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
