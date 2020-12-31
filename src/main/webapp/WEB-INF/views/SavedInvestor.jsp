<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri= "http://www.springframework.org/tags/form" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Created Investor</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
	span{
		margin: auto;
	}
	
	#output{
		margin: auto;
		width: 22%;
		margin-top: 5%;
		border: 3px solid green;
		padding: 10px;
		text-align: center;
	}	

</style>
</head>
<body>
	<div>
		<nav class="navbar navbar-dark bg-primary">
			<div><a href="${pageContext.request.contextPath}" class="text-light font-weight-bold">Index</a></div>
			<span class="text-light font-weight-bold" >Investor Created Successfully</span>
		</nav>
	</div>
	<hr/>
	<div id = output>
		<h2>Saved Details...</h2>
		<hr/>
		<h5>Investor Id: ${newInvestorOut.investorId}</h5>
		<h5>First Name: ${newInvestorOut.firstName}</h5>
		<h5>Last Name: ${newInvestorOut.lastName}</h5>
		<h5>Mobile Number: ${newInvestorOut.mobileNumber}</h5>
		<h5>Pan Id: ${newInvestorOut.panId}</h5>
		<h5>Gender: ${newInvestorOut.gender}</h5>
		<h5>Email Id: ${newInvestorOut.emailId}</h5>
		<h5>Login Key: ${newInvestorOut.loginKey}</h5>
	</div>
</body>
</html>