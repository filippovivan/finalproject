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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library webapp - ${catalogue.name}</title>
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/general.css" />
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/table.css" />
</head>
<body>
	<div id="root">
		<c:import url="../common/header.jsp"></c:import>
		<div id="menu">Меню</div>
		<div id="content">
			<span id="catalogueinfo">${catalogue.name} -
				${catalogue.description}</span>
			<table>
				<tr>
					<th>№</th>
					<th>Title</th>
					<th>Author</th>
					<th>Description</th>
				</tr>
				<c:forEach items="${publications}" var="publication"
					varStatus="count">
					<tr onclick="choosePublication('${publication.isbn}')">
						<td>${count.count}</td>
						<td>${publication.title}</td>
						<td>${publication.author}</td>
						<td>${publication.genre},${publication.format},
							${publication.publishYear} - <span id="isbn">${publication.isbn}</span>
							<c:if test="${user.type eq 'LIBRARIST'}">
								<br>
								<a href="#"
									onclick="removePublication('${publication.isbn}',
								'${catalogue.id}')">remove</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="helper"></div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>