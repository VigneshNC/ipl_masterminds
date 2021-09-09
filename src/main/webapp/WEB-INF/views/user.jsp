<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registeration: IPL - Masterminds</title>
	<link rel="stylesheet" href="/css/bootstrap_4_5_2.css">
	<script src="/js/jquery_3_5_1.js"></script>
	<script src="/js/bootstrap_4_5_2.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script src="/js/sweetalert.js"></script>
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1 id="h1user"></h1>
		<hr />
		<form id="registerForm" class="needs-validation">
			<div class="form-group">
				<input type="hidden" id="hdnUserId" name="id" value="${userData.id}" class="form-control font-weight-bold" />
				<label class="font-weight-bold" for="txtFirstName">First Name</label>
				<input type="text" id="txtFirstName" name="firstName" value="${userData.firstName}" class="form-control font-weight-bold" placeholder="Enter your first name" required />
				<div class="invalid-feedback">
					Please enter first name!
				</div>
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtFirstName">Last Name</label>
				<input type="text" id="txtLastName" name="lastName" value="${userData.lastName}" class="form-control font-weight-bold" placeholder="Enter your last name" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtFirstName">Username</label>
				<input type="text" id="txtUsername" name="username" value="${userData.username}" class="form-control font-weight-bold" placeholder="Enter username" required />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtFirstName">Password</label>
				<input type="password" id="password" name="password" value="${userData.password}" class="form-control font-weight-bold" placeholder="Enter password" required />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtTeamName">Team Name</label>
				<input type="text" id="teamName" name="teamName" value="${userData.teamName}" class="form-control font-weight-bold" placeholder="Enter password" required />
			</div>
			
			<button id="btnSaveOrUpdateUser" class="btn btn-primary" type="submit"></button>
		</form>
		<br>
		<button id="btnBackUser" class="btn btn-primary">Back</button>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>