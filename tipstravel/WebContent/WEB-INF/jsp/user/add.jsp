<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
</head>
<body>

	<sf:form method="post" modelAttribute="user" action="/tipstravel/user/add">
<table width="700" align="center" border="1">
	<tr>
	<td>用户名：</td><td><sf:input path="username"/><sf:errors path="username"/></td>
	</tr>
	<tr>
	<td>密码：</td><td><sf:password path="password"/><sf:errors path="password"/></td>
	</tr>
	<tr>
	<td>邮箱：</td><td><sf:input path="email"/><sf:errors path="email"/></td>
	</tr>
	<tr>
	<td colspan="2">
		<input type="submit" value="用户添加"/>
	</td>
	</tr>
</table>
</sf:form>
</body>
</html>