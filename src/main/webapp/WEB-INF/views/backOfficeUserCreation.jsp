<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri= "http://www.springframework.org/tags/form" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create BackOffice User</title>
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
	<div id=center>
		<spring:form action="${pageContext.request.contextPath}/superuser/confirm" method="POST" modelAttribute="backofficeuser">
			<table>
				<tr>
					<td><spring:label path="firstName">First name:</spring:label> </td>
					<td><spring:input type="text" path="firstName"></spring:input> </td>
				</tr>
				<tr>
					<td></td>
					<td><spring:errors path="firstName" cssClass="error"/></td>
				</tr>
				<tr>
					<td><spring:label path="lastName">Last Name:</spring:label> </td>
					<td><spring:input type="text" path="lastName"></spring:input> </td>
				</tr>
				<tr>
					<td></td>
					<td><spring:errors path="lastName" cssClass="error"/></td>
				</tr>
				<tr>
					<td><spring:label path="emailId">Email ID:</spring:label> </td>
					<td><spring:input type="text" path="emailId"></spring:input> </td>
				</tr>
				<tr>
					<td></td>
					<td><spring:errors path="emailId" cssClass="error"/></td>
				</tr>
				<tr>
					<td><spring:label path="password">Password:</spring:label> </td>
					<td><spring:input type="password" path="password"></spring:input> </td>
				</tr>
				<tr>
					<td></td>
					<td><spring:errors path="password" cssClass="error"/></td>
				</tr>
				<tr>
					<td></td>
					<td><hr/><input type=Submit name=Submit Value=Submit></td>
				</tr>
			</table>
		</spring:form>
	</div>
</body>
</html>