<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript" type="text/javascript"
	src="/finalproject/javascript/submitHeader.js">
	
</script>
<script language="javascript" type="text/javascript"
	src="/finalproject/javascript/submitUtils.js">
	
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/header.css" />
</head>
<body>
	<div id="header">
		<c:if test="${not empty user}">
			<div id="control">
				<span id="username">"You are logged as ${user.name}</span> <br>
				<a href="#" onclick="logout()">Logout</a>
			</div>
		</c:if>
		<div id="caption">
			<h1>Library</h1>
		</div>
	</div>
</body>
</html>