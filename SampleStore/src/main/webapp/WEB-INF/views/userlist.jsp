<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>User management</title>
</head>
<body>
	<c:url value="/logout" var="logoutUrl" />

	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<div align="center">
		<ul>
			<sec:authorize access="isAuthenticated()">
				<li><a href="#"><span class="glyphicon glyphicon-user">Dear,
							<sec:authentication property="name" />
					</span></a></li>
				<li><a href="${logoutUrl }"><span
						class="glyphicon glyphicon-log-out">Logout</span></a></li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="${pageContext.request.contextPath }/login"><span
						class="glyphicon glyphicon-user">Login</span></a></li>
			</sec:authorize>
			<li><a id="cartlink" href="addtocartlist"><span
					class="glyphicon glyphicon-shopping-cart">Cart</span></a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="products"><span
						class="glyphicon glyphicon-list-alt">Product Management</span></a></li>
				<li><a href="users"><span
						class="glyphicon glyphicon-list">Users Management</span></a></li>
				<li><a href="orders"><span
						class="glyphicon glyphicon-pushpin">Orders Management</span></a></li>
				<li><a href="stores"><span
						class="glyphicon glyphicon-floppy-disk">Store Management</span></a></li>
			</sec:authorize>
			<li><a href="search"><span
					class="glyphicon glyphicon-search">Search</span></a></li>
			<li><a href="${pageContext.request.contextPath }"><span
					class="glyphicon glyphicon-home">Home</span></a></li>
		</ul>
	</div>
	<div class="container">
		<center>
			<a class="btn btn-info" href="users/newuser">Add new User</a>
		</center>
	</div>
	<div>
		<center>
			<table class="table table-hover" style="width: 100%" border="1">
				<thead>
					<tr>
						<th>User name</th>
						<th>Gender</th>
						<th>Enabled</th>
						<th>non Locked</th>
						<th>Acc non Expired</th>
						<th>Cred non Expired</th>
						<th>Address</th>
						<th>PhoneNumber</th>
						<th>Actions</th>
					</tr>
				</thead>
				<c:forEach var="user" items="${userList }">
					<tr>
						<td>${user.username }</td>
						<td>${user.gender }</td>
						<td>${user.enabled }</td>
						<td>${user.accountNonLocked }</td>
						<td>${user.accountNonExpired }</td>
						<td>${user.credentialsNonExpired }</td>
						<td>${user.address }</td>
						<td>${user.phoneNumber }</td>
						<td colspan="2"><div class="btn-group-vertical">
								<a href="#" class="btn btn-danger">Delete</a> <a href="#"
									class="btn btn-success">Edit</a>
							</div></td>
					</tr>
				</c:forEach>
			</table>
		</center>
	</div>
</body>
</html>