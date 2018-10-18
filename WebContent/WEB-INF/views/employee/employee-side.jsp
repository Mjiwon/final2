<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="sidebar-sticky">
	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link active" href="${pageContext.servletContext.contextPath }/index.do"> <span
				data-feather="home"></span> 나의정보 <span class="sr-only">(current)</span>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath }/messagehome.do"> <span
				data-feather="file"></span> 쪽지함
		</a></li>
		<li class="nav-item"><a class="nav-link" href="#"> <span
				data-feather="shopping-cart"></span> 게시판
		</a></li>
		<li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath }/chat/room.do"> <span
				data-feather="users" ></span> 채팅방 <span id="newChat"></span>
		</a></li>
	</ul>
	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>${user.DNAME}</span> <a
			class="d-flex align-items-center text-muted" href="#"> <span
			data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath }/chat/timRoom.do"> <span
				data-feather="file-text"></span> 부서채팅방 <span id="newTimChat"></span>
		</a></li>
		<li class="nav-item"><a class="nav-link" href="#"> <span
				data-feather="file-text"></span> Last quarter
		</a></li>
	</ul>
	<hr />
	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>Saved reports</span> <a
			class="d-flex align-items-center text-muted" href="#"> <span
			data-feather="plus-circle"></span>
		</a>
	</h6>
	<div id="alert" style="font-size: .75em"></div>
</div>
</nav>
<script>
	var ws = new WebSocket("ws://" + location.host
			+ "${pageContext.servletContext.contextPath}/relay.do");

	ws.onmessage = function(got) {
		var obj = JSON.parse(got.data);
		switch (obj.mode) {
		case "login":
			loginAlertHandle(obj);
			break;
		case "doubleLogin" :
			doubleLoginHandle(obj);
			break;
		case "remsg" :
			receiverMsgHandle(obj);
			break;
		case "public" :
			publicHandle(obj);
			break;
		default : 
			timChatHandle(obj);
			break;
		}
	};

	var loginAlertHandle = function(obj) {
		var html = "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">";
		html += "<strong>로그인</strong><br/>" + obj.NAME + " " + obj.PNAME + "("
				+ obj.DNAME + ")"
		html += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">";
		html += "<span aria-hidden=\"true\">&times;</span>";
		html += "</div>";

		document.getElementById("alert").innerHTML += html;
	};
	
	var doubleLoginHandle = function(obj){
		var html =
			"<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">";
		html +="<strong>중복로그인</strong> <br/>다른 컴퓨터에서 로그인되었습니다.<br/>";
		html += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">";
		html += "<span aria-hidden=\"true\">&times;</span>";
		html += "</button>";
		html += "</div>";
		document.getElementById("alert").innerHTML += html;
		document.getElementById("alert").id= "";
	};
	
	var receiverMsgHandle = function(obj){
		console.log(obj);
		var html = "<div class=\"alert alert-info alert-dismissible fade show\" role=\"alert\">";
		html += "<strong>쪽지알림</strong><br/>" + obj.receiverMsg.NAME + " " + obj.receiverMsg.PNAME + "("
				+ obj.receiverMsg. nDNAME + ")으로부터 메세지가 도착했습니다."
		html += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">";
		html += "<span aria-hidden=\"true\">&times;</span>";
		html += "</div>";

		document.getElementById("alert").innerHTML += html;
	};
	
	var publicHandle = function(obj){
		console.log(obj);
		var html = "<span class=\"badge badge-primary\"> new";
		html += "</span>";
		
		document.getElementById("newChat").innerHTML = html;
		
	};
	
	var timChatHandle = function(obj){
		console.log(obj);
		var html = "<span class=\"badge badge-primary\"> new";
		html += "</span>";
		
		document.getElementById("newTimChat").innerHTML = html;
		
	};
	
</script>