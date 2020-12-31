<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri= "http://www.springframework.org/tags/form" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
	span {
		margin: auto;
	}
	#login{
		margin: auto;
		width: 22%;
		margin-top: 2%;
		border: 3px solid green;
		padding: 10px;
	}
	table{
		text-align: center;
	}
	.error{
		color:red;
	}
	#error{
		margin: auto;
		width: 50%;
		margin-top: 1%;
		color:red;
		padding-left: 50px;
		text-align:center;
	}
</style>
<title>Back Office User Login Page</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-dark bg-primary">
			<div><a href="${pageContext.request.contextPath}/index" class="text-light font-weight-bold">Index</a></div>
			<span class="text-light font-weight-bold" >Back Office User Login Page</span>
		</nav>
	</div>
	<br/>
	<h4 id =error>${Message}</h4>
	<div id=login>
		<spring:form action="${pageContext.request.contextPath}/bovalidate" method="POST" modelAttribute="backofficeuser">
			<table>
				<tr>
					<td><spring:label path="loginId">Login ID</spring:label> </td>
					<td><spring:input type="text" path="loginId"></spring:input> </td>
				</tr>
				<tr>
					<td></td>
					<td><spring:errors path="loginId" cssClass="error" id="errors"/></td>
				</tr>
				<tr>
					<td><spring:label path="password">Password</spring:label> </td>
					<td><spring:input type="password" path="password"></spring:input> </td>
				</tr>
				<tr>
					<td></td>
					<td><spring:errors path="password" cssClass="error" id="errors"/></td>
				</tr>
				<tr>
					<td></td>
					<td><hr/><input type=Submit name=Submit Value=Login></td>
				</tr>
			</table>
		</spring:form>
	</div>
</body>
</html>