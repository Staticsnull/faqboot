package cn.bdqn.dao;

import cn.bdqn.entity.Questions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
	//查询全部
//	BookUser login(String name);
	List<Questions> list();
	int add(Questions questions);
	Questions getQuestionById(String id);
	int updateStatus(Questions questions);


}
