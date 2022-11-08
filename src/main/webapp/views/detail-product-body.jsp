<%@page import="java.math.BigDecimal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entities.Product" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale"%>
<%@ page import="entities.Cart" %>
<%@ page import="java.math.BigDecimal" %>

<h3 class="mt-3">${product.name}</h3>
<hr>
<div class ="row my-4">	
	<div class="col-md-3 col-6">
		<img src="${product.src}" class="mb-3" alt="product">
	</div>       				
	<div class="col-md-6 col-12">
		<h3 class="price mb-4">
			<% double price = ((Product)request.getAttribute("product")).getPrice();%>
			<%=NumberFormat.getInstance(new Locale("da", "DK")).format(
				Math.round(price * 100.0) * 10000) + " VND"%>
		</h3>
		<pre><b>Product Description: <c:out value = "${product.description}" default="No description"/></b></pre>
		<c:choose>
  	  	   <c:when test="${sessionScope.userLogin == null}">
			  <a href="<%=request.getContextPath()%>/login" class="btn btn-warning mt-2 mb-3">Add to cart</a>
           </c:when>    
           <c:otherwise>
              <a href="<%=request.getContextPath()%>/cart/add-to-cart?id=${param.id}" class="btn btn-warning mt-2 mb-3">Add to cart</a>
           </c:otherwise>
  	  </c:choose>
	</div>
	<div class="col-md-3 offset-md-0 col-8 offset-2">
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
