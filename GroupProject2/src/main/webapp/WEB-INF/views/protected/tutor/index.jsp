<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<title>Tutor Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src=""></script>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #555;
	color: white;
	padding: 10px;
	position: fixed;
	bottom: 0px;
	left: 0px;
	right: 0px;
	height: 70px;
}

.footerlinks {
	color: white;
}
</style>
</head>
<body>

	<c:choose>
		<c:when test="${empty pageContext.request.userPrincipal}">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="index">B e a n</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							<li><a href="aboutUs">About</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="login"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
							<li><a href="register"><span
									class="glyphicon glyphicon-link"></span> Register</a></li>
						</ul>
					</div>
				</div>
			</nav>

		</c:when>

		<c:otherwise>
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="index">B e a n</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							<li><a href="aboutUs">About</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="logout"><span
									class="glyphicon glyphicon-log-out"></span> Logout</a></li>

							<c:choose>
								<c:when test="${user.role == 'S' }">
									<li><a href="studentIndex"><span
											class="glyphicon glyphicon-user"></span> ${user.username }</a></li>
								</c:when>
								<c:when test="${user.role == 'T' }">
									<li><a href="tutorHome"><span
											class="glyphicon glyphicon-user"></span> ${user.username }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="adminIndex"><span
											class="glyphicon glyphicon-user"></span>${user.username }</a></li>

								</c:otherwise>
							</c:choose>

						</ul>
					</div>
				</div>
			</nav>

		</c:otherwise>
	</c:choose>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<h2>Welcome to your home page: ${user.firstname}</h2>
			<a href="tutorAccount" role="button" class="btn btn-success">Tutor
				Account</a>



			<c:set var="hiredOrNot" value="${user.hired}" />

			<c:if test="${hiredOrNot == true}">
				<a href="lesson" role="button" class="btn btn-success"> display
					lessons</a>

			</c:if>

			<br> </br>
			<div
				style="font-size: 25px; background-color: #8c8c8c; font color: white">
				<font color="black"><font color="white">Online Courses
				</font>
			</div>
			<div style="display: flex">




				<div style="width: 33%; height: 200px; border: 1px solid black">
					<b>Learn Advanced Features of SQL</b> <br>
					<p>This course will help you understand the advanced features
						of SQL. Learning these features will help you query and manipulate
						data within the database</p>
				</div>

				<div style="width: 33%; height: 200px; border: 1px solid black">
					<b> Object-Oriented Programming with Java</b> <br>
					<p>This Java introductory short course teaches object-oriented
						programming skills using Java, the "learn once, apply anywhere"
						language.</p>
				</div>

				<div style="width: 33%; height: 200px; border: 1px solid black">
					<b> Microsoft Excel 2013</b> <br>
					<p>This course is training for basic, intermediate, and
						advanced features of Microsoft Office Excel 2013 software.</p>
				</div>
			</div>
			<div
				style="font-size: 15px; font-family: Britannic Bold; background-color: #8c8c8c; font color: white">
				<font color="black"><font color="white">Extra Info </font>
			</div>
			<div class="col-sm-1"></div>
			<div id="FeederNinja_b82ddbb7661a48e48d43d189040e854e"></div>
			<script type="text/javascript">
				(function() {
					var fn = document.createElement('script');
					fn.type = 'text/javascript';
					fn.src = ('https:' == document.location.protocol ? 'https://'
							: 'http://')
							+ 'www.feederninja.com/api/feed/b82ddbb7661a48e48d43d189040e854e?fnurl='
							+ window.location.href;
					var s = document.getElementsByTagName('head')[0]
							.appendChild(fn);
				})();
			</script>
		</div>
	</div>
	<footer class="container-fluid text-center">
		<p>
			<a class="footerlinks" href="contactUs">Contact Us</a><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a
				class="footerlinks" href="aboutUs">About</a></span>
		</p>
		<p>Created by the Beneficial Bean</p>
	</footer>
</body>
</html>