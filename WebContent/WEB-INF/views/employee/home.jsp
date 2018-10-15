<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${changPass && !empty changPass }">
	<div class="alert alert-success alert-dismissible fade show" role="alert">
		<strong>비밀번호 변경 성공</strong>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>
<p>
	<br>
</p>
<h3>나의 정보</h3>
<hr/>
<form>
	<div class="form-row">
		<div class="col-5">
			<b>이름</b> <input type="text" class="form-control col-10"
				value="${user.NAME }" readonly style="background-color: white;">
		</div>
	</div>
	<p>
		<br />
	<div class="form-row">
		<div class="col-5">
			<b>부서</b> <input type="text" class="form-control col-10"
				value="${user.DNAME }" readonly style="background-color: white;">
		</div>

		<div class="col-5">
			<b>직책</b> <input type="text" class="form-control col-10"
				value="${user.PNAME }" readonly style="background-color: white;">
		</div>
	</div>
</form>
</p>
<form action="${pageContext.servletContext.contextPath }/changpass.do">
	<div class="form-row">
		<div class="col-5">
			<b>아이디</b> <input type="text" class="form-control col-10"
				value="${user.ID}" readonly style="background-color: white;">
			<p>
				<br />
			</p>
			<button type="submit" class="btn btn-secondary">비밀번호변경</button>
		</div>
	</div>
</form>
