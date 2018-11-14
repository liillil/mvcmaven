package cn.java.mvcproject.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.java.mvcproject.model.Online;
import cn.java.mvcproject.model.User;
import cn.java.mvcproject.service.FactoryService;
import cn.java.mvcproject.service.OnlineService;
import cn.java.mvcproject.service.UserService;
import cn.java.utils.Captcha;
import cn.java.utils.CookieUtils;
import cn.java.utils.GeneratingRandomNumbers;
 @WebServlet(urlPatterns = {"*.udo"})//注解用于代替web.xml文件
public class UserController extends HttpServlet{
	UserService userService = FactoryService.getUserService();
	OnlineService onlineService = FactoryService.getOnlineServoce();
	private static final long serialVersionUID = 1L;
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);//将get请求转换成post请求
	} 
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//将界面的信息以utf-8的编码格式接受
		resp.setCharacterEncoding("UTF-8");//将信息以utf-8的格式注入界面
		String mn = req.getServletPath();//获取请求名称
		mn = mn.substring(1);
		mn = mn.substring(0,mn.length()-4);
		try {//通过反射获取方法
			Method method =  this.getClass().getDeclaredMethod(mn, HttpServletRequest.class,HttpServletResponse.class);//参数 类名（字符串）该方法参数类信息类
			method.invoke(this,req, resp);//执行该方法
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	 
	@SuppressWarnings("unused")
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String token = req.getParameter("token");
		String uuid = (String) session.getAttribute("uuid");
		session.removeAttribute("uuid");
		System.out.println(uuid +"\t" + token);
		if(uuid == null && token == null) {
			resp.sendRedirect(req.getContextPath() +"/login.jsp");
			return;
		}
		if(!uuid.equals(token)) {
			req.setAttribute("note","请不要重复提交");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);	
			return;
		}
		User user = new User();
		//SimpleDateFormat format = new  SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		//String date = format.format(new Date());
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String cc = req.getParameter("checkCode");
		String scc = (String) session.getAttribute("cc");//拿到放入session中的验证码
		session.removeAttribute("cc");
		if(!cc.equals(scc)) {
			req.setAttribute("note","验证码错误");
			req.getRequestDispatcher("/add.jsp").forward(req, resp);
			return;
		}
		if(username == null || "".equals(username)) {
			req.setAttribute("note","用户名不能为空");
			req.getRequestDispatcher("/add.jsp").forward(req, resp);
			return;
		}
		if(password == null || "".equals(password)) {
			req.setAttribute("note","密码不能为空");
			req.getRequestDispatcher("/add.jsp").forward(req, resp);	
			return;
		}
		if(phone == null || "".equals(phone)) {
			req.setAttribute("note","联系电话不能为空");
			req.getRequestDispatcher("/add.jsp").forward(req, resp);	
			return;
		}
		
		long num = 0;
		String userId = null;
		do {
			userId = GeneratingRandomNumbers.randomNumbers();
			num = userService.selectCountByuserId(userId);
		}while(num != 0);
		long count = userService.selectCountByName(username);
		
		if(count>0) {//用户名能在数据库中找到相同记录，则发生冲突
			req.setAttribute("note",username+"这个名字已被占用");
			req.getRequestDispatcher("/add.jsp").forward(req, resp);
			return;
		}
		user.setUserId(userId);
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setAddress(address);
		user.setRegDate(new Date());
		int i = userService.insert(user);
		if(i>0) {//注册成功回到主界面，否则跳转到错误页面
			req.setAttribute("note","注册成功!!! 账号为:"+userId);
			req.getRequestDispatcher("/add.jsp").forward(req, resp);
			return;
		}else {
			
			resp.sendRedirect(req.getContextPath()+"/error.jsp");
			return;
		}
		
	}
	/**
	 * 实现首页的模糊查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String username =  req.getParameter("username"); //获取界面出入的信息
		String phone =  req.getParameter("phone"); 
		String address =  req.getParameter("address");
		username = username == null?"":username.replaceAll("[`~!@#$%^&*()=|{}':;',\\[\\].<>?~!@#￥%……&*（）&mdash;-|{}【】‘；：’“”'。，·、？]","");//通过正则表达式过滤到特殊字符
	 	phone = phone == null?"":phone.replaceAll("[`~!@#$%^&*()=|{}':;',\\[\\].<>?~!@#￥%……&*（）&mdash;-|{}【】‘；：’“”'。，·、？]","");
	 	userId = userId == null?"":userId.replaceAll("[`~!@#$%^&*()=|{}':;',\\[\\].<>?~!@#￥%……&*（）&mdash;-|{}【】‘；：’“”'。，·、？]","");
		address = address == null?"":address.replaceAll("[`~!@#$%^&*()=|{}':;',\\[\\].<>?~!@#￥%……&*（）&mdash;-|{}【】‘；：’“”'。，·、？]","");
		List<User> list = userService.query(userId,username, address, phone);//将界面传过来的信息传入方法中
		req.setAttribute("userList", list);//把结果集放到req属性空间
		req.getRequestDispatcher("/main.jsp").forward(req, resp);//整个req和resp注入到jsp页面

	}
	
	@SuppressWarnings("unused")
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id")); 
		int i = userService.deleteUser(id);
		if(i>0) {//删除成功回到主界面，否则跳转到错误页面
			resp.sendRedirect(req.getContextPath()+"/main.jsp");
			return;
		}else {
			
			resp.sendRedirect(req.getContextPath()+"/error.jsp");
			return;
		}
		
	}
	
	@SuppressWarnings("unused")
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id")); 
	 	User user = userService.select(id);
	 	req.setAttribute("user", user);
	 	req.getRequestDispatcher("/update.jsp").forward(req, resp);
	}
	
	@SuppressWarnings("unused")
	private void updatedo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		//通过id拿到原来数据库中的数据
		User yUser = userService.select(id);
		String yusername = yUser.getUsername();
		String xusername = req.getParameter("username");//新的用户名
		long count = userService.selectCountByName(xusername);
		if(!xusername.equals(yusername) && count>0) {//新名字与旧名字不同且与数据库中找到相同记录，则发生冲突
			req.setAttribute("note",xusername+"这个名字已被占用");
			req.getRequestDispatcher("/update.udo?id="+id).forward(req, resp);
			return;
		}	
		yUser.setUsername(xusername);
		yUser.setPhone(req.getParameter("phone"));
		yUser.setAddress(req.getParameter("address"));
		int i = userService.updateUser(yUser);
		if(i>0) {//修改成功回到主界面，否则跳转到错误页面
			req.setAttribute("note","修改成功");
			req.getRequestDispatcher("/update.udo?id="+id).forward(req, resp);
		}else {
			
			resp.sendRedirect(req.getContextPath()+"/error.jsp");
		}
	}
	
	@SuppressWarnings("unused")
	private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("id", id);
	 	req.getRequestDispatcher("/updatePassword.jsp").forward(req, resp);
//		String Jpassword = req.getParameter("Jpassword");
//		String Xpassword = req.getParameter("Xpassword");
//		String Qpassword = req.getParameter("Qpassword");
		
	}
	@SuppressWarnings("unused")
	private void updatePasswordDo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		//System.out.println(id);
		User user = userService.select(id);
		String Jpassword = req.getParameter("Jpassword");
		String Xpassword = req.getParameter("Xpassword");
		String Qpassword = req.getParameter("Qpassword");
		Jpassword = Jpassword == null ? "":Jpassword;
		if(Jpassword.equals(user.getPassword())) {
			if(Xpassword == null ||  "".equals(Xpassword) ) {
				req.setAttribute("note","密码不能为空！！");
				req.getRequestDispatcher("/updatePassword.udo?id="+id).forward(req, resp);
				return;
			}else if(Xpassword.equals(Qpassword)){
				user.setPassword(Xpassword);
				int i = userService.updatePassword(user);
				if( i > 0) {
					req.setAttribute("note","修改成功");
					req.getRequestDispatcher("/updatePassword.udo?id="+id).forward(req, resp);
				}else {
					resp.sendRedirect(req.getContextPath()+"/error.jsp");
				}
			}else{
				req.setAttribute("note","两次密码不一致");
				req.getRequestDispatcher("/updatePassword.udo?id="+id).forward(req, resp);
				return;
			}
			
		}else {
			req.setAttribute("note","密码错误！！");
			req.getRequestDispatcher("/updatePassword.udo?id="+id).forward(req, resp);
			return;
		}
		
	}
	 
	/**
	 * 用户登录方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取传输过来的值
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String expiredays = req.getParameter("expiredays");
		
		Cookie[] cookies = req.getCookies();//创建一个cookie数组，获取所有的cookie值
		
		boolean login = false;//是否登录标记:true表示已登录，false表示还没登录。
		String account = null;//登录账号
		String ssid = null;//这是一个标记，通过cookie拿到的一个判断用户登录成不成功的标志
		
		if(cookies != null && cookies.length>0) {//判断cookies数组是否为空
			for (Cookie cookie : cookies) {//如果不为空遍历cookies
				if(cookie.getName().equals("userKey")) {
					account = cookie.getValue();
				}
				if(cookie.getName().equals("ssid")) {
					ssid = cookie.getValue();
				}
			}			
		}
		
		if(account != null && ssid != null) {//如果ssid与account加密之后相等 login == true
			login = ssid.equals(CookieUtils.md5Encrypt(userId));
		}
		
		
		if(!login) {//login取反 如果login为false:用户还没有登录过
			//用户第一次登陆
			User user = userService.login(userId,password);//通过访问数据库来判断用户方法是否正确
			//userService中的login方法判断用户账号和密码对不对 如果正确则返回一个User对象 否则返回空
			if(user != null) {//user不为空 ,登录成功
				expiredays = expiredays == null? "":expiredays;
				switch (expiredays) {
				case "7"://选择记住一周
					CookieUtils.createCookie(userId, req, resp, 7*24*3600);
					break;
				case "30"://选择记住一月
					CookieUtils.createCookie(userId, req, resp, 30*24*3600);
					break;
				case "100"://选择永久记住
					CookieUtils.createCookie(userId, req, resp,Integer.MAX_VALUE);
					break;
				default:
					CookieUtils.createCookie(userId, req, resp, -1);
					break;
				}	
				req.getSession().setAttribute("user", user.getUserId());
				//把在线状态的online表里的username从游客改为正真的username
				HttpSession session = req.getSession();
				Online ol = onlineService.getOnlineBySsid(session.getId());
				if(ol != null) {
					ol.setUserId(userId);
					User u = userService.selectuserId(userId);
					ol.setUsername(u.getUsername());
					onlineService.updateOnlie(ol);
				}
				//登录成功，准许用户进入main页面	
				resp.sendRedirect(req.getContextPath()+"/main.jsp");
			}else {
				req.setAttribute("note", "账号或密码错误！");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		}else {
			req.getSession().setAttribute("user", userId);
			//把在线状态的online表里的username从游客改为正真的username
			HttpSession session = req.getSession();
			Online ol = onlineService.getOnlineBySsid(session.getId());
			if(ol != null) {
				ol.setUserId(userId);
				User u = userService.selectuserId(userId);
				ol.setUsername(u.getUsername());
				onlineService.updateOnlie(ol);
			}
			resp.sendRedirect(req.getContextPath()+"/main.jsp");
		}
		
	}
	
	/**
	 * 用于注销方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//记录登录状态的cookie和session删除，随后跳转到登录界面
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("userKey")) {
					cookie.setMaxAge(0);//将cookie的时间设置为0
					resp.addCookie(cookie);//再将cookie传回
				}
				if(cookie.getName().equals("ssid")) {
					cookie.setMaxAge(0);//将cookie的时间设置为0
					resp.addCookie(cookie);//再将cookie传回
				}
			}
			HttpSession session = req.getSession();
			if(session != null) {
				session.removeAttribute("user");
			}
			resp.sendRedirect(req.getContextPath()+"/login.jsp");	
	}
	
	@SuppressWarnings("unused")
	private void drawCheckCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("image/jpg");
		Captcha c = Captcha.getInstance();
		c.set(100, 25);
		String checkcode = c.randomChar();
		req.getSession().setAttribute("cc", checkcode);
		OutputStream os = resp.getOutputStream();
		
		//设置浏览器不用缓存
		resp.setDateHeader("expries", -1);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		
		
		
		ImageIO.write(c.drawing(checkcode), "jpg", os);
	}
	
	@SuppressWarnings("unused")
	private void online(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Online> list = onlineService.getAllOnline();
		req.setAttribute("online", list);
		req.getRequestDispatcher("/online.jsp").forward(req, resp);
	}
	
}
