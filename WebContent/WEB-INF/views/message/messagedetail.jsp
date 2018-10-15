<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="form-group">
	<label>보낸사람</label> <input type="email" class="form-control"
		id="exampleFormControlInput1" readonly value="${msgDetail.RECEVER }${msgDetail.PNAME}(${msgDetail.ID})-${msgDetail.DNAME}" style="background-color: white;">
</div>
<div class="form-group">
	<label>보낸 날짜</label> <input type="text" class="form-control"
		id="exampleFormControlInput1" readonly value="${msgDetail.SENDDATE }" style="background-color: white;">
</div>

<div class="form-group">
	<label>내용</label>
	<textarea class="form-control" id="exampleFormControlTextarea1"
		rows="10" readonly style="background-color: white;">${msgDetail.CONTENT }</textarea>
</div>
<form action="${pageContext.servletContext.contextPath }/messagehome.do">
<button type="submit" class="btn btn-info">목록</button>
</form>