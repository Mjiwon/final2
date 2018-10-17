package app.controller;

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
		wr.setAttribute("chat", "timchat", WebRequest.SCOPE_SESSION);
		return "guest.timchat";
	}
	
}
