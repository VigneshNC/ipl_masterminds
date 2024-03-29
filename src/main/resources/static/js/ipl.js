$(document).ready(function() {
	// $("body").css("background-color", "#3D4D61").css("font-family", "Ubuntu, 'times new roman', times, roman, serif");
	// $(".container").addClass("rounded").css("background-color", "#F3CA20").css("padding-bottom", "15px");
	
	$("#btnSaveOrUpdateUser").on("click", function(e) {
		var registerUserData = $("#registerForm").serializeObject();
		$.ajax({
			type: "post",
			url: "/ipl/save",
			contentType: "application/json",
			data: JSON.stringify(registerUserData),
			success: function(userData) {
				if (userData) {
					if (userData.status && userData.status == "duplicate") {
						Swal.fire({
							icon: "error",
							text: userData.reason + " is already exists!",
							confirmButtonText: "Okay"
						}).then((result) => {
							if (result.isConfirmed) {
								window.location.href = "/ipl/register";
							}
						});
					} else {
						Swal.fire({
							icon: "success",
							text: "Your registration is submitted. Please wait for the approval!",
							confirmButtonText: "Okay"
						}).then((result) => {
							if (result.isConfirmed) {
								window.location.href = "/ipl/register";
							}
						});
					}
				} else {
					Swal.fire({
						icon: "error",
						text: "Please enter in the required fields!"
					});
				}
				console.log("Result: " + userData);
			},
			error: function(error) {
				console.log("Error: " + error);
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Something went wrong!"
				});
			}
		});
		e.preventDefault();
	});

	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	
	// $('.nav-item').removeClass('active');
	// $("#btnBackUser").hide();
	/*var rolesToUI = "";*/
	
	/*if (window.location.pathname.includes("ipl/register")) {
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
		$("#navItemLogout, #navItemUsers, #navItemPlayers, #navItemRequestors, #navItemPointsTable, #divImport, #btnDelAllPlayers, .spanEditPlayer, #line, .hideField").show();
		$("#navItemLogin, #navItemRegister").hide();
		$("#tBodyParticipants, #tBodyPlayers tr").css("cursor", "pointer");
	} else if (document.cookie.includes("userOrAdmin=user")) {
		$("#navItemLogout, #navItemRules, #navItemUsers, #navItemPlayers, #navItemPointsTable").show();
		$("#navItemLogin, #navItemRegister, #navItemRequestors, #divImport, #btnDelAllPlayers, .spanEditPlayer, #line, .hideField").hide();
	} else {
		if (window.location.pathname != "/" && window.location.pathname != "/ipl" && window.location.pathname != "/ipl/logout") {
			location.href = "/";
		}
		$("#navItemLogin").show();
		$("#navItemLogout, #navItemUsers, #navItemPlayers, #navItemRequestors, #navItemPointsTable, .spanEditPlayer, #line, .hideField, #navItemProfile, #navItemBid").hide();
	}*/
	
	$(".delete").on("click", function() {
		Swal.fire({
			icon: "question",
			title: "Are you sure to delete the participant?",
			showDenyButton: true,
			confirmButtonText: "Delete",
			denyButtonText: "Don't delete"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					type: "delete",
					url: "/ipl/delete/" + $(this).prop("id"),
					success: function(result) {
						if (result == "success") {
							Swal.fire({
								icon: "success",
								text: "Participant is deleted!",
								confirmButtonText: "Okay"
							}).then((result) => {
								if (result.isConfirmed) {
									window.location.href = "/ipl/users";
								}
							});
						}
					},
					error: function(error) {
						console.log("Error: " + error);
						Swal.fire({
							icon: "error",
							title: "Oops...",
							text: "Something went wrong!"
						});
					}
				});
			} else if (result.isDenied) {
				Swal.fire({
					icon: "info",
					text: "Not deleted!"
				});
			}
		});
	});
	
	$("#btnLogin").on("click", function(e) {
		let username = $("#txtLoginUsername").val();
		let password = $("#loginPassword").val();
		$.ajax({
			type: "get",
			url: "/ipl/authenticate?username=" + username + "&password=" + password,
			success: function(id) {
				if (id == "0") {
					Swal.fire({
						icon: "error",
						title: "Username or password is wrong!",
						footer: '<a href="/ipl/register">If not registered, please click here to register!</a>'
					});
				} else if (id == "1") {
					Swal.fire({
						icon: "info",
						title: "Your account is in pending, please wait for the approval!",
					});
				} else {
					window.location.href = "/ipl/user/view/" + id;
				}
			},
			error: function(error) {
				console.log("Error: " + error);
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Something went wrong!"
				});
			}
		});
		e.preventDefault();
	});
	
	$("#btnEditUser").on("click", function() {
		window.location.href="/ipl/user/edit/" + $("#userId").val();
	});
	
	$("#btnBackUser").on("click", function() {
		window.location.href = window.location.pathname.replace("edit", "view");
	});
	
	$("#btnAddPlayer").on("click", function() {
		window.location.href = "/ipl/player";
	});
	
	$("#btnSaveOrUpdatePlayer").on("click", function() {
		var playerData = $("#playerForm").serializeObject();
		$.ajax({
			type: "post",
			url: "/ipl/savePlayer",
			contentType: "application/json",
			data: JSON.stringify(playerData),
			success: function(playerInfo) {
				console.log("Result: " + playerInfo);
				let userId = JSON.parse(playerInfo).id;
				window.location.href = "/ipl/playersList";
			},
			error: function(error) {
				console.log("Error: " + error);
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Something went wrong!"
				});
			}
		});
	});
	
	$("#btnBackPlayer").on("click", function() {
		window.location.href = "/ipl/playersList";
	});
	
	$(".deletePlayer").on("click", function() {
		Swal.fire({
			icon: "question",
			title: "Are you sure to delete the player?",
			showDenyButton: true,
			confirmButtonText: "Delete",
			denyButtonText: "Don't delete"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					type: "delete",
					url: "/ipl/player/delete/" + $(this).prop("id"),
					success: function(result) {
						if (result == "success") {
							Swal.fire({
								icon: "success",
								text: "Player is deleted!",
								confirmButtonText: "Okay"
							}).then((result) => {
								if (result.isConfirmed) {
									window.location.href = "/ipl/playersList";
								}
							});
						}
					},
					error: function(error) {
						console.log("Error: " + error);
						Swal.fire({
							icon: "error",
							title: "Oops...",
							text: "Something went wrong!"
						});
					}
				});
			} else if (result.isDenied) {
				Swal.fire({
					icon: "info",
					text: "Not deleted!"
				});
			}
		});
	});
	
	$(".approve").on("click", function() {
		Swal.fire({
			icon: "question",
			title: "Are you sure to approve the requestor?",
			showDenyButton: true,
			confirmButtonText: "Approve",
			denyButtonText: "Don't approve"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					type: "post",
					url: "/ipl/user/approve/" + $(this).prop("id"),
					success: function(result) {
						if (result == "success") {
							Swal.fire({
								icon: "success",
								text: "Player is approved!",
								confirmButtonText: "Okay"
							}).then((result) => {
								if (result.isConfirmed) {
									window.location.href = "/ipl/requestors";
								}
							});
						}
					},
					error: function(error) {
						console.log("Error: " + error);
						Swal.fire({
							icon: "error",
							title: "Oops...",
							text: "Something went wrong!"
						});
					}
				});
			} else if (result.isDenied) {
				Swal.fire({
					icon: "info",
					text: "Not approved!"
				});
			}
		});
	});
	
	$(".reject").on("click", function() {
		Swal.fire({
			icon: "question",
			title: "Are you sure to reject the participant?",
			showDenyButton: true,
			confirmButtonText: "Reject",
			denyButtonText: "Don't reject"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					type: "post",
					url: "/ipl/user/reject/" + $(this).prop("id"),
					success: function(result) {
						if (result == "success") {
							Swal.fire({
								icon: "success",
								text: "Player is rejected!"
							}).then((result) => {
								if (result.isConfirmed) {
									window.location.href = "/ipl/users";
								}
							});
						}
					},
					error: function(error) {
						console.log("Error: " + error);
						Swal.fire({
							icon: "error",
							title: "Oops...",
							text: "Something went wrong!"
						});
					}
				});
			} else if (result.isDenied) {
				Swal.fire({
					icon: "info",
					text: "Not rejected!"
				});
			}
		});
	});
	
	$("#btnImportPlayers").on("click", function() {
		if ($("#fileImportPlayers")[0].files.length > 0) {
			let formData = new FormData();
			formData.append('file', $("#fileImportPlayers")[0].files[0]);
			$.ajax({
				type: "POST",
				url: "/ipl/importplayers",
				data: formData,
				contentType: false,
				processData: false,
				enctype: 'multipart/form-data',
				success: function(result) {
					Swal.fire({
						icon: "success",
						text: "Players are imported successfully!"
					}).then((result) => {
						if (result.isConfirmed) {
							window.location.href = "/ipl/playersList";
						}
					});
				},
				error: function(err) {
					Swal.fire({
						icon: "error",
						text: "Players are not imported!"
					});
				}
			});
		} else {
			Swal.fire({
				icon: "error",
				text: "Please choose file to import!"
			});
		}
	});
	
	$("#tBodyParticipants tr").on("click", function() {
		// if (document.cookie.includes("userOrAdmin=admin")) {
			var columns = $(this).find("td");
			window.location.href = "/ipl/participantPoints/" + columns[0].innerHTML;
		// }
	});
	
	$("#btnBackPointsTable").on("click", function() {
		window.location.href = "/ipl/pointsTable";
	});
	
	$("#tBodyPlayers tr").on("click", function() {
		if (document.cookie.includes("userOrAdmin=admin")) {
			window.location.href = "/ipl/player/edit/" + $(this).prop("id");
		}
	});
	
	$("#btnDelAllPlayers").on("click", function() {
		Swal.fire({
			icon: "question",
			title: "Are you sure to delete all the players?",
			showDenyButton: true,
			confirmButtonText: "Delete",
			denyButtonText: "Don't delete"
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					type: "delete",
					url: "/ipl/deleteAllPlayers/",
					success: function(result) {
						if (result == "success") {
							Swal.fire({
								icon: "success",
								text: "All players are deleted!",
								confirmButtonText: "Okay"
							}).then((result) => {
								if (result.isConfirmed) {
									window.location.href = "/ipl/playersList";
								}
							});
						} else {
							Swal.fire({
								icon: "error",
								title: "Oops...",
								text: "Something went wrong!"
							});
						}
					},
					error: function(error) {
						console.log("Error: " + error);
						Swal.fire({
							icon: "error",
							title: "Oops...",
							text: "Something went wrong!"
						});
					}
				});
			} else if (result.isDenied) {
				Swal.fire({
					icon: "info",
					text: "Not deleted!"
				});
			}
		});
	});
	
	$("#navItemProfile").on("click", function() {
		if (document.cookie.includes("userId=")) {
			let cookie = document.cookie.split(";");
			for(var cookieIndex = 0; cookieIndex < cookie.length; cookieIndex++) {
				if (cookie[cookieIndex].includes("userId")) {
					let userId = cookie[cookieIndex].split("=")[1];
					window.location.href = "/ipl/user/view/" + userId;
				}
			}
		}
	});
	
	$("#btnIncrease5L, #btnIncrease20L, #btnNoBid").hide();
	
	/*if ($("#online").val() == "true") {
		$("#btnStartBid").show();
	} else {
		$("#btnStartBid").hide();
	}*/
	
	$("#btnIncrease5L").on("click", function() {
		var bidPrice = $("#bidPrice").text();
		if (bidPrice.includes("L")) {
			bidPrice = bidPrice.substring(0, bidPrice.length-1);
			bidPrice = Number(bidPrice) + 5;
			if (bidPrice < 100) {
				$("#bidPrice").text(bidPrice + "L");
			} else {
				$("#btnIncrease5L").hide();
				$("#btnIncrease20L").show();
				bidPrice = bidPrice/100;
				$("#bidPrice").text(bidPrice + "C");
			}
			let bidUserMap = {};
			bidUserMap["userId"] = $("#currentUserId").val();
			bidUserMap["nextUserId"] = $("#nextUserId").val();
			bidUserMap["playerId"] = $("#bidPlayerId").val();
			bidUserMap["price"] = $("#bidPrice").text();
			$("#bidUserMap").val(JSON.stringify(bidUserMap));
		}
		$("#btnIncrease5L").prop("disabled", true);
	});
	
	$("#btnIncrease20L").on("click", function() {
		var bidPrice = $("#bidPrice").text();
		if (bidPrice.includes("C")) {
			bidPrice = bidPrice.substring(0, bidPrice.length-1);
			bidPrice = (Number(bidPrice) + 0.20).toFixed(2);
			$("#bidPrice").text(bidPrice + "C");
			let bidUserMap = {};
			bidUserMap["userId"] = $("#currentUserId").val();
			bidUserMap["nextUserId"] = $("#nextUserId").val();
			bidUserMap["playerId"] = $("#bidPlayerId").val();
			bidUserMap["price"] = $("#bidPrice").text();
			$("#bidUserMap").val(JSON.stringify(bidUserMap));
		}
		$("#btnIncrease20L").prop("disabled", true);
	});
	
	var bidInterval;
	
	$("#btnNoBid").on("click", function() {
		clearInterval(bidInterval);
		$("#btnIncrease5L, #btnIncrease20L").hide();
		let bidUserMap = {};
		bidUserMap["userId"] = $("#currentUserId").val();
		bidUserMap["nextUserId"] = $("#nextUserId").val();
		bidUserMap["playerId"] = $("#bidPlayerId").val();
		bidUserMap["price"] = $("#bidPrice").text();
		$("#bidUserMap").val(JSON.stringify(bidUserMap));
		$("#" + $('#currentUserId').val() + " #tdNoBid").text("Yes");
		$("#btnNoBid").prop("disabled", true);
		$("#bidTimer").hide();
	});
	
	let totalBidUsers = $("#totalBidUsers").val();
	if (totalBidUsers < 6) {
		$("#noParticipantsDiv").show();
		$("#participantsDiv").hide();
	} else {
		$("#noParticipantsDiv").hide();
		$("#participantsDiv").show();
	}
	
	$("#bidTimer").hide();
	
	$("#btnStartBid").on("click", function() {
		savePickedPlayerInfo();
		$("#btnStartBid").hide();
	});
	
	function savePickedPlayerInfo() {
		let bidPlayerId = $("#bidPlayerId").val();
		$.ajax({
			type: "POST",
			url: "/ipl/savePickedPlayerInfo/" + bidPlayerId,
			success: function(result) {
				console.log("result: " + result);
			},
			error: function(error) {
				console.log(error);
			}
		});
	}
	
	function setBidTimer() {
		$("#bidTimer").show();
		$("#btnIncrease5L, #btnIncrease20L, #btnNoBid").show();
		if ($("#bidPrice").text().substring(0, $("#bidPrice").text().length-1) < 100) {
			$("#btnIncrease5L").show();
			$("#btnIncrease20L").hide();
		} else {
			$("#btnIncrease5L").hide();
			$("#btnIncrease20L").show();
		}
		let bidTime = 10;
		bidInterval = setInterval(function() {
			$("#bidTimer").text(bidTime);
			if (bidTime < 1) {
				clearInterval(bidInterval);
				$("#bidTimer").text("Timeout");
				$("#btnIncrease5L, #btnIncrease20L, #btnNoBid").hide();
				pickPlayer();
			}
			bidTime--;
		}, 1000);
	}
	
	function pickPlayer() {
		let bidUserMap = $("#bidUserMap").val();
		$.ajax({
			type: "PUT",
			url: "/ipl/pickPlayer",
			contentType: "application/json",
			data: JSON.parse(bidUserMap),
			success: function(result) {
				if (result && result == "success") {
					refreshBidPage("false");
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	}
	
	let otherOnlineUserIds = [];
	$(".onlineUserIds").each(function() {
		if (currentUserId < this.id) {
			otherOnlineUserIds.push(this.id);
		}
	});
	console.log("Other online user ids: " + otherOnlineUserIds);
	
	if (otherOnlineUserIds.length > 0) {
		let nextUserId = Math.min.apply(Math, otherOnlineUserIds);
		$("#nextUserId").val(nextUserId);
		console.log("next user id: " + nextUserId);
	}
	
	var bidRefreshInterval;
	
	function refreshBidPage(isCurrentUser) {
		bidRefreshInterval = setInterval(function() {
			let bidPlayerId = $("#bidPlayerId").val();
			
			if (bidPlayerId) {
				if (isCurrentUser == "true") {
					$.ajax({
						type: "GET",
						url: "/ipl/getCurrentTurnPlayer/" + bidPlayerId,
						success: function(result) {
							clearInterval(bidRefreshInterval);
							console.log(result);
							if (result) {
								let currentBidPlayerUserOrder = $("#userOrder").val();
								let currentUserId = $("#currentUserId").val();
								
								if (currentBidPlayerUserOrder && result.userId) {
									if (currentBidPlayerUserOrder == result.userId) {
										clearInterval(bidRefreshInterval);
										setBidTimer();
									} else {
										refreshBidPage("false");
									}
								}
							} else {
								refreshBidPage("false");
							}
						},
						error: function(error) {
							console.log("error: " + error);
						}
					});
				} else {
					$.ajax({
						type: "GET",
						url: "/ipl/getUpdatedPickedPlayerInfo/" + bidPlayerId,
						success: function(result) {
							console.log("result: " + result);
							// $("#bidPrice").text();
							refreshBidPage("true");
						},
						error: function(error) {
							console.log(error);
						}
					});
				}
			}
		}, 500);
	}
	
	if (document.cookie.includes("userOrAdmin=user") && window.location.pathname.includes("ipl/bid")) {
		refreshBidPage("true");
	}
	
	// validation to be implemented
	function validation() {
		
	}
	
});