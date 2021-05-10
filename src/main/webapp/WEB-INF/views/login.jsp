<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login: IPL - Masterminds</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<%-- <jsp:include page="modal.jsp" /> --%>
	
	<div class="container pt-3">
		<h1 id="h1user"></h1>
		<hr />
		<form id="loginForm">
			<div class="form-group">
				<label class="font-weight-bold" for="txtLoginUsername">Username</label>
				<input type="text" id="txtLoginUsername" name="username" class="form-control font-weight-bold" placeholder="Enter username" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="loginPassword">Password</label>
				<input type="password" id="loginPassword" name="password" class="form-control font-weight-bold" placeholder="Enter password" />
			</div>
			<button id="btnLogin" class="btn btn-primary">Login</button>
		</form>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>