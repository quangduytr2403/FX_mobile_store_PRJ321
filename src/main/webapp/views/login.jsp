<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<!-- Boostrap 4 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.css" />
</head>
<body>
	<% 
		String clUserMail = "";
		if(request.getAttribute("userMailFeedback") != null) {
			clUserMail = "is-invalid";
		}
		
		String clPassword = "";
		if(request.getAttribute("passwordFeedback") != null) {
			clPassword = "is-invalid";
		}
		
		Object remember = request.getAttribute("remember");
	%>

	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-5 offset-1 col-md-3 offset-md-3 p-4" id="form-container">
				<div>
					<h2 class="center-content"><b>Sign in</b></h2>
					<p class="error center-content">${loginFail}</p>
				</div>
				<form action="<%=request.getContextPath()%>/login" method="post" id="form-login" class="p-3">
					<div class="form-group">
						<input type="text" name="userMail" id="userMail" 
							class="form-control <%= clUserMail%>"
							placeholder="Email" value="${userLogin.userMail}" >
						<p class="invalid-feedback">${userMailFeedback}</p>
					</div>
					<div class="form-group">
						<input type="password" name="password" id="password" 
							class="form-control <%= clPassword%>" 
							placeholder="Passwork" value="${userLogin.password}">
						<p class="invalid-feedback">${passwordFeedback}</p>
					</div>
					<div class="form-group form-check">
						<label class="form-check-label"> <input type="checkbox"
							class="form-check-input" name="remember" id="remember" 
							<%= (remember != null) ? "checked" : "" %> > Remember me
						</label>
					</div>
					<div class="center-content"><a href="#">Forgot your password?</a></div>
					<div class="center-content"><button type="submit" class="btn btn-danger mt-3 px-5"><b>LOGIN</b></button></div>
				</form>
			</div>
			<div class="col-5 col-md-3 center-content" id="form-info">
				<h3><b>Welcome back!</b></h3>
				<p>To keep connected with us <br> please login with your personal info</p>
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