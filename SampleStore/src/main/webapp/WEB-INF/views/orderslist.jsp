<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Orders Management</title>
<c:url value="/resources/css/" var="cssUrl" />
<c:url value="/resources/js/" var="scriptUrl" />
<c:url value="/resources/images/" var="imagesUrl" />
<script type="text/javascript" src="${scriptUrl }productScript.js"></script>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link rel="stylesheet" href="${cssUrl }styles.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<c:url value="/logout" var="logoutUrl" />
	<c:set var="appurl" value="${pageContext.request.contextPath }" />
	<jsp:useBean id="today" class="java.util.Date" />
	<!-- csrf for log out-->
	<center>
		<jsp:include page="topmenu.jsp" />
	</center>
	<div align="center">
		<p>
			Today :
			<fmt:formatDate value="${today }" type="date" pattern="dd-MM-yyyy" />
		</p>
		<center>
			<div align="center">
				<h2>Orders List</h2>
			</div>
			<div align="center">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Date</th>
							<th>Delivery Date</th>
							<th>Customer</th>
							<th>Status</th>
							<th>Actions</th>
						</tr>
					</thead>
					<c:forEach items="${orders }" var="order">
						<tr>
							<td>${order.id }</td>
							<td><fmt:formatDate value="${order.orderDate }" type="date"
									pattern="dd-MM-yyyy" /></td>
							<td><fmt:formatDate value="${order.deliveryDate }"
									type="date" pattern="dd-MM-yyyy" /></td>
							<td><a class="btn btn-primary" href="#">${order.user.username }</a></td>
							<td><c:choose>
									<c:when test="${order.success}">
										<span style="background-color: green">Success</span>
									</c:when>
									<c:when
										test="${!order.success && (order.deliveryDate.date lt today.date)}">
										<span style="background-color: red">out of Date</span>
									</c:when>
									<c:otherwise>In order yet</c:otherwise>
								</c:choose></td>
							<td colspan="4"><span class="btn-group"><c:choose>
										<c:when test="${!order.success }">
											<a href="#" class="btn btn-warning">Cancel order</a>
											<a href="#" class="btn btn-info">Edit</a>
										</c:when>
									</c:choose> <a href="${appurl }/orders/getorder/${order.id}"
									class="btn btn-success">Show Details</a> <a
									href="${appurl }/orders/removeorders/${order.id}"
									class="btn btn-danger">Delete</a> </span></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</center>
	</div>
</body>
</html>