package cn.bdqn.controller;

import cn.bdqn.entity.Classes;
import cn.bdqn.entity.Faqs;
import cn.bdqn.service.ClassesService;
import cn.bdqn.service.FaqsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class FaqsController {
    @Resource
    private FaqsService faqsService;
    @Resource
    private ClassesService classesService;

    @RequestMapping("index")
    @ResponseBody
    public Map<String, Object> index(@RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        if (keyword == null) {
            keyword = "";
        }
        if (pageIndex == null || "".equals(pageIndex) || "0".equals(pageIndex)) {
            pageIndex = "1";
        }
        Map<String, Object> result = faqsService.list(keyword, Integer.parseInt(pageIndex));
        return result;
    }

    /**
     * 根据关键字模糊查找问题信息
     *
     * @param model
     * @param keyword
     * @param pageIndex
     * @return
     */

    @RequestMapping("/listFaqs")
    public String listFaqs(Model model,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        if (keyword == null) {
            keyword = "";
        }
        if (pageIndex == null || "".equals(pageIndex) || "0".equals(pageIndex)) {
            pageIndex = "1";
        }
        Map<String, Object> result = faqsService.list(keyword, Integer.parseInt(pageIndex));
        result.put("pageIndex", pageIndex);
        result.put("keyword", keyword);
        model.addAttribute("result", result);
        return "faqsList";
    }

    @RequestMapping(value = "/toAddFaqs", method = RequestMethod.GET)
    public String toAddFaq() {
        return "add";
    }

    //使用ajax加载分类
    @RequestMapping(value = "/getClasses", method = RequestMethod.GET)
    @ResponseBody
    public List<Classes> getClasses() {
        List<Classes> list = classesService.list();
        return list;
    }

    @RequestMapping(value = "/toShowFaq", method = RequestMethod.GET)
    public String toShowFaq(@RequestParam("id") Integer id, Model model) {
        Map<String, Object> faqs = faqsService.get(id);
        model.addAttribute("result", faqs);
        return "show";
    }


    /*@RequestMapping(value = "/addFaq", method = RequestMethod.POST)
    public String addFaq(@ModelAttribute Faqs faqs) {
        System.out.println(faqs.getTitle());
        faqs.setCreationDate(new Date());
        boolean flag = faqsService.add(faqs);
        if(flag){
            return "redirect:/listUI" ;
        }
        return "add";
    }*/
    @RequestMapping(value = "/addFaq", method = RequestMethod.POST)
    public String addFaq(@ModelAttribute Faqs faqs) {
        System.out.println(faqs.getTitle());
        faqs.setCreationDate(new Date());
        boolean flag = faqsService.add(faqs);
        if(flag){
            return "redirect:/listUI" ;
        }
        return "add";
    }


}
