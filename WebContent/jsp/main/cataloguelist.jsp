<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script language="javascript" type="text/javascript"
	src="/finalproject/javascript/submitUser.js">
	
</script>
<script language="javascript" type="text/javascript"
	src="/finalproject/javascript/submitUtils.js">
	
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Library webapp - Catalogues</title>
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/general.css" />
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/table.css" />
</head>
<body>
	<div id="root">
		<c:import url="../common/header.jsp"></c:import>
		<div id="menu">
			<h1>Menu</h1>
			<hr>
			<ul>
				<li>Search</li>
				<li>Catalogues</li>
				<li>My books</li>
			</ul>
		</div>
		<div id="content">
			<table>
				<tr>
					<th>№</th>
					<th>Name</th>
					<th>Decsription</th>
				</tr>
				<c:forEach items="${catalogues}" var="catalogue" varStatus="status">
					<tr onclick="chooseCatalogue('${catalogue.id}')">
						<td>${status.count}</td>
						<td>${catalogue.name}</td>
						<td>${catalogue.description}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="helper"></div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>