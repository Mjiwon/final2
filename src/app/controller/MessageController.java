package app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import app.model.AccountDao;
import app.model.MessageDao;
import app.service.AlertService;

@Controller
public class MessageController {
	@Autowired
	AccountDao adao;
	
	@Autowired
	MessageDao mdao;
	
	@Autowired
	AlertService service;
	
	@GetMapping("/messagehome.do")
	public String messageHomeController(WebRequest wr) {
		String id = (String)wr.getAttribute("userId", WebRequest.SCOPE_SESSION);
		System.out.println("id는 " + id);
		List<Map> messageList = mdao.messageList(id);
		System.out.println(messageList);

		for(int i = 0; i<messageList.size();i++) {
			Map m = messageList.get(i);
			String ctr = (String)m.get("CONTENT");
			if(ctr.contains("\n")) {
				m.put("REP", ctr.substring(0,ctr.indexOf("\n")));
			}else {
				m.put("REP", ctr);
			}
		}
		wr.setAttribute("messageList", messageList, WebRequest.SCOPE_SESSION);
		return "message.home";
	}
	
	@GetMapping("/messagesend.do")
	public String messageSendController(WebRequest wr) {
		List<Map> memberList = adao.allMember();
		wr.setAttribute("allmember", memberList, WebRequest.SCOPE_SESSION);
		return "message.send";
	}
	
	@PostMapping("/messagesend.do")
	public String messageSendController(@RequestParam Map param,WebRequest wr) {
		String code = UUID.randomUUID().toString().split("-")[0];
		param.put("code", code);
		
		String id = (String)wr.getAttribute("userId", WebRequest.SCOPE_SESSION);
		param.put("sender", id);
		
		int i = mdao.sendMessage(param);

		String receiver = (String)param.get("receiver");
		System.out.println("받는이 : " + receiver);
		
		Map receiverMsg = mdao.receiverMsg(code);
		Map map = new HashMap<>();
		map.put("mode", "remsg");
		map.put("receiverMsg",receiverMsg);
		
		System.out.println("map다"+map);
		service.sendOne(map, receiver);
		
		return "message.home";
	}
	
	@RequestMapping("/sendmessge2.do")
	public String msgSendController(WebRequest wr) {
		Map msg = (Map)wr.getAttribute("msgDetail", WebRequest.SCOPE_SESSION);

		wr.setAttribute("reSend", msg, WebRequest.SCOPE_SESSION);
		return "message.send2";
	}
	

	
	
	@RequestMapping("/msgdetail.do")
	public String msgDetail(@RequestParam String code, WebRequest wr ) {
		System.out.println("code" + code);
		Map messages = mdao.msgdetail(code);
		System.out.println("ㅡㄷㄴㄴㅁㅎㄷㄴ" +messages);
		int i = mdao.receiverDate(code);
		System.out.println(i);
		

		String ctr = (String)messages.get("CONTENT");
		if(ctr.contains("\n")) {
			messages.put("REP", ctr.substring(0,ctr.indexOf("\n")));
		}else {
			messages.put("REP", ctr);
		}

		
		wr.setAttribute("msgDetail", messages, WebRequest.SCOPE_SESSION);
		
		return "message.detail";
	}
}
