<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
					<li> <a href="studentAcademy">Academy</a></li>
						<li class="active"><a href="studentbalance">Balance</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="studentIndex">
							${user.username }</a></li>
					<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>
							Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div>
		<div>
			<legend>Add Funds</legend>
			<h2 align="center">Balance</h2>
			<h3 align="center">£ <fmt:formatNumber type="number" 
            maxFractionDigits="2" value="${user.balance}" /></h3>
			
			
			<br>
			<form action="deposit" class="form-horizontal" method="post">
				<fieldset>
					<div class="form-group">
						<label class="col-md-4 control-label" for="deposit">Amount</label>
						<div class="col-md-4">
							<input id="fn" name="deposit" type="text"
								class="form-control input-md" required="">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="cardnumber">Card
							Number</label>
						<div class="col-md-4">
							<input id="ln" name="cardnumber" type="text"
								placeholder="Card Number" class="form-control input-md"
								required="" pattern="[0-9]{16}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="cvv">CVV</label>
						<div class="col-md-4">
							<input id="email" name="cvv" type="password" placeholder="CVV"
								class="form-control input-md" required="" pattern="[0-9]{3}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="expire">Expire</label>
						<div class="col-md-4">
							<input id="email" name="expire" type="text" placeholder="MM/YY"
								class="form-control input-md" required="">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="address">Billing
							Address</label>
						<div class="col-md-4">
							<input id="email" name="address" type="text"
								placeholder="Address" class="form-control input-md" required="">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="submit"></label>
						<div class="col-md-4">
							<input type="hidden" value="${user.username }" name="username" />
							<button id="submit" name="submit" class="btn btn-primary">SUBMIT</button>
						</div>
					</div>

				</fieldset>
			</form>

		</div>
		<footer class="container-fluid text-center">
<p><a class="footerlinks" href="contactUs">Contact Us</a><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a class="footerlinks" href="aboutUs">About</a></span></p>
<p>Created by the Beneficial Bean</p>
</footer>
</body>
</html>