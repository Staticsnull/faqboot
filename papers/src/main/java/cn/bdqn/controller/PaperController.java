package cn.bdqn.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.bdqn.entity.Code;
import cn.bdqn.entity.Paper;
import cn.bdqn.entity.User;
import cn.bdqn.service.CodeService;
import cn.bdqn.service.PaperService;

@Controller
public class PaperController {
	
	@Resource
	private PaperService paperService;
	@Resource
	private CodeService codeService;
	
	@RequestMapping("toMain")
	public String toMain(HttpSession session) {
		Object user = session.getAttribute("user");
		//ModelAndView mav = new ModelAndView();
		if(user==null) {
			//mav.setViewName("redirect:/toLogin");
			return "redirect:/toLogin";
		}else {
			//mav.setViewName("main");
			return "main";
		}
	}
	
	@RequestMapping("listPaper")//listPaper
	public String listPaper(Model model,
			@RequestParam(value = "title",required = false) String title,
			@RequestParam(value = "type",required = false)String type,
			@RequestParam(value = "pageNum",required = false)String pageNum) {
		//ModelAndView mav = new ModelAndView();
		//String title = request.getParameter("title");
		//String type = request.getParameter("type");
		//String pageNum = request.getParameter("pageNum");
		if(title==null) {
			title = "";
		}
		if(type==null) {
			type = "0";
		}
		if(pageNum==null||"".equals(pageNum)||"0".equals(pageNum)) {
			pageNum = "1";
		}
		Map<String, Object> result = paperService.list(title,Integer.parseInt(type),Integer.parseInt(pageNum));
		List<Code> codeList = codeService.getBy("1000");
		result.put("pageNum", pageNum);
		result.put("title", title);
		result.put("type", type);
		result.put("typeList", codeList);
		//mav.addObject("result", result);
		model.addAttribute("result",result);
		//mav.setViewName("paperList");
		return "paperList";
	}
	
	
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(Model model) {
		//ModelAndView mav = new ModelAndView();
		List<Code> codeList = codeService.getBy("02");
		//mav.addObject("codeList", codeList);
		model.addAttribute("codeList",codeList);
		//mav.setViewName("add");
		return "add";
	}
	
	@RequestMapping(value="/toUpdate",method=RequestMethod.GET)
	public String toUpdate(@RequestParam String id,Model model) {
		//String id = request.getParameter("id");
		//ModelAndView mav = new ModelAndView();
		List<Code> codeList = codeService.getBy("1000");
		Map<String, Object> result = paperService.get(Integer.parseInt(id));
		//mav.addObject("codeList", codeList);
		model.addAttribute("codeList",codeList);
		//mav.addObject("paper",result);
		model.addAttribute("paper",result);
		//mav.setViewName("update");
		return "update";
	}
	
	@RequestMapping(value="/deletePaper",method=RequestMethod.GET)
	public String deletePaper(String id) {
		//String id = request.getParameter("id");
		paperService.delete(Integer.parseInt(id));
		return "redirect:/listPaper"; 
	}

	/**
	 * 异步删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deletePapers",method=RequestMethod.GET)
	public String deletePapers(String id) {
		String result = "";
		//String id = request.getParameter("id");
		boolean flag = paperService.delete(Integer.parseInt(id));
		if (flag){
			result = "{\"OK\":\"true\"}";
		}else {
			result = "{\"OK\":\"false\"}";
		}
		return result;
	}
	
	@ResponseBody  
	@RequestMapping(value="/hasTitle",method=RequestMethod.GET)
	public String hasTitle(String title) {
		//String title = request.getParameter("title");
		int result = paperService.hasTitle(title);
		return result+""; 
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute Paper paper,@RequestParam(value = "file",required = false) MultipartFile file,HttpServletRequest request) {
		
		String filePath = "";
		String filename = "";
        if(!file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/images/");
            filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            if (!filepath.getParentFile().exists()) { 
                filepath.getParentFile().mkdirs();
            }
            try {
            	filePath = path + File.separator + filename;
				file.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } 
		User user = (User)request.getSession().getAttribute("user");
		paper.setCreatedBy(user.getUserName());
		paper.setModifyBy(user.getUserName());
		//paper.set
		paper.setPaperPath(filePath);
		paper.setFileName(filename);
		paperService.add(paper);
		return "redirect:/listPaper"; 
	}
	

}
