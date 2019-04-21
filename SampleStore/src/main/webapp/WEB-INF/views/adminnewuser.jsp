<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Users</title>
<c:url value="/resources/css/" var="cssUrl" />
<c:url value="/resources/images/" var="imageUrl" />
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
<title>add new user</title>
</head>
<body>
	<c:url value="${pageContext.request.contextPath }" var="appurl"></c:url>
	<c:url value="/logout" var="logoutUrl" />
	<center>
		<jsp:include page="topmenu.jsp" />
	</center>
	<center>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<form:form action="${appurl }/users/saveuser" method="POST"
			modelAttribute="user">
			<table>
				<form:hidden path="id" />
				<tr>
					<td>User name</td>
					<td><form:input path="username" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:password path="password" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Phone number</td>
					<td><form:input path="phoneNumber" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><form:textarea path="address" col="30" rows="5"
							cssClass="form-control" /></td>
				</tr>
				<!-- 				<tr> -->
				<!-- 					<td>Expire Date</td> -->
				<%-- 					<td><form:input path="expiryDate" type="text" --%>
				<%-- 							cssClass="form-control" /></td> --%>
				<!-- 				</tr> -->
				<tr>
					<td>Roles</td>
					<td><form:select path="userRoles">
							<form:option value="1" label="--Select role type--" />
							<form:options items="${roleList }" />
						</form:select></td>
				</tr>
				<!-- 				<tr> -->
				<!-- 					<td>Price</td> -->
				<%-- 					<td><form:input path="price" cssClass="form-control" /></td> --%>
				<!-- 				</tr> -->
				<!-- 				<tr> -->
				<!-- 					<td>Quantity</td> -->
				<%-- 					<td><form:input path="quantity" cssClass="form-control" /></td> --%>
				<!-- 				</tr> -->
				<!-- 				<tr> -->
				<!-- 					<td>Description</td> -->
				<%-- 					<td><form:textarea path="description" rows="6" cols="30" --%>
				<%-- 							cssClass="form-control" /></td> --%>
				<!-- 				</tr> -->
				<!-- 				<tr> -->
				<!-- 					<td colspan="2" align="center"><button class="btn btn-success" -->
				<!-- 							type="submit">Save</button> <a class="btn btn-warning" -->
				<!-- 						href="storeManaging">Cancel</a></td> -->
				<!-- 				</tr> -->
				<!-- 				<tr> -->
				<%-- 					<c:if test="${not empty error}"> --%>
				<!-- 						<div class="text bg-danger"> -->
				<%-- 							<strong>${error}</strong> --%>
				<!-- 						</div> -->
				<%-- 					</c:if> --%>
				<!-- 				</tr> -->
			</table>
		</form:form>
	</center>
</body>
</html>