<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${ pageContext.request.userPrincipal.name}</title>
</head>
<body>
	<center>
		<div>
			<h2>Welcome ,${user.username }</h2>
			<br> <a href="#">My profile</a><br> <a
				href="store/getuserorders/${pageContext.request.userPrincipal.name }">My
				orders</a><br> <a
				href="store/getusercopmments/${pageContext.request.userPrincipal.name }">My
				comments</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath }">back to store</a>
		</div>
	</center>
</body>
</html>