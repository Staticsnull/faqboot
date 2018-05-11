package cn.bdqn.service.impl;

import cn.bdqn.dao.AnswerMapper;
import cn.bdqn.entity.Answers;
import cn.bdqn.service.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Resource
    private AnswerMapper answerMapper;

    @Override
    public List<Answers> getAnswerListByPid(String id) {
        return answerMapper.getAnswerListByPid(id);
    }

    @Override
    public boolean insertAnswer(Answers answer) {
        return answerMapper.saveAnswers(answer) > 0;
    }
}
