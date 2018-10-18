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
		String id = (String)session.getAttributes().get("userId");
		
		Map user = (Map)session.getAttributes().get("user");
		
		String got = message.getPayload();
		
		Map msg = new HashMap<>();
		msg = gson.fromJson(got, Map.class);
		msg.put("sendUser", user);
		
		TextMessage messages = new TextMessage(gson.toJson(msg));
		String chatMode = (String)session.getAttributes().get("chat");
		
		
		for(int i = 0; i<sockets.size();i++) {
			String dchatMode = (String)sockets.get(i).getAttributes().get("chat");
			System.out.println(dchatMode);
			if(dchatMode.equals(chatMode)) {
				try {
					sockets.get(i).sendMessage(messages);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}

}
