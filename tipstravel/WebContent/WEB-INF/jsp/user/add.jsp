<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<sf:form method="post" modelAttribute="user" action="/tipstravel/user/adding">
Username:<sf:input path="username" />
		<sf:errors path="username" />
		<br />
Password:<sf:password path="password" />
		<sf:errors path="password" />
		<br />
Email:<sf:input path="email" />
		<sf:errors path="email" />
		<br />
		<input type="submit" value="添加用户">

	</sf:form>
</body>
</html>