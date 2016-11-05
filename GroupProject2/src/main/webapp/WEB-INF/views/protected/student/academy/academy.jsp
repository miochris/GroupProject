<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beans Academy</title>
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
							<li><a href="studentIndex">Home</a></li>
							<li class="active"><a href="studentAcademy">Academy</a></li>
							<li><a href="studentBalance">Balance</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">

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
							<li><a href="logout"><span
									class="glyphicon glyphicon-log-out"></span> Logout</a></li>

						</ul>
					</div>
				</div>
			</nav>

		</c:otherwise>
	</c:choose>
	<div class="container">
		<div class="row">
			<div class="col-sm-1"></div>
			<c:choose>

				<c:when test="${requestCheck == 1 }">
					<br>
					<br>
					<h2 align="center">Course Request Pending</h2>
				</c:when>
				<c:when test="${empty user.course }">
					<div class="container">
						<br> <br>
						<h2 align="center">Course List</h2>
						<table class="table">
							<thead>
								<tr>
									<th>Courses</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Tutors</th>
									<th>Cost</th>
									<th>Description</th>
									<th>Join?</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="course" items="${courseList }">
									<tr>
										<td><c:out value="${course.courseName }" /></td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${course.startDate }" /></td>
										<td><fmt:formatDate pattern="dd-MM-yyyy"
												value="${course.endDate }" /></td>
										<td><c:out value="${fn:length(course.tutorList) }" /></td>
										<td>${course.price }</td>
										<sf:form action="enrollRequest" method="post"
											modelAttribute="request">
											<sf:input type="hidden" path="courseName"
												value="${course.courseName }" />
											<sf:input type="hidden" path="username"
												value="${user.username }" />
											<sf:input type="hidden" path="type" value="S" />
											<td><sf:input type="text" path="description" /></td>
											<td><c:choose>
													<c:when test="${empty course.tutorList }">
													No tutors assigned
													</c:when>
													<c:otherwise>
														<input type="submit" value="enroll" />
													</c:otherwise>
												</c:choose></td>
										</sf:form>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>


				</c:when>

				<c:otherwise>

					<div style="display: flex">
						<div style="width: 33.3333333333%">
							<h2>Course: ${studentCourse.courseName }</h2>
						</div>
						<div style="width: 33.3333333333%">
							<h3>
								Start Date:
								<fmt:formatDate pattern="dd-MM-yyyy"
									value="${studentCourse.startDate }" />
							</h3>
						</div>
						<div style="width: 33.3333333333%">
							<h3>
								End Date:
								<fmt:formatDate pattern="dd-MM-yyyy"
									value="${studentCourse.endDate }" />
							</h3>
						</div>
					</div>

					<div class="container">
						<h2>Lesson List</h2>
						<table class="table">
							<thead>
								<tr>
									<th>Lesson</th>
									<th>Tutor</th>
									<th>Materials</th>
									<th>Grade</th>
									<th>Assessments</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="Lesson" items="${lessonList }">
									<tr>
										<td>${Lesson.name }</td>
										<td>${Lesson.tutor.firstname }&nbsp${Lesson.tutor.lastname
											}</td>
										<td>
											<form action="download" method="get">
												<input type="hidden" value="${Lesson.tutor.username }"
													name="username" /> <input type="hidden"
													value="${Lesson.id }" name="type" /> <input type="submit"
													value="Download" />
											</form>
										</td>
										<td>
											<c:forEach var="grade" items="${user.gradeList }">
												<c:if test="${grade.lesson.name eq Lesson.name}">
													<c:choose>
													<c:when test="${grade.mark<0}">
													N/A
													</c:when>
													<c:otherwise>
													${grade.mark}
													</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</td>
										<td>
										<c:forEach var="grade" items="${user.gradeList }">
												<c:if test="${grade.lesson.name eq Lesson.name}">
										
										<c:forEach var="entry" items="${lessonMapUpload }">
												<c:if test="${entry.key == grade.id }">
													<c:choose>
														<c:when test="${entry.value == '1' }">
															Assignment uploaded
														</c:when>
														<c:otherwise>
															<a href="upload?id=${Lesson.id }" ><button
																	class="btn btn-primary" style="color:blue">Upload</button></a>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
											</c:if>
											</c:forEach></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<form action="quitCourse" method="post" align="center" >
						<input type="hidden" value="${user.username}" name="username" /> <input
							type="submit" class="btn btn-danger"  value="Quit" />
					</form>

				</c:otherwise>
			</c:choose>


			<div class="col-sm-1"></div>
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