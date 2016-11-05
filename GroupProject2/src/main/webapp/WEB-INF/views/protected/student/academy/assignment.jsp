<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
<script>
function validation(thisform)
{
   with(thisform)
   {
      if(validateFileExtension(file, "valid_msg", "pdf/docx files are only allowed!",
      new Array("pdf","docx")) == false)
      {
         return false;
      }
      if(validateFileSize(file,1048576, "valid_msg", "Document size should be less than 1MB !")==false)
      {
         return false;
      }
   }
}
function validateFileExtension(component,msg_id,msg,extns)
{
   var flag=0;
   with(component)
   {
      var ext=value.substring(value.lastIndexOf('.')+1);
      for(i=0;i<extns.length;i++)
      {
         if(ext==extns[i])
         {
            flag=0;
            break;
         }
         else
         {
            flag=1;
         }
      }
      if(flag!=0)
      {
         document.getElementById(msg_id).innerHTML=msg;
         component.value="";
         component.style.backgroundColor="#eab1b1";
         component.style.border="thin solid #000000";
         component.focus();
         return false;
      }
      else
      {
         return true;
      }
   }
}
function validateFileSize(component,maxSize,msg_id,msg)
{
   if(navigator.appName=="Microsoft Internet Explorer")
   {
      if(component.value)
      {
         var oas=new ActiveXObject("Scripting.FileSystemObject");
         var e=oas.getFile(component.value);
         var size=e.size;
      }
   }
   else
   {
      if(component.files[0]!=undefined)
      {
         size = component.files[0].size;
      }
   }
   if(size!=undefined && size>maxSize)
   {
      document.getElementById(msg_id).innerHTML=msg;
      component.value="";
      component.style.backgroundColor="#eab1b1";
      component.style.border="thin solid #000000";
      component.focus();
      return false;
   }
   else
   {
      return true;
   }
}
</script>

<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
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
				<a class="navbar-brand" href="Hc1">Logo</a>
				<img src="<c:url value= "/resources/BB_Logo.jpg" /> style="width:60px;height:45px;border:0"">
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="studentIndex">Home</a></li>
					<li class="active"><a href="studentAcademy">Academy</a></li>
					<li><a href="studentBalance">Balance</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="studentIndex"> ${user.username }</a></li>
					<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>
							Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div>
	<h2>Upload Assignment</h2>	

	<form class="form-horizontal" method="post" action="upload" enctype="multipart/form-data" onsubmit="return validation(this)">
	<fieldset>
	
	<!-- Form Name -->
	<legend>Upload Assignment</legend>
	
	<!-- File Button --> 
	<div class="form-group">
	  <label class="col-md-4 control-label" for="filebutton">File to Upload</label>
	  <div class="col-md-4">
	    <input id="file" name="file" class="input-file" type="file">
	  </div>
	</div>
	<div align="center" id="valid_msg"></div>
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="submit"></label>
	  <div class="col-md-4">
	  	<input type="hidden" value="${user.username }" name="username"/>
	  	<input type="hidden" value="${id }" name="id"/>
	    <button id="submit" name="submit" class="btn btn-primary">Upload</button>
	  </div>
	</div>
	</fieldset>
	</form>


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