<%@page import="java.io.File"%>
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
		<br>
		<div class="row">
			<div class="col-sm-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="2" class="text-center">글 상세보기</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (session.getAttribute("userid") != null) {
								if (session.getAttribute("userid").toString().equals("ADMIN")) {
						%>
						<tr>
							<td class="tag">요청 ID</td>
							<td><%=requestvo.getId()%></td>
						</tr>
						<%
							}
							}
						%>
<<<<<<< HEAD
						<tr>
							<td class="tag">제목</td>
							<td><%=requestvo.getTitle()%></td>
						</tr>
						<tr>
							<td class="tag">작성자</td>
							<td><%=requestvo.getUser_name()%></td>
						</tr>
						<tr>
							<td class="tag">작성일자</td>
							<td><%=requestvo.getRequest_date()%></td>
						</tr>
						<tr>
							<td class="tag">내용</td>
							<td>
								<p><%=requestvo.getContent().replace("\r\n", "<br>")%></p>
							</td>
						</tr>
						<%
							if (!requestvo.getProcess_state_id().equals("S01")) {
=======
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
			RequestVO requestvo = (RequestVO) request.getAttribute("requestvo");
		%>
		<div class="container-fluid">
			<br>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-7" style="text-align: center">
					<table class="table table-hover">
						<thead>
							<tr>
								<th colspan="4">글 상세보기</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (session.getAttribute("userid") != null) {
									if (session.getAttribute("userid").toString().equals("ADMIN")) {
							%>
							<tr>
								<td class="tag" colspan="2">요청 ID</td>
								<td colspan="2"><%=requestvo.getId()%></td>
							</tr>
							<%
								}
								}
							%>
							<tr>
								<td class="tag" colspan="2">제목</td>
								<td colspan="2"><%=requestvo.getTitle()%></td>
							</tr>
							<tr>
								<td class="tag" colspan="2">처리상태</td>
								<td colspan="2"><%=requestvo.getProcess_state_name()%></td>
							</tr>
							<%
								if (!requestvo.getProcess_state_id().equals("S01")) {
							%>
							<tr>
								<td class="tag" colspan="2">처리분류</td>
								<td colspan="2"><%=requestvo.getProcess_type_name()%></td>
							</tr>
							<tr>
								<td class="tag">처리담당자</td>
								<td><%=requestvo.getManager_id()%></td>
								<td class="tag">처리형태</td>
								<td><%=requestvo.getProcess_form_name()%></td>
							</tr>
							<%
								}
							%>
							<tr>
								<td class="tag" colspan="2">작성자</td>
								<td colspan="2"><%=requestvo.getUser_name()%></td>
							</tr>
							<tr>
								<td class="tag" colspan="2">작성일자</td>
								<td colspan="2"><%=requestvo.getRequest_date()%></td>
							</tr>
							<tr>
								<td class="tag" colspan="2">내용</td>
								<td colspan="2">
									<div><%=requestvo.getContent()%></div>
								</td>
							</tr>
							<tr>
								<td colspan="2">첨부파일</td>
								<td colspan="2">
									<%
										String saveDir = application.getRealPath("/filestorage/" + request.getParameter("id"));
										File dir = new File(saveDir);

										String fName[] = dir.list();
										if (fName != null) {
											for (String fileName : fName) {
												out.write("<a href=\"" + request.getContextPath() + "/Download?id="
														+ request.getParameter("id") + "&filename=" + java.net.URLEncoder.encode(fileName, "UTF-8")
														+ "\">" + fileName + "</a><br>");
											}
										}
									%>
								</td>
							</tr>
						</tbody>
					</table>

					<div class="float-right">
						<%
							if (requestvo.getUser_id().equals((String) session.getAttribute("userid"))) {
								if(!requestvo.getProcess_state_id().equals("S04")){
>>>>>>> 파일첨부
						%>
						<tr>
							<td class="tag">처리담당자</td>
							<td><%=requestvo.getManager_id()%></td>
						</tr>
						<tr>
							<td class="tag">처리분류</td>
							<td><%=requestvo.getProcess_type_name()%></td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>


			</div>
			<div class="col-sm-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="2" class="text-center">처리내용</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="tag">처리상태</td>
							<td><%=requestvo.getProcess_state_name()%></td>
						</tr>
						<%
							if (!requestvo.getProcess_state_id().equals("S01")) {
						%>
						<tr>
							<td class="tag">처리형태</td>
							<td><%=requestvo.getProcess_form_name()%></td>
						</tr>
						<%
							}
						%>
						<%
							if (requestvo.getProcess_state_id().equals("S04")) {
						%>
						<tr>
							<td class="tag">처리완료일</td>
							<td><%=requestvo.getComplete_date()%></td>
						</tr>

						<tr>
							<td class="tag">처리공수</td>
							<td><%=requestvo.getProcess_hour()%></td>
						</tr>

						<tr>
							<td class="tag">처리내용</td>
							<td><%=requestvo.getProcess_content()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row" style="float: right;">
			<div>
				<%
					if (requestvo.getUser_id().equals((String) session.getAttribute("userid"))
							&& !requestvo.getProcess_state_id().equals("S04")) {
				%><a href="#" class="btn btn-primary"
					data-remote="/ZIOWEB/Factory?cmd=updateRequestForm&id=<%=requestvo.getId()%>&state_name=<%=request.getParameter("state_name")%>>"
					data-toggle="modal" data-target="#theModal2"> 수정하기</a>
					<a class="btn btn-primary" href="/ZIOWEB/Factory?cmd=deleteRequest&id=<%=requestvo.getId()%>">삭제하기</a>
				<%
					}
				%>
<<<<<<< HEAD
=======
				<div class="col-sm-3">
					<table class="table table-hover">
						<thead>
							<tr>
								<th colspan="2">처리내용</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tag">처리완료일</td>
								<td><%=requestvo.getComplete_date()%></td>
							</tr>

							<tr>
								<td class="tag">처리공수</td>
								<td><%=requestvo.getProcess_hour()%></td>
							</tr>

							<tr>
								<td class="tag">처리내용</td>
								<td><%=requestvo.getProcess_content()%></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="col-sm-1"></div>
>>>>>>> 파일첨부
				<%
					if (session.getAttribute("userid") != null) {
						if (session.getAttribute("userid").toString().equals("ADMIN")) {
							if (!requestvo.getProcess_state_id().equals("S04")) {
				%>
				<a class="btn btn-primary"
					href="/ZIOWEB/Factory?cmd=processChangeForm&id=<%=requestvo.getId()%>">처리상태변경</a>
				<%
					}
							if (requestvo.getProcess_state_id().equals("S03")) {
				%>
				<a class="btn btn-primary"
					href="/ZIOWEB/Factory?cmd=finishRequestForm&id=<%=requestvo.getId()%>">처리완료하기</a>
				<%
					}
						}
					}
				%>
			</div>
		</div>
	</div>

</body>
</html>