package cn.bdqn.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import cn.bdqn.entity.User;
import cn.bdqn.service.UserService;

@Controller
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public String toLogin(
			@RequestParam(value = "status",required = false) String status,
			Model model) {
		//ModelAndView mav = new ModelAndView();
		//String status = request.getParameter("status");
//		mav.addObject("status", status);
//		mav.setViewName("login");
		model.addAttribute("status",status);
		return "login";
	}
	
	
	@RequestMapping(value="/isLogin",method=RequestMethod.POST)
	public String isLogin(@ModelAttribute User user,HttpSession session) {
		List<User> list = userService.isLogin(user.getUserName(), user.getUserPassword());
		if(list.size()==1) {
			//HttpSession session = request.getSession();
			session.setAttribute("user", list.get(0));
			return "redirect:/toMain";
		}else {
			return "redirect:/toLogin?status=0";
		}
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/toLogin";
	}
	
	@ResponseBody  
	@RequestMapping(value="/hasAccepter",method=RequestMethod.GET)
	public String hasAccepter(@RequestParam String accepter) {
		//String accepter = request.getParameter("accepter");
		int result = userService.hasEmail(accepter);
		return result+""; 
	}
	

}
