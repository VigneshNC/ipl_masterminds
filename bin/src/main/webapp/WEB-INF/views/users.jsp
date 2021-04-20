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
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<jsp:include page="modal.jsp" />
	
	<div class="container pt-3">
		<h1>Participants</h1>
		<hr />
		<table class="table table-hover table-dark" id="tableUser">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Username</th>
					<th>Team Name</th>
					<!-- <th>Action</th> -->
				</tr>
			</thead>
			<tbody id="tBodyUsers">
				<c:forEach items="${usersData}" var="user">
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.username}</td>
						<td>${user.teamName}</td>
						<%-- <td><button id="${user.id}" class="btn btn-danger delete">Delete</button> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>