<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row header">
	<div class="col-3 py-sm-3 py-2">
		<img src="<%=request.getContextPath()%>/resources/media/logo.png" alt="logo"/>
	</div>
	<div class="col-9">
		<form action="<%=request.getContextPath()%>/search/1" method="get">
			<div class="input-group mt-sm-4">
				<div class="input-group-prepend">
	      			<span class="input-group-text">Categories</span>
	    		</div>
				<input type="text" class="form-control" name="keyword" id="keyword" 
					placeholder="What are you looking for?" value="${sessionScope.searchKey}">
				<button class="btn btn-warning" type="submit"><i class="fas fa-search"></i></button>
			</div>
		</form>
	</div>
</div>

<div class="row">
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark col-12">
      <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
          aria-expanded="false" aria-label="Toggle navigation"></button>
      <div class="collapse navbar-collapse" id="collapsibleNavId">
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
              <li class="nav-item active ml-sm-5 mr-3">
                  <a class="nav-link" href="<%=request.getContextPath()%>/home/1">Home</a>
              </li>
              <li class="nav-item mr-3">
                  <a class="nav-link" href="#">Products</a>
              </li>
              <li class="nav-item">
                  <a class="nav-link" href="#">About us</a>
              </li>
          </ul>
          
          <ul class="navbar-nav navbar-right">
          	  <c:if test = "${(sessionScope.userLogin != null) && (sessionScope.userLogin.role == 1)}">
         		  <li class="nav-item">
         		  	  <a class="nav-link mr-3" 
                       	  href="<%=request.getContextPath()%>/admin/home">
                       	  AdminPage
		              </a>
         		  </li>
      		  </c:if>
        	  <li class="nav-item">
        	  	  <c:choose>
        	  	  	 <c:when test="${sessionScope.userLogin == null}">
        				 <a class="nav-link mr-3" href="<%=request.getContextPath()%>/login">Login</a>
                     </c:when>    
                     <c:otherwise>
                     	 <a class="nav-link mr-3" href="<%=request.getContextPath()%>/show-history">${sessionScope.userLogin.userMail}</a> 
                     </c:otherwise>
        	  	  </c:choose>
              </li>
              <li class="nav-item">
            	  <c:choose>
        	  	  	 <c:when test="${sessionScope.userLogin == null}">
        				 <a class="nav-link" href="<%=request.getContextPath()%>/register">Register</a>
                     </c:when>    
                     <c:otherwise>
                         <a class="nav-link" href="<%=request.getContextPath()%>/logout"> Logout</a>
                     </c:otherwise>
        	  	  </c:choose>
              </li> 
          </ul>
      </div>
  </nav>
</div>

