<%@page import="java.util.List"%>
<%@page import="model.piclistDAO"%>
<%@page import="model.piclistDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	piclistDTO dto= new piclistDTO();
	piclistDAO dao= new piclistDAO();
	List<piclistDTO> imageLists=dao.myFileList();
	pageContext.setAttribute("imageList", imageLists);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진첩</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/piclist.css" />
</head>
<body>
<div class="center-layout-column">
	<div class="center-layout">
		<div>
			<c:if test="${param.id eq loginuserid }">
				<a href="fileupload.jsp?id=${param.id }"><input type="button" value="사진 등록"/></a>
			</c:if>
		</div>
		<div><h1>PHOTO ALBUM</h1></div>
		<div>
			<a href="home.jsp?id=${param.id }"><input type="button" value="홈"/></a>
		</div>
	</div>
	<div class="center-layout-column">
			<c:forEach var="imageList" items="${imageList }" varStatus="i">
				<c:if test="${param.id eq imageList.id }">
					<img class="profile" alt="profile" src="./resources/img/${imageList.imgName }" style="height: 300px;" width="300px;"/>
					<h4>TITLE: ${imageList.title }&nbsp;&nbsp;ADDDATE: ${imageList.created }</h4>
				</c:if>
			</c:forEach>
	</div>
</div>
</body>
</html>