<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.CompanyVO"%>
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
		<%
			UserVO user = (UserVO) request.getAttribute("user");
		%>
		<div class="container-fluid">
			<br>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-7" style="text-align: center">
					<form action="/ZIOWEB/Factory" method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<th colspan="2"><input type="hidden" value="updateUser"
										name="cmd">유저 수정하기</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th colspan=2>필수정보</th>
								</tr>
								<tr>
									<td>회사ID</td>
									<td>
										<div class="form-group">
											<select class="form-control" name="company_id">
												<%
													if (request.getAttribute("company") != null) {
														ArrayList<CompanyVO> list = (ArrayList<CompanyVO>) request.getAttribute("company");
														for (CompanyVO c : list) {
															if (c.getId().equals(user.getCompany_id())) {
												%>
												<option value="<%=c.getId()%>" selected="selected"><%=c.getName()%></option>
												<%
													} else {
												%>
												<option value="<%=c.getId()%>"><%=c.getName()%></option>
												<%
													}
														}
													}
												%>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td>ID</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control"
												value="<%=user.getId()%>" placeholder="Enter ID" name="id"
												readonly="readonly">
										</div>
									</td>
								</tr>
								<tr>
									<td>이름</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" maxlength="50"
												value="<%=user.getName()%>" name="name" id="name" required>
										</div>
									</td>
								</tr>
								<tr>
									<th colspan=2>부가정보</th>
								</tr>
								<tr>
									<td>직급</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" maxlength="50"
												value="<%=user.getPosition()%>" name="position" required>
										</div>
									</td>
								</tr>
								<tr>
									<td>부서</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" maxlength="30"
												value="<%=user.getTeam()%>" name="team">
										</div>
									</td>
								</tr>

								<tr>
									<td>전화번호</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" maxlength="15"
												value="<%=user.getTel()%>" name="tel">
										</div>
									</td>
								</tr>

								<tr>
									<td>휴대번호</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" maxlength="15"
												value="<%=user.getPhone()%>" name="phone">
										</div>
									</td>
								</tr>
								<tr>
									<td>Email</td>
									<td>
										<div class="form-group">
											<input type="email" class="form-control" maxlength="50"
												value="<%=user.getEmail()%>" name="email">
										</div>
									</td>
								</tr>
								<tr>
									<td>주소</td>
									<td>
										<div class="form-group">
											<input type="text" class="form-control" maxlength="250"
												value="<%=user.getAddress()%>" name="address">
										</div>
									</td>
								</tr>
							</tbody>
						</table>

						<div class="float-right">
							<button class="btn btn-primary" type="submit">수정하기</button>
							<button class="btn btn-primary" type="reset">초기화</button>
							<a class="btn btn-primary" href="/ZIOWEB/Factory?cmd=back">뒤로가기</a>
						</div>
					</form>
				</div>
				<div class="col-sm-4"></div>
			</div>
		</div>
	</section>
</body>
</html>