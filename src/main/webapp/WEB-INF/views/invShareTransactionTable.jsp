<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri= "http://www.springframework.org/tags/form" prefix="spring"%>
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
	  margin-left: 10px;
	  height: 50px;
	}
	thead {
	  background: DodgerBlue;
	  color: #fff;
	}
	
	.shareTransaction{
		width:65%;
		margin:auto;
		text-align: center;
	  	border-collapse: collapse;
	}
	#primaryNavigators{
		margin-left:50px;
		color:white;
		position:relative;
	}
	.container{
		position:absolute;
	}
	#content{
		text-align: center;	
	}
	span{
		margin: auto;
	}
</style>
<title>Share Transaction Table</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-dark bg-primary">
			<div><a href="${pageContext.request.contextPath}/user/home" id="primaryNavigators">Home</a></div>
			<span class="text-light font-weight-bold">Share Transaction Table</span>
			<div><a href="${pageContext.request.contextPath}/logout" id="primaryNavigators">Logout</a></div>
		</nav>
	</div>

	<div>
		<br/>
		<table class="shareTransaction">
			<thead>
				<tr>
					<th scope="col" colspan="2">Share Transaction Table</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Transaction ID</td>
					<td>${shareTransactionDto.shareTransactionId}</td>
				</tr>
				<tr>
					<td>Transaction Type</td>
					<td>${shareTransactionDto.transactionType}</td>
				</tr>
				<tr>
					<td>Stock Name</td>
					<td>${shareTransactionDto.stockName}</td>
				</tr>
				<tr>
					<td>Stock Price</td>
					<td>${shareTransactionDto.stockPrice}</td>
				</tr>
				<tr>
					<td>Wallet Id</td>
					<td>${shareTransactionDto.walletId}</td>
				</tr>
				<tr>
					<td>Transaction Share Count</td>
					<td>${shareTransactionDto.transactionShareCount}</td>
				</tr>
				<tr>
					<td>Date Time</td>
					<td>${shareTransactionDto.dateTime}</td>
				</tr>
				<tr>
					<td>Transaction Amount</td>
					<td>${shareTransactionDto.transactionAmount}</td>
				</tr>
				<tr>
					<td>Transaction commission</td>
					<td>${shareTransactionDto.commission}</td>
				</tr>
				<tr>
					<td>Company/Commodity</td>
					<td>${shareTransactionDto.companyCommodity}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>