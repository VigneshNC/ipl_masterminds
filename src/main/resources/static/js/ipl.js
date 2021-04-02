$(document).ready(function() {
	$("body").css("background-color", "#3D4D61").css("font-family", "Ubuntu, 'times new roman', times, roman, serif");
	$(".container").addClass("rounded").css("background-color", "#F3CA20").css("padding-bottom", "15px");

	$("#btnSaveOrUpdateUser").on("click", function() {
		var registerUserData = $("#registerForm").serializeObject();
		$.ajax({
			type: "post",
			url: "/ipl/save",
			contentType: "application/json",
			data: JSON.stringify(registerUserData),
			success: function(userInfo) {
				console.log("Result: " + userInfo);
				let userId = JSON.parse(userInfo).id;
				window.location = "/ipl/user/" + userId;
			},
			error: function(error) {
				console.log("Error: " + error);
			}
		});
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
		document.cookie = "isLogin=yes; path=/";
	} else if (window.location.pathname.includes("user/edit")) {
		$("#h1user").html("Edit Participant");
		$("#btnSaveOrUpdateUser").html("Update");
		$("#navItemUsers").show();
		$("#btnBackUser").show();
	} else if (window.location.pathname.includes("ipl/login")) {
		$("#h1user").html("Login Form");
	} else if (window.location.pathname.includes("ipl/logout")) {
		isLogin = "no";
		document.cookie = "isLogin=; path=/";
	} else if (window.location.pathname.includes("player/edit")) {
		/*rolesToUI = "";
		for(var roleIndex = 0; roleIndex < productArray.length; roleIndex++) {
		    rolesToUI += "<option value='" + productArray[i] + "'>" + productArray[i] + "</option>";
		}
		$( 'select[name="inptProduct"]' ).append(rolesToUI);*/
		$("#btnSaveOrUpdatePlayer").html("Update");
	} else if (window.location.pathname.includes("player")) {
		/*rolesToUI = "";
		let roles = "${roles}";
		console.log("roles: " + roles);
		for(var roleIndex = 0; roleIndex < roles.length; roleIndex++) {
		    roles += "<option value='" + roles[i] + "'>" + roles[i] + "</option>";
		}
		$('select[name="inptProduct"]').append(rolesToUI);*/
		$("#btnSaveOrUpdatePlayer").html("Save");
	}
	
	if (document.cookie && document.cookie.includes("isLogin=yes")) {
		$("#navItemLogout").show();
		$("#navItemLogin, #navItemRegister").hide();
	} else {
		$("#navItemLogout").hide();
		$("#navItemLogin").show();
		$("#navItemUsers").hide();
	}
	
	
	$("#tBodyUsers tr").on("click", function() {
		/*var columns = $(this).closest('tr').find("td");
		var user = [];
		$.each(columns, function(i, item) {
			user.push(item.innerHTML);
		});*/
	});

	$("modal-body").html("");
	
	$(".delete").on("click", function() {
		$(".modal-body").html("Are you sure to Delete the participant?");
		$(".modal-title").html("Delete participant");
		$("#btnDelete").show();
		$("#btnOkayOrCancel").html("Cancel");
		$("#idModal").val($(this).prop("id"));
		$("#myModal").modal("show");
	});
	
	$("#btnDelete").on("click", function() {
		if ($("#idModal").val() != undefined && $("#idModal").val() > 0) {
			let title = $(".modal-title").html();
			if (title == "Delete participant") {
				$.ajax({
					type: "delete",
					url: "/ipl/delete/" + $("#idModal").val(),
					success: function(result) {
						if (result == "success") {
							window.location = "/ipl/users"
						}
					},
					error: function(error) {
						console.log("Error: " + error);
					}
				});
			} else if (title == "Delete player") {
				$.ajax({
					type: "delete",
					url: "/ipl/player/delete/" + $("#idModal").val(),
					success: function(result) {
						if (result == "success") {
							window.location = "/ipl/playersList"
						}
					},
					error: function(error) {
						console.log("Error: " + error);
					}
				});
			}
		}
	});
	
	$("#btnLogin").on("click", function() {
		let username = $("#txtLoginUsername").val();
		let password = $("#loginPassword").val();
		$.ajax({
			type: "get",
			url: "/ipl/authenticate?username=" + username + "&password=" + password,
			success: function(result) {
				if (result != "0") {
					window.location = "/ipl/user/" + result + "/view";
				} else {
					$(".modal-body").html("Username or Password is incorrect!");
					$("#btnDelete").hide();
					$("#btnOkayOrCancel").html("Okay");
					$("#myModal").modal("show");
				}
			},
			error: function(error) {
				console.log("Error: " + error);
			}
		});
	});
	
	$("#btnEditUser").on("click", function() {
		window.location="/ipl/user/" + $("#userId").val() + "/edit";
	});
	
	$("#btnBackUser").on("click", function() {
		window.location = window.location.pathname.replace("edit", "view");
	});
	
	$("#btnAddPlayer").on("click", function() {
		window.location = "/ipl/player";
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
				window.location = "/ipl/playersList";
			},
			error: function(error) {
				console.log("Error: " + error);
			}
		});
	});
	
	$("#btnBackPlayer").on("click", function() {
		window.location = "/ipl/playersList";
	});
	
	$(".deletePlayer").on("click", function() {
		$(".modal-body").html("Are you sure to Delete the player?");
		$(".modal-title").html("Delete player");
		$("#btnDelete").show();
		$("#btnOkayOrCancel").html("Cancel");
		$("#idModal").val($(this).prop("id"));
		$("#myModal").modal("show");
	});
	
});