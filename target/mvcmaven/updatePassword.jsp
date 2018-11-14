<%@  taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	tr{height:40px;}
	td{padding:10px;}
</style>
</head>
<body>
	
		<form action="${pageContext.request.contextPath}/updatePasswordDo.udo" method = "post">
			<input type = "hidden" name = "id" value = "${requestScope.id}"/>
			<table style ="margin-left:100px;padding:50px;border:1px #ccc solid;width: 600px" >
			<c:if test="${not empty requestScope.note}">
				<tr>
					<td colspan="2" style = "text-align:center; color:red;font-weight: bolder;">${note }</td>
				</tr>
			</c:if>
			<tr>
				<td style = "text-align:right;">旧密码:</td>
				<td style = "text-align:left;"><input type = "password" name = "Jpassword" /></td>
			</tr>
			<tr>
				<td style = "text-align:right;">新密码:</td>
				<td style = "text-align:left;"><input type = "password" name = "Xpassword" /></td>
			</tr>
			<tr>
				<td style = "text-align:right;">确认新密码:</td>
				<td style = "text-align:left;"><input type = "password" name = "Qpassword" /></td>
			</tr>
			<tr><td colspan="2" style = "text-align:center;"><input type = "submit" value = "确认修改" />
				</td>
				<td colspan="2" style = "text-align:right;"><a href="${pageContext.request.contextPath }/update.jsp">返回上一层</a>
				</td>
			</tr>
		</table>
		
		
		
		</form>
</body>
</html>