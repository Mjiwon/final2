package app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import app.model.AccountDao;
import app.model.AlertService;

@Controller
public class LoginController {

	@Autowired
	AccountDao adao; 
	
	@Autowired
	AlertService service;
	
	@RequestMapping("/login.do")
	public String loginHandler(@RequestParam Map param, WebRequest wr) {
		Map map = adao.logConfirm(param);
		
		if(map!=null) {
			wr.setAttribute("auth", "on", WebRequest.SCOPE_SESSION);
			String id = (String)param.get("id");
			System.out.println("id입니다 " +id);
			
			Map data = adao.loginMember(id);
			data.put("mode", "login");
			System.out.println(data);
			
			wr.setAttribute("user", data, WebRequest.SCOPE_SESSION);
		}else {
			wr.setAttribute("err", "err", WebRequest.SCOPE_SESSION);
		}
		return "redirect:/index.do";
		
	}
}
