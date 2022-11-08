<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-12 col-sm-8 p-3">
	<table class="table table-hover" id="content-table">
        <thead>
        	<tr>
        	 	<th colspan="4">Members of the team</th>
        	 </tr>
            <tr>
                <th>Id</th>
                <th>User Mail</th>
                <th>Name</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
			<% int counter = 1; %>
        	<c:forEach items="${listOfAdmins}" var="account">
				<tr>
					<td><%= counter++%></td>
					<td>${account.userMail}</td>
					<td>${account.name}</td>
					<td>${account.address}</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
</div>