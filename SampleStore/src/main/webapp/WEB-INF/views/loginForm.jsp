<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login Page</title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<c:url var="cssUrl" value="/resources/css/" />
<c:url var="jsUrl" value="/resources/js/" />
<c:url var="imagesUrl" value="/resources/images/" />

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<link rel="stylesheet" href="${cssUrl }bootstrap.min.css">
<link rel="stylesheet" href="${cssUrl }welcomstyle.css">
<link rel="stylesheet" href="${cssUrl }loginform.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body onload='document.loginForm.username.focus();'>

	<div align="center">
		<h1>Login to Store</h1>

		<div id="login-box">

			<h2>Login with Username and Password</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form name='loginForm'
				action="<c:url value='j_spring_security_check' />" method='POST'>

				<table>
					<tr>
						<td>User:</td>
						<td><input type='text' name='username' value=''
							placeholder="insert your username"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type='password' name='password' /></td>
					</tr>
					<tr>
						<td colspan='2'><input name="submit" type="submit"
							value="Login" /></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			</form>
		</div>
		<div align="center">
			<a href="${pageContext.request.contextPath }/register">I'm a new
				User</a>
		</div>
	</div>
</body>
</html>