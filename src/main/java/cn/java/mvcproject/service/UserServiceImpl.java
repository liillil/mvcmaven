 package cn.java.mvcproject.service;

 
import java.sql.Connection; 
import java.util.List;

import cn.java.mvcproject.dao.FactoryDao;
import cn.java.mvcproject.dao.UserDao;
import cn.java.mvcproject.model.User;
import cn.java.utils.JdbcUtil;

public class UserServiceImpl implements UserService {
	UserDao userDao = FactoryDao.getUserDao();
	
	@Override
	public int insert(User user) {
		
		return userDao.insert(user);
	}
 
	@Override
	public int deleteUser(int id) {
		
		return userDao.deleteUser(id);
	} 

	@Override
	public int updateUser(User user) {
		
		return userDao.updateUser(user);
	}

	@Override
	public User select(int id) {
		// TODO Auto-generated method stub
		return userDao.select(id);
	}

	@Override
	public User selectTransation(int id) {
		Connection conn = null;
		User user = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false); //开启事务
			user =  userDao.select(conn, id);
			conn.commit();//执行成功提交事务
			
		}catch(Exception e) {
			JdbcUtil.rollbackTransation(conn);//执行失败，回滚事务
		}
		return user;
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	@Override
	public long selectCountByName(String username) {
		
		return userDao.selectCountByName(username);
	}

	@Override
	public List<User> query(String userId,String username, String address, String phone) {
		
		return userDao.query( userId,username, address, phone); 
	}

	@Override
	public long selectCountByuserId(String userId) {
		return userDao.selectCountByuserId(userId);
	}

	@Override
	public User login(String userId, String password) {
		
		return userDao.getUserByIp(userId,password);
	}

	@Override
	public int updatePassword(User user) {
		
		return userDao.updatePassword(user);
	}

	@Override
	public User selectuserId(String userId) {
		
		return userDao.selectuserId(userId);
	}

	

}
