<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
		<c:when test="${type == 'S' }">
<h2>List of all the Students</h2>
<br />

			<c:choose>
				<c:when test="${empty studentsList}">
<h2>You do not have any students.</h2>
</c:when>
				<c:otherwise>
					<table class="table">
						<tr>
							<th>Username</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Password</th>
							<th>Email</th>
							<th>Balance</th>
							<th>Course</th>
							<th>Grades</th>
							<th>Action</th>
							<th> </th>
						</tr>
						<c:forEach var="Student" items="${studentsList }">
							<tr>
								<td><c:out value="${Student.username }" /></td>
								<td><c:out value="${Student.firstname }" /></td>
								<td><c:out value="${Student.lastname }" /></td>
								<td><c:out value="${Student.password }" /></td>
								<td><c:out value="${Student.email }" /></td>
								
							<td>	<fmt:formatNumber type="number" 
            maxFractionDigits="2" value="${Student.balance}" /></td>
								<td><c:out value="${Student.course.courseName }" /></td>
								<td><c:out value="${Student.mark }"/></td>
								<td>
									<form action="deleteStudent" method="post">
										<input type="hidden" name="username"
											value="${Student.username }" />
											<button type="submit" name="submit" class="btn btn-danger">Delete</button>
									</form> <br />
									<form action="adminQuitStudentFromCourse" method="post">
									<input type="hidden" name="username" value="${Student.username }"/>
									<button type="submit" class="btn btn-danger">Quit From Course</button>
									</form>
									<form action="changePassword" method="post">
										New Password: <input type="password" name="newPassword" /> 
										<input type="hidden" name="type" value="S"/> <input
											type="hidden" name="username" value="${Student.username }" />
										<button type="submit" class="btn btn-primary">Change Password</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>

		</c:when>
		<c:when test="${type == 'T' }">
<h2>List of all the tutors.</h2>
<br />

			<c:choose>
				<c:when test="${empty tutorsList}">
<h2>You do not have any tutors.</h2>
</c:when>
				<c:otherwise>
					<table class="table">
						<tr>
							<th>Username</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Password</th>
							<th>Email</th>
							<th>Balance</th>
							<th>Course</th>
							<th>Salary Percentage</th>
							<th>CV</th>
							<th>Action</th>
							<th> </th>
						</tr>
						<c:forEach var="Tutor" items="${tutorsList }">
							<tr>
								<td><c:out value="${Tutor.username }" /></td>
								<td><c:out value="${Tutor.firstname }" /></td>
								<td><c:out value="${Tutor.lastname }" /></td>
								<td><c:out value="${Tutor.password }" /></td>
								<td><c:out value="${Tutor.email }" /></td>
								<td>	<fmt:formatNumber type="number" 
            maxFractionDigits="2" value="${Tutor.balance}" /></td>
								<td><c:out value="${Tutor.course.courseName }" /></td>
								<td><c:out value="${Tutor.percentage }%"/><br/>
								<form class="form-horizontal" action="changePer" method="post">
								<input type="hidden" name="username" value="${Tutor.username }" />
								<input name="percentage" type="number" step="1" min="0" max="100" placeholder="Enter percentage" class="form-control input-md"><button type="submit" class="btn btn-primary">&nbsp&nbsp Set &nbsp&nbsp</button>						
								</form>
								</td>
								<td>
								
								<c:forEach var="entry" items="${TutorCVMap }">
								<c:if test="${entry.key == Tutor.username }">
								<c:choose>
								<c:when test="${entry.value == '0' }">
								No CV Uploaded
								</c:when>
								<c:otherwise>
								<a href="download?username=${Tutor.username}&amp;type=0"><button class="btn btn-primary">Download CV</button></a>
								</c:otherwise>
								</c:choose>
								</c:if>
								</c:forEach>
								
								
								
								</td>
								<td>
									<form class="form-horizontal" action="changePassword" method="post">
										<label class="col-md-4 control-label" for="textinput">New Password:</label> <input type="password" name="newPassword" /> <input
											type="hidden" name="username" value="${Tutor.username }" />
											<input type="hidden" name="type" value="T"/>
										<button type="submit" class="btn btn-primary">Change Password</button>
									</form>
								</td>
								<td>
									<form  class="form-horizontal" action="deleteTutor" method="post">
										<input type="hidden" name="username"
											value="${Tutor.username }" /><button type="submit" class="btn btn-danger">&nbsp&nbsp Delete &nbsp&nbsp</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>

		</c:when>
		<c:when test="${type ==  'C'}">
<h2>List of all courses.</h2>

<a href="createCourseWithoutReq"><button type="submit" class="btn btn-primary">Create Course</button></a>

			<br />

			<c:choose>
				<c:when test="${empty coursesList}">
<h2>You do not have any course.</h2>
</c:when>
				<c:otherwise>
					<table class="table">
						<tr>
							<th>Course ID</th>
							<th>Course Name</th>
							<th>price</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Number of Lessons</th>
							<th>Number of Tutors</th>
							<th>Number of Students</th>
							<th>Action</th>
							<th> </th>
						</tr>

						<c:forEach var="Course" items="${coursesList }">
							<tr>
								<td><c:out value="${Course.id }" /></td>
								<td><c:out value="${Course.courseName }" /></td>
								<td><c:out value="${Course.price }" /></td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" value="${Course.startDate }" /></td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" value="${Course.endDate }" /></td>
								<td>
								<c:forEach var="entry" items="${courseLessonMap }">
								<c:if test="${entry.key == Course.id }">
								<c:out value="${entry.value }"/>
								</c:if>
								</c:forEach>
								</td>
								<td><c:out value="${fn:length(Course.tutorList) }" /></td>
								<td><c:out value="${fn:length(Course.studentList) }" /></td>
								<td>
									<form action="changePrice" method="post">
										New Price: <input type="number" name="newPrice" /> <input
											type="hidden" name="courseId" value="${Course.id }" />
										<button type="submit" class="btn btn-primary">Change Price</button>
									</form>
								</td>
								<td>
									<form action="deleteCourse" method="post">
										<input type="hidden" name="courseId" value="${Course.id }" />
										<button type="submit" class="btn btn-danger">Delete</button>
									</form> 
								</td>
							</tr>
						</c:forEach>

					</table>
				</c:otherwise>
			</c:choose>

		</c:when>

		<c:otherwise>
<h2>List of all lessons.</h2>
<div>
<a href="createLessonWithoutReq"><button type="submit" class="btn btn-primary">Create Lesson</button></a>
</div>
			<c:choose>
				<c:when test="${empty lessonsList}">
<h2>You do not have any Lesson.</h2>
</c:when>
				<c:otherwise>
					<table class="table">
						<tr>
							<th>Lesson ID</th>
							<th>Lesson Name</th>
							<th>Tutor</th>
							<th>Course</th>
							<th>Number of Students</th>
							<th>Action</th>
							<th> </th>
						</tr>
						<c:forEach var="Lesson" items="${lessonsList }">
							<tr>
								<td><c:out value="${Lesson.id }" /></td>
								<td><c:out value="${Lesson.name }" /></td>
								<td><c:out value="${Lesson.tutor.firstname }" /></td>
								<td><c:out value="${Lesson.tutor.course.courseName }"/></td>
								<td><c:out value="${fn:length(Lesson.tutor.course.studentList) }" /></td>
								<td>
									
									<form class="form-horizontal" action="changeLessonName" method="post">
										<label class="col-md-4 control-label" for="textinput">New Lesson name:</label> <input type="text" name="lessonName" /> 
										<input type="hidden" name="lessonId" value="${Lesson.id }" />
										<button type="submit" class="btn btn-primary">Change Lesson Name</button>
									</form>
									
								</td>
								<td>
									
									<form  class="form-horizontal" action="deleteLesson" method="post">
										<input type="hidden" name="lessonId" value="${Lesson.id }" />
										<button type="submit" class="btn btn-danger">&nbsp&nbsp Delete &nbsp&nbsp</button>
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