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
	<style>
		.bidPlayer {
			background-color: #FFFFFF;
		}
	</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>Bid</h1>
		<hr />
		<div class="row m-1 p1 bidPlayer rounded">
			<div class="col-md-6">
				<h2 class="text-center">Bid Player</h2>
				<div class="row">
					<div class="col-md-3"><img src="/img/batsman.jpg" width="75" height="75" class="img-rounded" alt="${bidPlayer.playerName}"></div>
					<div class="col-md-9">
						<div>${bidPlayer.playerName}</div>
						<div>${bidPlayer.nationality}</div>
						<div>Base Price is 5 Lakhs</div>
					</div>
				</div>
				<div class="mb-3 fixed-bottom position-absolute pl-3">
					<h3>Bidding Price: <span id="bidPrice">5L</span></h3>
					<div class="row">
						<div class="col-md-4">
							<button id="btnIncrease5L" class="btn btn-primary">+5L</button>
						</div>
						<div class="col-md-4">
							<button id="btnIncrease20L" class="btn btn-primary">+20L</button>
						</div>
						<div class="col-md-4">
							<button id="btnNoBid" class="btn btn-danger">No Bid</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<h2 class="text-center">Participants</h2>
				<table class="table table-hover table-dark" style="border-radius: 1.25rem!important; border-top: hidden;">
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
								<td><span class="badge badge-success">&nbsp;&nbsp;O&nbsp;&nbsp;</span></td>
							</tr>
						</c:forEach>
						<c:forEach items="${offlineUsers}" var="offlineUser">
							<tr id="${offlineUser.userId}">
								<td>${offlineUser.username}</td>
								<td>${offlineUser.totalPlayers}</td>
								<td>${offlineUser.remainingPrice}</td>
								<td><span class="badge badge-danger">&nbsp;&nbsp;O&nbsp;&nbsp;</span></td>
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