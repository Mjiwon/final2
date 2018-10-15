package app.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao {
	@Autowired
	SqlSessionTemplate template;
	
	public int sendMessage(Map param) {
		return template.insert("messages.sendMessage",param);
	}
	
	public Map receiverMsg(String id) {
		return template.selectOne("messages.receiverMsg",id);
	}
	
	public List<Map> messageList(String id){
		return template.selectList("messages.messageList", id);
	}
	
	public Map msgdetail(String code) {
		return template.selectOne("messages.msgdetail",code);
	}
	
	public int receiverDate(String code) {
		return template.update("messages.receiverDate",code);
	}
	

}
