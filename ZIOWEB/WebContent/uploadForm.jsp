<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Google jQuery file -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Bootstrap file -->
<link rel="stylesheet" href="/ZIOWEB/css/bootstrap.css">
<link rel="stylesheet" href="/ZIOWEB/css/custom.css">
<script src="/ZIOWEB/js/bootstrap.js"></script>
<title>FILE UPLOAD EXAMPLE</title>
</head>
<body>
	<form action="uploadAction.jsp" method="post" enctype="multipart/form-data">
		이름 : <input type="text" name="name"><br>
		업로드 : <input type="file" name="file" value="<%=application.getRealPath("/filestorage/")%>"><br>
		업로드1 : <input type="file" name="file1" value="<%=application.getRealPath("/filestorage/")%>"><br>
		<input type="submit" name="업로드"><br>
	</form>
</body>
</html>