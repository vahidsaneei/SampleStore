<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Products pages</title>
<c:url value="/resources/css/" var="cssUrl" />
<c:url value="/resources/images/" var="imageUrl" />
<c:url value="/resources/js" var="scriptUrl" />
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="${scriptUrl }productScript.js" type="text/javascript"></script>
</head>
<body>
	<c:set var="appurl">${pageContext.request.contextPath}</c:set>
	<c:url value="/logout" var="logoutUrl" />

	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<center>
		<div align="center">
			<ul>
				<sec:authorize access="isAuthenticated()">
					<li><a href="${appurl }/user/showprofile"><span
							class="glyphicon glyphicon-user">Dear, <sec:authentication
									property="name" />
						</span></a></li>
					<li><a href="${logoutUrl }"><span
							class="glyphicon glyphicon-log-out">Logout</span></a></li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${appurl }/login"><span
							class="glyphicon glyphicon-user">Login</span></a></li>
				</sec:authorize>
				<li><a id="cartlink" href="${appurl }/addtocartlist"><span
						class="glyphicon glyphicon-shopping-cart">Cart</span></a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="${appurl }/products"><span
							class="glyphicon glyphicon-list-alt">Product Management</span></a></li>
					<li><a href="${appurl }/users"><span
							class="glyphicon glyphicon-list">Users Management</span></a></li>
					<li><a href="${appurl }/orders"><span
							class="glyphicon glyphicon-pushpin">Orders Management</span></a></li>
					<li><a href="${appurl }/stores"><span
							class="glyphicon glyphicon-floppy-disk">Store Management</span></a></li>
				</sec:authorize>
				<li><a href="${appurl }/search"><span
						class="glyphicon glyphicon-search">Search</span></a></li>
				<li><a href="${appurl }/"><span
						class="glyphicon glyphicon-home">Home</span></a></li>
			</ul>
		</div>
		<div class="container">
			<center>
				<a class="btn btn-info" href="${appurl }/products/newprod">Add
					new product</a>
			</center>
		</div>
		<center>
			<div>
				<center>
					<table class="table table-hover" style="width: 100%">
						<thead>
							<tr>
								<th>Prd Name</th>
								<th>Company</th>
								<th>Create date</th>
								<th>Expire date</th>
								<th>Category</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Description</th>
								<th>Actions</th>
							</tr>
						</thead>
						<c:forEach var="product" items="${products }">
							<tr>
								<td>${product.fullName }</td>
								<td>${product.companyName }</td>
								<td>${product.createdDate }</td>
								<td>${product.expiryDate }</td>
								<td>${product.category }</td>
								<td>${product.price }</td>
								<td>${product.quantity }</td>
								<td>${product.description }</td>
								<td colspan="2"><div class="btn-group-vertical">
										<a href="${appurl }/products/remove/${product.id }"
											class="btn btn-danger">Delete</a> <a
											href="${appurl }/products/edit/${product.id }"
											class="btn btn-success">Edit</a>
									</div></td>
							</tr>
						</c:forEach>
					</table>
				</center>
			</div>
		</center>
	</center>
</body>
</html>