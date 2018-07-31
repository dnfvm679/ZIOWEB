<%@page import="java.util.Properties"%>
<%@page import="com.util.PropertyManager"%>
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
				href="/ZIOWEB/Factory?cmd=getRequestList&page=1&id=<%=session.getAttribute("userid").toString()%>">나의
					문의 사항</a></li>
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
	<%
		PropertyManager prom = PropertyManager.getInstance();
		Properties pro = prom.getProperties();
		String value = pro.getProperty("hello");
	%>
	<section>
		<br>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<div class="card" style="width: 100%">
						<div class="card-body">
							<h4 class="card-title">처리된 요청 수</h4>
							<p class="card-text" id="finished_request">N/A</p>
						</div>
					</div>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<div class="card" style="width: 100%">
						<div class="card-body">
							<h4 class="card-title">미처리된 요청 수</h4>
							<p class="card-text" id="unfinished_request">N/A</p>
						</div>
					</div>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<div class="card" style="width: 100%">
						<div class="card-body">
							<h4 class="card-title">오늘의 요청 건수</h4>
							<p class="card-text" id="todayRequest">N/A</p>
						</div>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div id="donutchart" style="width: 100%; height: 500PX;"></div>
	</section>


	<!-- Google Chart -->
	<script src="https://www.gstatic.com/charts/loader.js"></script>
	<script>
		var finished;
		var unfinished;
		setInterval(function() {
			$.ajax({
				type : "GET",
				url : "Factory?cmd=getRequestNum",
				dataType : "json",
				error : function() {
					alert('통신실패!!');
				},
				success : function(data) {
					$("#todayRequest").html(data.total);
					$("#finished_request").html(data.finished);
					finished = data.finished;
					$("#unfinished_request").html(data.unfinished);
					unfinished = data.unfinished;
				}
			});
			google.charts.load("current", {
				packages : [ "corechart" ]
			});
	
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
				var data = google.visualization.arrayToDataTable([
					[ '상태', 'State per totalRequest' ],
					[ 'finish', finished ],
					[ 'unfinish', unfinished ],
				]);
	
				var options = {
					title : '오늘의 요청 처리수',
					pieHole : 0.4,
				};
	
				var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
				chart.draw(data, options);
			}
		}, 1000);
		$(".card-body").css("text-align", "center");
	</script>
</body>
</html>