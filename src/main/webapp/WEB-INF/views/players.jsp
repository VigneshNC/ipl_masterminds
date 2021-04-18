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
		<div class="form-check-inline float-right"><label><input type="file" name="file" class="form-control" id="fileImportPlayers" /></label>
		<button class="btn btn-primary" id="btnImportPlayers">Import Players</button></div>
		<button class="btn btn-primary" id="btnAddPlayer">Add Player</button>
		<br /><br />
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<td>Player Name</td>
					<td>Role</td>
					<td>Nationality</td>
					<td>IPL Team</td>
					<td>Owner</td>
					<!-- <td>Bid</td> -->
					<td>Total Points</td>
					<td>Match 1</td>
					<td>Match 2</td>
					<td>Match 3</td>
					<td>Match 4</td>
					<td>Match 5</td>
					<td>Match 6</td>
					<td>Match 7</td>
					<td>Match 8</td>
					<td>Match 9</td>
					<td>Match 10</td>
					<td>Match 11</td>
					<td>Match 12</td>
					<td>Match 13</td>
					<td>Match 14</td>
					<td>Match 15</td>
					<td>Match 16</td>
					<td>Match 17</td>
					<td>Match 18</td>
					<td>Action</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${playersData}" var="playerData">
					<tr>
						<td>${playerData.playerName}</td>
						<td>${playerData.role}</td>
						<td>${playerData.nationality}</td>
						<td>${playerData.iplTeam}</td>
						<td>${playerData.owner}</td>
						<%-- <td>${playerData.bid}</td> --%>
						<td>${playerData.points}</td>
						<td>${playerData.match1}</td>
						<td>${playerData.match2}</td>
						<td>${playerData.match3}</td>
						<td>${playerData.match4}</td>
						<td>${playerData.match5}</td>
						<td>${playerData.match6}</td>
						<td>${playerData.match7}</td>
						<td>${playerData.match8}</td>
						<td>${playerData.match9}</td>
						<td>${playerData.match10}</td>
						<td>${playerData.match11}</td>
						<td>${playerData.match12}</td>
						<td>${playerData.match13}</td>
						<td>${playerData.match14}</td>
						<td>${playerData.match15}</td>
						<td>${playerData.match16}</td>
						<td>${playerData.match17}</td>
						<td>${playerData.match18}</td>
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