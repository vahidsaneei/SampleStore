<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/resources/css/" var="cssUrl" />
<c:url value="/resources/js/" var="scriptUrl" />
<c:url value="/resources/images/" var="imagesUrl" />
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<title>Register new User</title>
</head>
<body>
	<center>
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
		<div align="center">
			<h2>Welcome to registration page</h2>
		</div>
		<div></div>
		<form:form method="POST" action="saveuser" modelAttribute="user">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<table>
				<form:hidden path="id" />
				<tr>
					<td><form:label path="username">User Name</form:label></td>
					<td><form:input path="username" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:password path="password" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td><form:label path="gender">Gender</form:label></td>
					<td><form:radiobutton path="gender" cssClass="radio-inline"
							value="M" label="Male" /> <form:radiobutton path="gender"
							cssClass="radio-inline" value="F" label="Female" /></td>
				</tr>
				<tr>
					<td><form:label path="address">Address</form:label></td>
					<td><form:textarea path="address" cssClass="form-control"
							rows="5" cols="30" /></td>
				</tr>
				<tr>
					<td><form:label path="phoneNumber">Mobile number</form:label></td>
					<td><form:input path="phoneNumber" cssClass="form-control" /></td>
				</tr>
			</table>
			<br>
			<center>
				<input type="submit" class="btn btn-info" value="Register" />
			</center>
		</form:form>
		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				<strong>${error}</strong>
			</div>
		</c:if>
	</center>
</body>
</html>