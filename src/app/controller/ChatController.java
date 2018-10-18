package app.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@RequestMapping("/room.do")
	public String chatRoomHandle(WebRequest wr) {
		wr.setAttribute("chat", "public", WebRequest.SCOPE_SESSION);
		return "guest.chat";
	}
	
	@RequestMapping("/timRoom.do")
	public String chatTimRoomHandler(WebRequest wr) {
		Map map = (Map)wr.getAttribute("user", WebRequest.SCOPE_SESSION);
		
		wr.setAttribute("chat", ""+map.get("DID"), WebRequest.SCOPE_SESSION);
		return "guest.timchat";
	}
	
}
