<%@page import="com.dto.RequestVO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.util.DBManager"%>
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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="/ZIOWEB/js/bootstrap.js"></script>
<title>ZIOWEB</title>
</head>
<body>
	<%
		RequestVO requestvo = (RequestVO) request.getAttribute("requestvo");
	%>
	<div class="container-fluid">
		<form action="/ZIOWEB/Factory" method="post">
			<input type="hidden" name="id" value="<%=requestvo.getId()%>">
			<input type="hidden" name="cmd" value="updateRequest">
			<table class="table table-hover">
				<thead>
					<tr>
						<th colspan="2">글 상세보기</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="tag">제목</td>
						<td>
							<div class="form-gruop">
								<input class="form-control" name="title"
									value="<%=requestvo.getTitle()%>">
							</div>
						</td>
					</tr>
					<tr>
						<td class="tag">작성자</td>
						<td><%=requestvo.getUser_id()%></td>
					</tr>
					<tr>
						<td class="tag">작성일자</td>
						<td><%=requestvo.getRequest_date()%></td>
					</tr>
					<tr>
						<td class="tag">내용</td>
						<td>
							<div class="form-group">
								<textarea id="requestwrite" class="form-control" rows="1"
									maxlength="2048" cols="1" name="content"
									placeholder="Enter Content"><%=requestvo.getContent()%></textarea>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<div class="float-right">
				<%
					if (requestvo.getUser_id().equals((String) session.getAttribute("userid"))) {
				%>
				<button class="btn btn-primary" type="submit">수정하기</button>
				<%
					}
				%>
				<a class="btn btn-primary" href="/ZIOWEB/Factory?cmd=back">뒤로가기</a>
			</div>

		</form>
	</div>
</body>
</html>