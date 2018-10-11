package app.controller;

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
		System.out.println(map);
		if(map!=null) {
			wr.setAttribute("auth", "on", WebRequest.SCOPE_SESSION);
		}else {
			wr.setAttribute("err", "err", WebRequest.SCOPE_SESSION);
			map.put("mode", "login");
			service.sendAll(map);
		}
		return "redirect:/index.do";
		
	}
}
