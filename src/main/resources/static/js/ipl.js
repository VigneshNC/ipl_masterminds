$(document).ready(function() {
	$("body").css("background-color", "#3D4D61").css("font-family", "Ubuntu, 'times new roman', times, roman, serif");
	$(".container").addClass("rounded").css("background-color", "#F3CA20").css("padding-bottom", "15px");
	$("#tBodyParticipants, #tBodyPlayers tr").css("cursor", "pointer");
	
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
	
	$('.nav-item').removeClass('active');
	$("#btnBackUser").hide();
	/*var rolesToUI = "";*/
	
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
		let userOrAdmin = $("#userOrAdmin").val();
		document.cookie = "userOrAdmin=" + userOrAdmin + "; path=/";
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
	} else if (window.location.pathname.includes("player/edit")) {
		/*rolesToUI = "";
		for(var roleIndex = 0; roleIndex < productArray.length; roleIndex++) {
		    rolesToUI += "<option value='" + productArray[i] + "'>" + productArray[i] + "</option>";
		}
		$( 'select[name="inptProduct"]' ).append(rolesToUI);*/
		$("#btnSaveOrUpdatePlayer").html("Update");
	} else if (window.location.pathname.includes("ipl/playersList")) {
		$("#playerTable").DataTable();
		if (document.cookie.includes("userOrAdmin=admin")) {
			$("#btnAddPlayer").show();
		} else {
			$("#btnAddPlayer").hide();
		}
		if (document.getElementById("tBodyPlayers").children[0].innerText == "No data available in table") {
			$("#btnDelAllPlayers").hide();
		} else {
			$("#btnDelAllPlayers").show();
		}
		$(".container").css("background-color", "#FFFFFF");
		$("#playersHead").text("List of Players");
		$(".owner, #divImport").show();
		$("#btnAddPlayer").show();
		$("#spanTotPnts").text("").hide();
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
		$("#spanTotPnts").text("Total Points: " + $("#totalPoints").val()).show();
	}
	
	if (document.cookie.includes("userOrAdmin=admin")) {
		$("#navItemLogout, #navItemUsers, #navItemPlayers, #navItemRequestors").show();
		$("#navItemLogin, #navItemRegister").hide();
	} else if (document.cookie.includes("userOrAdmin=user")) {
		$("#navItemLogout").show();
		$("#navItemLogin, #navItemRegister, #navItemUsers, #navItemPlayers, #navItemRequestors").hide();
	} else {
		$("#navItemLogin").show();
		$("#navItemLogout, #navItemUsers, #navItemPlayers, #navItemRules, #navItemRequestors").hide();
	}
	
	
	$("#tBodyUsers tr").on("click", function() {
		/*var columns = $(this).closest('tr').find("td");
		var user = [];
		$.each(columns, function(i, item) {
			user.push(item.innerHTML);
		});*/
	});

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
	});
	
	// validation to be implemented
	function validation() {
		
	}
	
	$("#tBodyParticipants tr").on("click", function() {
		var columns = $(this).find("td");
		window.location.href = "/ipl/participantPoints/" + columns[0].innerHTML;
	});
	
	$("#btnBackPointsTable").on("click", function() {
		window.location.href = "/ipl/pointsTable";
	});
	
	$("#tBodyPlayers tr").on("click", function() {
		window.location.href = "/ipl/player/edit/" + $(this).prop("id");
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
	
});