
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tutor Account Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
			<h2>
				Your balance is:

				<fmt:formatNumber type="number" maxFractionDigits="2"
					value="${user.balance}" />

			</h2>
			</br>

			<form action="tutor/withdraw" method="post">

				<label>Withdraw Amount <input type="text" name="withdraw" /></label>
				<input type="submit" value="Withdraw" /> <br />

			</form>


			<!-- 	<a href="leaveTutor">  -->
			<!-- 	<button type="button" class="btn btn-danger">Quit</button> -->
			<!-- 	</a> -->
			</br> <a href="uploadCV"> Upload Your CV</a>









			<div class="col-sm-4">
				<c:forEach var="entry" items="${TutorCVMap }">
					<c:if test="${entry.key == user.username }">
						<c:choose>
							<c:when test="${entry.value == '0' }">
								No CV Uploaded
								</c:when>
							<c:otherwise>
							<c:choose>
							<c:when test="${user.hired == false }">
							
							
								<sf:form action="applyForNewCourse" method="post"
									modelAttribute="tutorEmployRequest" class="form-horizontal">
									<fieldset>

										<legend>
											<h4>Apply For A New Course</h4>
										</legend>

										<div class="form-group" style="color: black">
											<label class="col-md-4 control-label">Course</label>
											<div class="col-md-4">
												<sf:select path="preferredCourseName" class="form-control">
													<sf:option value="NONE" label="--- Select ---" />
													<c:forEach var="Course" items="${courseList}">
														<sf:option value="${Course.courseName}">${Course.courseName}</sf:option>

													</c:forEach>
													<sf:hidden value="T" path="type" />
												</sf:select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-md-4 control-label" for="submit"></label>
											<div class="col-md-4">
												<button id="submit" name="submit" class="btn btn-primary">SUBMIT</button>
											</div>
										</div>

									</fieldset>
								</sf:form>
								</c:when>
								
								<c:otherwise>
								
								</c:otherwise>
								</c:choose>
								
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</div>


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