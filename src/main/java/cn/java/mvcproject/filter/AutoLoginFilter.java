package cn.java.mvcproject.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.java.mvcproject.model.Online;
import cn.java.mvcproject.model.User;
import cn.java.mvcproject.service.FactoryService;
import cn.java.mvcproject.service.OnlineService;
import cn.java.mvcproject.service.UserService;
import cn.java.utils.CookieUtils;

public class AutoLoginFilter extends HttpFilter{
	/*
	 * 使用拦截器实现自动登录
	 */ 
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		OnlineService onlineService = FactoryService.getOnlineServoce();
		UserService userService = FactoryService.getUserService(); 
		/*
		 * 思路：获取到客户端传过来的cookie集合，如果cookie为空，则正常放行，让其进入login.jsp页面
		 * 遍历cookie集合，找出所需的cookie
		 * 如果存在将值赋给两个临时变量
		 * 如果两个临时变量不为空且userId加密后与ssid相同
		 * 则将userId放入session域空间跳转到main.jsp页面否者则正常放行
		 */
		Cookie[] cookies =  req.getCookies();
		if(cookies != null && cookies.length > 0) {
			String userId = null;
			String ssid = null; 
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("userKey")) {
					userId = cookie.getValue();
				}
				if(cookie.getName().equals("ssid")) {
					ssid = cookie.getValue();
				}
			} 
			if(userId != null && ssid != null && userId != "" && 
				ssid != "" && ssid.equals(CookieUtils.md5Encrypt(userId))) {
				HttpSession session = req.getSession();
				session.setAttribute("user", userId);
				//把在线状态的online表里的username从游客改为正真的username

				Online ol = onlineService.getOnlineBySsid(session.getId());
				if(ol != null) {
					ol.setUserId(userId);
					User u = userService.selectuserId(userId);
					ol.setUsername(u.getUsername());
					onlineService.updateOnlie(ol);
				}
				resp.sendRedirect(req.getContextPath()+"/main.jsp");
			}else {
				chain.doFilter(req, resp);//正常放行
			}

		}else {
			chain.doFilter(req, resp);//正常放行
		}
	}
}
