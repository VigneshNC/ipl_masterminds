<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Players: IPL - Masterminds</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
			
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1>List of Players</h1>
		<hr />
		<button class="float-right btn btn-primary" id="btnAddPlayer">Add Player</button>
		<br /><br />
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<td>Player Name</td>
					<td>Role</td>
					<td>Nationality</td>
					<td>IPL Team</td>
					<td>Owner</td>
					<td>Bid</td>
					<td>Points</td>
					<td>Action</td>
				</tr>
			</thead>
			<tbody id="tBodyPlayers">
				<c:forEach items="${playersData}" var="playerData">
					<tr>
						<td>${playerData.playerName}</td>
						<td>${playerData.role}</td>
						<td>${playerData.nationality}</td>
						<td>${playerData.iplTeam}</td>
						<td>${playerData.ownedBy}</td>
						<td>${playerData.bid}</td>
						<td>${playerData.points}</td>
						<td>
							<button id="btnEditPlayer" onclick="editPlayer(${playerData.id})" class="btn btn-primary">Edit</button>
							<button id="${playerData.id}" class="btn btn-danger deletePlayer">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		function editPlayer(playerId) {
			window.location = "/ipl/player/edit/" + playerId;
		}
	</script>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>