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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<title>Add and Update product</title>
</head>
<body>
	<c:set var="appPath"
		value="${pageContext.request.contextPath }/products/" />
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
	<div align="center">
		<h2 class="text text-primary bg-info">Add and Update</h2>
	</div>
	<div align="center">
		<form:form action="${appPath }saveprod" method="POST"
			modelAttribute="product">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<table>
				<form:hidden path="id" />
				<tr>
					<td>Name</td>
					<td><form:input path="fullName" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Store name</td>
					<td><form:input path="seller" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Company name</td>
					<td><form:input path="companyName" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Created Date</td>
					<td><form:input path="createdDate" type="text"
							cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Expire Date</td>
					<td><form:input path="expiryDate" type="text"
							cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Category</td>
					<td><form:select path="category">
							<form:option value="1" label="--Select business type--" />
							<form:options items="${businessList }" />
						</form:select></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><form:input path="price" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Quantity</td>
					<td><form:input path="quantity" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><form:textarea path="description" rows="6" cols="30"
							cssClass="form-control" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button class="btn btn-success"
							type="submit">Save</button> <a class="btn btn-warning"
						href="storeManaging">Cancel</a></td>
				</tr>
				<tr>
					<c:if test="${not empty error}">
						<div class="text bg-danger">
							<strong>${error}</strong>
						</div>
					</c:if>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>