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
	
	public Map logConfirm(Map param) {
		return template.selectOne("account.logConfirm",param);
	}
	
	public int logConfirms(Map param) {
		return template.selectOne("account.logConfirms",param);
	}
	

	public Map loginMember(String id){
		return template.selectOne("account.loginMemver",id);
	}
}
