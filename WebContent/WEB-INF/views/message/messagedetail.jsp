<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<p>
<br/>
</p>
<h3>받은쪽지</h3>
<hr/>
<form action="${pageContext.servletContext.contextPath }/sendmessge2.do" method="post">
<div class="form-group">
	<label>보낸사람</label> <input type="email" class="form-control"
		id="exampleFormControlInput1" readonly value="${msgDetail.NAME }${msgDetail.PNAME}(${msgDetail.ID})-${msgDetail.DNAME}" style="background-color: white;" name="receiver">
</div>
<div class="form-group">
	<label>보낸 날짜</label> <input type="text" class="form-control"
	<fmt:formatDate value="${msgDetail.SENDDATE}" pattern="yyyy-MM-dd HH:mm:ss" var="date"/>
		id="exampleFormControlInput1" readonly value="${date}" style="background-color: white;">
</div>

<div class="form-group">
	<label>제목</label> <input type="email" class="form-control"
		id="exampleFormControlInput1" readonly value="${msgDetail.REP}" style="background-color: white;">
</div>

<div class="form-group">
	<label>내용</label>
	<textarea class="form-control" id="exampleFormControlTextarea1"
		rows="10" readonly style="background-color: white;" name="content">${msgDetail.CONTENT }</textarea>
</div>


<button type="submit" class="btn btn-info">답장보내기</button>
<a href="${pageContext.servletContext.contextPath }/messagehome.do"><button type="button" class="btn btn-info">목록</button></a>
</form>