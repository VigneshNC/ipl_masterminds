<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Save Player: IPL - Masterminds</title>
	<link rel="stylesheet" href="/css/bootstrap_4_5_2.css">
	<script src="/js/jquery_3_5_1.js"></script>
	<script src="/js/bootstrap_4_5_2.js"></script>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin">
	<script type="text/javascript" src="/js/preload_ipl.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container">
		<h1>Add Player</h1>
		<hr />
		<form id="playerForm">
			<div class="form-group">
				<input type="hidden" id="hdnPlayerId" name="id" value="${playerData.id}" class="form-control font-weight-bold" />
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
				<label class="font-weight-bold" for="txtOwner">Owner Name</label>
				<input type="text" id="txtOwner" name="owner" value="${playerData.owner}" class="form-control font-weight-bold" placeholder="Enter owner name" />
			</div>
			<%-- <div class="form-group">
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
				<input type="text" id="bid" name="bid" value="${playerData.bid}" class="form-control font-weight-bold" placeholder="Enter bid as yes or no" />
			</div> --%>
			<div class="form-group">
				<label class="font-weight-bold" for="bid">Price</label>
				<input type="number" id="bid" name="bid" value="${playerData.bid} Cr." class="form-control font-weight-bold" placeholder="Enter price in crores" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="points">Total Points</label>
				<input type="number" id="points" name="points" value="${playerData.points}" class="form-control font-weight-bold" placeholder="Enter points" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match1">Match 1</label>
				<input type="number" id="match1" name="match1" value="${playerData.match1}" class="form-control font-weight-bold" placeholder="Enter points for Match 1" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match2">Match 2</label>
				<input type="number" id="match2" name="match2" value="${playerData.match2}" class="form-control font-weight-bold" placeholder="Enter points for Match 2" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match3">Match 3</label>
				<input type="number" id="match3" name="match3" value="${playerData.match3}" class="form-control font-weight-bold" placeholder="Enter points for Match 3" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match4">Match 4</label>
				<input type="number" id="match4" name="match4" value="${playerData.match4}" class="form-control font-weight-bold" placeholder="Enter points for Match 4" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match5">Match 5</label>
				<input type="number" id="match5" name="match5" value="${playerData.match5}" class="form-control font-weight-bold" placeholder="Enter points for Match 5" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match6">Match 6</label>
				<input type="number" id="match6" name="match6" value="${playerData.match6}" class="form-control font-weight-bold" placeholder="Enter points for Match 6" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match7">Match 7</label>
				<input type="number" id="match7" name="match7" value="${playerData.match7}" class="form-control font-weight-bold" placeholder="Enter points for Match 7" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match8">Match 8</label>
				<input type="number" id="match8" name="match8" value="${playerData.match8}" class="form-control font-weight-bold" placeholder="Enter points for Match 8" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match9">Match 9</label>
				<input type="number" id="match9" name="match9" value="${playerData.match9}" class="form-control font-weight-bold" placeholder="Enter points for Match 9" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match10">Match 10</label>
				<input type="number" id="match10" name="match10" value="${playerData.match10}" class="form-control font-weight-bold" placeholder="Enter points for Match 10" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match11">Match 11</label>
				<input type="number" id="match11" name="match11" value="${playerData.match11}" class="form-control font-weight-bold" placeholder="Enter points for Match 11" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match12">Match 12</label>
				<input type="number" id="match12" name="match12" value="${playerData.match12}" class="form-control font-weight-bold" placeholder="Enter points for Match 12" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match13">Match 13</label>
				<input type="number" id="match13" name="match13" value="${playerData.match13}" class="form-control font-weight-bold" placeholder="Enter points for Match 13" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match14">Match 14</label>
				<input type="number" id="match14" name="match14" value="${playerData.match14}" class="form-control font-weight-bold" placeholder="Enter points for Match 14" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match15">Match 15</label>
				<input type="number" id="match15" name="match15" value="${playerData.match15}" class="form-control font-weight-bold" placeholder="Enter points for Match 15" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match16">Match 16</label>
				<input type="number" id="match16" name="match16" value="${playerData.match16}" class="form-control font-weight-bold" placeholder="Enter points for Match 16" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match17">Match 17</label>
				<input type="number" id="match17" name="match17" value="${playerData.match17}" class="form-control font-weight-bold" placeholder="Enter points for Match 17" />
			</div>
			<div class="form-group">
				<label class="font-weight-bold" for="match18">Match 18</label>
				<input type="number" id="match18" name="match18" value="${playerData.match18}" class="form-control font-weight-bold" placeholder="Enter points for Match 18" />
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