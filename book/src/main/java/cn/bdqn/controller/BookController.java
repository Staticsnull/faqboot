package cn.bdqn.controller;

import cn.bdqn.entity.Book;
import cn.bdqn.entity.BookUser;
import cn.bdqn.service.BookService;
import cn.bdqn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	private BookService bookService;
	
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		logger.info("login====>");
		return "add";
	}
	@RequestMapping("/add")
    public String add(Book book){
        boolean flag = bookService.add(book);
        if (flag){
            return "redirect:/list";
        }
        return "add";
    }
    @RequestMapping("/toDelete")
	@ResponseBody
	public Map<String,Object> toDelete(String id){
		Map<String,Object> result = new HashMap<>();
		boolean flag = bookService.deleteBook(id);
		if(flag){
			result.put("OK","true");
		}else {
			result.put("OK","false");
		}
		return result;
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String id,Model model){
		Book book = bookService.findById(id);
		model.addAttribute("book",book);
		return "update";
	}
	@RequestMapping("/update")
	public String update(Book book){
		logger.info("book====>"+book.getId());
		boolean flag = bookService.updateBook(book);
		if (flag){
			return "redirect:/list";
		}
		return "update";
	}

	

}
