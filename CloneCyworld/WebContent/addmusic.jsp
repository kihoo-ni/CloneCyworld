<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
<script type="text/javascript" src="./resources/js/music.js"></script>
</head>
<body>
	<div class="center-layout-column">
		<form action="musicaddServlet" method="post" name="musicadd" class="center-layout-column">
				<h2>JUKEBOX에 음악등록</h2>
			<input type="hidden" name="id" value="${param.id }">
			<h3>Artist: <input type="text" name="artist" required="required"/></h3>
			<h3>Title: <input type="text" name="title" required="required"/></h3>
			<h3>Youtube_id: <input type="text" name="youtube_id" required="required"/></h3>
			
				<div class="center-layout">
					<input type="submit" value="등록"/>
					<c:if test="${result==1 }">
						<button type="button" onclick="Ok()">
							닫기
						</button>
					</c:if>
				</div>
		</form>
	</div>
</body>
</html>