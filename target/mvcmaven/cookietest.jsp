<%@ page language="java" session = "false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		//获取客户端发来的cookie
		Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length >0){
				for(int i = 0;i<cookies.length;i++){
					out.println("cookie的名字:"+cookies[i].getName()+"\t"+"cookie的值:"+cookies[i].getValue());
				}	
		}else{
			//新建一个cookie
			Cookie cookie = new Cookie("uu","uuvalue");
			//设置cookie有效日期（单位s）
			cookie.setMaxAge(1*24*3600);
			//通过response将cookie设置到客户端
			response.addCookie(cookie);
		}
	%>

</body>
</html>