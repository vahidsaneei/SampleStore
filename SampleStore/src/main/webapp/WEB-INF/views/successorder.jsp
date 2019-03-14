<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:url var="cssUrl" value="/resources/css/" />
<c:url var="jsUrl" value="/resources/js/" />
<c:url var="imagesUrl" value="/resources/images/" />

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="${jsUrl }productScript.js" type="text/javascript">
	
</script>
<title>Order complete</title>
</head>
<body>
	<c:url value="/logout" var="logoutUrl" />

	<!-- csrf for log out-->
	<center>
		<div align="center">
			<ul>
				<sec:authorize access="isAuthenticated()">
					<li><a href="#"><span class="glyphicon glyphicon-user">Dear,
								<sec:authentication property="name" />
						</span></a></li>
					<li><a href="${logoutUrl }" onclick="logoutPerform();"><span
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
					<li><a href="users"><span class="glyphicon glyphicon-list">Users
								Management</span></a></li>
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
	</center>
	<center>
		<div class="container">
			<h2>Your order</h2>
			<c:choose>
				<c:when test="${not empty error}">
					<div class="text bg-danger">
						<strong>${error}</strong>
					</div>
				</c:when>
				<c:otherwise>
					<div class="text bg-info">
						<strong>${message}</strong><br> Delivery date is : <strong>${order.deliveryDate }</strong>
					</div>
				</c:otherwise>
			</c:choose>
			<a href="${pageContext.request.contextPath }" class="btn btn-info">Back
				to Store</a>
		</div>
	</center>
</body>
</html>