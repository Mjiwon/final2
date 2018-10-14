<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!changPass }">
	<div class="alert alert-danger" role="alert">
		 비밀번호 변경 실패<br/> <small>다시한번 변경해주세요.</small>
		 <button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div> 
</c:if>

<form method="post" action="${pageContext.servletContext.contextPath }/changpass.do">
<p>
<br/>
</p>

<h3>비밀번호 변경</h3>
<small>기본패스워드는 [<b style="color: red;"> 1111 </b>]입니다.</small>
  <div class="form-group row">
    <label class="col-sm-2 col-form-label">아이디</label>
    <div class="col-sm-10">
     <input type="text" readonly class="form-control-plaintext" name="id" value="${user.ID}">
    </div>
  </div>
  <div class="form-group row">
    <label class="col-sm-2 col-form-label">현재 비밀번호</label>
    <div class="col-sm-5">
      <input type="password" class="form-control" name="pass" placeholder="Password" >
    </div>
  </div>
  <div class="form-group row">
    <label class="col-sm-2 col-form-label">새 비빌번호</label>
    <div class="col-sm-5">
      <input type="password" class="form-control" name="newpass1" placeholder="Password">
    </div>
   </div>
   <div class="form-group row">
    <label class="col-sm-2 col-form-label">새 비밀번호 확인</label>
    <div class="col-sm-5">
      <input type="password" class="form-control" name="newpass2" placeholder="Password">
    </div>
  </div>
  <button type="submit" class="btn btn-info">변경</button>
</form>