package app.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import app.service.AlertService;

@Controller
public class ChatSocketController extends TextWebSocketHandler {
	List<WebSocketSession> sockets;

	@Autowired
	Gson gson;

	@Autowired
	AlertService alertService;

	public ChatSocketController() {
		sockets = new ArrayList<>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sockets.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sockets.remove(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String chatMode = (String)session.getAttributes().get("chat");
		
		Map user = (Map)session.getAttributes().get("user");
		
		String got = message.getPayload();
		
		Map msg = new HashMap<>();
		msg = gson.fromJson(got, Map.class);
		msg.put("sendUser", user);
		
		TextMessage messages = new TextMessage(gson.toJson(msg));
		
		List<String> groupIds = new ArrayList<>();
		for(int i = 0 ; i<sockets.size();i++) {
			String chat = (String)sockets.get(i).getAttributes().get("chat");
			if(chatMode.equals(chat)) {
				groupIds.add((String)sockets.get(i).getAttributes().get("userId"));				
				alertService.sendIncludeGroup(gson.toJson(msg), groupIds);
			}else {
				alertService.sendExcludeGroup(gson.toJson(msg), groupIds);
			}
		}
		
		
		
		
		
		
	}

}
