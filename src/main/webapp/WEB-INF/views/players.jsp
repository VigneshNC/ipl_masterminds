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
	<style>
		#tableDiv {
			overflow: scroll;
		    height: 100vh;
		}
	</style>
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
		<div id="tableDiv">
			<table class="table table-dark table-hover">
				<thead>
					<tr>
						<th>Player Name</th>
						<th>Role</th>
						<th>Nationality</th>
						<th>IPL Team</th>
						<th>Owner</th>
						<!-- <th>Bid</th> -->
						<th>Total Points</th>
						<th>Match 1</th>
						<th>Match 2</th>
						<th>Match 3</th>
						<th>Match 4</th>
						<th>Match 5</th>
						<th>Match 6</th>
						<th>Match 7</th>
						<th>Match 8</th>
						<th>Match 9</th>
						<th>Match 10</th>
						<th>Match 11</th>
						<th>Match 12</th>
						<th>Match 13</th>
						<th>Match 14</th>
						<th>Match 15</th>
						<th>Match 16</th>
						<th>Match 17</th>
						<th>Match 18</th>
						<th>Action</th>
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
	</div>
	<script>
		function editPlayer(playerId) {
			window.location = "/ipl/player/edit/" + playerId;
		}
	</script>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>