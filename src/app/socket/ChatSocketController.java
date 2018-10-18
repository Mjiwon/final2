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
		
		String chatMode = (String)session.getAttributes().get("chat");

		Map msg = new HashMap<>();
		msg = gson.fromJson(got, Map.class);
		msg.put("sendUser", user);
		msg.put("mode", chatMode);
		
		System.out.println("sendUser = >" + msg);
		
		TextMessage messages = new TextMessage(gson.toJson(msg));
		System.out.println("나의모드으~~ "+chatMode);
      	List<String> groupInIds = new ArrayList<>( );

      	for(int i = 0; i<sockets.size();i++) {
			String ids = (String)sockets.get(i).getAttributes().get("userId");
			System.out.println("ids ssss = "+ ids);
			
			String dchatMode = (String)sockets.get(i).getAttributes().get("chat");
			System.out.println("너의(socket) 모드는?? " + dchatMode);
			
			if(dchatMode.equals(chatMode)) {
				try {
					sockets.get(i).sendMessage(messages);
					for(int j = 0 ; j <alertService.alertList().size();j++) {
						groupInIds.add(id);
					}					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("emfdhk??");
				for(int j = 0 ; j <alertService.alertList().size();j++) {
					groupInIds.add(id);
				}	
			}
			System.out.println("구릅리스드 : " + groupInIds);
		}
		
		/*String json ="{\"mode\":\""+chatMode+"\"}";*/
		alertService.sendExcludeGroup(gson.toJson(msg), groupInIds);
		
		
		
		
	}

}
