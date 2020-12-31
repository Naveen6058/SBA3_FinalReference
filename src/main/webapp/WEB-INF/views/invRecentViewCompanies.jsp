<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	  margin-left: 10px;
	  height: 50px;
	}
	thead {
	  background: DodgerBlue;
	  color: #fff;
	}
	
	#footer {
		width:80%;
		position: absolute;
		right:9%;
	}
	#primaryNavigators{
		margin-left:50px;
		color:white;
		position:relative;
	}
	#content{
		text-align: center;	
		margin-top: 50px;
	}
	span{
		margin: auto;
	}
</style>
<title>Recent Viewed Companies</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-dark bg-primary">
			<div><a href="${pageContext.request.contextPath}/user/home" id="primaryNavigators">Home</a></div>
			<span class="text-light font-weight-bold" >Recent Viewed Companies</span>
			<div><a href="${pageContext.request.contextPath}/logout" id="primaryNavigators">Logout</a></div>
		</nav>
	</div>
	
	<div id=content>
		<table id=footer>
			<thead>
				<tr>
					<td colspan="4" style="color: white;font-size:20px;text-align:center"><b>Recent Companies Viewed</b></td>
				<tr>
					<th scope="col" colspan="1">Company ID</th>
					<th scope="col" colspan="1">Company Name</th>
					<th scope="col" colspan="1">Price</th>
					<th>View</th>
				</tr>
			</thead>
			<tbody>
				<core:choose>
				    <core:when test="${empty recentViewCompanies}">
				   		<td>NA</td>
						<td>NA</td>
						<td>NA</td>
						<td>NA</td>
				    </core:when>
				<core:otherwise>
					<core:forEach var="company" items="${recentViewCompanies}">
						<tr>
							<td>${company.companyCode}</td>
							<td>${company.companyTitle}</td>
							<td>${company.sharePrice}</td>
							<td>
								<a href ="${pageContext.request.contextPath}/user/company/${company.companyTitle}">View Company</a>
							</td>
						</tr>
					</core:forEach>
				</core:otherwise>
				</core:choose>
			</tbody>
		</table>
	</div>

</body>
</html>