package app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import app.model.AccountDao;
import app.service.AlertService;

@Controller
public class LoginController {

	@Autowired
	AccountDao adao; 
	
	@Autowired
	AlertService service;
	
/*	@RequestMapping("/login.do")
	public String loginHandler(@RequestParam Map param, WebRequest wr) {
		Map map = adao.logConfirm(param);
		
		if(map!=null) {
			wr.setAttribute("auth", "on", WebRequest.SCOPE_SESSION);
			String id = (String)param.get("id");
			
			Map data = adao.loginMember(id);
			data.put("mode", "login");
			
			wr.setAttribute("user", data, WebRequest.SCOPE_SESSION);
			
			one.put("mode", "login");
			service.sendAll(one);
		}else {
			wr.setAttribute("err", "err", WebRequest.SCOPE_SESSION);
		}
		return "redirect:/index.do";
	}*/	
	
	
	@RequestMapping("/login.do")
	public String loginHandle(WebRequest wr, @RequestParam Map param) {
		int cnt = adao.logConfirms(param);
		if(cnt>0) {
			String id = (String)param.get("id");
			Map one = adao.loginMember(id);
			wr.setAttribute("userId", id, WebRequest.SCOPE_SESSION);
			wr.setAttribute("user", one, WebRequest.SCOPE_SESSION);
			wr.setAttribute("auth", "on", WebRequest.SCOPE_SESSION);
			
			/*Map msg = new HashMap<>();
			msg.put("mode", "login");
			msg.put("actor", one);
			service.sendAll(msg);*/
			
			one.put("mode", "login");
			service.sendAll(one);
		}else {
			wr.setAttribute("err", "errr", WebRequest.SCOPE_SESSION);
		}
		return "redirect:/index.do"; //redirect:/index.do
	}

}
