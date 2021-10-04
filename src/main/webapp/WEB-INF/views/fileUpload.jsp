<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>파일업로드</title>
</head>
<body>
<h3>파일 업로드</h3>
<form action="/multipartFile" method="post" enctype="multipart/form-data">
	<input type='file' name='myFile' multiple="multiple">	
	<input type='submit' value='upload'>
</form>

<h3>파일 다운로드</h3>
<input type = 'button' value='클릭' onclick="goDown()">

<script>
let goDown = () => {
	location.href="/filedownloader?filename=22c4b24b88794e3f80eedbe81b5c6552.pdf";
}
</script>
</body>
</html>