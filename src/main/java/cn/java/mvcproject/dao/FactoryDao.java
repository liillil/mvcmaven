package cn.java.mvcproject.dao;

import java.util.Date;

import cn.java.mvcproject.model.Online;

public class FactoryDao {
	
	public static UserDao getUserDao() {
		UserDao userDao = new UserDaoImpl();
		return userDao; 
	}
	
	public static OnlineDao getOnlineImpl() {
		OnlineDao onlineDao = new OnlineDaoImpl();
		return onlineDao; 
	}
	
	public static void main(String[] args) {
		OnlineDao onlineDao =  FactoryDao.getOnlineImpl();
		Online online = new Online();
		online.setSsid("544");
		online.setIp("111");
		online.setPage("111");
		online.setTime(new Date());
		online.setUsername("111");
		online.setUserId("111");
		
		//System.out.println(onlineDao.getAllOnline());
		//onlineDao.updateOnlie(online);
		//System.out.println(onlineDao.getOnlineBySsid("544"));
		onlineDao.deleteOnline("544");
	}
}
