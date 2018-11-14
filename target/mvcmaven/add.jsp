<%@page import="java.util.UUID"%>
<%@  taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
<script type="text/javascript">
	 window.onload = function() {
		var btn = document.getElementById("submitbtn");
		btn.onclick = function() {
			this.disabled = true;//让按钮失效
			this.parentNode.submit();//提交
		} 
	}
		

</script>
	tr{height:40px;}
	td{padding:10px;}
</style>
</head>
<body>
	<% String uuid = UUID.randomUUID().toString();
		session.setAttribute("uuid",uuid);
	%>
	<form action="${pageContext.request.contextPath }/add.udo" method="post">
	<input type = "hidden" name = "token" value = <%=uuid %> />
		<table style ="margin-left:100px;padding:50px;border:1px #ccc solid;width: 600px" >
			<c:if test="${not empty requestScope.note}">
				<tr>
					<td colspan="2" style = "text-align:center; color:red;font-weight: bolder;">${note }</td>
				</tr>
			</c:if>
			
			
			<tr>
				<td style = "text-align:right;">用户名:</td>
				<td style = "text-align:left;"><input type = "text" name = "username" /></td>
			</tr>
			<tr>
				<td style = "text-align:right;">密码:</td>
				<td style = "text-align:left;"><input type = "password" name = "password" /></td>
			</tr>
			<tr>
				<td style = "text-align:right;">用户地址:</td>
				<td style = "text-align:left;"><input type = "text" name = "address" /></td>
			</tr>
			<tr>
				<td style = "text-align:right;">用户电话:</td>
				<td style = "text-align:left;"><input type = "text" name = "phone" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">验证码:</td>
				<td style="text-align: left;"><input type="text" name="checkCode" />&nbsp;&nbsp;<img
				 id="pic"	alt="验证码" src="${pageContext.request.contextPath}/drawCheckCode.udo" /></td>
			</tr>
			<tr>
				<td colspan="2" style = "text-align:center;"><input id = "submitbtn" type = "submit" value = "注册用户" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style = "text-align:right;"><a href="${pageContext.request.contextPath }/login.jsp">返回登录界面</a>
				</td>
			</tr>
		</table>
		
	</form>

</body>
</html>