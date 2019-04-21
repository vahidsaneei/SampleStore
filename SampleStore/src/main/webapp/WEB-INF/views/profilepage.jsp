<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Profile</title>
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
	<c:set var="home" value="${pageContext.request.contextPath }" />
	<c:url value="/logout" var="logoutUrl" />
	<center>
		<jsp:include page="topmenu.jsp" />
	</center>
	<center>
		<div>
			<ul>
				<li><a href="#">My Information</a></li>
				<li><a
					href="${home }/store/getuserorders/${pageContext.request.userPrincipal.name }">My
						orders</a></li>
				<li><a
					href="${home }/store/getusercopmments/${pageContext.request.userPrincipal.name }">My
						comments</a></li>
				<li><a href="${home }/user/myfavorites">My favorite
						Products</a></li>
			</ul>
		</div>
	</center>
</body>
</html>