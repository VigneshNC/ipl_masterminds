<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Bid: IPL - Masterminds!</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>Bid</h1>
		<hr />
		<div class="row">
			<div class="col-md-6">
				<h2>Bid Player</h2>
				<table class="table table-hover table-dark">
					<tbody id="tBodyBidPlayer">
						<tr id="${bidPlayer.id}">
							<td>${bidPlayer.playerName}</td>
							<td>${bidPlayer.nationality}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<h2>Participants</h2>
				<table class="table table-hover table-dark">
					<thead>
						<tr>
							<th>Username</th>
							<th>Total Players</th>
							<th>Remaining Price</th>
							<th>Online</th>
						</tr>
					</thead>
					<tbody id="tBodyOnlineParticipants">
						<c:forEach items="${onlineUsers}" var="onlineUser">
							<tr id="${onlineUser.userId}">
								<td>${onlineUser.username}</td>
								<td>${onlineUser.totalPlayers}</td>
								<td>${onlineUser.remainingPrice}</td>
								<td>Green</td>
							</tr>
						</c:forEach>
						<c:forEach items="${offlineUsers}" var="offlineUser">
							<tr id="${offlineUser.userId}">
								<td>${offlineUser.username}</td>
								<td>${offlineUser.totalPlayers}</td>
								<td>${offlineUser.remainingPrice}</td>
								<td>Red</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>