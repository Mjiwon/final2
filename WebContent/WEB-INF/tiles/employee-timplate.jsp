<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="http://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/employeeboard.css"> 	
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<title>그룹웨어</title>
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="nav"/>
		<div class="row">
			<div class="col-md-2">
				<tiles:insertAttribute name="left"/>
			</div>
			<div class="col-md-9">
				<tiles:insertAttribute name="center"/>
			</div>
		</div>
	</div>
</body>
</html>