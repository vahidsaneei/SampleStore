<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Select description files for product</title>
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
		<jsp:include page="topmenu.jsp" />
	</center>

	<form id="uploadform" action="${home }/products/filesave/${prodid}"
		method="post" enctype="multipart/form-data">
		<div class="container-fluid">
			<table class="table table-bordered" bgcolor="#C0C0C0">
				<tr>
					<th colspan="2" bgcolor="#C0C0C0">Header image</th>
				</tr>
				<tr>
					<td colspan="2"><input type="file" name="headerimage"
						accept=".jpeg,.jpg,.png,.bmp" /></td>
				</tr>
				<tr bgcolor="#C0C0C0">
					<th bgcolor="#C0C0C0">Other images</th>
					<th bgcolor="#C0C0C0">Document for description</th>
				</tr>
				<tr>
					<td><input type="file" name="subimages"
						accept=".jpeg,.jpg,.png,.bmp" /></td>
					<td rowspan="2" bgcolor="#FFFFFF"><input type="file"
						name="descDoc" /></td>
				</tr>
				<tr>
					<td><input type="file" name="subimages"
						accept=".jpeg,.jpg,.png,.bmp" /></td>
				</tr>
				<tr>
					<td><input type="file" name="subimages"
						accept=".jpeg,.jpg,.png,.bmp" /></td>
					<th bgcolor="#C0C0C0">Technical Analytic document</th>
				</tr>
				<tr>
					<td><input class="" type="file" name="subimages"
						accept=".jpeg,.jpg,.png,.bmp" /></td>
					<td><input class="" type="file" name="analysdoc" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2" bgcolor="#C0C0C0"><input
						type="submit" class="btn btn-success" value="Upload"><a
						href="#" class="btn btn-warning">Cancel</a></td>
				</tr>
			</table>
		</div>
	</form>
	</center>
</body>
</html>