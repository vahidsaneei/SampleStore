<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="${jsUrl }productScript.js" type="text/javascript"></script>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Order</title>
</head>
<body>
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
				<c:forEach var="product" items="${products }">
					<tr>
						<td>${product.key.fullName }</td>
						<td>${product.key.price }</td>
						<td>${product.value }</td>
						<td>${product.key.price }*${product.value }</td>
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
			<a type="button" class="btn btn-info" href="javascript:addOrder()">Pay
				Cash</a>
		</div>
	</center>
</body>
</html>