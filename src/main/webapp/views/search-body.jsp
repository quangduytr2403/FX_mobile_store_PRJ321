<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entities.Product" %>
<%@ page import="entities.Cart" %>

<div class ="row mb-3">
	<div class="col-sm-9 col-12">
		<div class="row">
			<c:forEach items="${listOfProducts}" var="product">
				<div class="col-sm-4 col-6">
	      			<div class="card-content mt-3">
	      				<input type="hidden" value="${product.id}">
	       				<img src="${product.src}" alt="product">
	       				<div class="ml-3"> 
	       					<p class="mt-2 mb-0 product-categories">${product.type}</p>
	         				<p class="product-name">${product.name}</p>
	         				<p class="mt-2 product-price">${product.price}$</p>
	       				</div>
	      			</div>
	    		</div>
    		</c:forEach>
    		<c:if test="${listOfProducts.size() == 0}">
    			<div class="col-12 center-content mt-3">No product found!</div>
    		</c:if>
		</div>
		<div class="d-flex justify-content-center mt-5">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="../search/1?keyword=${param.keyword}">First</a></li>
				<c:choose>
					<c:when test="${currentPage != 1}">
						<li class="page-item"><a class="page-link" href="../search/${currentPage - 1}?keyword=${param.keyword}">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link">Previous</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="counter" begin="1" end="${totalPages}">
					<c:choose>
						<c:when test="${currentPage != counter}">
							<li class="page-item"><a class="page-link" href="../search/${counter}?keyword=${param.keyword}">${counter}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link">${counter}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${currentPage != totalPages}">
						<li class="page-item"><a class="page-link" href="../search/${currentPage + 1}?keyword=${param.keyword}">Next</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link">Next</a></li>
					</c:otherwise>
				</c:choose>
				<li class="page-item"><a class="page-link" href="../search/${totalPages}?keyword=${param.keyword}">Last</a></li> 
			</ul>
		</div>
	</div>
	<div class="col-sm-3 offset-sm-0 col-8 offset-2 mt-3">
		<div class="card px-4">
			<h4 class="mt-2">Shopping cart</h4>
			<p id="cart">
				<i class="fas fa-shopping-cart fa-2x"></i> 
				<% Cart cart = (Cart)request.getSession().getAttribute("cart");%>
				<span class="badge badge-pill badge-warning">
					<%=cart == null ? 0 : cart.getNumberProduct()%> product(s)
				</span>
			<p>
			<p class="mb-0">
				<b>Total: </b>
				<span id="total-price">
					<%=cart == null ? 0 : cart.getAmount()%>$
				</span>
			</p>
		</div>
		<div class="card">
			<h4 class="mt-2 ml-4">Popular products</h4>
			<c:forEach items="${listOfPopularProducts}" var="product">
				<div class="popular-product">
					<input type="hidden" value="${product.id}">
					<img src="${product.src}"/>
					<span>${product.name}</span>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
