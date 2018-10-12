package app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.Gson;

import app.model.AccountDao;
import app.service.AlertService;

@Controller
public class IndexController {
	@Autowired
	AccountDao adao;
	
	@Autowired
	Gson gson;
	
	@Autowired
	AlertService service;
	
/*	@RequestMapping("/index.do")
	public String indexHandle(WebRequest wr) {
		if(wr.getAttribute("auth", WebRequest.SCOPE_SESSION)==null) {
			return "guest";
		}else {
			
			return "employee.home";	
		}
	}*/
	
	@RequestMapping("/index.do")
	public String indexHandle(@SessionAttribute(required=false)String auth) {
		if(auth==null) {
			return "guest";
		}else {
			return "employee.home";	
		}
	}
}
