<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>Requestors</h1>
		<hr />
		<table class="table table-hover table-dark" id="tableUser">
			<thead>
				<tr>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Username</td>
					<td>Team Name</td>
					<td>Action</td>
				</tr>
			</thead>
			<tbody id="tBodyUsers">
				<c:forEach items="${usersData}" var="user">
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.username}</td>
						<td>${user.teamName}</td>
						<td><button id="${user.id}" class="btn btn-primary approve">Approve</button>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>