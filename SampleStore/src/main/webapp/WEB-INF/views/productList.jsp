<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Products pages</title>
<c:url value="/resources/css/" var="cssUrl" />
<c:url value="/resources/images/" var="imagesUrl" />
<c:url value="/resources/js" var="scriptUrl" />
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="${scriptUrl }productScript.js" type="text/javascript"></script>
</head>
<body>
	<c:set var="home">${pageContext.request.contextPath}</c:set>
	<c:set var="linkDis" value="pointer-event:none;cursor:default;" />
	<c:url value="/logout" var="logoutUrl" />

	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<center>
		<jsp:include page="topmenu.jsp" />
	</center>
	<div class="container">
		<center>
			<a class="btn btn-info" href="${home }/products/newprod">Add new
				product</a>
		</center>
	</div>
	<center>
		<div>
			<center>
				<table class="table table-hover" style="width: 100%">
					<thead>
						<tr>
							<th>Product image</th>
							<th>Prd Name</th>
							<th>Company</th>
							<th>Create date</th>
							<th>Expire date</th>
							<th>Category</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Description</th>
						</tr>
					</thead>
					<c:forEach var="product" items="${products }">
						<tr>
							<td align="center" rowspan="2"><img alt="not found"
								src="${imagesUrl }productsImages/${product.id}/header.jpg"
								style="width: 150px; height: 150px"></td>
							<td>${product.fullName }</td>
							<td>${product.companyName }</td>
							<td>${product.createdDate }</td>
							<td>${product.expiryDate }</td>
							<td>${product.category }</td>
							<td>${product.price }</td>
							<td>${product.quantity }</td>
							<td>${product.description }</td>
						</tr>
						<tr>
							<td colspan="9"><div>
									<a href="${home }/products/remove/${product.id }"
										class="btn btn-danger">Finish Product</a> <a
										href="${home }/products/edit/${product.id }"
										class="btn btn-success">Edit</a> <a href="#"
										class="btn btn-info">Recharge</a> <input style="width: 100px"
										value="100" type="number" min="0" max="1000" step="1">
									<a href="#" class="btn btn-primary">Set discount</a> <input
										style="width: 100px" value="10" type="number" min="0"
										max="1000" step="1"> <a href="#"
										class="btn btn-warning">Out of Stock</a> <a href="#"
										class="btn btn-danger">Increase price </a> <input
										style="width: 100px" value="10" type="number" min="0"
										max="1000" step="1"> <a href="#"
										class="btn btn-success">Decrease price</a> <input
										style="width: 100px" value="10" type="number" min="0"
										max="1000" step="1">

								</div></td>
						</tr>
					</c:forEach>
				</table>
			</center>
		</div>
		<!-- 			<div align="center"> -->
		<%-- 				<span> <c:choose> --%>
		<%-- 						<c:when test="${currentpage>1 }"> --%>
		<%-- 							<a href="${home}/products/${currentpage-1}">Prev</a> --%>
		<%-- 						</c:when> --%>
		<%-- 						<c:otherwise> --%>
		<%-- 							<a style="${linkDis}">Prev</a> --%>
		<%-- 						</c:otherwise> --%>
		<%-- 					</c:choose> <c:if test="${currentpage>=maxpage }"> --%>
		<%-- 						<c:set var="page" value="${maxpage }" /> --%>
		<%-- 					</c:if> <c:if test="${currentpage-1 < 1}"> --%>
		<%-- 						<c:set var="page" value="1" /> --%>
		<%-- 					</c:if> <c:if test="${currentpage>=1 && currentpage<=maxpage }"> --%>
		<%-- 						<c:set var="page" value="${currentpage }" /> --%>
		<%-- 					</c:if> ${page } of ${maxpage } <c:choose> --%>
		<%-- 						<c:when test="${currentpage<maxpage }"> --%>
		<%-- 							<a href="${home}/products/${currentpage+1}">Next</a> --%>
		<%-- 						</c:when> --%>
		<%-- 						<c:otherwise> --%>
		<%-- 							<a style="${linkDis}">Next</a> --%>
		<%-- 						</c:otherwise> --%>
		<%-- 					</c:choose> --%>
		<!-- 				</span> -->
		<!-- 			</div> -->
	</center>
	</center>
</body>
</html>