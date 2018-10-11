package app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.EmployeeDAO;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeDAO edao;
	
	@GetMapping("add.do")
	public String addController(ModelMap modelMap) {
		modelMap.put("department", edao.getDepartment());
		modelMap.put("position", edao.getPosition());
		return "admin.employee.add";
	}
	
	@PostMapping("add.do")
	public String addController(@RequestParam Map param , ModelMap modelMap) {
		String id = edao.getSequenceVal();
		param.put("id", id);
		
		try {
			int i = edao.addEmployee(param);
			System.out.println("EmployeeController 36번줄 [회원등록 성공]");
			modelMap.put("employee",param);
			return "admin.employee.joinComplete";	
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("EmployeeController 42번줄 [회원등록 실패]");
			modelMap.put("err", "on");
			modelMap.put("department", edao.getDepartment());
			modelMap.put("position", edao.getPosition());
			return "admin.employee.add";			
		}
		
	}
}
