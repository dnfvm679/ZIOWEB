<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Google jQuery file -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Bootstrap file -->
<link rel="stylesheet" href="/ZIOWEB/css/bootstrap.css">
<link rel="stylesheet" href="/ZIOWEB/css/custom.css">
<script src="/ZIOWEB/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%
		String saveDir = application.getRealPath("/filestorage/");
		File dir = new File(saveDir, "bootstrap-4.1.2-dist.zip");

		String fName = dir.getName();
	%>
	<a
		href="/ZIOWEB/Download?filename=<%=java.net.URLEncoder.encode(fName, "UTF-8")%>/"><%=fName%></a>
	<br>
	<button type="button">업로드 취소</button>
	><
</body>
</html>