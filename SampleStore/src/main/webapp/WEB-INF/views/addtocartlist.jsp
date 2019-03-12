<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">

<c:url var="cssUrl" value="/resources/css/" />
<c:url var="jsUrl" value="/resources/js/" />
<c:url var="imagesUrl" value="/resources/images/" />

<title>Cart list</title>
<script type="text/javascript" src="${jsUrl }productScript.js">
</script>
<script type="text/javascript" src="${jsUrl }cartscript.js">
</script>
</head>
<body>
	<center>
		<h1>list of sales</h1>
		<div align="center">
			<c:if test="${message==null }">
				<table border="2" id="salelist">
					<tr>
						<th>name</th>
						<th>company</th>
						<th>price</th>
						<th>quantity</th>
						<th>total fee</th>
						<th>action</th>
					</tr>

					<c:forEach var="product" items="${ products}">
						<tr id="row${product.id }">
							<td>${product.fullName }</td>
							<td>${product.companyName }</td>
							<td id="price${product.id }">${product.price }</td>
							<td><input onchange="totalCalc();"
								onkeyup="calcPrice(${product.id });" type="text"
								id="quantity${product.id }" value="1"></td>
							<td id="total${product.id }">0</td>
							<td><a href="javascript:removeproduct(${product.id })">Remove</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="1">total quantity</td>
						<td id="allproduct"></td>
						<td colspan="2">total price</td>
						<td id="totalprice"></td>
						<td></td>
					</tr>
				</table>
				<div align="center">
					<a href="javascript:goToCart()">complete shopping</a> <a
						href="${pageContext.request.contextPath }">continue to
						shopping</a>
				</div>
			</c:if>
			<c:if test="${message!=null }">
				<div align="center">
					<h2
						style="color: orange; border-radius: 2px; border: solid black 1px">${message }</h2>
					<a href="${pageContext.request.contextPath }">continue to
						shopping</a>
				</div>
			</c:if>
		</div>
	</center>
</body>
</html>