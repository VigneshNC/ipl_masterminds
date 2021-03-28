$(document).ready(function() {
	$("body").css("background-color", "#3D4D61").css("font-family", "Ubuntu, 'times new roman', times, roman, serif");
	$(".container").addClass("rounded").css("background-color", "#F3CA20").css("padding-bottom", "15px");

	$("#btnSaveOrUpdateUser").on("click", function() {
		var registerData = $("#registerForm").serializeObject();
		$.ajax({
			type: "post",
			url: "/ipl/save",
			contentType: "application/json",
			data: JSON.stringify(registerData),
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
	} else if (window.location.pathname.includes("player")) {
		$("#btnSaveOrUpdatePlayer").html("Save");
	} else if (window.location.pathname.includes("player/edit")) {
		$("#btnSaveOrUpdatePlayer").html("Update");
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
		$("#btnDelete").show();
		$("#btnOkayOrCancel").html("Cancel");
		$("#idModal").val($(this).prop("id"));
		$("#myModal").modal("show");
	});
	
	$("#btnDelete").on("click", function() {
		if ($("#idModal").val() != undefined && $("#idModal").val() > 0) {
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
	
	$("#tBodyPlayers").append("<tr><td>Washington</td></tr>");
});