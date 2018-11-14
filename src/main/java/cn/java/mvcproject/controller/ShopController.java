package cn.java.mvcproject.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns= {"*.pdo"})
public class ShopController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//将界面的信息以utf-8的编码格式接受
		resp.setCharacterEncoding("UTF-8");//将信息以utf-8的格式注入界面
		String mn = req.getServletPath();//获取请求名称
		mn = mn.substring(1);
		mn = mn.substring(0,mn.length()-4);
		try {//通过反射获取方法
			Method mothed =  this.getClass().getDeclaredMethod(mn, HttpServletRequest.class,HttpServletResponse.class);
			mothed.invoke(this,req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 购物
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void shopping(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pname = (String)req.getParameter("pname");
		resp.getCharacterEncoding();
		req.setAttribute("pname", pname);
		req.getRequestDispatcher("produtdetails.jsp").forward(req, resp);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 购物车
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		String pname = req.getParameter("pname");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) session.getAttribute("cart");
		if(list==null) {
			list = new ArrayList<>();
			
		}
		list.add(pname);
		
		session.setAttribute("cart", list);
		
		req.setCharacterEncoding("utf-8");
		
		req.getRequestDispatcher("/shoppingcart.jsp").forward(req, resp);
	}

}
