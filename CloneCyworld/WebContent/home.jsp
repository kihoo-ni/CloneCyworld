<%@page import="java.util.ArrayList"%>
<%@page import="model.musicDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.musicDAO"%>
<%@page import="model.piclistDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.guestbookDAO"%>
<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String id=request.getParameter("id"); // 오너 아이디 
	
	memberDAO memberdao=new memberDAO();
	member memberdto=new member();
	memberdto=memberdao.getMemberInfo(id);
	
	guestbookDAO gbdao=new guestbookDAO();
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	String todayDate = formatter.format(new Date());
	
	int result=gbdao.newAddguestbook(todayDate, id);
	if(result>=1){
	pageContext.setAttribute("result", "[N]");
	} else if(result==0){
		
	}
	piclistDAO pcdao=new piclistDAO();
	SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
	String todayDate1 = formatter.format(new Date());
	
	int result1=pcdao.newAddpic(todayDate1, id);
	if(result1>=1){
	pageContext.setAttribute("result1", "[N]");
	} else if(result1==0){
		
	}
	String gettitle=pcdao.getpicTitle(id);
	pageContext.setAttribute("gettitle", "[최근 올라온 사진 : "+gettitle+"]");
	
	musicDAO musicdao=new musicDAO();
	List<musicDTO> musicdto=new ArrayList<>();
	boolean picked=true;
	musicdto=musicdao.musicinfo(picked, id);
	
	pageContext.setAttribute("musicdto", musicdto);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미니홈피</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<link rel="stylesheet" href="./resources/css/home.css" />
<script type="text/javascript" src="./resources/js/home.js"></script>
<script type="text/javascript" src="./resources/js/bgm.js"></script>
<script type="text/javascript" src="https://www.youtube.com/player_api"></script>
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
		<div class="right-box">
			<div class="box-radius-5 center-layout-column" style="height: inherit;">
				<div class="bgm-wrapper">
					<div class="center-layout">
						<div class="center-layout-column">
							<c:forEach var="musicdto" items="${musicdto }" >
								<div class="center-layout">
									<p class="title">타이틀: ${musicdto.title }</p>&nbsp;
									<p class="artist">아티스트: ${musicdto.artist }</p>&nbsp;
								</div>
								<input type="hidden" name="title" value="${musicdto.title }">
								<input type="hidden" name="artist" value="${musicdto.artist }">
								<input type="hidden" name="youtube_id" value="${musicdto.youtube_id }">
							</c:forEach>
						</div>
						&nbsp;&nbsp;<h2>BGM</h2>&nbsp;&nbsp;
						 <div id="bgmPlayer"></div>
  						 <div id="player-controls">
							<button onclick="playMusic()">재생</button>&nbsp;
							<button onclick="pauseMusic()">일시정지</button>&nbsp;
							<button onclick="nextMusic()">다음곡</button>&nbsp;
					     </div>
						   <script>
							    var youtubeIDs = [
							        <% for(int i = 0; i < musicdto.size(); i++) { %>
							            '<%= musicdto.get(i).getYoutube_id() %>'<% if(i < musicdto.size() - 1) { %>,<% } %>
							        <% } %> 
							       
							    ];
							    
							    window.onload = function() {
							        loadPlayer(youtubeIDs);
							    };
							</script>
					</div>
				</div>
				<div class="right-box-content">
					<div>
						<p>일기</p>
						<p>사진 <span style="color: blue;">${gettitle }</span></p>
					</div>
					<div style="border:1px solid black;">
						<div class="menu-wrapper" style="display: flex;">
							<div class="menu">투데이</div>
							<div class="menu"><a href="jukebox.jsp?id=<%=memberdto.getId() %>">주크박스</a></div>
						</div>
						<div class="menu-wrapper" style="display: flex;">
							<div class="menu"><a href="piclist.jsp?id=<%=memberdto.getId() %>">사진첩</a><span style="color: red;">${result1 }</span></div>
							<div class="menu"><a href="ListBookServlet?id=<%=memberdto.getId() %>">방명록</a><span style="color: red;">${result }</span></div>
						</div>
					</div>
				</div>
					<img class="big-img" alt="big" src="https://i.namu.wiki/i/8nrRmRQCSLKG6PLZbWhW99M7bKGf4NcjLH75rwOskScYugaWT6Jskpjh0cKDJlx1iOpd5gSqY4fmboQX_8U-BQ.webp">
			</div>
		</div>
	</div>
</body>
</html>