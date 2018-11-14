package cn.java.mvcproject.dao;

import java.sql.Connection;
import java.util.List;

import cn.java.mvcproject.model.User;

public interface UserDao {
	/**
	 * 新增一条用户数据
	 * @param user
	 * @return
	 */
	public int insert(User user);
	
	/**
	 * 
	 * 根据用户编号删除一条指定的用户数据
	 * @param id
	 * @return
	 */
	public int deleteUser(int id);

	
	/**
	 * 根据用户编号修改一条指定的用户数据
	 * @param id
	 * @return
	 */
	public int updateUser(User user);
	
	/**
	 * 不支持事务
	 *根据用户编号 查找一条指定用户数据
	 * @param id
	 * @return
	 */
	public User select(int id);
	
	
	/**
	 * 不支持事务
	 * 根据用户账号 查找一条指定用户数据
	 * @param userId
	 * @return
	 */
	public User selectuserId(String userId);
	
	/**
	 * 支持事务
	 * 根据用户编号 查找一条指定用户数据
	 * @param id
	 * @return
	 */
	public User select(Connection conn,int id);
	
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<User> selectAll();
	
	/**
	 * 查询指定的用户姓名的个数
	 * @param username
	 * @return
	 */
	public long selectCountByName(String username);
	
	/**
	 * Dao层中实现模糊查询方法
	 * @param username
	 * @param address
	 * @param phone
	 * @param phone2 
	 * @return
	 */
	public List<User> query(String userId,String username, String address, String phone);

	/**
	 * 查询指定的用户账号的个数
	 * @param userId
	 * @return
	 */
	long selectCountByuserId(String userId);
	
	/**
	 * Dao层根据账号和密码查询用户是否存在
	 * @param userId
	 * @param password
	 * @return
	 */
	public User getUserByIp(String userId, String password);
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public int updatePassword(User user);
	
}
