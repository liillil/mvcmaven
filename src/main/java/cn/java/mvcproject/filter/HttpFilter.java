package cn.java.mvcproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpFilter implements Filter{
	private FilterConfig config;
	
	public FilterConfig getFilterConfig() {
		return this.config;
	}
	
	//服务器关闭时销毁Filter实例对象
	@Override
	public void destroy() {//用于销毁Filter对象
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		doFilter((HttpServletRequest)request,(HttpServletResponse)response,chain);
		
	}
	/**
	 * 重写doFilter方法
	 * @param req
	 * @param resp
	 * @param chain
	 */
	public void doFilter(HttpServletRequest req,HttpServletResponse resp,FilterChain chain) 
			throws IOException, ServletException {
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		init();
	}
	/**
	 * 构建一个用于继承的init()无参方法
	 */
	protected void init() {
		
	}
	
	
	
}
