<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
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
	
	String id=request.getParameter("id"); // 오너 아이디 
	
	memberDAO memberdao=new memberDAO();
	member memberdto=new member();
	memberdto=memberdao.getMemberInfo(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진첩</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/piclist.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<link rel="stylesheet" href="./resources/css/home.css" />
</head>
<body class="body-box">
	<div class="box-radius-5 logo-wrapper content-box">
		<a href="index.jsp"><img class="literal-logo"  style="width: 100%; height: 100px;" alt="cy-literal-logo" src="./resources/img/Cyworld-literal.svg"></a>
	</div>
	<div class="container content-box">
		<div class="left-box" style="display: flex;  flex-direction: column;  justify-content: space-between;" >
			<div class="box-radius-5 center" style="height: 5%;">
				<%=memberdto.getId()%>님의 미니홈피
			</div>
			<div class="box-radius-5 center" style="height: 5%;">
				<form action="LogoutServlet" method="post">
					<input type="submit" value="로그아웃"/>
				</form>
			</div>
			<div class="box-radius-5 center" style="height: 5%;">Today 222 | Total 1111</div>
			<div class="box-radius-5 center-layout-column" style="height: 85%;">
				<img class="profile" alt="profile" src="./resources/img/<%=memberdto.getImgName()%>"/>
				<div class="dot-line"></div>
				<div>
					<div class="box-radius-5" style="text-align: center;">
						화이팅 문구
					</div>
					<div>
						<%=memberdto.getId()%>님의 미니홈피에 오신것을 환영합니다!
					</div>
				</div>
				<div class="dot-line"></div>
				<div>
					<div>소개글</div>
					<select id="pageSelect" onchange="openPage()">
						<option value="">파도타기</option>
						<option value="https://www.naver.com">네이버</option>
						<option value="https://www.google.com">구글</option>
					</select>
				</div>
			</div>
		</div>
		<div class="right-box" style="overflow: auto;">
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
		</div>
</body>
</html>