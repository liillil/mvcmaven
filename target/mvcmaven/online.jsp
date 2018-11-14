<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看在线人数</title>
<style>
tr {
	height: 40px;
}

td {
	padding: 10px;
}
</style>
</head>
<body>
	<table border="1" style="width: 80%;margin: 0 arto">
		<tr>
			<td>在线用户的ssid</td>
			<td>在线用户的账号</td>
			<td>在线用户的用户名</td>
			<td>在线用户的IP地址</td>
			<td>在线用户访问的页面</td>
			<td>在线用户的访问时间</td>
		</tr>
		<c:forEach items="${online }" var="online">
			<tr>
				<td>${online.ssid }</td>
				<td>${online.userId }</td>
				<td>${online.username }</td>
				<td>${online.ip }</td>
				<td>${online.page }</td>
				<td>${online.time }</td>
			</tr>
		
		</c:forEach>
	</table>
</body>
</html>