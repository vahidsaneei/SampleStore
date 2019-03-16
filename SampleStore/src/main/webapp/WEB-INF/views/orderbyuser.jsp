<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/resources/css/" var="cssUrl" />
<c:url var="jsUrl" value="/resources/js/" />
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }productDetailCss.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<c:set value="${pageContext.request.contextPath }" var="appurl"></c:set>
<script src="${jsUrl }productScript.js" type="text/javascript"></script>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Order</title>
</head>
<body>
	<c:url value="/logout" var="logoutUrl" />
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
				<li><a href="${appurl }"><span
						class="glyphicon glyphicon-home">Home</span></a></li>
			</ul>
		</div>
	</center>
	<center>
		<h2>Completing order</h2>
		<div>
			<table border="1">
				<tr>
					<th>product name</th>
					<th>price</th>
					<th>quantity</th>
					<th>total price</th>
				</tr>
				<c:forEach var="item" items="${items }">
					<tr>
						<td>${item.product.fullName }</td>
						<td>${item.product.price }</td>
						<td>${item.quantity }</td>
						<td>${item.product.price * item.quantity }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<h3>Customer Info</h3>
			<br />
			<h4>Customer name ${userinfo.username }</h4>
			<br />
			<h4>Address ${userinfo.address }</h4>
			<br />
			<h4>PhoneNumber ${userinfo.phoneNumber }</h4>
		</div>
		<div>
			<a type="button" class="btn btn-info"
				href="${appurl }/store/addorder">Pay Cash</a>
		</div>
	</center>
</body>
</html>