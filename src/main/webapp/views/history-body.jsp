<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entities.Product" %>
<%@ page import="entities.Cart" %>

<div class ="row mb-3">
	<div class="col-12">
		<table class="table table-stripped">
	        <thead>
	            <tr>
	            	<th>No.</th>
	                <th>Order Id</th>
	                <th>Receiver name</th>
	                <th>Address</th>
	                <th>Status</th>
	                <th>Order Date</th>
	                <th>Price</th>
	                <th>Detail</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<% int counter = 1; %>
	        	<c:forEach items="${listOfOrders}" var="order">
					<tr>
						<td><%= counter++%></td>
						<td>${order.orderId}</td>
						<td>${order.orderName}</td>
						<td>${order.orderAddress}</td>
						<td>${order.status}</td>
						<td>${order.orderDate}</td>
						<td>${order.getPrice()}$</td>
						<td>
							<c:forEach items="${order.productOrders}" var="product">
								<p>${product.productName}: ${product.amount}</p>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
	        </tbody>
	    </table>
	</div>
</div>
