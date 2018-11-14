 package cn.java.mvcproject.listener;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.java.mvcproject.model.Online;
import cn.java.mvcproject.model.User;
import cn.java.mvcproject.service.FactoryService;
import cn.java.mvcproject.service.OnlineService;
import cn.java.mvcproject.service.UserService;

/*
 * 基本思路:
 * 1.当请求进来时记录访问者信息，判断访问者在数据库中是否存在这个用户，更新他的访问时间及页面，如果不存在的话则插入数据库
 * 2.记录在线用户的时候，记录访问过来的时间，10分钟，10分钟用户没有操作，则用户自动离线，将数据表中的在线记录删除
 * 3.ServletRequestListener,web应用启动时没过五秒进行一次检查，对过期用户执行删除
 */
@WebListener
public class OnlineRequestListener implements ServletRequestListener{
	UserService userService = FactoryService.getUserService();
	OnlineService onlineService = FactoryService.getOnlineServoce();
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		
		//通过请求拿到访问者的信息
	 	HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
	 	HttpSession session = req.getSession();
	 	//获取sessionId
	 	String ssid = session.getId();
	 	//获取访问者IP地址
	 	String ip = req.getRemoteAddr(); 
	 	//获取访问者这在访问的页面
	 	String page = req.getRequestURI();
	 	//获取用户的账号
	 	String userId = (String) session.getAttribute("user");
	 	String username = null;
	 	if(userId == null || userId == "") {
	 		userId = "";
	 		username = "游客";
	 	}else {
	 		   User user = userService.selectuserId(userId);
	 		   username = user.getUsername();
	 	}

	 	//连接数据路,把信息放到数据库，如果数据库中已存在则更新time,没有则插入
	 	//1.根据ssid查找数据库，有无记录
	 	Online online = onlineService.getOnlineBySsid(ssid);
	 	if(online != null) {
	 		online.setTime(new Date());
	 		online.setPage(page);
	 		onlineService.updateOnlie(online);
	 	}else {
	 		Online ol = new Online();
	 		ol.setIp(ip);
	 		ol.setPage(page);
	 		ol.setSsid(ssid);
	 		ol.setUsername(username);
	 		ol.setTime(new Date());
	 		onlineService.insertOnline(ol);
	 	}
	}
}
