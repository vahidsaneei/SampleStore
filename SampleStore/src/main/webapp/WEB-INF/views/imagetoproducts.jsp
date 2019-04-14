<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Select an image for product</title>
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
</head>
<body>
	<c:set var="home" value="${pageContext.request.contextPath }" />
	<center>
		<div>
			<form id="uploadform" action="${home }/products/filesave/${prodid}"
				method="post" enctype="multipart/form-data">
				<table class="table">
					<tr>
						<td>Header image</td>
						<td><input type="file" name="headerfile" /></td>
					</tr>
					<tr>
						<td>Other images</td>
						<td>------------------------</td>
					</tr>
					<tr>
						<td>1</td>
						<td><input type="file" name="upFile" /></td>
					</tr>
					<tr>
						<td>2</td>
						<td><input type="file" name="upFile" /></td>
					</tr>
					<tr>
						<td>3</td>
						<td><input type="file" name="upFile" /></td>
					</tr>
					<tr>
						<td>4</td>
						<td><input type="file" name="upFile" /></td>
					</tr>
					<tr>
						<td>Action</td>
						<td><input type="submit" value="Upload"></td>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>
</html>