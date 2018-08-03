<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
		String saveDir = application.getRealPath("/filestorage/A");
		File dir = new File(saveDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		int maxSize = 1024 * 1024 * 100;
		String encType = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
				new DefaultFileRenamePolicy());

		out.write("이름 : " + multipartRequest.getParameter("name") + "<br>");
		out.write("파일 : " + multipartRequest.getParameter("file") + "<br>"); //null 값을 갖는다.
		out.write("업로드파일명 : " + multipartRequest.getFilesystemName("file") + "<br>");
		out.write("원래파일명 : " + multipartRequest.getOriginalFileName("file") + "<br>");

		File file = multipartRequest.getFile("file");
	%>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	파일용량 :
	<fmt:formatNumber value="<%=file.length()%>" groupingUsed="true" />
</body>
</html>