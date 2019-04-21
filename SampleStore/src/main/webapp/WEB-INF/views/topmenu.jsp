<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/resources/css/" var="cssUrl" />
<c:url value="/resources/js/" var="jsUrl" />

<link rel="stylesheet" type="text/css"
	href="${cssUrl }topmenusstyle.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="${jsUrl }topmenuscript.js"></script>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
</head>
<body>
	<c:set var="home" value="${pageContext.request.contextPath }" />

	<c:url value="/logout" var="logoutUrl" />

	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div class="topnav">
		<sec:authorize access="isAuthenticated()">
			<a href="${home }/user/showprofile"><span
				class="glyphicon glyphicon-user">Dear, <sec:authentication
						property="name" />
			</span></a>
			<a href="javascript:doLogout()"><span
				class="glyphicon glyphicon-log-out">Logout</span></a>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<a href="${home }/login"><span class="glyphicon glyphicon-user">Login</span></a>
		</sec:authorize>
		<a id="cartlink" href="${home }/addtocartlist"><span
			class="glyphicon glyphicon-shopping-cart">Cart</span></a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="${home }/products/1"><span
				class="glyphicon glyphicon-list-alt">Product Management</span></a>
			<a href="${home }/users"><span class="glyphicon glyphicon-list">Users
					Management</span></a>
			<a href="${home }/orders"><span
				class="glyphicon glyphicon-pushpin">Orders Management</span></a>
		</sec:authorize>
		<a href="${home }"><span class="glyphicon glyphicon-home">Home</span></a>

		<div class="search-container">
			<form action="${home }/search" method="post">
				<input type="text" placeholder="Search.." name="search">
				<button type="submit">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>
	</div>
</body>
</html>