<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}
</style>
<title>Beneficial Beans</title>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="index">B e a n</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Requests
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="adminViewRequest?type=${'T' }">Tutor Job Requests(${fn:length(tutorRequest) })</a></li>
          <li><a href="adminViewRequest?type=${'S' }">Enrollment Requests(${fn:length(studentRequest)})</a></li>
          <li><a href="adminViewRequest?type=${'C' }">Course Requests(${fn:length(courseRequest)})</a></li> 
          <li><a href="adminViewRequest?type=${'L' }">Lesson Requests(${fn:length(lessonRequest)})</a></li> 
        </ul>
        </li>
        </ul>
        <ul class="nav navbar-nav">
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">BEAN Fam
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="adminView?type=${'T' }">Tutors</a></li>
          <li><a href="adminView?type=${'S' }">Students</a></li>
        </ul>
        </li>
        </ul>
                <ul class="nav navbar-nav">
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Academia
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="adminView?type=${'C' }">Courses</a></li>
          <li><a href="adminView?type=${'L' }">Lessons</a></li>
        </ul>
        </li>
        </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
		 <li><a href="adminIndex"><span class="glyphicon glyphicon-log-in"></span> Profile</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
	<c:choose>
		<c:when test="${type == 'T' }">
<h2>Tutor Job Applications</h2>
<br />

			<c:choose>
				<c:when test="${empty reqList}">
<h2>You do not have any job applications.</h2>
</c:when>
				<c:otherwise>
					<table class="table">
						<tr>
							<th>Request ID</th>
							<th>Applicant Username</th>
							<th>Preferred Course</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
						<c:forEach var="Request" items="${reqList }">
							<tr>
								<td><c:out value="${Request.id }" /></td>
								<td><c:out value="${Request.username }" /></td>
								<td><c:out value="${Request.preferredCourseName }" /></td>
								<td><c:out value="${Request.description }" /></td>
								<td>
									<form action="approveEmployRequest" method="post">
									Course:
									<select name="courseName">
									<c:forEach var="Course" items="${coursesList}">
								<option value="${Course.courseName }" >${Course.courseName }</option>
								</c:forEach>
								</select>
										<input
											type="hidden" name="requestId" value="${Request.id }" />
											<button type="submit" class="btn btn-primary">Employ</button>
									</form> <br />
									<form action="rejectEmployRequest" method="post">
										<input type="hidden" name="requestId" value="${Request.id }" />
										<button type="submit" class="btn btn-danger">Do Not Employ</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</c:when>

		<c:when test="${type == 'S' }">
<h2>Student Course Enrolments</h2>
<br />

			<c:choose>
				<c:when test="${empty reqList}">
<h2>You do not have any enrolment requests.</h2>
</c:when>
				<c:otherwise>
					<table class="table">
						<tr>
							<th>Request ID</th>
							<th>Student Username</th>
							<th>Course</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
						<c:forEach var="Request" items="${reqList }">
							<tr>
								<td><c:out value="${Request.id }" /></td>
								<td><c:out value="${Request.username }" /></td>
								<td><c:out value="${Request.courseName }" /></td>
								<td><c:out value="${Request.description }" /></td>
								<td>
									<form action="approveEnroll" method="post">
										<input type="hidden" name="requestId" value="${Request.id }" />
										<button type="submit" class="btn btn-primary">Enroll</button>
									</form> <br />
									<form action="rejectEnroll" method="post">
										<input type="hidden" name="requestId" value="${Request.id }" />
										<button type="submit" class="btn btn-danger">Do Not Enroll</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>

		</c:when>
		<c:when test="${type ==  'C'}">
<h2>Course Addition Requests</h2>
<br />

			<c:choose>
				<c:when test="${empty reqList}">
<h2>You do not have any course addition requests.</h2>
</c:when>
				<c:otherwise>

						<table class="table">

							<tr>
								<th>Request ID</th>
								<th>Tutor Username</th>
								<th>Course</th>
								<th>Price</th>
								<th>Description</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Action</th>
							</tr>
							<c:forEach var="Request" items="${reqList }">

								<tr>
							<form action="responseCreateCourse" method="post">			
									<td><c:out value="${Request.id }" /></td>
									<td><c:out value="${Request.username }" /></td>
									<td><c:out value="${Request.courseName }" /></td>
									<td><input type="text" name="price" /></td>
									<td><c:out value="${Request.description }" /></td>
									<td>
										<div class="form-group">
											<label class="col-md-4 control-label" for="date">Start
												Date</label>
											<div class="col-md-4">
												<input id="date" name="startDate" type="date"
													placeholder="Enter start date"
													class="form-control input-md" required="true" />
											</div>
										</div>
									</td>
									<td>
										<div class="form-group">
											<label class="col-md-4 control-label" for="date">Start
												Date</label>
											<div class="col-md-4">
												<input id="date" name="endDate" type="date"
													placeholder="Enter end date" class="form-control input-md"
													required="true" />
											</div>
										</div>
									</td>
									<td><input type="hidden" name="requestId"
										value="${Request.id }" />
										<button type="submit" value="Approve" class="btn btn-primary">Approve</button> <br/>
										<button type="submit" value="Do Not Approve" class="btn btn-danger">Do Not Approve</button>
									</td>
								</form>					
								</tr>

							</c:forEach>
						</table>

				</c:otherwise>
			</c:choose>

		</c:when>

		<c:otherwise>
<h2>Lesson Addition Requests</h2>
<br />

			<c:choose>
				<c:when test="${empty reqList}">
<h2>You do not have any Lesson addition requests.</h2>
</c:when>
				<c:otherwise>
					<table class="table">
						<tr>
							<th>Request ID</th>
							<th> Tutor</th>
							<th> Lesson</th>
							<th> Description</th>
							<th> Action</th>
						</tr>
						<c:forEach var="Request" items="${reqList }">
							<tr>
								<td><c:out value="${Request.id }" /></td>
								<td><c:out value="${Request.username }" /></td>
								<td><c:out value="${Request.lessonName }" /></td>
								<td><c:out value="${Request.description }" /></td>
								<td>
									<form action="approveCreateLesson" method="post">
										<input type="hidden" name="requestId" value="${Request.id }" />
										<button type="submit" value="Approve" class="btn btn-primary">Approve</button>
									</form> <br />
									<form action="rejectCreateLesson" method="post">
										<input type="hidden" name="requestId" value="${Request.id }" />
										<button type="submit" value="Do Not Approve" class="btn btn-danger">Do Not Approve</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
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