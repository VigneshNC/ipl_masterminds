<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Save Player: IPL - Masterminds</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container">
		<h1>Add Player</h1>
		<hr />
		<form id="registerForm">
			<div class="form-group">
				<input type="hidden" id="hTxtId" name="id" value="${userData.id}" class="form-control font-weight-bold" />
				<label class="font-weight-bold" for="txtFirstName">First Name</label>
				<input type="text" id="txtFirstName" name="firstName" value="${userData.firstName}" class="form-control font-weight-bold" placeholder="Enter your first name" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtFirstName">Last Name</label>
				<input type="text" id="txtLastName" name="lastName" value="${userData.lastName}" class="form-control font-weight-bold" placeholder="Enter your last name" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtFirstName">Username</label>
				<input type="text" id="txtUsername" name="username" value="${userData.username}" class="form-control font-weight-bold" placeholder="Enter username" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtFirstName">Password</label>
				<input type="password" id="password" name="password" value="${userData.password}" class="form-control font-weight-bold" placeholder="Enter password" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtTeamName">Team Name</label>
				<input type="text" id="teamName" name="teamName" value="${userData.teamName}" class="form-control font-weight-bold" placeholder="Enter password" />
			</div>
			
			<button id="btnSaveOrUpdatePlayer" class="btn btn-primary"></button>
		</form>
		<br>
		<button id="btnBackPlayer" class="btn btn-primary">Back</button>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>