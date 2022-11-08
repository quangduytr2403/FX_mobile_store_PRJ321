<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Management</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/index-admin.css" />
</head>

<body onload="showNoti()">
	<div class="container-fluid">
		<div class="row">
			<div class="col-4 col-sm-2" id="left-menu">
				<div class="mt-3"><b>RYAN MOBILE</b> <hr></div>
				<ul class="nav flex-column">
					<li class="nav-item mb-2 active-menu" id="dashboard">
						<a class="nav-link" href="#" >
							<i class="mr-3 fas fa-user"></i>
							Dashboard
						</a>
					</li>
					<li class="nav-item mb-2" id="product">
						<a class="nav-link" href="#" >
							<i class="mr-3 fas fa-phone"></i>
							Product
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/home/1" id="form-content">
							<i class="mr-3 fas fa-home"></i>
							Home page
						</a>
					</li>
				</ul>
			</div>
			<div class="col-8 col-sm-10" id="page-content">
				<div class="row justify-content-end p-3">
					<span class="mr-3" id="userLogin">${sessionScope.userLogin.userMail}</span>
					<a href="<%=request.getContextPath()%>/logout"> 
						<i class="fas fa-sign-out-alt"></i>Logout
					</a>
				</div>
				<div class="row" id="body-content">
				</div>
			</div>
		</div>
	</div>
	
	<div id="snackbar">Welcome ${sessionScope.userLogin.userMail}</div>
	
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
	<script 
		type="text/javascript" 
		src="<%=request.getContextPath()%>/resources/js/index-admin.js"></script>
</body>
</html>