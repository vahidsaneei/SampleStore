<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
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
<title>Cart list</title>
<script type="text/javascript" src="${jsUrl }productScript.js">
	
</script>
<script type="text/javascript" src="${jsUrl }cartscript.js">
	
</script>
</head>
<body>
	<center>
		<div align="center">
			<c:set var="appurl" value="${pageContext.request.contextPath }" />
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
				<li><a href="searchpage"><span
						class="glyphicon glyphicon-search">Search</span></a></li>
				<li><a href="${appurl }"><span
						class="glyphicon glyphicon-home">Home</span></a></li>
			</ul>
		</div>
		<h1>list of sales</h1>
		<div align="center">
			<c:choose>
				<c:when test="${sessionScope.cart!=null }">
					<table border="2">
						<tr>
							<th>name</th>
							<th>company</th>
							<th>price</th>
							<th>quantity</th>
							<th>total fee</th>
							<th>action</th>
						</tr>
						<c:set var="total" value="0"></c:set>
						<c:forEach var="item" items="${ sessionScope.cart}">
							<c:set var="total"
								value="${total+item.product.price*item.quantity }"></c:set>
							<tr>
								<td>${item.product.fullName }</td>
								<td>${item.product.companyName }</td>
								<td>${item.product.price }</td>
								<td>${item.quantity }</td>
								<td>${item.product.price*item.quantity }</td>
								<td><a href="${appurl }/removefromcart/${item.product.id }"
									class="btn btn-danger">Remove</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4">total price</td>
							<td>${total }</td>
							<td></td>
						</tr>
					</table>
					<div align="center">
						<a href="${appurl }/store/completesale" class="btn btn-success">complete shopping</a> <a
							href="${appurl }" class="btn btn-info">continue
							to shopping</a>
					</div>
				</c:when>
				<c:otherwise>
					<div align="center">
						<h2
							style="color: orange; border-radius: 2px; border: solid black 1px">Your
							cart is empty!</h2>
						<a href="${appurl}" class="btn btn-info">continue
							to shopping</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</center>
</body>
</html>