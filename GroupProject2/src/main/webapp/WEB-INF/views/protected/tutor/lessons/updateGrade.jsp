<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Grade</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
    position: bottom;
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
<%-- <c:choose> --%>
<%-- 		<c:when test="${empty pageContext.request.userPrincipal}"> --%>
<!-- 			<nav class="navbar navbar-inverse"> -->
<!-- 				<div class="container-fluid"> -->
<!-- 					<div class="navbar-header"> -->
<!-- 						<button type="button" class="navbar-toggle" data-toggle="collapse" -->
<!-- 							data-target="#myNavbar"> -->
<%-- 							<span class="icon-bar"></span> <span class="icon-bar"></span> <span --%>
<%-- 								class="icon-bar"></span> --%>
<!-- 						</button> -->
<!-- 						<a class="navbar-brand" href="index">B e a n</a> -->
<!-- 					</div> -->
<!-- 					<div class="collapse navbar-collapse" id="myNavbar"> -->
<!-- 						<ul class="nav navbar-nav"> -->
<!-- 							
<!-- 							<li><a href="aboutUs">About</a></li> -->
<!-- 						</ul> -->
<!-- 						<ul class="nav navbar-nav navbar-right"> -->
<%-- 							<li><a href="login"><span --%>
<%-- 									class="glyphicon glyphicon-log-in"></span> Login</a></li> --%>
<%-- 							<li><a href="register"><span --%>
<%-- 									class="glyphicon glyphicon-link"></span> Register</a></li> --%>
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</nav> -->

<%-- 		</c:when> --%>

<!-- <<<<<<< .mine -->
<%-- 		<c:otherwise> --%>
<!-- 			<nav class="navbar navbar-inverse"> -->
<!-- 				<div class="container-fluid"> -->
<!-- 					<div class="navbar-header"> -->
<!-- 						<button type="button" class="navbar-toggle" data-toggle="collapse" -->
<!-- 							data-target="#myNavbar"> -->
<%-- 							<span class="icon-bar"></span> <span class="icon-bar"></span> <span --%>
<%-- 								class="icon-bar"></span> --%>
<!-- 						</button> -->
<!-- 						<a class="navbar-brand" href="index">B e a n</a> -->
<!-- 					</div> -->
<!-- 					<div class="collapse navbar-collapse" id="myNavbar"> -->
<!-- 						<ul class="nav navbar-nav"> -->
<!-- 						
<!-- 							<li><a href="aboutUs">About</a></li> -->
<!-- 						</ul> -->
<!-- 						<ul class="nav navbar-nav navbar-right"> -->
<%-- 							<li><a href="logout"><span --%>
<%-- 									class="glyphicon glyphicon-log-out"></span> Logout</a></li> --%>
									
<%-- 							<c:choose> --%>
<%-- 							<c:when test="${user.role == 'S' }"> --%>
<%-- 							<li><a href="studentIndex"><span class="glyphicon glyphicon-user"></span> ${user.username }</a></li> --%>
<%-- 							</c:when> --%>
<%-- 							<c:when test="${user.role == 'T' }"> --%>
<%-- 							<li><a href="tutorHome"><span class="glyphicon glyphicon-user"></span> ${user.username }</a></li> --%>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<%-- 							<li><a href="adminIndex"><span class="glyphicon glyphicon-user"></span>${user.username }</a></li> --%>
							
<%-- 							</c:otherwise> --%>
<%-- 							</c:choose> --%>

<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</nav> -->

<%-- 		</c:otherwise> --%>
<%-- 	</c:choose> --%>
<!-- <div class="container"> -->
<!-- <div class="row"> -->
<!-- <div class="col-sm-1"></div> -->
<%-- <sf:form class="form-horizontal" method="post" action="setStudentGradeForThisLesson" --%>
<%-- 		modelAttribute="grade"> --%>
<!-- 		<fieldset> -->
<!-- ======= -->
<%-- <sf:form action="setStudentGradeForThisLesson" method="post" modelAttribute="grade"> --%>
<!-- >>>>>>> .r409 -->

<%-- <label>new grade<sf:input type="text" path ="mark" /></label> <br/> --%>
<!-- <input type="submit" value ="update" /> <br/> -->
<%-- </sf:form> --%>


<!-- <<<<<<< .mine -->
<!-- 				</div> -->
<!-- 			</div> -->


<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-md-4 control-label" for="description">Description</label> -->
<!-- 				<div class="col-md-4"> -->
<%-- 					<sf:input type="text" path="description" --%>
<%-- 						placeholder="Please enter the lesson description" --%>
<%-- 						class="form-control input-md" /> --%>

<!-- 				</div> -->
<!-- 			</div> -->

<!-- 			<!-- Button --> -->
<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-md-4 control-label" for="singlebutton"></label> -->
<!-- 				<div class="col-md-4"> -->
<!-- 					<button type="submit" value="Add New Lesson" name="submit" -->
<!-- 						class="btn btn-primary">Add</button> -->
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 		</fieldset> -->
<%-- 	</sf:form> --%>
<!-- <div class="col-sm-1"></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <footer class="container-fluid text-center"> -->
<%-- <p><a class="footerlinks" href="contactUs">Contact Us</a><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a class="footerlinks" href="aboutUs">About</a></span></p> --%>
<!-- <p>Created by the Beneficial Bean</p> -->
<!-- </footer> -->

<!-- ======= -->
<!-- >>>>>>> .r409 -->
</body>
</html>