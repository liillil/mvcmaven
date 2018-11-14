<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@  taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
	<form action="${pageContext.request.contextPath }/updatedo.udo"
		method="post">
		<input type="hidden" name="id" value="${requestScope.user.id }" />
		<table
			style="margin-left: 100px; padding: 50px; border: 1px #ccc solid; width: 500px">
			<c:if test="${not empty requestScope.note}">
				<tr>
					<td colspan="2"
						style="text-align: center; color: red; font-weight: bolder;">${note }</td>
				</tr>
			</c:if>
			<tr>
				<td style="text-align: right;">用户名:</td>
				<td style="text-align: left;"><input type="text"
					name="username" value="${requestScope.user.username }" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">用户地址:</td>
				<td style="text-align: left;"><input type="text" name="address"
					value="${requestScope.user.address }" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">用户电话:</td>
				<td style="text-align: left;"><input type="text" name="phone"
					value="${requestScope.user.phone }" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					id="submitbtn" type="submit" value="修改信息" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: left;"><a
					href="${pageContext.request.contextPath }/updatePassword.udo?id=${requestScope.user.id }">修改密码</a>
				</td>
				<td colspan="2" style="text-align: right;"><a
					href="${pageContext.request.contextPath }/main.jsp">返回主界面</a></td>
			</tr>
		</table>
	</form>
</body>
</html>