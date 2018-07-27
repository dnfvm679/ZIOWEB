<%@page import="com.dto.RequestVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.UserVO"%>
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
		<a class="navbar-brand" href="/ZIOWEB/Factory?cmd=main">ZIONEX</a>

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
		<%
			if (session.getAttribute("userid") != null) {
				String userid = (String) session.getAttribute("userid");
		%>
		<div><%=userid%>님이 로그인 되었습니다.
		</div>
		<br>
		<%
			}
		%>
		<a href="/ZIOWEB/Factory?cmd=getRequestList&page=1">이슈게시판</a> <br>
		<br> <a href="#">이슈게시판</a> <br> <br> <a href="#">이슈게시판</a>
	</aside>
	<!-- End of SideMenu -->

	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-7">
					<table class="table table-hover" style="width: 100%;text-align: center;">
						<thead>
							<tr>
								<th colspan="4"><input type="hidden" value="loginAction"
									name="cmd">요청목록</th>
							</tr>
							<tr>
								<td>요청번호</td>
								<td>제목</td>
								<td>작성자</td>
								<td>작성일자</td>
								<td>처리상태</td>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<RequestVO> list = (ArrayList<RequestVO>) request.getAttribute("boardList");
								if (list != null) {
									for (RequestVO r : list) {
							%>
							<tr>
								<td><a href="/ZIOWEB/Factory?cmd=viewRequest&id=<%=r.getId()%>"><%=r.getReq_index()%></a></td>
								<td><a href="/ZIOWEB/Factory?cmd=viewRequest&id=<%=r.getId()%>"><%=r.getTitle()%></a></td>
								<td><%=r.getUser_id()%></td>
								<td><%=r.getRequest_date()%></td>
								<td><%=r.getProcess_state_id() %></td>
							</tr>
							<%
								}
								}
							%>
						</tbody>
					</table>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<ul class="pagination">
						<%
							int currpage = Integer.parseInt(request.getParameter("page"));
							int lastPage = (int) request.getAttribute("lastPage");
							String keyword = request.getParameter("keyword");
							String dataType = request.getParameter("dataType");
							int startPage = (currpage - 1) / 3 * 3 + 1;
							int endPage = startPage + 2;
							if (endPage > lastPage) {
								endPage = lastPage;
							}
							if (currpage != 1 && keyword == null) {
						%>
						<li class="page-item"><a class="page-link"
							href="/ZIOWEB/Factory?cmd=getRequestList&page=<%=currpage - 1%>">Previous</a></li>
						<%
							} else if (currpage != 1 && keyword != null) {
						%>
						<li class="page-item"><a class="page-link"
							href="/ZIOWEB/Factory?cmd=userSearch&page=<%=currpage - 1%>&keyword=<%=keyword%>&dataType=<%=dataType%>">Previous</a></li>
						<%
							}
						%>
						<%
							for (int i = startPage; i <= endPage; i++) {
								if (keyword == null) {
									if (i == currpage) {
						%>
						<li class="page-item active"><a class="page-link"
							href="/ZIOWEB/Factory?cmd=getRequestList&page=<%=i%>"><%=i%></a></li>
						<%
							} else {
						%>
						<li class="page-item"><a class="page-link"
							href="/ZIOWEB/Factory?cmd=getRequestList&page=<%=i%>"><%=i%></a></li>
						<%
							}
								} else if (keyword != null) {
									if (i == currpage) {
						%>
						<li class="page-item active"><a class="page-link"
							href="/ZIOWEB/Factory?cmd=userSearch&page=<%=i%>&keyword=<%=keyword%>&dataType=<%=dataType%>"><%=i%></a></li>
						<%
							} else {
						%>
						<li class="page-item"><a class="page-link"
							href="/ZIOWEB/Factory?cmd=userSearch&page=<%=i%>&keyword=<%=keyword%>&dataType=<%=dataType%>"><%=i%></a></li>
						<%
							}

								}
							} //end of for
							if (currpage != lastPage) {
						%>
						<li class="page-item"><a class="page-link"
							href="/ZIOWEB/Factory?cmd=getRequestList&page=<%=currpage + 1%>">Next</a></li>
						<%
							}
						%>
					</ul>
				</div>
				<div class="col-sm-4">
					<form class="form-inline" action="/ZIOWEB/Factory">
						<input type="hidden" value="userSearch" name="cmd"> <input
							type="hidden" value="1" name="page"> <select
							class="forom-control mr-sm-2" name="dataType">
							<option value="title">제목</option>
							<option value="writer">작성자</option>
							<option value="t+w">제목+작성자</option>
						</select> <input class="form-control mr-sm-2" type="text" name="keyword"
							placeholder="Search">
						<button class="btn btn-primary" type="submit">Search</button>
					</form>
				</div>
				<div class="col-sm-1">
					<a class="btn btn-primary float-right"
						href="/ZIOWEB/Factory?cmd=writeRequestForm">글쓰기</a>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</section>
</body>
</html>