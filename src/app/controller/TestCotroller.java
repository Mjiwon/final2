package app.controller;

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
public class TestCotroller extends TextWebSocketHandler {
	List<WebSocketSession> sockets;
	
	@Autowired
	Gson gson;
	
	@Autowired
	AlertService alertService;
	
	public TestCotroller() {
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
		
		String got = message.getPayload();
		Map user = (Map)session.getAttributes().get("user");
		

		Map msg = new HashMap<>();
		msg = gson.fromJson(got, msg.getClass());
		msg.put("sendUser", user);
				
		TextMessage messages = new TextMessage(gson.toJson(msg));
		
		List<WebSocketSession> alertList = alertService.alertList();
		switch (chatMode) {
		case "public": 
			List<String>UserList = new ArrayList<>();
			for(int i = 0; i<alertList.size();i++) {
				UserList.add((String)alertList.get(i).getAttributes().get("userId"));
			}
			
			System.out.println("userList = " +UserList);
			for(int i = 0; i<UserList.size();i++) {
				String ids = (String)sockets.get(i).getAttributes().get("userId");
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
			break;

		case "timchat":
			List<Map>userList = new ArrayList<>();
			String[] targets = new String[alertList.size()];
			
			for(int i = 0; i<alertList.size();i++) {
				Map mmmm = (Map)alertList.get(i).getAttributes().get("user");
				Map m = new HashMap<>();
				m.put((String)mmmm.get("ID"), mmmm);
				if(user.containsValue(mmmm.get("DNAME"))) {
					userList.add(m);
					targets[i]=(String)mmmm.get("ID");
				}
			}
			List<String> groupList = new ArrayList<>();
			
			for(int i = 0;i<userList.size();i++) {
				
				Map u = userList.get(i);
				
				Map udam = (Map)sockets.get(i).getAttributes().get("user");
				System.out.println("dddddd" + udam.get("DNAME"));

				
				System.out.println(u.containsValue(udam.get("DNAME")));

/*				if(!u.containsValue(udam)) {
					alertService.sendExcludeGroup(gson.toJson(msg),targets[i] );
				}else {
					try {
						alertService.sendIncludeGroup(gson.toJson(msg),targets[i]);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}*/
			}
			break;
		} 
		
	}
}
