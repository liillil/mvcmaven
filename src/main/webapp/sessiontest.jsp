<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 	
		/**
			当访问html、image等静态资源时是不会生成session
			HttpSession session = request.getSession(); //创建一个session对象
			request.getSession(true)//强制生成一个session，
		*/
		//session = request.getSession(); //jsp页面当中隐藏了一个session对象 可以直接使用 不用去创建
	//	session.setAttribute("data", "孤狼");//设置session的值
	//	session.setMaxInactiveInterval(3600);//配置失效时间
	//	String sessionValue = (String)session.getAttribute("data");//返回的是一个Object，需要进行强转
	//	String sessionId = session.getId();//获取sessionId
		 //判断session是不是新创建的
	//	if(session.isNew()){
	//		response.getWriter().write("session创建成功，session的id是："+sessionId+"\t"+"Value:"+sessionValue);
	//	}else{
//			response.getWriter().write("session已存在，session的id是："+sessionId+"\t"+"Value:"+sessionValue);
	//	}
	
	%>
	<br><br><br>
	<!-- 当cookie禁用时  或不被支持时（正常情况下Seesion要依靠Cookie来识别的）
	 可以通过URL地址重写的方式将用户Session的id信息重写到URL地址中。
	 服务器能够解析重写后的URL获取Session的id -->
	 <!-- 跳转 -->
<!--  	<a href="<%//response.encodeURL(request.getContextPath()+"/main.jsp")%>" >URL地址重写跳转main.jsp</a>-->
	<br><br><br>
	<% String  url = response.encodeRedirectURL(request.getContextPath()+"/cookietest.jsp");
		request.setAttribute("url", url);
		System.out.println(url);
		response.sendRedirect(url);
	%>
	<%%>
</body>
</html>