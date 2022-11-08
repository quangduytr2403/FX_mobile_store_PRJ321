function showNoti() {
  // Get the snackbar DIV
  var x = document.getElementById("snackbar");

  // Add the "show" class to DIV
  x.className = "show";

  // After 3 seconds, remove the show class from DIV
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}
$(document).ready(function() {
	$.get({
		url: "/PRJ321x_ASM3_duytqFX11834/admin/dashboard",
		success: function(response) {
			$("#body-content").html(response);
		}
	});
	
	$("#dashboard").click(function() {
		//Active menu
		$(".nav-item").removeClass("active-menu");
		$(this).addClass("active-menu")
		
		$.get({
			url: "/PRJ321x_ASM3_duytqFX11834/admin/dashboard",
			success: function(response) {
				$("#body-content").html(response);
			}
		});
	});
	
	$("#product").click(function() {
		//Active menu
		$(".nav-item").removeClass("active-menu");
		$(this).addClass("active-menu")
		
		$.get({
			url: "/PRJ321x_ASM3_duytqFX11834/admin/product",
			data: {
				keyword: ""
			},
			success: function(response) {
				$("#body-content").html(response);
			}
		});
	});
});