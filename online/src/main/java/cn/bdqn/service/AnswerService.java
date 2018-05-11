package cn.bdqn.service;

import cn.bdqn.entity.Answers;

import java.util.List;
import java.util.Map;

public interface AnswerService {
	//根据id获取所有商品
    List<Answers> getAnswerListByPid(String id);
    boolean insertAnswer(Answers answer);
}
