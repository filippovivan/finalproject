<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript" type="text/javascript"
	src="/finalproject/javascript/submitUtils.js">
	
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library webapp - ${publiation.title}</title>
<link type="text/css" rel="stylesheet"
	href="/finalproject/css/common/general.css" />
</head>
<body>
	<div id="root">
		<c:import url="../common/header.jsp"></c:import>
		<div id="menu">Меню</div>
		<div id="content">
			<div>
				<table>
					<tr>
						<td>ISBN</td>
						<td>${publication.isbn}</td>
					</tr>
					<tr>
						<td>Title</td>
						<td>${publication.title}</td>
					</tr>
					<tr>
						<td>Publish year</td>
						<td>${publication.publishYear}</td>
					</tr>
					<tr>
						<td>Author</td>
						<td>${publication.author}</td>
					</tr>
					<tr>
						<td>Genre</td>
						<td>${publication.genre}</td>
					</tr>
					<tr>
						<td>Format</td>
						<td>${publication.format}</td>
					</tr>
				</table>
			</div>
			<div>${publication.annotation}</div>
			<div>
				<form action="controller" method="post">
					<input type="hidden" name="isbn" value="${publication.isbn}">
					<input type="hidden" name="command" value="TakeBook"> <input
						type="radio" name="giveoutType" value="HALL">Читальный зал<br>
					<input type="radio" name="giveoutType" value="ABONEMENT">Абонемент<br>
					<input type="submit" name="submit" value="Заказать"><br>
					Free books: ${bookcount}.
				</form>
			</div>
		</div>
		<div id="helper"></div>
	</div>
	<c:import url="../common/footer.jsp"></c:import>
</body>
</html>