<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Save Player: IPL - Masterminds</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container">
		<h1>Add Player</h1>
		<hr />
		<form id="playerForm">
			<div class="form-group">
				<input type="hidden" id="hTxtId" name="id" value="${playerData.id}" class="form-control font-weight-bold" />
				<label class="font-weight-bold" for="txtPlayerName">Player Name</label>
				<input type="text" id="txtPlayerName" name="playerName" value="${playerData.playerName}" class="form-control font-weight-bold" placeholder="Enter player name" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtRole">Role</label>
				<input type="text" id="txtRole" name="role" value="${playerData.role}" class="form-control font-weight-bold" placeholder="Enter role" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtNationality">Nationality</label>
				<input type="text" id="txtNationality" name="nationality" value="${playerData.nationality}" class="form-control font-weight-bold" placeholder="Enter nationality" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtIplTeam">IPL Team</label>
				<input type="text" id="txtIplTeam" name="iplTeam" value="${playerData.iplTeam}" class="form-control font-weight-bold" placeholder="Enter IPL Team name" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="txtOwnedBy">Owner Name</label>
				<input type="text" id="txtOwnedBy" name="ownedBy" value="${playerData.ownedBy}" class="form-control font-weight-bold" placeholder="Enter owner name" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="bid">Bid</label>
				<div class="form-check-inline">
					<label class="form-check-label" for="rdBidYes">
						<input type="radio" class="form-check-input" id="rdBidYes" name="bid" value="Yes">Yes
					</label>
				</div>
				<div class="form-check-inline">
					<label class="form-check-label" for="rdBidNo">
						<input type="radio" class="form-check-input" id="rdBidNo" name="bid" value="No">No
					</label>
				</div>
				<%-- <input type="text" id="bid" name="bid" value="${playerData.bid}" class="form-control font-weight-bold" placeholder="Enter bid as yes or no" /> --%>
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="points">Points</label>
				<input type="number" id="points" name="points" value="${playerData.points}" class="form-control font-weight-bold" placeholder="Enter points" />
			</div>
			<button id="btnSaveOrUpdatePlayer" class="btn btn-primary"></button>
		</form>
		<br>
		<button id="btnBackPlayer" class="btn btn-primary">Back</button>
	</div>
	<script>
		if ("${playerData}" != undefined) {
			if ("${playerData.bid}" == "Yes") {
				$("#rdBidYes").prop("checked", true);
			} else if ("${playerData.bid}" == "No") {
				$("#rdBidNo").prop("checked", true);
			}
		}
	</script>
	<script type="text/javascript" src="/js/ipl.js"></script>
</body>
</html>