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
		<jsp:include page="topmenu.jsp" />
	</center>
	<center>
		<div align="center">
			<h2>Welcome to registration page</h2>
		</div>
		<div></div>
		<form:form method="POST" action="saveuser" modelAttribute="user"
			enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<table>
				<form:hidden path="id" />
				<tr>
					<td><form:label path="username">User Name</form:label></td>
					<td><form:input path="username" cssClass="form-control"/></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:password path="password" cssClass="form-control" /></td>
				</tr>
				<tr>
					<td><form:label path="passwordConfirm">Repeat Password</form:label></td>
					<td><form:password path="passwordConfirm"
							cssClass="form-control" /></td>
				</tr>
			</table>
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