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
		return "add.employee.add";
	}
	
	@PostMapping("add.do")
	public String addController(@RequestParam Map param , ModelMap modelMap) {
		String id = edao.getSequenceVal();
		param.put("id", id);
		param.put("pass", 1111);
		System.out.println(param);
		
		int i = edao.addEmployee(param);
		
		modelMap.put("employee",param);
		
		System.out.println(i);
		System.out.println(modelMap);
		if(i==1) {
			return "add.employee.joinComplete";			
		}else {
			return "add.employee.error";
		}
	}
}
