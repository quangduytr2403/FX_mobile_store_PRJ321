<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-12 col-sm-8 p-3">
	<table class="table table-striped table-hover" id="content-table">
        <thead>
        	<tr>
        	 	<th colspan="2">All available products</th>
        	 	<th colspan="3">
        	 		<input type="text" class="form-control py-0 px-2" id="search" 
        	 			placeholder="Search name..." value="${param.keyword}" autofocus>
        	 	</th>
        	 </tr>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Type</th>
                <th>Brand</th>
            </tr>
        </thead>
        <tbody>
			<% int counter = 1; %>
        	<c:forEach items="${listOfProducts}" var="product">
				<tr>
					<td><%= counter++%></td>
					<td>${product.name}</td>
					<td>${product.price}$</td>
					<td>${product.type}</td>
					<td>${product.brand}</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
</div>

<script 
		type="text/javascript" 
		src="<%=request.getContextPath()%>/resources/js/product-admin.js"></script>