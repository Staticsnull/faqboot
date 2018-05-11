package cn.bdqn.service;

import cn.bdqn.entity.Questions;

import java.util.List;

public interface QuestionService {
	//根据id获取所有商品
	//BookUser login(String name, String password)throws  Exception;
	List<Questions> list();
	boolean addQuestions(Questions questions);
	Questions getQuestionById(String id);
	boolean updateStatu(Questions questions);

}
