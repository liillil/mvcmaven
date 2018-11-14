package cn.java.mvcproject.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 利用Filter判断需要登陆后才能访问的URL
 * 思路:
 * ①获取访问的页面名称
 * ②从配置文件中拿到设置的所用页面的名称
 * ③将获取到的页面的名称与配置中的页面进行对比，如果为能直接访问的页面则正常放行，
 * ④如果为不能直接访问的页面则通过session拿到session域中的“user”，如果“user”为空则使其跳转到登录界面，如果“user”不为空则正常放行   	
 */
public class IsloginFilter extends HttpFilter {
	
	
	
	 @Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String path = req.getServletPath().substring(1);
		String str1 = getFilterConfig().getInitParameter("authority");
		String str2 = getFilterConfig().getInitParameter("noAuthority");
		HttpSession session = req.getSession();
		String[] noauthArr = str2.split(",");
		String[] authArr = str1.split(",");
		for (String str : noauthArr) { 
			if(str.equals(path)) {
				chain.doFilter(req, resp);
			}
		} 
		for(String str : authArr) {
			if(str.equals(path)) {
				String userId = (String) session.getAttribute("user");
				if(userId != null) {
					chain.doFilter(req, resp);
				}else {
					resp.sendRedirect(req.getContextPath()+"/login.jsp");
				}
			}
			
		}

	}
}
