package cn.bdqn.controller;

import cn.bdqn.entity.Answers;
import cn.bdqn.entity.Questions;
import cn.bdqn.service.AnswerService;
import cn.bdqn.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private QuestionService questionService;
	@Resource
	private AnswerService answerService;
	@RequestMapping("/list")
	public String list(Model model){
		List<Questions> list = questionService.list();
		model.addAttribute("list",list);
		return "list";
	}
	@RequestMapping("/question")
	public String qustion(){
		return "add";
	}
	@RequestMapping("/saveQuestion")
	public String saveQuestion(Questions questions){
		questions.setAnswerCount(0);
		questions.setLastModified(new Date());
		boolean flag = questionService.addQuestions(questions);
		if (flag){
			return "redirect:/list";
		}
		return "add";
	}
	@RequestMapping("/findQuestion")
	public String findQuestion(String id,Model model,
		@RequestParam(value="ansContent",required=false)String ansContent){
		Questions questions = questionService.getQuestionById(id);
		List<Answers> answers = answerService.getAnswerListByPid(id);
		Integer answerCount = questions.getAnswerCount();
		if(null != ansContent && !"".equals(ansContent)){
			Answers answer = new Answers();
			answer.setQid(Integer.parseInt(id));
			answer.setAnsContent(ansContent);
			answer.setAnsDate(new Date());
			if(answerService.insertAnswer(answer)){
				answerCount++;
				questions.setAnswerCount(answerCount);
				questions.setLastModified(new Date());
				if(questionService.updateStatu(questions)){
					return "redirect:/list";
				}
			}

		}
		model.addAttribute("answers",answers);
		model.addAttribute("question",questions);
		return "show";
	}

	

}
