<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
  <title>Tutor Home</title>
  <meta charset="utf-8">
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
    position: fixed;
    bottom: 0px;
    left:0px;
    right:0px;
    height:70px;
}

.footerlinks{
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

body{
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
						<a class="navbar-brand" href="/">Logo</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
						
							<li><a href="aboutUs">About</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="login"><span
									class="glyphicon glyphicon-log-in"></span> Login</a></li>
							<li><a href="register"><span
									class="glyphicon glyphicon-log-in"></span> Register</a></li>
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
						<a class="navbar-brand" href="/">Logo</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav">
							
							<li><a href="aboutUs">About</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="logout"><span
									class="glyphicon glyphicon-log-in"></span> Logout</a></li>
									
							<c:choose>
							<c:when test="${user.role == 'S' }">
							<li><a href="studentIndex"><span class="glyphicon glyphicon-log-in"></span> Profile</a></li>
							</c:when>
							<c:when test="${user.role == 'T' }">
							<li><a href="tutorHome"><span class="glyphicon glyphicon-log-in"></span> Profile</a></li>
							</c:when>
							<c:otherwise>
							<li><a href="adminIndex"><span class="glyphicon glyphicon-log-in"></span> Profile</a></li>
							
							</c:otherwise>
							</c:choose>

						</ul>
					</div>
				</div>
			</nav>

		</c:otherwise>
	</c:choose>

<div>
	<h2>Upload Your File For This Lesson</h2>	

	<form class="form-horizontal" method="post" action="upload" enctype="multipart/form-data">
	<fieldset>
	
	<!-- Form Name -->
	<legend>Upload Your File For This Lesson</legend>
	
	<!-- File Button --> 
	<div class="form-group">
	  <label class="col-md-4 control-label" for="filebutton">File to Upload</label>
	  <div class="col-md-4">
	    <input id="file" name="file" class="input-file" type="file">
	  </div>
	</div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="submit"></label>
	  <div class="col-md-4">
	  	<input type="hidden" name="tutorUsername" value="${user.username }"/>
	    <button id="submit" name="submit" class="btn btn-primary">Upload</button>
	  </div>
	</div>
	
	</fieldset>
	</form>


	</div>

<footer class="container-fluid text-center">
   <ul class="nav navbar-nav">
        <li class="active"><a href="#">Contact Us</a></li>
        <li><a href="#">About</a></li>
		<center>Created by Beneficial beans.</center>
      </ul>
      <br></br>
</footer>
</body>
</html>