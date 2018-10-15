package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@RequestMapping("/room.do")
	public String chatRoomHandle() {
		
		return "guest.chat";
	}
	
}
