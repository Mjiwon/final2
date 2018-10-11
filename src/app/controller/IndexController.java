package app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import app.model.AccountDao;

@Controller
public class IndexController {
	@Autowired
	AccountDao adao;
	
	@RequestMapping("/index.do")
	public String indexHandle(WebRequest wr) {
		if(wr.getAttribute("auth", WebRequest.SCOPE_SESSION)==null) {
			return "guest";
		}else {
			return "employee.home";
		}
	}
}
