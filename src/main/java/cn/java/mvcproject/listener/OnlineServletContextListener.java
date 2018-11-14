package cn.java.mvcproject.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import javax.swing.Timer;

import cn.java.mvcproject.model.Online;
import cn.java.mvcproject.service.FactoryService;
import cn.java.mvcproject.service.OnlineService;
@WebListener
public class OnlineServletContextListener implements ServletContextListener{
	/*
	 * 10分钟，访问中超过时间的设置毫秒没有操作，则认为离线
	 */
	private final int MAX_MILLIS = 10*60*1000;
	OnlineService onlineService = FactoryService.getOnlineServoce();
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//存放过时的访问者信息
		List<Online> expiresUserList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//服务器启动时就被执行
		//定时器
		new Timer(5*1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Online> list = onlineService.getAllOnline();
				if(list != null) {
					Date date = null;
					for (Online ol : list) {
						date = ol.getTime();
						simpleDateFormat.format(date);
						Long exMillis ;
						try {
							exMillis = simpleDateFormat.parse(date.toString()).getTime();
							if((System.currentTimeMillis()-exMillis) > MAX_MILLIS) {
								expiresUserList.add(ol);
							}
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
				//从数据库中删除离线用户
				onlineService.deleteOnline(expiresUserList);
				//删除expiresUserList容器中的数据
				expiresUserList.clear();
			}
		}).start();
		
	}

}
