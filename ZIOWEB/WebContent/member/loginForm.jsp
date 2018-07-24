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
				<li class="nav-item"><a class="nav-link" href="#">메인</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/ZIOWEB/Factory?cmd=test">문의하기</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/ZIOWEB/Factory?cmd=test">찾아오시는 길</a></li>
			</ul>

			<!-- Dropdown -->
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> 로그인하기 </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/ZIOWEB/Factory?cmd=loginform">로그인</a>
						<a class="dropdown-item" href="#">회원가입</a>
					</div></li>

			</ul>
		</div>
	</nav>

	<div class="container-fluid">
		<br>
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6" style="text-align: center">
				<form action="/ZIOWEB/Factory" method="post">
					<table class="table table-hover">
						<thead>
							<tr>
								<th colspan="2">
								<input type="hidden" value="loginAction" name="cmd">
								로그인
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>아이디</td>
								<td>
									<div class="form-group">
										<input type="email" class="form-control" id="email"
											placeholder="Enter Email" name="email">
											<%
												session.setAttribute("id", "");
											%>
									</div>
								</td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td>
									<div class="form-group">
										<input type="password" class="form-control" id="email"
											placeholder="Enter Password" name="password">
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td class="float-right"><button class="btn btn-primary" type="submit">로그인</button>
									<button class="btn btn-primary" type="reset">초기화</button></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
</body>
</html>