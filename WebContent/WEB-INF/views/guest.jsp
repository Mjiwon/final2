<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/signin.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<title>그룹웨어</title>

<body class="text-center">
	<form class="form-signin">
		<img class="mb-4" src="${pageContext.servletContext.contextPath }/image/guestmain.jpg" alt=""
			width="300" height="300" style="border-radius: 10px;">
		<h1 class="h3 mb-3 font-weight-normal">GROUP WARE</h1>
		<label for="inputEmail" class="sr-only">아이디</label> <input
			type="email" id="inputid" class="form-control"
			placeholder="아이디" required autofocus> <label
			for="inputPassword" class="sr-only">패스워드</label> <input
			type="password" id="inputPassword" class="form-control"
			placeholder="패스워드" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				로그인 유지
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2018 JIWONI COMPANY</p>
	</form>
</body>
</html>