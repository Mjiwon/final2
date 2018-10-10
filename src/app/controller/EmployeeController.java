package app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void addPostController() {
		
	}
}
