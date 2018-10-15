<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>
<br/>
</p>
<h3>쪽지보내기</h3>
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
        <a class="nav-link" href="#"><b style="color: navy;">쪽지보내기</b></a>
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
<form action="${pageContext.servletContext.contextPath }/messagesend.do" method="post">
	<div class="form-group">
		<label>보내는 사람</label> 
		<input type="user" class="form-control"
			placeholder="${user.NAME }${user.PNAME }(${user.ID })-${user.DNAME}">
	</div>
	<div class="form-group">
		<label>받는사람</label> 
		<select class="form-control"
			id="exampleFormControlSelect1" name="receiver">
			<c:forEach var="i" items="${allmember }">
				<option value="${i.ID}">${i.NAME}${i.PNAME }(${i.ID })-${i.DNAME } </option>
			
			</c:forEach>
		</select>
		<script>
/* 			var allmember= function(){
				var req = new XMLHttpRequest();
				req.open("get", "${pageContext.servletContext.contextPath}/allmember.do", true);
				req.onreadystatechange = function(){
					if(this.readyState == 4 )
						var obj = JSON.parser
				}
			} */
		</script>
	</div>
	<div class="form-group">
		<label for="exampleFormControlTextarea1">보내는 내용</label>
		<textarea class="form-control" id="exampleFormControlTextarea1"
			rows="3" name="content"></textarea>
	</div>
	<button type="submit" class="btn btn-info">보내기</button>
</form>