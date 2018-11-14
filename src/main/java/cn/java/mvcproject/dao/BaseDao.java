 package cn.java.mvcproject.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.java.utils.JdbcUtil;

/**
 * 这是一个Dao层里的基本类，在于被具体的dao类，UserDao去继承他来用的，不能被new BaseDao（）来直接用
 * @author Administrator
 *
 * @param <T> :针对要操作各张数据表映射到Java工程里的java类，User
 * 
 */
public class BaseDao<T> {
	QueryRunner qr = new QueryRunner();
	private Class<T> clazz;
	@SuppressWarnings("unchecked")
	public BaseDao(){//
		//用BaseDao的构造方法初始化clazz属性
		//this 谁调用该方法  就指向谁 BaseDao的子类  
		//getGenericSuperclass()作用是拿到调用者的父类的类型， BaseDao的子类去调用，所用最终能拿到BaseDao的类型（即泛型T的具体类型）
		Type superType = this.getClass().getGenericSuperclass();
		//由于要拿到类型的类信息类class   例：User 的User.class
		//ParameterizedType为Type接口的子接口
		if (superType instanceof ParameterizedType) {//判断实现类superType是否是与ParameterizedType是同一种类型
			ParameterizedType pt = (ParameterizedType) superType;//将superType强转为ParameterizedType类型(父类转子类)
			Type[] tarry = pt.getActualTypeArguments();//返回一个类型数组，第一个元素就是我们要的泛型T，
			if(tarry[0] instanceof Class) {
				clazz =  (Class<T>) tarry[0];
			}
		}
	}
	
	
	//对数据库进行增删改查
	/**
	 * 查询数据表，取出sql语句的结果集的第一条数据，封装成一个对象返回，不支持事务
	 *用到dbutils工具类
	 * @param sql
	 * @param args 
	 * @return
	 */ 
	public T get(String sql ,Object...args) {
		Connection conn = null;
		T entity = null;
		try { 
			conn = JdbcUtil.getConnection();
			entity = qr.query(conn, sql,new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.jdbcClose(conn, null, null);
		}
		
		return entity;

	}
	
	/**
	 * 查询数据表，取出sql语句的结果集的第一条数据，封装成一个对象返回，支持事务
	 *用到dbutils工具类
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(Connection conn, String sql ,Object...args) {
		T entity = null;
		try {
			entity = qr.query(conn, sql,new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}	
		return entity;
	}
	/**
	 * 查询数据表，取出多条数据，不支持事务
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getList(String sql ,Object...args) {
		
		Connection conn = null;
		List<T> list = null; 
		try {
			conn = JdbcUtil.getConnection();
			list = qr.query(conn, sql,new BeanListHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.jdbcClose(conn, null, null);
		}
		
		return list;
		
		
	}
	
	/**
	 * 对数据库进行更新（执行增删改）
	 * @param sql
	 * @param args
	 * @return
	 */
	public int update(String sql ,Object...args) {
		 
		Connection conn = null;
		int count = 0; 
		try {
			conn = JdbcUtil.getConnection();
			count = qr.update(conn, sql, args);
		} catch (Exception e) {
			e.printStackTrace(); 
		}finally {
			JdbcUtil.jdbcClose(conn, null, null);
		}
		
		return count;
	}
	
	
	
	/**
	 * 通用的放回sql语句的结果只有一个数值的类型的查询，用户个数，count(id);
	 * @param sql
	 * @param args
	 * @return
	 */
	public Object getValue(String sql ,Object...args) {
		
		Connection conn = null;
		Object obj = null;
		try { 
			// 拿conn
			conn = JdbcUtil.getConnection();
			obj = qr.query(conn, sql, new ScalarHandler<Object>(), args); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.jdbcClose(conn, null, null);
		}
		return obj;
		
	}  
	
	
	
}
