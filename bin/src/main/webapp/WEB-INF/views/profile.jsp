<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Participant Profile: IPL - Masterminds</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<input type="hidden" id="userId" value="${userData.id}" />
	<div class="container pt-3">
		<table class="table table-hover table-dark" id="tableUser">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Username</th>
					<th>Team Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody id="tBodyUser">
				<tr>
					<td>${userData.firstName}</td>
					<td>${userData.lastName}</td>
					<td>${userData.username}</td>
					<td>${userData.teamName}</td>
					<td><button id="btnEdit" class="btn btn-warning">Edit</button>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>