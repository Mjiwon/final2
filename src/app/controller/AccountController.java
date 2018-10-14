package app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import app.model.AccountDao;
import app.service.AlertService;

@Controller
public class AccountController {
	
	@Autowired
	AccountDao adao;
	
	@Autowired
	AlertService service;

	@GetMapping("/changpass.do")
	public String changpassHandler(WebRequest wr) {
		wr.setAttribute("changPass", true, WebRequest.SCOPE_SESSION);
		return "employee.changpass";
	}
	
	@PostMapping("/changpass.do")
	public String changPassHandler(@RequestParam Map map, WebRequest wr) {
		int j= adao.logConfirms(map);
		if(j>0) {
			String newPass1 = (String)map.get("newpass1");
			String newPass2 = (String)map.get("newpass2");
			if(newPass1.equals(newPass2)) {
				map.put("pass", newPass1);
				int i = adao.changPass(map);
				System.out.println(i);
				if(i>0) {
					wr.setAttribute("changPass", true, WebRequest.SCOPE_SESSION);
					return "employee.home";
				}else {
					wr.setAttribute("changPass", false, WebRequest.SCOPE_SESSION);
					return "employee.changpass";		
				}				
			}else {
				wr.setAttribute("changPass", false, WebRequest.SCOPE_SESSION);
				return "employee.changpass";
			}
		}else {
			wr.setAttribute("changPass", false, WebRequest.SCOPE_SESSION);
			return "employee.changpass";	
		}
	}
	
}
