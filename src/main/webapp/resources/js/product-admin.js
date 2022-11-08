$(document).ready(function() {	
	//Focus input
	var length = $('#search').val().length;
	$('#search').focus();
	$('#search')[0].setSelectionRange(length, length);
	
	$('#search').on('input', function() {
		var key = $(this).val();
		console.log(key);
		$.get({
			url: "/PRJ321x_ASM3_duytqFX11834/admin/product",
			data: {
				keyword: key
			},
			success: function(response) {
				$("#body-content").html(response);
			}
		});
	});
});