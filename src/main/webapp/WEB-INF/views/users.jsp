<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Participants: IPL - Masterminds</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>Participants</h1>
		<hr />
		<table class="table table-hover table-dark" id="tableUser">
			<thead>
				<tr>
					<th class="hideField">First Name</th>
					<th class="hideField">Last Name</th>
					<th>Username</th>
					<th>Team Name</th>
					<th class="hideField">Action</th>
				</tr>
			</thead>
			<tbody id="tBodyUsers">
				<c:forEach items="${usersData}" var="user">
					<tr>
						<td class="hideField">${user.firstName}</td>
						<td class="hideField">${user.lastName}</td>
						<td>${user.username}</td>
						<td>${user.teamName}</td>
						<td class="hideField">
							<button id="${user.id}" class="btn btn-danger delete">Delete</button>
							<button id="${user.id}" class="btn btn-danger reject">Reject</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>