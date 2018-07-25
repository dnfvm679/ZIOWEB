<%@page import="com.dto.BoardVO"%>
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
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- Brand/logo -->
		<a class="navbar-brand" href="#">ZIONEX</a>

		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="/ZIOWEB/Factory?cmd=main">메인</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/ZIOWEB/Factory?cmd=test">문의하기</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/ZIOWEB/Factory?cmd=test">찾아오시는 길</a></li>
			</ul>
			<%
				if (session.getAttribute("userid") == null) {
			%>
			<!-- Login Button -->
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="/ZIOWEB/Factory?cmd=loginform">로그인</a></li>
			</ul>
			<%
				} else {
			%>
			<!-- Member info -->
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> 내정보 </a>

					<div class="dropdown-menu">
						<a class="dropdown-item" href="/ZIOWEB/Factory?cmd=test">회원정보</a>
						<%
							if (session.getAttribute("userid").toString().equals("ADMIN")) {
						%>
						<a class="dropdown-item"
							href="/ZIOWEB/Factory?cmd=useradmin&page=1">회원관리</a>
						<%
							}
						%>
						<a class="dropdown-item" href="/ZIOWEB/Factory?cmd=logout">로그아웃</a>
					</div></li>

			</ul>
			<%
				}
			%>
		</div>
	</nav>
	<!-- End of Navigation -->

	<!-- Side Menu -->
	<aside class="bg-dark">
		<div class="container-fluid">
			<a href="#">Link1</a> <br> <a href="#">Link2</a> <br> <a
				href="#">Link3</a>
		</div>
	</aside>
	<!-- End of Side Menu -->

	<section>
		<%
			BoardVO board = (BoardVO) request.getAttribute("board");
		%>
		<div class="container-fluid">
			<br>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-7" style="text-align: center">
					<table class="table table-hover">
						<thead>
							<tr>
								<th colspan="2"><input type="hidden" value="writeIssue"
									name="cmd">글 상세보기</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tag">제목</td>
								<td><%=board.getTitle()%></td>
							</tr>
							<tr>
								<td class="tag">작성자</td>
								<td><%=board.getWriter()%></td>
							</tr>
							<tr>
								<td class="tag">작성일자</td>
								<td><%=board.getCreatedate()%></td>
							</tr>
							<tr>
								<td class="tag">내용</td>
								<td>
									<div><%=board.getContent()%></div>
								</td>
							</tr>
						</tbody>
					</table>

					<div class="float-right">
						<%
							if (!board.getStatus().equals("C") && board.getWriter().equals((String)session.getAttribute("userid"))) {
						%>
						<a class="btn btn-primary"
							href="/ZIOWEB/Factory?cmd=updateIssueForm&boardnum=<%=board.getBoardnum()%>">수정하기</a>
						<a class="btn btn-primary"
							href="/ZIOWEB/Factory?cmd=deleteIssue&boardnum=<%=board.getBoardnum()%>">삭제하기</a>
						<%
							}
						%>
						<a class="btn btn-primary" href="/ZIOWEB/Factory?cmd=back">뒤로가기</a>
					</div>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</section>
</body>
</html>