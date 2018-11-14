<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script type="text/javascript">
	/* function getCookie(c_name) {
		if (document.cookie.length > 0) {
			var c_start = document.cookie.indexOf(c_name + "=");
			c_start = c_start + c_name.length + 1;
			var c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1)
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	} */
	window.onload = function() {
		//alert();
		/* var form = document.getElementById("loginform");
		var userId = document.getElementById("userId"); */
		var btn = document.getElementById("submitbtn");
		/* if (getCookie("userKey") != "" && getCookie("userKey") != null
				&& getCookie("ssid") != "" && getCookie("ssid") != null) {
			userId.value = getCookie("userKey");
			form.submit();
			//alert(getCookie("userKey"));
		} */
		btn.onclick = function() {
			this.disabled = true;//让按钮失效
			this.parentNode.submit();//提交
		}
	}

</script>
</head>
<body>
	<form id="loginform" action="<%=request.getContextPath()%>/login.udo"
		method="post">
		
		<c:if test="${not empty requestScope.note}">
			<span style="text-align: left; color: red; font-weight: bolder;">${note }</span>
		</c:if>
		<br>
		<br> 账号:<input id="userId" type="text" name="userId" value="" />
		<br>
		<br> 密码:<input type="password" name="password" /> <br>
		<br> <input type="radio" name="expiredays" value="7" />记住七天<input
			type="radio" name="expiredays" value="30" />记住一月<input type="radio"
			name="expiredays" value="100" />永久記住 <br>
		<br> <input id="submitbtn" type="submit" value="登录" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span style="text-align: center;"><a
			href="${pageContext.request.contextPath }/add.jsp">注册新用户</a></span>

	</form>
</body>
</html>