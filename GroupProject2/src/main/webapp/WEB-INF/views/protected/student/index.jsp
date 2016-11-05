<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Beneficial Beans</title>
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
</head>
<body>
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
					<li class="active"><a href="studentIndex">Home</a></li>
					<li><a href="studentAcademy">Academy</a></li>
					<li><a href="studentBalance">Balance</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="studentIndex"> ${user.username }</a></li>
					<li><a href="logout"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
	  <br>
	  <h2 align="center">Account Details</h2>
	  <br>
	  <br>
		<div class="row">
			<div class="col-sm-6" style="background-color:grey ;" align="center">
				<fieldset>
					<legend>Your Details</legend>
					<label for="forename">Firstname:</label>
					<h4>${user.firstname}</h4>

					<label for="lastname">Lastname:</label> 
					<h4>${user.lastname}</h4>
					
					<label for="username">Username:</label> 
					<h4>${user.username}</h4>

					<label for="email">Email:</label> 
					<h4>${user.email}</h4>
					
					<label for="grade">Overall Grade:</label> 
					<h4>${user.mark}</h4>
					
					<div align="center" height: auto">
                        <h4>Balance: <fmt:formatNumber type="number" 
            maxFractionDigits="2" value="${user.balance}" /></h4>
                        <a href="studentBalance"><button type="button" class="btn btn-primary"> Add Funds </button></a>
                        
					<form action="deleteStudentAccount" method="post" align="right">
						<input type="hidden" value="${user.username}" name="username" />
						<input type="submit" class="btn btn-danger" value="Quit"/>
					</form>										
           	 		</div>
					
					
				</fieldset>
			</div>
		<div class="col-sm-6">
			<div id="FeederNinja_53a5531f72894d3fb0d915e273948aff"></div>
		</div>
		<script type="text/javascript">
		(function() {
			var fn = document.createElement('script'); fn.type = 'text/javascript';
			fn.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'www.feederninja.com/api/feed/53a5531f72894d3fb0d915e273948aff?fnurl=' + window.location.href;
			var s = document.getElementsByTagName('head')[0].appendChild(fn);
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