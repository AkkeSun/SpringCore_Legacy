<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���Ͼ��ε�</title>
</head>
<body>
<h3>���� ���ε�</h3>
<form action="/multipartFile" method="post" enctype="multipart/form-data">
	<input type='file' name='myFile' multiple="multiple">	
	<input type='submit' value='upload'>
</form>

<h3>���� �ٿ�ε�</h3>
<input type = 'button' value='Ŭ��' onclick="goDown()">

<script>
let goDown = () => {
	location.href="/filedownloader?filename=22c4b24b88794e3f80eedbe81b5c6552.pdf";
}
</script>
</body>
</html>