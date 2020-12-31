<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri= "http://www.springframework.org/tags/form" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Back Office User Creation</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
	span{
		margin: auto;
	}
	#center{
		margin: auto;
		width: 22%;
		margin-top: 5%;
		border: 3px solid green;
		padding: 10px;
	}
	.error{
		color:red;
	}
	
	#output{
		margin: auto;
		width: 22%;
		margin-top: 5%;
		border: 3px solid green;
		padding: 10px;
		text-align: center;
	}
	h4{
		color:DodgerBlue;
	}
</style>
</head>
<body>
	<div>
		<nav class="navbar navbar-dark bg-primary">
			<div><a href="${pageContext.request.contextPath}/superuser/home" class="text-light font-weight-bold">Home</a></div>
			<span class="text-light font-weight-bold">Create BackOffice User</span>
			<div><a href="${pageContext.request.contextPath}/superuser/logout" class="text-light font-weight-bold">Logout</a></div>
		</nav>
	</div>
	<div id = output>
		<h4>Back-office User Created Successfully</h4>
		<hr/>
		<h5>FIRST NAME : ${backofficeuser.firstName}</h5>
		<h5>LAST NAME : ${backofficeuser.lastName}</h5>
		<h5>EMAIL: ${backofficeuser.emailId}</h5>
		<h5>PASSWORD: ${backofficeuser.password}</h5>
		<h5>Login ID: ${backofficeuser.loginId}</h5>
	</div>
	
</body>
</html>