<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri= "http://www.springframework.org/tags/form" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SuperUser Home Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<style>
	body {
		font-family: "Lato", sans-serif;
	}
	
	.sidebar {
		height: 100%;
		width: 0%;
		position: fixed;
		z-index: 1;
		top: 0;
		left: 0;
		background-color: #111;
		overflow-x: hidden;
		transition: 0.5s;
		padding-top: 60px;
	}
	
	.sidebar a {
		text-decoration: none;
		font-size: 20px;
		color: #818181;
		display: block;
		transition: 0.3s;
	}
	
	.sidebar ol {
		color: #818181;
	}
	
	.sidebar ul {
		list-style: square;
		color: #818181;
	}
	
	.sidebar a:hover {
		color: #f1f1f1;
	}
	
	.sidebar .closebtn {
		position: absolute;
		top: 0;
		right: 25px;
		font-size: 36px;
		margin-left: 50px;
	}
	
	.openbtn {
		font-size: 20px;
		cursor: pointer;
		background-color: #111;
		color: white;
		padding: 10px 15px;
		border: none;
	}
	
	.openbtn:hover {
		background-color: #444;
	}
	
	#main {
		transition: margin-left .5s;
	}
	#home{
		padding-left:25px;
		color:white;
		position:relative;
	}

.container{
	margin:50px;
}
span{
	margin:auto;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
	.sidebar {
		padding-top: 15px;
	}
	.sidebar a {
		font-size: 18px;
	}
}
</style>
<body>
	<div id="mySidebar" class="sidebar">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
		<a href="${pageContext.request.contextPath}/superuser/bocreate">Create BackOffice user</a>
	</div>
	<div id="main" >
		<nav class="navbar navbar-dark bg-primary">
			<button class="navbar-toggler" type="button" onclick="openNav()"
				aria-controls="navbarToggleExternalContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div><a href="${pageContext.request.contextPath}/superuser/home" class="text-light font-weight-bold" id="home">Home</a></div>
			<span class="text-light font-weight-bold">SuperUser Dashboard</span>
			<div><a href="${pageContext.request.contextPath}/superuser/logout" class="text-light font-weight-bold">Logout</a></div>
		</nav>
		<div class="container">
			<h4>Welcome to the SuperUser HomePage:</h4>
			<hr>
			<p><a href="${pageContext.request.contextPath}/superuser/bocreate">Create BackOffice user</a></p>
		</div>
	</div>
	<script>
		function openNav() {
			document.getElementById("mySidebar").style.width = "250px";
			document.getElementById("main").style.marginLeft = "250px";
		}

		function closeNav() {
			document.getElementById("mySidebar").style.width = "0";
			document.getElementById("main").style.marginLeft = "0";
		}
	</script>

</body>
</html>