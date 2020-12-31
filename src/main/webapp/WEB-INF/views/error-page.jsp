<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
	span{
		margin: auto;
	}

</style>
<title>Error Page</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-dark bg-primary">
			<div><a href="${pageContext.request.contextPath}/index" class="text-light font-weight-bold">Index</a></div>
			<span class="text-light font-weight-bold" >Error Page</span>
		</nav>
	</div>
	<div><h4>Error in Processing....</h4></div>
	<div><h4>Kindly login again</h4></div>
		
</body>
</html>