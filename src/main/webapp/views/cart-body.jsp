<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entities.Product" %>
<%@ page import="entities.Cart" %>

<%
	String clCustomerName = "", clCustomerAddress = "", clDiscount = "";
	if (request.getAttribute("customerNameFeedback") != null) {
		clCustomerName = "is-invalid";
	} 
	if (request.getAttribute("customerAddressFeedback") != null) {
		clCustomerAddress = "is-invalid";
	}
	if (request.getAttribute("discountFeedback") != null) {
		clDiscount = "is-invalid";
	}
%>

<div class ="row mb-3">
	<div class="col-12">
		<table class="table table-hover">
	        <thead>
	            <tr>
	            	<th>No.</th>
	                <th>Product in cart: ${sessionScope.cart.getNumberProduct()}</th>
	                <th>Price</th>
	                <th>Quantity</th>
	                <th>Amount</th>
	                <th>Remove</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<% int counter = 1; %>
	        	<c:forEach items="${cart.items}" var="product">
					<tr>
						<td><%= counter++%></td>
						<td>${product.name}</td>
						<td>($) ${product.price}</td>
						<td>
							<input class="form-control-sm quantity" type="number" 
								min="1" value="${product.number}" required>
						</td>
						<%
							Product product = (Product)pageContext.getAttribute("product");
							double amount = Math.round(product.getNumber() * product.getPrice() * 100.0) / 100.0;
						%>
						<td> ($) <%=amount %> </td>
						<td>
							<button type="button" class="btn btn-danger remove-button">
								<i class="fas fa-trash fa-lg"></i>
							</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6"><b>Total: ${sessionScope.cart.getAmount()}$</b></td>				
				</tr>
	        </tbody>
	    </table>
	</div>
	<div class="col-sm-6 offset-sm-3 col-12">
		<form action="<%=request.getContextPath()%>/cart/submit-order" method="get">
		    <div class="form-group row">
		        <label for="customerName" class="col-4"><b>Customer name</b></label>
		        <input type="text" name="customerName" id="customerName" 
		        	class="form-control col-8 <%= clCustomerName%>"
		        	value="${order.orderName != null ? order.orderName : sessionScope.userLogin.name}">
		        <p class="invalid-feedback col-8 offset-4">${customerNameFeedback}</p>
		    </div>
		    <div class="form-group row">
		        <label for="customerAddress" class="col-4"><b>Customer address</b></label>
		        <input type="text" name="customerAddress" id="customerAddress" 
		        	class="form-control col-8 <%= clCustomerAddress%>"
		        	value="${order.orderAddress != null ? order.orderAddress : sessionScope.userLogin.address}">
		        <p class="invalid-feedback col-8 offset-4">${customerAddressFeedback}</p>
		    </div>
			<div class="form-group row">
		        <label for="discount" class="col-4"><b>Discount code (if any)</b></label>
		        <input type="text" name="discount" id="discount" 
		        	class="form-control col-8 <%= clDiscount%>"
		        	value="${order.discount}">
		        <p class="invalid-feedback col-8 offset-4">${discountFeedback}</p>
		    </div>
		    <div class="row">
		    	<button type="submit" class="btn btn-warning col-6 offset-3">Submit</button>
		    </div>
		</form>
	</div>
</div>
