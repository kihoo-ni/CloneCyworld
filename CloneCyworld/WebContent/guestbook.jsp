<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String id = (String) request.getAttribute("id");
	String owner_id = (String) request.getAttribute("owner_id");
	memberDAO memberdao = new memberDAO();
	member memberdto = new member();
	memberdto = memberdao.getMemberInfo(owner_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<link rel="stylesheet" href="./resources/css/home.css" />
<link rel="stylesheet" href="./resources/css/guestbook.css" />
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
	<div class="center-layout-column" style="width:100%;" >
		<div class="center-layout-column margin-top">
			<div class="center">
				<h4>"${param.id }"님의 방명록에 글을 남기시겠습니까?</h4>
				<h4>
					아니면 <a href="home.jsp?id=${param.id }">"홈"</a>으로 가시겠습니까?
				</h4>
			</div>
			<%
				if (!id.equals(owner_id)) {
				System.out.println(id);
				System.out.println(owner_id);
			%>
			<form action="WriteBookServlet?id=${owner_id }"
				class="center-layout-column" method="post" name="frm">
				<div class="center-layout-column">
					<div class="flex-between">
						<div class="left">
							<img class="homeimg" alt="프로필사진"
								src="./resources/img/${imgName }">
						</div>
						<div class="right">
							<textarea name="content" style="width: 100%; height: 200px;"></textarea>
						</div>
					</div>
					<div>
						<input type="submit" value="등록" />
					</div>
				</div>
			</form>
		</div>
		<%
			}
		%>

		<c:forEach var="list" items="${booklist }" varStatus="i">
			<div class="center-layout-column margin-top">
				<div class="center-layout-list">
					<div class="center-layout-list">
						<h4>NO.${booklist.size()-i.index}</h4>
						<div>
							<a href="home.jsp?id=${list.id }"><input type="button" value="미니홈피"/></a>
						</div>
						<h4>${list.created }</h4>
						<c:choose>
							<c:when test="${list.id eq loginuserid }"> <!-- 주인x 글쓴사람이 삭제   -->
								<input type="button" class="delete-btn" value="삭제" onClick="location.href='deleteGuestBook?no=${list.no}&owner_id=<%=owner_id%>'">
							</c:when>
							<c:when test="${owner_id eq loginuserid }"> <!-- 주인o 주인이 삭제   -->
								<input type="button" class="delete-btn" value="삭제" onClick="location.href='deleteGuestBook?no=${list.no}&owner_id=<%=owner_id%>'">
							</c:when>
						</c:choose>
					</div>
				</div>
				<div class="flex-between">
					<div class="left">
						<img class="homeimg" alt="프로필사진"
							src="./resources/img/${list.imgName }" />
					</div>
					<div class="right">
						<textarea style="width: 100%; height: 200px;" disabled="disabled">${list.content }</textarea>
					</div>
				</div>
				
				<div class="center-layout-list-column">
					<c:forEach var="replylist" items="${replylist }">
					<c:if test="${list.no eq replylist.b_no }">
						<div class="flex-between" style="border: 1px solid gray;">
							<h4>ID: ${replylist.id}</h4>
							<h4>작성날짜: ${replylist.created }</h4>
							<h4>${replylist.content }</h4>
							<c:choose>
								<c:when test="${replylist.id eq loginuserid }"> <!-- 주인x 글쓴사람이 삭제   -->
									<input type="button" class="delete-btn" value="삭제" onClick="location.href='deleteReplyServlet?r_no=${replylist.r_no}&owner_id=<%=owner_id%>'">
								</c:when>
								<c:when test="${owner_id eq loginuserid }"> <!-- 주인o 주인이 삭제   -->
									<input type="button" class="delete-btn" value="삭제" onClick="location.href='deleteReplyServlet?r_no=${replylist.r_no}&owner_id=<%=owner_id%>'">
								</c:when>
							</c:choose>
						</div>
					</c:if>
					</c:forEach>
					<div class="center-layout-list">
						 <form action="ReplyAddServlet?no=${list.no }" method="post"
							class="center-layout-list-column">
							<div class="flex-between">
								<input type="hidden" value="<%=owner_id%>" name="owner_id">
								<div class="left">
									<textarea name="content" style="width: 300px; height: 50px;"></textarea>
								</div>
								<div class="right">
									<input type="submit" value="등록" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	</div>
		</div>
</body>
</html>