<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript" type="text/javascript"
	src="/finalproject/javascript/submitAdmin.js">
	
</script>
<script language="javascript" type="text/javascript"
	src="/finalproject/javascript/submitUtils.js">
	
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library - Admin page</title>
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/general.css" />
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/table.css" />
</head>
<body>
	<div id="root">
		<c:import url="../common/header.jsp"></c:import>
		<div id="menu">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="CreateLibrarist">
				Login: <input type="text" name="login"><br> Name: <input
					type="text" name="name"><br> Password: <input
					type="text" name="password"><br> Repeat password: <input
					type="text" name="passwordrepeat"><br> <input
					type="submit" value="Create">
			</form>
		</div>
		<div id="content">
			<table>
				<tr>
					<th>Login</th>
					<th>Name</th>
					<th>Remove</th>
				</tr>
				<c:forEach items="${librarists}" var="librarist">
					<tr>
						<td>${librarist.login}</td>
						<td>${librarist.name}</td>
						<td><a href="#"
							onclick="removeLibrarist('${librarist.login}')">remove</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="helper"></div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>