<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<h2>Hello ${user.firstname } ${user.lastname }</h2> <br/>

<h3>Below is the site information.</h3><br/>

<table class="table">
  <tr>
    <th>Tutors</th>
    <th>Students</th>
    <th>Courses</th>
    <th>Lessons</th>
    <th>Balance</th>
  </tr>
  
  <tr>
  	<th><c:out value="${fn:length(tutorsList) }"/></th>
	<th><c:out value="${fn:length(studentsList) }"/></th>
  	<th><c:out value="${fn:length(coursesList) }"/></th>
  	<th><c:out value="${fn:length(lessonsList) }"/></th>
  	<th><fmt:formatNumber type="number" 
            maxFractionDigits="2" value="${admin.balance}" /></th>
  	
  	
  </tr>
  <tr>
	<th><a href="adminView?type=${'T' }" ><button class="btn btn-primary">View All Tutors</button></a></th>
	<th><a href="adminView?type=${'S' }"><button class="btn btn-primary">View All Students</button></a></th>
	<th><a href="adminView?type=${'C' }"><button class="btn btn-primary">View All Courses</button></a></th>
	<th><a href="adminView?type=${'L' }"><button class="btn btn-primary">View All Lessons</button></a></th>
	<th></th>
</tr>
</table>

<br/>
<h2>Below are the requests.</h2>
<br/>
<table class="table">
  <tr>
    <th>Job Applications</th>
    <th>Enroll Applications</th>
    <th>Course Add Requests</th>
    <th>Lesson Add Requests</th>
  </tr>
  
  <tr>
  	<th><c:out value="${fn:length(tutorRequest) }"/></th>
	<th><c:out value="${fn:length(studentRequest) }"/></th>
  	<th><c:out value="${fn:length(courseRequest) }"/></th>
  	<th><c:out value="${fn:length(lessonRequest) }"/></th>
  </tr>
	<th><a href="adminViewRequest?type=${'T' }" ><button class="btn btn-primary">View</button></a></th>
	<th><a href="adminViewRequest?type=${'S' }"><button class="btn btn-primary">View</button></a></th>
	<th><a href="adminViewRequest?type=${'C' }"><button class="btn btn-primary">View</button></a></th>
	<th><a href="adminViewRequest?type=${'L' }"><button class="btn btn-primary">View</button></a></th>

</table>
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