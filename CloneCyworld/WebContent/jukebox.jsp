<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.musicDTO"%>
<%@page import="model.musicDAO"%>
<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String id=request.getParameter("id"); // 오너 아이디 
	String loginid=(String)session.getAttribute("loginuserid"); //로그인 아이디
	pageContext.setAttribute("loginid", loginid);
	memberDAO memberdao=new memberDAO();
	member memberdto=new member();
	memberdto=memberdao.getMemberInfo(id);
	
	musicDAO mdao= new musicDAO();
	musicDTO mdto= new musicDTO();
	List<musicDTO> mlist=new ArrayList<>();
	mlist=mdao.myMusiclist(id);
	pageContext.setAttribute("mlist", mlist);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주크박스</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<link rel="stylesheet" href="./resources/css/home.css" />
<script type="text/javascript" src="./resources/js/music.js"></script>
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
		
		
		<div class="right-box" style="overflow: auto; overflow: auto;
 		   display: flex;  flex-direction: column;  justify-content: center; align-items: center;" >
			<h1>JUKEBOX SONG LIST</h1>
			<div class="center-layout">
					<a href="home.jsp?id=<%=id%>"><input type="button" value="홈"/></a>&nbsp;&nbsp;
					<c:if test="${param.id eq loginid }">
						<input type="hidden" value="<%=id %>" name="id"/>
						<input type="button" onclick="musicadd()" value="음악추가"/>&nbsp;&nbsp;
					</c:if>
			</div>
			<div class="center-layout-column">
				<form action="selectmusicServlet" method="post">
					<c:forEach var="mlist" items="${mlist }">
						<input type="submit"  value="배경음악등록"/>
						<div class="center-layout" style="border: 1px solid gray;">
							<input type="checkbox" name="m_no" value="${mlist.m_no }">&nbsp;&nbsp;
							<input type="hidden" name="id" value="${mlist.id }">
							<h4>Title: ${mlist.title }</h4>&nbsp;&nbsp;
							<h4>Artist: ${mlist.artist  }</h4>&nbsp;&nbsp;
							<h4>AddDate: ${mlist.created }</h4>
						</div>
					</c:forEach>
				</form>
			</div>
			
		</div> 
	</div>
</body>
</html>