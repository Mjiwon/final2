package app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.Gson;

import app.model.AccountDao;
import app.model.AlertService;

@Controller
public class IndexController {
	@Autowired
	AccountDao adao;
	
	@Autowired
	Gson gson;
	
	@Autowired
	AlertService service;
	
	@RequestMapping("/index.do")
	public String indexHandle(WebRequest wr) {
		if(wr.getAttribute("auth", WebRequest.SCOPE_SESSION)==null) {
			return "guest";
		}else {
			Map datas = (Map)wr.getAttribute("user", WebRequest.SCOPE_SESSION);
			System.out.println("로그인 정보" + datas);
			String data = gson.toJson(datas);
			System.out.println(data);
			service.sendAll(data);
			
			return "employee.home";
		}
	}
}
