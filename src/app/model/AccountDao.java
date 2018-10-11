package app.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {
	@Autowired
	SqlSessionTemplate template;
	
	public Map memberConfirm(Map param) {
		return template.selectOne("account.memberConfirm",param);
	}
	

	public List<Map> loginMember(String id){
		return template.selectList("account.loginMember",id);
	}
}
