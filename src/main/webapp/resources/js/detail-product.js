$(document).ready(function() {
	$("#cart").on('click', function(){
		$(location).prop('href', '/PRJ321x_ASM3_duytqFX11834/cart/show-cart');
	});
	
	$(".popular-product").on('click', function() {
		var id = $(this).find(">:first-child").val();
		$(location).prop('href', '/PRJ321x_ASM3_duytqFX11834/detail?id=' + id);
	});
})