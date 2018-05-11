package cn.bdqn.dao;

import cn.bdqn.entity.Answers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerMapper {
	//查询全部
//	BookUser login(String name);
    List<Answers> getAnswerListByPid(@Param("qid") String qid);
    int saveAnswers(Answers answer);

}
