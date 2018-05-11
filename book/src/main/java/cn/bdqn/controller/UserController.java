package cn.bdqn.controller;

import cn.bdqn.entity.Book;
import cn.bdqn.entity.BookUser;
import cn.bdqn.service.BookService;
import cn.bdqn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private BookService bookService;
	
	
	@RequestMapping("/login")
	public String login() {
		logger.info("login====>");
		return "login";
	}
	@RequestMapping("/isLogin")
	public String isLogin(String name, String password,
						  HttpSession session, Model model){
		BookUser user = null;
		try {
			user = userService.login(name,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != user){
			session.setAttribute("user",user);
			return "redirect:/main";
		}else {
			model.addAttribute("error","用户名或者密码有误!");
			return "login";
		}
	}

	@RequestMapping("/main")
	public String main(HttpSession session,Model model){
		BookUser user = (BookUser) session.getAttribute("user");
		if(null == user){
			return "login";
		}else {
			return "redirect:/list";
		}

	}
	@RequestMapping("/list")
	public String list(Model model,/*HttpSession session,*/
		@RequestParam(value = "pageIndex",required = false)	String pageIndex){
		if (pageIndex == null || "".equals(pageIndex) || "0".equals(pageIndex)) {
			pageIndex = "1";
		}
//		BookUser user = (BookUser) session.getAttribute("user");
//		logger.info("usertype==="+user.getUsertype());
//		model.addAttribute("user",user);
		Map<String, Object> result = bookService.list(Integer.parseInt(pageIndex));
		result.put("pageIndex", pageIndex);
		//result.put("keyword", keyword);
		model.addAttribute("result", result);
		return "booklist";
	}

	

}
