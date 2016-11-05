<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Beneficial Beans</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    left:0px;
    right:0px;
    height:70px;
}

.footerlinks{
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
						<a class="navbar-brand" href="index">
						B e a n
						</a>
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
						<a class="navbar-brand" href="index">B e a n </a>
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
							<li><a href="studentIndex"><span class="glyphicon glyphicon-user"></span> ${user.username }</a></li>
							</c:when>
							<c:when test="${user.role == 'T' }">
							<li><a href="tutorHome"><span class="glyphicon glyphicon-user"></span> ${user.username }</a></li>
							</c:when>
							<c:otherwise>
							<li><a href="adminIndex"><span class="glyphicon glyphicon-user"></span> ${user.username }</a></li>
							
							</c:otherwise>
							</c:choose>

						</ul>
					</div>
				</div>
			</nav>

		</c:otherwise>
	</c:choose>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
		</ol>

		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="<c:url value= "/resources/banner.jpg" />" alt="Image">
				<div class="carousel-caption">
					
				</div>
			</div>

			<div class="item">
				<img src="<c:url value= "/resources/banner2.jpg" />" alt="Image">
				<div class="carousel-caption">
					
				</div>
			</div>
		</div>

		
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<div class="container text-center" >
		<h2>Courses We Offer</h2>
		<br>
		<div class="row" style="display: flex;">
			<div class="col-sm-4">
				<img src="<c:url value= "/resources/sql.png" />"
					class="img-responsive"  alt="Image"></a>
			</div>
			<div class="col-sm-4">
				
				<img src="<c:url value= "/resources/java.png" />"
					class="img-responsive"  alt="Image">
				</a>
			</div>
			<div class="col-sm-4">
				
				<img src="<c:url value= "/resources/excel.png" />"
					class="img-responsive" alt="Image">
				</a>
			</div>
		</div>
	</div>
	<br>
	
<footer class="container-fluid text-center">

<p><a class="footerlinks" href="contactUs">Contact Us</a><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a class="footerlinks" href="aboutUs">About</a></span></p>
<p>Created by the Beneficial Bean</p>
</footer>
</body>
</html>