<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Players: IPL - Masterminds</title>
	<link rel="stylesheet" href="/css/bootstrap_4_5_2.css">
	<script src="/js/jquery_3_5_1.js"></script>
	<script src="/js/bootstrap_4_5_2.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script src="/js/sweetalert.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/jquery_data_table.css">
	<script type="text/javascript" charset="utf8" src="/js/jquery_data_table.js"></script>
	<style>
		#tableDiv {
			overflow: scroll;
		    height: 100vh;
		}
	</style>
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container pt-3">
		<h1 id="playersHead"></h1>
		<hr />
		<input type="hidden" id="totalPoints" value="${totalPoints}" />
		<input type="hidden" id="totalPlayers" value="${totalPlayers}" />
		<div class="row">
			<div class="col-md-2">
				<!-- <button class="btn btn-primary" id="btnAddPlayer">Add Player</button> -->
			</div>
			<!-- <div id="divImport" class="col-md-6"> -->
				<div id="divImport" class="col-md-6 row">
					<input type="file" name="file" class="form-control col-md-6" id="fileImportPlayers" />
					<button class="btn btn-primary" id="btnImportPlayers">Import Players</button>
				<!-- </div> -->
			</div>
			<div class="col-md-4">
				<button class="btn btn-danger" id="btnDelAllPlayers">Delete All Players</button>
			</div>
		</div>
		<span class="spanEditPlayer" style="color:red;"><b>Note:</b> Click on any row to edit each player.</span>
		<div class="row">
			<h2 class="col-md-5" id="h2TotalPoints"></h2>
			<h2 class="col-md-5" id="h2TotalPlayers"></h2>
			<button class="btn btn-primary" id="btnBackPointsTable">Back</button>
		</div>
		<hr id="line" />
		<div id="tableDiv">
			<table id="playerTable" class="display compact">
				<thead>
					<tr>
						<th>S.No</th>
						<th>Player Name</th>
						<th>Role</th>
						<th>Nationality</th>
						<th>IPL Team</th>
						<th class="owner">Owner</th>
						<th>Bid</th>
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
						<th class="hideField">Action</th>
					</tr>
				</thead>
				<tbody id="tBodyPlayers">
					<c:forEach items="${playersData}" var="playerData">
						<tr id="${playerData.id}">
							<td>${playerData.playerId}</td>
							<td>${playerData.playerName}</td>
							<td>${playerData.role}</td>
							<td>${playerData.nationality}</td>
							<td>${playerData.iplTeam}</td>
							<td class="owner">${playerData.owner}</td>
							<td>${playerData.bid}</td>
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
								<%-- <button id="btnEditPlayer" onclick="editPlayer(${playerData.id})" class="btn btn-primary">Edit</button> --%>
								<button id="${playerData.id}" class="btn btn-danger deletePlayer hideField">Delete</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		function editPlayer(playerId) {
			window.location.href = "/ipl/player/edit/" + playerId;
		}
	</script>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>