<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to Store</title>
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
<script src="${jsUrl }productScript.js" type="text/javascript">
	
</script>
</head>
<body>
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
					<li><a href="user/showprofile"><span
							class="glyphicon glyphicon-user">Dear, <sec:authentication
									property="name" />
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
		<div align="center" class="container">
			<center>
				<c:forEach var="product" items="${products }">

					<div class="box">
						<div class="imgBox">
							<img alt="image for this product" src="${ imagesUrl}test.jpg">

						</div>

						<a href="showdetails/${product.id }">
							<div align="center" class="details">
								<center>
									<div align="center" class="content">
										<h2>${product.fullName }</h2>
										<table border="0">
											<tr align="center">
												<td>Type :${product.category}</td>
											</tr>
											<tr align="center">
												<td>Company :${product.companyName}</td>
											</tr>
											<c:if test="${product.quantity > 0 }">
												<tr align="center">
													<td>Price :${product.price }</td>
												</tr>
											</c:if>
											<c:if test="${product.quantity==0 }">
												<tr align="center">
													<td><p style="color: red;">Not available</p></td>
												</tr>
											</c:if>
										</table>
									</div>
								</center>
							</div>
						</a>
					</div>
				</c:forEach>
			</center>
		</div>
	</center>
</body>
</html>