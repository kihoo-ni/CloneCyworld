<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://www.youtube.com/iframe_api"></script>
<script type="text/javascript" src="./resources/js/youtubePlayer.js"></script>
</head>
<body onload="loadPlayer()">
	<button onclick="playMusic()">재생</button>
		<button onclick="pauseMusic()">일시정지</button>
		<button onclick="nextMusic()">다음곡</button>
		<div id="bgmPlayer" style="display: none;"></div>
</body>
</html>