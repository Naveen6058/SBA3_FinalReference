<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri= "http://www.springframework.org/tags/form" prefix="spring"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	  border-collapse: collapse;
	  padding: 10px;
	}
	thead {
	  background: DodgerBlue;
	  color: #fff;
	}
	
	.walletDetails{
		position:relative;
		margin:10px;
		left:50px;
		width:800px;
	}
	
	.container{
		position:absolute;
		left:1000px;
		top:100px;
	}
	
	#primaryNavigators{
		margin-left:50px;
		color:white;
		position:relative;
	}
	.walletDetails{
		margin:auto;
		margin-top: 5%;
		width: 80%;
		margin-left:5%;
	}
	span{
		margin: auto;
	}
	#buttons{
		margin:auto;
		width: 35%;
		padding: 10px;
	}
	#button{
		background-color:DodgerBlue;
		margin-left :10px;
		color:white;
	}
	.walletDetails{
		border: 1px solid black;
	  	border-collapse: collapse;
	  	text-align:center;
	}
</style>
<title>Investor Wallet</title>
</head>
<body>
	<div>
		<div>
			<nav class="navbar navbar-dark bg-primary">
				<div><a href="${pageContext.request.contextPath}/user/home" id="primaryNavigators">Home</a></div>
				<span class="text-light font-weight-bold" >My Wallet</span>
				<div><a href="${pageContext.request.contextPath}/logout" id="primaryNavigators">Logout</a></div>
			</nav>
		</div>
		
	    <div>
	      <table class="walletDetails">
			<thead class="walletDetails">
				<tr>
				<td colspan="5" style="color: white;font-size:20px;text-align:center"><b>My Wallet details</b></td>
				</tr>
				<tr class="walletDetails">
					<th scope="col" colspan="1">Wallet ID</th>
					<th scope="col" colspan="1">Investor ID</th>
					<th scope="col" colspan="1">Investor Name</th>
					<th scope="col" colspan="1">Wallet Balance</th>
				</tr>
			</thead>
			<tbody class="walletDetails">
				<tr>
					<td>${walletDto.walletId}</td>
					<td>${walletDto.investorID}</td>
					<td>${walletDto.fullName}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${walletDto.balance}"/></td>
				</tr>
			</tbody>
		</table>
	    </div>
	  </div>
	  <hr/>
	  <div id=buttons>
		<table>
			<tr>
				<td><spring:form action="${pageContext.request.contextPath}/user/returnAddmoney" method="POST">
					<button type=Submit name=Submit id=button>Add Money</button>
				</spring:form></td>
			
				<td><spring:form action="${pageContext.request.contextPath}/user/returnWithdrawmoney" method="POST">
					<button type=Submit name=Submit id=button>Withdraw Money</button>
				</spring:form></td>
				
				<td><spring:form action="${pageContext.request.contextPath}/user/walletTransactionSummary" method="POST">
					<button type=Submit name=Submit id=button>Wallet Transactions</button>
				</spring:form></td>
			</tr>
		</table>
	</div>
		
</body>
</html>