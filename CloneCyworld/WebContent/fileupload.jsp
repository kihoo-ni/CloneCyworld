<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	
	String id=request.getParameter("id"); // 오너 아이디 
	
	memberDAO memberdao=new memberDAO();
	member memberdto=new member();
	memberdto=memberdao.getMemberInfo(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
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
		
		
		<div class="right-box" style="overflow: auto; overflow: auto;
 		   display: flex;  flex-direction: column;  justify-content: center; align-items: center;" >
			<div>
					<a href="home.jsp?id=<%=id%>"><input type="button" value="홈"/></a>
			</div>
			<h3>사진 업로드</h3>
			<span style="color: red;">${errorMessage }</span>
			<form name="fileForm" method="post" enctype="multipart/form-data" action="ListPictureServlet" style="text-align: center;">
				ID : <input type="text" name="id"  value="${param.id }" readonly><br>
				IMAGE : <input type="file" name="imgName"><br> 
				TITLE : <input type="text" name="title"><br> 
				<input type="submit" value="등록">
			</form>
		</div> 
	</div>
</body>
</html>