<%@page import="java.util.List"%>
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
		List<String> list =	(List<String>)session.getAttribute("cart");
		for(String string :list){
			out.println(string);
			out.println("<br><br>");
		}
	
	%>
</body>
</html>