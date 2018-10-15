<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>
<br/>
</p>
<h3>쪽지함</h3>
<hr/>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
  <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="#">받은 쪽지 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">보낸 쪽지</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="${pageContext.servletContext.contextPath }/messagesend.do"><b style="color: navy;">쪽지보내기</b></a>
      </li>
            <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
       	   정렬
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">이름순</a>
          <a class="dropdown-item" href="#">날짜순</a>
          <a class="dropdown-item" href="#">안읽은순</a>
        </div>
      </li>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<ul class="list-group">
	<ul class="list-group">
	<c:forEach var="msg" items="${messageList }">
		<li class="list-group-item d-flex justify-content-between align-items-center"><a href="${pageContext.servletContext.contextPath }/msgdetail.do?code=${msg.CODE }">${msg.NAME }${msg.PNAME }-${msg.DNAME }</a>
 		<c:choose>
	 		<c:when test="${msg.RECEIVEDATE == null}">
				<span class="badge badge-primary badge-pill">안읽음</span>
			</c:when>
			<c:otherwise>
				<span class="badge badge-danger">읽음</span>
			</c:otherwise>
		</c:choose> 
		 </li>
	</c:forEach>
	</ul>	

</ul>	