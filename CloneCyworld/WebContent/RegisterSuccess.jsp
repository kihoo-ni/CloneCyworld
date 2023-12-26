<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<title>회원가입 성공</title>
</head>
<body class="center-layout-column">
	<a href="index.jsp"><img class="logo" alt="logo" src="./resources/img/cyworld.png" /></a>
	<h3>${id }님 가입을 환영합니다.</h3>
	<button onclick="location.href='login.jsp';">로그인페이지로</button>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>