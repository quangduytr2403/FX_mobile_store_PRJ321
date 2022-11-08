<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<!-- Boostrap 4 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/register.css" />
</head>
<body>
	<%
	String clUserMail = "", clPassword = "", clRePassword = "", clName = "", clAddress = "", clPhone ="";
	if (request.getAttribute("userMailFeedback") != null) {
		clUserMail = "is-invalid";
	} 
	if (request.getAttribute("passwordFeedback") != null) {
		clPassword = "is-invalid";
	}
	if (request.getAttribute("rePasswordFeedback") != null) {
		clRePassword = "is-invalid";
	}
	if (request.getAttribute("nameFeedback") != null) {
		clName = "is-invalid";
	}
	if (request.getAttribute("addressFeedback") != null) {
		clAddress = "is-invalid";
	}
	if (request.getAttribute("phoneFeedback") != null) {
		clPhone = "is-invalid";
	}
	%>

	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-5 offset-1 col-md-3 offset-md-3 p-4" id="form-container">
				<div>
					<h2 class="center-content"><b>Register</b></h2>
					<p class="error center-content">${registerFail}</p>
				</div>
				<form action="<%=request.getContextPath()%>/register" method="post" id="form-register" class="p-3">
					<div class="form-group">
						<input type="text" name="userMail" id="userMail" 
							class="form-control <%= clUserMail%>"
							placeholder="User mail" value="${userRegister.userMail}" >
						<p class="invalid-feedback">${userMailFeedback}</p>
					</div>
					<div class="form-group">
						<input type="password" name="password" id="password" 
							class="form-control <%= clPassword%>"
							placeholder="Password" value="${userRegister.password}" >
						<p class="invalid-feedback">${passwordFeedback}</p>
					</div>
					<div class="form-group">
						<input type="password" name="rePassword" id="rePassword" 
							class="form-control <%= clRePassword%>"
							placeholder="Re Password" value="${rePassword}" >
						<p class="invalid-feedback">${rePasswordFeedback}</p>
					</div>
					<div class="form-group">
						<input type="text" name="name" id="name" 
							class="form-control <%= clName%>"
							placeholder="Name" value="${userRegister.name}" >
						<p class="invalid-feedback">${nameFeedback}</p>
					</div>
					<div class="form-group">
						<input type="text" name="address" id="address" 
							class="form-control <%= clAddress%>"
							placeholder="Address" value="${userRegister.address}" >
						<p class="invalid-feedback">${addressFeedback}</p>
					</div>
					<div class="form-group">
						<input type="text" name="phone" id="phone" 
							class="form-control <%= clPhone%>"
							placeholder="Phone" value="${userRegister.phone}" >
						<p class="invalid-feedback">${phoneFeedback}</p>
					</div>
					
					<div class="center-content"><button type="submit" class="btn btn-danger mt-3 px-5"><b>REGISTER</b></button></div>
				</form>
			</div>
			<div class="col-5 col-md-3 center-content" id="form-info">
				<h3><b>Register!</b></h3>
				<p>To connect with us <br> please register your personal info</p>
			</div>
		</div>
	</div>

	<!-- Bootstrap 4 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<!-- Fontwesome 5 -->
	<script src="https://kit.fontawesome.com/a12342cbee.js"
		crossorigin="anonymous"></script>
</body>
</html>