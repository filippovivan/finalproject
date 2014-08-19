<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="init" scope="request" value="true"></c:set>
	<form name="loginForm" method="post" action="controller">
		<input type="hidden" name="command" value="Login" /> Please log in: <br>
		Login: <input type="text" name="login"> <br> Password: <input
			type="password" name="password"> <br> <input
			type="submit" name="login">
		<p>${noSuchUser}</p>
	</form>
	<form name="registerForm" method="post" action="controller">
		<br>Register: <input type="hidden" name="command"
			value="StartRegistration" /> <input type="submit" name="login">
	</form>
</body>
</html>