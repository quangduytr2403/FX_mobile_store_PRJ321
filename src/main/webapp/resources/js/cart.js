$(document).ready(function() {		
	var val, prevVal;
	
	$('input.quantity').keypress(function (e) {
    	e.preventDefault();
	});
	
	$('input.quantity').on('input', function() {
		var id = $(this).parent().prev().prev().prev().text();
		prevVal = val;
		val = $(this).val();
		if(val != ""){
			$(location).prop('href', 
				'/PRJ321x_ASM3_duytqFX11834/cart/change-quantity?id=' + id + '&value=' + val);
		}
	});
	
	$('input.quantity').on('focusin', function() {
		var id = $(this).parent().prev().prev().prev().text();
		prevVal = "";
		val = $(this).val();
	});
	
	$('input.quantity').on('focusout', function() {
		var id = $(this).parent().prev().prev().prev().text();
		if(val == "" || val < 1){
			$(this).val(prevVal);
		}
	});
	
	$('button.remove-button').on('click', function() {
		var id = $(this).parent().prev().prev().prev().prev().prev().text();
		$(location).prop('href', '/PRJ321x_ASM3_duytqFX11834/cart/remove-item?id=' + id);
	});
});