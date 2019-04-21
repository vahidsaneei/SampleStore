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
		<jsp:include page="topmenu.jsp" />
	</center>
	<center>
		<h1>list of sales</h1>
		<div align="center">
			<c:if test="${not empty error}">
				<div class="text bg-danger text-danger">
					<strong>${error}</strong>
				</div>
			</c:if>
			<c:choose>
				<c:when test="${sessionScope.cart!=null }">
					<form action="${appurl }/store/completesale" method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>name</th>
									<th>company</th>
									<th>price</th>
									<th>quantity</th>
									<th>total fee</th>
									<th>action</th>
								</tr>
							</thead>
							<c:set var="total" value="0"></c:set>
							<c:forEach var="item" items="${ sessionScope.cart}">
								<c:set var="total" value="${total+item.totalprice }"></c:set>
								<tr>
									<td>${item.product.fullName }</td>
									<td>${item.product.companyName }</td>
									<td>${item.product.price }</td>
									<td><input type="number" id="quantity${item.product.id }"
										name="quantity" min="1" max="10" step="1"
										value="${item.quantity }"></td>
									<td>${item.totalprice }</td>
									<td><a
										href="${appurl }/removefromcart/${item.product.id }"
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
							<input type="submit" value="Complete Shopping"
								class="btn btn-success" /> <a href="${appurl }"
								class="btn btn-info">continue to shopping</a>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<div align="center">
						<h2
							style="color: orange; border-radius: 2px; border: solid black 1px">Your
							cart is empty!</h2>
						<a href="${appurl}" class="btn btn-info">continue to shopping</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</center>
</body>
</html>