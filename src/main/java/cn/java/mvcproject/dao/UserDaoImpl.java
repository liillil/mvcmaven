package cn.java.mvcproject.dao;

import java.sql.Connection;
import java.util.List;

import cn.java.mvcproject.model.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	
	@Override
	public int insert(User user) {
		String sql ="INSERT INTO users(`userId`,`username`,`password`,`phone`,`address`,`reg_date`)VALUES(?,?,?,?,?,?);";
		return 	super.update(sql,user.getUserId(), user.getUsername(),user.getPassword(),user.getPhone(),user.getAddress(),user.getRegDate());
	
	} 
 
	
	@Override
	public int deleteUser(int id) {
		String sql = "DELETE FROM users where id = ?;";
		
		return super.update(sql, id);
	} 
 
	@Override 
	public int updateUser(User user) {
		String sql = "UPDATE users SET `username`=?,`phone`=?,`address`=? WHERE `id`=?;";
		
		return super.update(sql, user.getUsername(),user.getPhone(),user.getAddress(),user.getId());
	}
 
	
	@Override
	public User select(int id) {
		String sql = "SELECT `id`,`userId`,`username`,`password`,`phone`,`address`,`reg_date` regDate FROM users WHERE id = ?;";
		return super.get(sql, id);
	}
	
	@Override
	public User select(Connection conn, int id) {
		String sql = "SELECT `id`,`userId`,`username`,`password`,`phone`,`address`,`reg_date` regDate FROM users WHERE id = ?;";
		return super.get(conn, sql, id);
		
	}
 
	@Override
	public List<User> selectAll() {
		String sql = "SELECT `id`,`userId`,`username`,`password`,`phone`,`address`,`reg_date` regDate FROM users;";
		
		return super.getList(sql);
	}

	@Override
	public long selectCountByName(String username) {
		String sql = "SELECT COUNT(id) FROM users WHERE`username`= ?;";
		return (long) super.getValue(sql, username); 
	}
	
	@Override
	public long selectCountByuserId(String userId) {
		String sql = "SELECT COUNT(id) FROM users WHERE`userId`= ?;";
		return (long) super.getValue(sql, userId); 
	}
 
	@Override
	public List<User> query(String userId,String username, String address, String phone) {
		String sql = "SELECT `id`,`userId`,`username`,`password`,`phone`,`address`,`reg_date` regDate FROM `users` WHERE 1=1";
		if(userId!=null && !"".equals(userId)) {
			sql = sql + " AND userId like '%"+userId+"%'";
		}
		if(username!=null && !"".equals(username)) {
			sql = sql + " AND username like '%"+username+"%'";  //sql注入攻击的风险
		}
		if(address!=null && !"".equals(address)) {
			sql = sql + " AND address like '%"+address+"%'";
		}
		if(phone!=null && !"".equals(phone)) {
			sql = sql + " AND phone like '%"+phone+"%'"; 
		}
		System.out.println(sql);  
		return super.getList(sql);
	}


	@Override
	public User getUserByIp(String userId, String password) {
		String sql = "SELECT `id`,`userId`,`username`,`password`,`phone`,`address`,`reg_date` regDate FROM `users` WHERE userId = ? AND password = ? ";
		return super.get(sql, userId,password);
	}


	@Override
	public int updatePassword(User user) {
		String sql = "UPDATE users SET `password`=? WHERE `id`=?;";
		return super.update(sql, user.getPassword(),user.getId());
	}


	@Override
	public User selectuserId(String userId) {
		String sql = "SELECT `id`,`userId`,`username`,`password`,`phone`,`address`,`reg_date` regDate FROM users WHERE userId = ?;";
		return super.get(sql, userId);
	}

	 

}
