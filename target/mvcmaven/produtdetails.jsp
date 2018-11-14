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
		String pname = (String)request.getAttribute("pname");
		out.println(pname);
	%>
	<br><br>
	产品详细信息...
	<br><br>
	<form action="<%=request.getContextPath()%>/addCart.pdo">
		<input type ="hidden" name = "pname" value = <%=pname %>>
		<input style = "width: 100px;height: 30px;background: #ffff;color: #000;" type = "submit"  value = "加入购物车" />
	</form>
</body>
</html>