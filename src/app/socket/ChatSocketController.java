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

import app.controller.AlertSocketController;
import app.service.AlertService;

@Controller
public class ChatSocketController extends TextWebSocketHandler{
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
		String got = message.getPayload();
		Map user = (Map)session.getAttributes().get("user");
		

		Map msg = new HashMap<>();
		msg = gson.fromJson(got, msg.getClass());
		msg.put("sendUser", user);
				
		TextMessage messages = new TextMessage(gson.toJson(msg));
		
		
		System.out.println("messages "   + messages);
		
		List<WebSocketSession> alertList = alertService.alertList();

		List<String>UserList = new ArrayList<>();
		for(int i = 0; i<alertList.size();i++) {
			UserList.add((String)alertList.get(i).getAttributes().get("userId"));
		}
		
		System.out.println("userList = " +UserList);
		for(int i = 0; i<UserList.size();i++) {
			String ids = (String)sockets.get(i).getAttributes().get("userId");
			System.out.println(ids);
			if(!UserList.get(i).contains(ids)) {
				alertService.sendAll(gson.toJson(msg));
			}else {
				try {
					sockets.get(i).sendMessage(messages);
				}catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}
	}
}
