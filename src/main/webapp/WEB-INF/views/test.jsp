<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form id='frm'>
	<input type='text' name="id" id='id'>
	<input type='text' name="pwd" id='pwd'>
	<input type='button' id='btn' value='click'>
</form>

<script>
	$(function(){
		$("#btn").click( () => {
			$("#frm").attr('action', "/formattertest/" + $('#id').val());
			$("#frm").submit();
		})
	})

</script>
</body>
</html>