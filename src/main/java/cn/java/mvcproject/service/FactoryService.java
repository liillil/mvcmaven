package cn.java.mvcproject.service;

public class FactoryService {
	public static UserService getUserService() {
		
		UserService userService = new UserServiceImpl();
		return  userService;
	}  
	
	public static OnlineService getOnlineServoce() {
		OnlineService onlineService = new OnlineServiceImpl();
		return onlineService;
	}
	
	public static void main(String[] args) {
		UserService userService = FactoryService.getUserService();
		System.out.println(userService.selectAll());
	}
}
