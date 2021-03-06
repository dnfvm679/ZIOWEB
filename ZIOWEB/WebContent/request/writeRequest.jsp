<%@page import="com.dto.CommonCodeVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	<!-- login check -->
	<%
		if (session.getAttribute("userid") == null) {
	%>
	<script>
		alert("로그인이 필요한 서비스입니다.");
		location.href("/ZIOWEB/member/loginForm.jsp");
	</script>
	<%
		}
	%>
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
						<a class="dropdown-item"
							href="/ZIOWEB/Factory?cmd=viewUser&userid=<%=(String) session.getAttribute("userid")%>">회원정보</a>
						<%
							if (session.getAttribute("userid").toString().equals("ADMIN")) {
						%>
						<a class="dropdown-item"
							href="/ZIOWEB/Factory?cmd=userManagement&page=1">회원관리</a>
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
		<ul class="nav flex-column">
			<%
				if (session.getAttribute("userid") != null) {
					String userid = (String) session.getAttribute("userid");
			%>
			<li class="nav-item"><%=userid%>님이 로그인 되었습니다.</li>
			<%
				if (userid.equals("ADMIN")) {
			%>
			<li class="nav-itme"><a class="nav-item"
				href="/ZIOWEB/Factory?cmd=userManagement&page=1">회원관리</a></li>
			<li class="nav-itme"><a class="nav-item"
				href="/ZIOWEB/Factory?cmd=userManagement&page=1">고객사관리</a></li>
			<%
				}
			%>
			<li class="nav-item"><a class="nav-link"
				href="/ZIOWEB/Factory?cmd=getRequestList&page=1">나의 문의 사항</a></li>
			<%
				}
			%>
			<li class="nav-item"><a class="nav-link"
				href="/ZIOWEB/Factory?cmd=getRequestList&page=1">이슈게시판</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li>
		</ul>

	</aside>
	<!-- End of SideMenu -->

	<section>
		<div class="container-fluid">
			<br>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-7" style="text-align: center">
					<%
						String company_id = (String) request.getAttribute("company_id");
						String board_num = (String) request.getAttribute("board_num");
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						String request_id = "R_" + company_id + "_" + sdf.format(date) + "_" + board_num;
					%>
					<form action="/ZIOWEB/Factory?cmd=writeRequest&id=<%=request_id%>"
						method="post" enctype="multipart/form-data">
						<table class="table table-hover">
							<thead>
								<tr>
									<th colspan="2">요청 작성하기</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>제목</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" maxlength="100"
												placeholder="Enter Title" name="title">
										</div>
									</td>
								</tr>
								<tr>
									<td>작성자</td>
									<td>
										<div class="form-group">
											<input type="hidden" class="form-control" name="user"
												value=<%=session.getAttribute("userid")%>
												readonly="readonly"> <input type="text"
												class="form-control" name="dummy"
												value=<%=session.getAttribute("userName")%>
												readonly="readonly">
										</div>
									</td>
								</tr>
								<tr>
									<td>내용</td>
									<td>
										<div class="form-group">
											<textarea id="requestwrite" class="form-control" rows="1"
												maxlength="2048" cols="1" name="content"
												placeholder="Enter Content"></textarea>
										</div>
									</td>
								</tr>

								<tr>
									<td>파일 첨부하기 <a id="add" class="btn btn-light">+</a> <a
										id="remove" class="btn btn-light">-</a>
									</td>
									<td id="fileUpload"><input class="form-control"
										type="file" name="file"></td>
								</tr>
								<tr>
									<td colspan="2">이미지가 많은 경우 압축해서 올려주세요. 최대 100MB까지 업로드
										가능합니다.</td>
								</tr>
							</tbody>
						</table>

						<div class="float-right">
							<button class="btn btn-primary" type="submit">작성하기</button>
							<button class="btn btn-primary" type="reset">초기화</button>
							<a class="btn btn-primary" href="/ZIOWEB/Factory?cmd=back">뒤로가기</a>
						</div>
					</form>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</section>
	<script>
		var i = 0;
		$("#add").click(function() {
			if (i < 4) {
				$("#fileUpload").append("<div id=i><br><input class='form-control' type='file' name='file'+i></div>");
				i++;
			}
		});
	
		$("#remove").click(function() {
			if (i > 0) {
				i--;
				$("#i").remove();
			}
		});
	</script>
</body>
</html>