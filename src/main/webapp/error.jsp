<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@  taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	哎呀，出错了！！！  操作失败！！！
	<br><br><br><br><br><br><br><br>
			<c:if test="${not empty requestScope.note}">
			<span style="text-align: left; color: red; font-weight: bolder;">${note }</span>
			</c:if>
</body>
</html>