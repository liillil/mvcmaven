<%@page import="java.util.UUID"%>
<%@  taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; UTF_8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		var btn = document.getElementById("submitbtn");
		btn.onclick = function() {
			this.disabled = true;//让按钮失效
			this.parentNode.submit();//提交
		}
}
</script>
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
	<form action="${pageContext.request.contextPath}/query.udo" method="post">
		<table
			style="margin-left: 100px; padding: 50px; border: 1px #ccc solid; width: 400px;">
			<tr>
				<td style="text-align: right;">用户账号:</td>
				<td style="text-align: left"><input type="text" name="userId" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">用户名:</td>
				<td style="text-align: left"><input type="text" name="username" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">用户地址:</td>
				<td style="text-align: left"><input type="text" name="address" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">用户电话:</td>
				<td style="text-align: left"><input type="text" name="phone" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					id="submitbtn" type="submit" value="查询用户" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: left;"><a
					href="${pageContext.request.contextPath}/logout.udo">注销</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/online.udo">在线人数</a></td>
			</tr>
		</table>
	</form>


	<table style="margin-left: 100px; padding: 50px;" border="1"
		cellpadding="0" cellspacing="0">
		<tr>
			<td>用户id</td>
			<td>用户名称</td>
			<td>用户账号</td>
			<td>用户电话</td>
			<td>用户地址</td>
			<td>注册日期</td>
			<td>操作记录</td>
		</tr>
		<c:if test="${not empty requestScope.userList }">
			<c:forEach items="${requestScope.userList }" var="user">
		<tr>
			<td>${user.id }</td>
			<td>${user.username }</td>
			<td>${user.userId }</td>
			<td>${user.phone }</td>
			<td>${user.address }</td>
			<td>${user.regDate }</td>
			<td><a
				href="${pageContext.request.contextPath}/update.udo?id=${user.id}">编辑</a>
				| <a
				href="${pageContext.request.contextPath}/delete.udo?id=${user.id}">删除</a></td>
		</tr>
			</c:forEach>
		</c:if>
	</table>
	<br>
	<br>

</body>
</html>