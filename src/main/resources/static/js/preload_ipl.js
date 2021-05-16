$(document).ready(function() {
	$("body").css("background-color", "#3D4D61").css("font-family", "Ubuntu, 'times new roman', times, roman, serif");
	$(".container").addClass("rounded").css("background-color", "#F3CA20").css("padding-bottom", "15px");
	
	$('.nav-item').removeClass('active');
	$("#btnBackUser").hide();
	
	if (window.location.pathname.includes("ipl/register")) {
		$("#h1user").html("Registeration Form");
		$("#btnSaveOrUpdateUser").html("Save");
		$("#navItemRegister").addClass('active');
		$("#navItemUsers").hide();
	} else if (window.location.pathname.includes("ipl/users")) {
		$("#h1user").html("Registeration Form");
		$("#navItemUsers").addClass('active');
		$("#navItemUsers").show();
	} else if (window.location.pathname.includes("ipl/rules")) {
		$("#h1user").html("Registeration Form");
		$("#navItemRules").addClass('active');
		$("#navItemUsers").show();
	} else if (window.location.pathname.includes("user/view")) {
		$("#navItemUsers").show();
		document.cookie = "userOrAdmin=" + $("#userOrAdmin").val() + "; path=/";
		document.cookie = "userId=" + $("#userId").val() + "; path=/";
		$("#navItemProfile").addClass('active');
		$("#participantId").val($("#userId").val());
	} else if (window.location.pathname.includes("user/edit")) {
		$("#h1user").html("Edit Participant");
		$("#btnSaveOrUpdateUser").html("Update");
		$("#navItemUsers").show();
		$("#btnBackUser").show();
	} else if (window.location.pathname.includes("ipl/login")) {
		$("#h1user").html("Login Form");
		$("#txtLoginUsername").focus();
	} else if (window.location.pathname.includes("ipl/logout")) {
		document.cookie = "userOrAdmin=; path=/";
		document.cookie = "userId=; path=/";
	} else if (window.location.pathname.includes("player/edit")) {
		$("#btnSaveOrUpdatePlayer").html("Update");
	} else if (window.location.pathname.includes("ipl/playersList")) {
		$("#playerTable").DataTable();
		
		if (document.getElementById("tBodyPlayers").children[0].innerText == "No data available in table") {
			$("#btnDelAllPlayers").hide();
		}
		
		$(".container").css("background-color", "#FFFFFF");
		$("#playersHead").text("List of Players");
		$(".owner, #divImport").show();
		$("#btnAddPlayer").show();
		$("#h2TotalPoints").text("").hide();
		$("#h2TotalPlayers").text("").hide();
		$("#btnBackPointsTable").hide();
		$("#navItemPlayers").addClass('active');
	} else if (window.location.pathname.includes("player")) {
		/*rolesToUI = "";
		let roles = "${roles}";
		console.log("roles: " + roles);
		for(var roleIndex = 0; roleIndex < roles.length; roleIndex++) {
		    roles += "<option value='" + roles[i] + "'>" + roles[i] + "</option>";
		}
		$('select[name="inptProduct"]').append(rolesToUI);*/
		$("#btnSaveOrUpdatePlayer").html("Save");
	} else if (window.location.pathname.includes("ipl/pointsTable")) {
		$("#navItemPointsTable").addClass('active');
	} else if (window.location.pathname.includes("ipl/requestors")) {
		$("#navItemRequestors").addClass('active');
	} else if (window.location.pathname.includes("ipl/participantPoints")) {
		$("#playerTable").DataTable();
		$(".container").css("background-color", "#FFFFFF");
		$("#btnDelAllPlayers").hide();
		$("#playersHead").text(window.location.pathname.split("/")[3].replaceAll("%20", " ") + " - Points");
		$(".owner, #divImport, #btnAddPlayer").hide();
		$("#btnBackPointsTable").show();
		$("#h2TotalPoints").text("Total Points: " + $("#totalPoints").val()).show();
		$("#h2TotalPlayers").text("Total Players: " + $("#totalPlayers").val()).show();
	} else if (window.location.pathname.includes("ipl/bid")) {
		$("#navItemBid").addClass('active');
	}
	
	if (document.cookie.includes("userOrAdmin=admin")) {
		$("#navItemLogout, #navItemUsers, #navItemPlayers, #navItemRequestors, #navItemPointsTable, #line, .hideField, #btnStartBid").show();
		if (window.location.pathname.includes("ipl/participantPoints")) {
			$("#divImport, #btnDelAllPlayers, .spanEditPlayer").hide();
		}
		$("#navItemLogin, #navItemRegister").hide();
		$("#tBodyParticipants, #tBodyPlayers tr").css("cursor", "pointer");
	} else if (document.cookie.includes("userOrAdmin=user")) {
		$("#navItemLogout, #navItemRules, #navItemUsers, #navItemPlayers, #navItemPointsTable").show();
		$("#navItemLogin, #navItemRegister, #navItemRequestors, #divImport, #btnDelAllPlayers, .spanEditPlayer, #line, .hideField, #btnStartBid").hide();
		$("#tBodyParticipants tr").css("cursor", "pointer");
	} else {
		if (window.location.pathname != "/" && window.location.pathname != "/ipl" && window.location.pathname != "/ipl/logout"
			&& window.location.pathname != "/ipl/login" && window.location.pathname != "/ipl/register" && window.location.pathname != "/ipl/rules") {
			location.href = "/";
		}
		$("#navItemLogin").show();
		$("#navItemLogout, #navItemUsers, #navItemPlayers, #navItemRequestors, #navItemPointsTable, .spanEditPlayer, #line, .hideField, #navItemProfile, #navItemBid").hide();
	}
});