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
		String chatMode = (String)session.getAttributes().get("chat");
		System.out.println("chat MODE   ---  "  + chatMode);
		
		String got = message.getPayload();
		Map user = (Map)session.getAttributes().get("user");
		String userId = (String)session.getAttributes().get("userId");
		
		Map msguser = new HashMap<>();
		msguser = gson.fromJson(got, Map.class);
		msguser.put("sendUser", user);
		
		TextMessage messages = new TextMessage(gson.toJson(msguser));
		
		List<WebSocketSession> alertList = alertService.alertList();
		
		List<String> allUser = new ArrayList<>();
		Map alertUserinfo = new HashMap();

		List<String> groupUser = new ArrayList<>();
		Map groupUserinfo = new HashMap();
		
		for(int i = 0; i<alertList.size();i++) {
			allUser.add((String)alertList.get(i).getAttributes().get("userId"));
			alertUserinfo = (Map)alertList.get(i).getAttributes().get("user");
		}

		switch (chatMode) {
		case "public":
			for(int i = 0; i<allUser.size();i++) {
				groupUser.add((String)sockets.get(i).getAttributes().get("userId"));
				if(allUser.get(i).contains((String)sockets.get(i).getAttributes().get("userId"))) {
					alertService.sendIncludeGroup(gson.toJson(msguser), groupUser);
				}else {
					alertService.sendExcludeGroup(gson.toJson(msguser), groupUser);
				}
			}
			break;

		case "timchat":
			for(int i = 0; i<allUser.size();i++) {
				
				groupUser.add((String)sockets.get(i).getAttributes().get("userId"));
				if(allUser.get(i).contains((String)sockets.get(i).getAttributes().get("userId"))) {
					alertService.sendIncludeGroup(gson.toJson(msguser), groupUser);
				}else {
					alertService.sendExcludeGroup(gson.toJson(msguser), groupUser);
				}
			}
			break;
		}
		
		
	}
	
}
