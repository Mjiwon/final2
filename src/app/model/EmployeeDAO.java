package app.model;

import java.util.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	public int addEmployee(Map param) {
		return template.insert("admin.addEmployee",param);
	}
	
	public String getSequenceVal() {
		Integer i = template.selectOne("admin.getSequenceVal");
		String id = "em"+i;
		return id;
	}
	
	public List<Map> getDepartment(){
		return template.selectList("admin.getDepartment");
	}
	
	public List<Map> getPosition(){
		return template.selectList("admin.getPosition");
	}
	
}
