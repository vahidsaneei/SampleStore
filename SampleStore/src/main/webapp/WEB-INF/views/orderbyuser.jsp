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
		<jsp:include page="topmenu.jsp" />
	</center>
	<center>

		<h2>Completing order</h2>
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>product name</th>
						<th>price</th>
						<th>quantity</th>
						<th>total price</th>
					</tr>
				</thead>
				<c:set var="total" value="0"></c:set>
				<c:forEach var="item" items="${items }">
					<c:set var="total" value="${total+item.totalprice }"></c:set>
					<tr>
						<td>${item.product.fullName }</td>
						<td>${item.product.price }</td>
						<td>${item.quantity }</td>
						<td>${item.totalprice }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2">Total</td>
					<td colspan="2">${total }</td>
				</tr>
				<tr>
					<td colspan="2">Action</td>
					<td colspan="2"><a href="${appurl }/addtocartlist"
						class="btn btn-success">Edit items</a><a type="button"
						class="btn btn-info" href="${appurl }/store/addorder">Pay Cash</a></td>
				</tr>
			</table>
		</div>
		<div>
			<table class="table table-striped table-hover" title="Customer Info">
				<thead>
					<tr>
						<th>Name</th>
						<th>Address</th>
						<th>Phone Number</th>
					</tr>
				</thead>
				<tr>
					<td><strong>${userinfo.username }</strong></td>
					<td><strong>${userinfo.address }</strong></td>
					<td><strong>${userinfo.phoneNumber}</strong></td>
				</tr>
			</table>
		</div>
	</center>
</body>
</html>