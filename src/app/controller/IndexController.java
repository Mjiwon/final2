package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class IndexController {
	
	@RequestMapping("/index.do")
	public void indexHandle(WebRequest wr) {
		System.out.println("auth" + wr.getAttribute("auth", WebRequest.SCOPE_SESSION));
	}
}
