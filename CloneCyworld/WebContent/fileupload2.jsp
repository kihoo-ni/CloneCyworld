<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>사진 업로드</h3>
	<span style="color: red;">${errorMessage }</span>
	<form name="fileForm" method="post" enctype="multipart/form-data" action="ListPictureServlet">
		ID : <input type="text" name="id"  value="${param.id }" readonly><br>
		IMAGE : <input type="file" name="imgName"><br> 
		TITLE : <input type="text" name="title"><br> 
		<input type="submit" value="등록">
	</form>
	
</body>
</html>