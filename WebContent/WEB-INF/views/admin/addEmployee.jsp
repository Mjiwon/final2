<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>사원등록</h2>
<form>
	<div class="form-group">
		<label>이름</label> 
		<input type="text" class="form-control" placeholder="Example input">
	</div>
	<div class="form-group">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label>부서명</label>
				<select class="form-control">
					<option>부서를 선택해주세요</option>
					<c:forEach var="de" items="${department }">
						<option value="${de.DID }">${de.DNAME }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group col-md-6">
				<label>직책</label>
				<select class="form-control">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
  		</div>
	</div>
	<div class="form-group">
		<label>입사일</label> <input
			type="date" class="form-control" placeholder="Another input">
	</div>
	<div class="form-group">
		<button type="submit"  class="form-control btn btn-outline-primary">사원등록</button>
	</div>
</form>