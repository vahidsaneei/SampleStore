<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${product.fullName }</title>

<c:url value="/resources/css/" var="cssUrl" />
<c:url value="/resources/js/" var="scriptUrl" />
<c:url value="/resources/images/" var="imagesUrl" />
<script type="text/javascript" src="${scriptUrl }productScript.js"></script>
<script type="text/javascript" src="${scriptUrl }jquery-3.2.1.min.js"></script>
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
	<center>
		<c:set var="home" value="${pageContext.request.contextPath }" />
		<c:set var="logoutUrl" value="logout" />
		<form id="logoutForm" action="${home }/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<div align="center">
			<ul>
				<sec:authorize access="isAuthenticated()">
					<li><a href="${home }/user/showprofile"><span
							class="glyphicon glyphicon-user">Dear, <sec:authentication
									property="name" />
						</span></a></li>
					<li><a href="javascript:doLogout()"><span
							class="glyphicon glyphicon-log-out">Logout</span></a></li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${pageContext.request.contextPath }/login"><span
							class="glyphicon glyphicon-user">Login</span></a></li>
				</sec:authorize>
				<li><a id="cartlink" href="addtocartlist"><span
						class="glyphicon glyphicon-shopping-cart">Cart</span></a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="${home }/products/1"><span
							class="glyphicon glyphicon-list-alt">Product Management</span></a></li>
					<li><a href="${home }/users"><span
							class="glyphicon glyphicon-list">Users Management</span></a></li>
					<li><a href="${home }/orders"><span
							class="glyphicon glyphicon-pushpin">Orders Management</span></a></li>
					<li><a href="${home }/stores"><span
							class="glyphicon glyphicon-floppy-disk">Store Management</span></a></li>
				</sec:authorize>
				<li><a href="${home }/searchpage"><span
						class="glyphicon glyphicon-search">Search</span></a></li>
				<li><a href="${home }"><span
						class="glyphicon glyphicon-home">Home</span></a></li>
			</ul>
		</div>
		<div align="center">
			<c:choose>
				<c:when test="${liked != null }">
					<c:set var="likeclass" value="btn btn-danger btn-lg" />
					<c:set var="likeoperation"
						value="javascript:setUnlike(${product.id })" />
				</c:when>
				<c:otherwise>
					<c:set var="likeclass" value="btn btn-default btn-lg" />
					<c:set var="likeoperation"
						value="javascript:setlike(${product.id })" />
				</c:otherwise>
			</c:choose>
			<h2>${product.fullName }</h2>
		</div>
		<div align="center" style="width: 90%; overflow: auto;">

			<table class="table" style="width: 100%">
				<tr align="center">
					<td rowspan="11" style="background: lightblue; width: 30%"><div
							class="imgbox">
							<img src="${imagesUrl }${product.id}/header.jpg" />

						</div></td>
				</tr>
				<tr align="right">
					<td colspan="3" style="height: 35px">${product.fullName}</td>
					<td colspan="2"
						style="background: lightgray; height: 35px; width: 20%">Product
						name</td>
				</tr>
				<tr align="right">
					<td colspan="3" style="height: 35px">${product.seller}</td>
					<td colspan="2"
						style="background: lightgray; height: 35px; width: 20%">Store
						Name</td>
				</tr>
				<tr align="right">
					<td colspan="3" style="height: 35px">${product.companyName}</td>
					<td colspan="2"
						style="background: lightgray; height: 35px; width: 20%">Company
						name</td>
				</tr>
				<tr align="right">
					<td colspan="3" style="height: 35px">${product.createdDate}</td>
					<td colspan="2"
						style="background: lightgray; height: 35px; width: 20%">Created
						date</td>
				</tr>
				<tr align="right">
					<td colspan="3" style="height: 35px">${product.expiryDate}</td>
					<td colspan="2"
						style="background: lightgray; height: 35px; width: 20%">Expiry
						date</td>
				</tr>
				<tr align="right">
					<c:if test="${peoduct.quantity>0 }">
						<td colspan="3" style="height: 35px">${product.price}</td>
						<td colspan="2"
							style="background: lightgray; height: 35px; width: 20%">Price</td>
					</c:if>
					<c:if test="${product.quantity==0 }">
						<td align="center" colspan="5"
							style="height: 35px; background-color: red"><strong>This
								product finished out!</strong></td>
					</c:if>
				</tr>
				<tr align="right">
					<td colspan="3" style="height: 35px">${product.category}</td>
					<td colspan="2"
						style="background: lightgray; height: 35px; width: 20%">category</td>
				</tr>
				<tr align="right">
					<td colspan="3" style="height: 35px">${product.description}</td>
					<td colspan="2"
						style="background: lightgray; height: 35px; width: 20%">Description</td>
				</tr>
				<tr>
					<td colspan="5">
						<div align="left" class="btn-group-horizontal">
							<a id="likelink${product.id}" title="Like this product"
								class="${likeclass }" href="${likeoperation }"> <span
								class="glyphicon glyphicon-heart-empty"></span>
							</a>
							<c:if test="${product.quantity>0 }">
								<a id="cartlink${product.id}" class="btn btn-success btn-lg"
									title="Add to cart" onclick="addToCart(${product.id },1)"
									href="${home }/addtocartlist/${product.id }"><span
									class="glyphicon glyphicon-shopping-cart"></span></a>
							</c:if>

							<a class="btn btn-info btn-lg" href="${home }"
								title="Back to home"><span
								class="glyphicon glyphicon-th-list"></span></a>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="5"><div>
							<form:form action="${home }/store/addcomment/${product.id}"
								method="POST">
								<textarea name="comment" id="comment"
									style="float: left; resize: none; border-radius: 10px;"
									cols="50" rows="5"></textarea>
								<input style="float: left;" type="submit"
									class="btn btn-primary" value="Post Comment">
							</form:form>
						</div></td>
				</tr>
			</table>
		</div>
		<div>
		</div>
		<div>
			<ul>
				<c:forEach items="${commentList }" var="comment">
					<li style="float: none;">
						<div>
							<div>
								<span><strong>${comment.user.username }</strong> </span>
							</div>
						</div>
						<p>${comment.text }</p>
					</li>
				</c:forEach>
			</ul>
		</div>
	</center>
</body>
</html>