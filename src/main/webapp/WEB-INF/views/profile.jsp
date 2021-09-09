<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Participant Profile: IPL - Masterminds</title>
	<link rel="stylesheet" href="/css/bootstrap_4_5_2.css">
	<script src="/js/jquery_3_5_1.js"></script>
	<script src="/js/bootstrap_4_5_2.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
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
					<td><button id="btnEditUser" class="btn btn-warning">Edit</button>
				</tr>
			</tbody>
		</table>
		<input type="hidden" id="userOrAdmin" value="${userOrAdmin}" class="form-control" />
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>