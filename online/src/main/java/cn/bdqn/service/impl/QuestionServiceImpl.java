package cn.bdqn.service.impl;

import cn.bdqn.dao.QuestionMapper;
import cn.bdqn.entity.Questions;
import cn.bdqn.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Resource
    private QuestionMapper questionMapper;

	@Override
	public List<Questions> list() {
		return questionMapper.list();
	}

	@Override
	public boolean addQuestions(Questions questions) {
		return questionMapper.add(questions) > 0;
	}

	@Override
	public Questions getQuestionById(String id) {
		return questionMapper.getQuestionById(id);
	}

	@Override
	public boolean updateStatu(Questions questions) {
		return questionMapper.updateStatus(questions) > 0;
	}
}
