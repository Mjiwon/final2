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
public class TimChatSocketController extends TextWebSocketHandler{
	List<WebSocketSession> sockets;
	
	@Autowired
	Gson gson;
	
	@Autowired
	AlertService alertService;
	
	public TimChatSocketController() {
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
		System.out.println(got+">>>????");
		
		Map user = (Map)session.getAttributes().get("user");
		
		Map msg = new HashMap<>();
		msg = gson.fromJson(got, msg.getClass());
		msg.put("sendUser", user);
				
		TextMessage messages = new TextMessage(gson.toJson(msg));
		
		List<WebSocketSession> alertList = alertService.alertList();
		

		List<Map>userList = new ArrayList<>();
		String[] targets = new String[alertList.size()];
		for(int i = 0; i<alertList.size();i++) {
			
			Map mmmm = (Map)alertList.get(i).getAttributes().get("user");
			if(user.containsValue(mmmm.get("DNAME"))) {
				Map m = new HashMap<>();
				m.put((String)mmmm.get("ID"), mmmm);
				userList.add(m);
				System.out.println("여긴?" + userList);
				targets[i] = (String)mmmm.get("ID");
				System.out.println("들ㅇ온거?" + targets[i]);
			}
		}
		
		for(int i = 0;i<userList.size();i++) {
			Map u = userList.get(i);
			Map udam = (Map)sockets.get(i).getAttributes().get("user");
			if(!u.containsValue(udam)) {
				System.out.println("여기는 들어오는거야 안들어는거야??" + targets[i]);
				alertService.sendSome(gson.toJson(msg), targets[i]);
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
