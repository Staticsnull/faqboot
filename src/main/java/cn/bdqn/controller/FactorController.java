package cn.bdqn.controller;

import cn.bdqn.service.FaqsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FactorController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private FaqsService service;

    @RequestMapping("/sayhello")
    //@ResponseBody
    public String home(Model model, HttpSession session){//测试热部署
        model.addAttribute("users","Hello");
        session.setAttribute("user","测试session");

        return "demo";
    }
    @RequestMapping("faqsList")
    @ResponseBody
    public Map<String,Object> faqsList(@RequestParam(value = "keyword", required = false) String keyword,
                                       @RequestParam(value = "pageIndex", required = false) String pageIndex){
        if (keyword == null) {
            keyword = "";
        }
        if (pageIndex == null || "".equals(pageIndex) || "-1".equals(pageIndex)) {
            pageIndex = "1";
        }
        Map<String, Object> result = service.list(keyword, Integer.parseInt(pageIndex));
        result.put("pageIndex", pageIndex);
        result.put("keyword", keyword);
        return result;
    }
    @RequestMapping("/listUI")
    public String listUI(){
        return "listFaqs";
    }
    @RequestMapping(value = "/toShowFaqs", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> toShowFaqs(@RequestParam("id") Integer id, Model model) {
        Map<String, Object> faqs = service.get(id);
        //model.addAttribute("result", faqs);
        return faqs;
    }

    /*@RequestMapping("deleteFaqs")
    @ResponseBody
    public String deleteFaqs(Integer id){
        String result = "";
        boolean flag = service.deleteFaqs(id);
        if(flag){
            // "{\"OK\":\"true\"}"
            result = "{\"OK\":\"true\"}";
        }else {
            result = "{\"OK\":\"false\"}";
        }
        return result;
    }*/

    @RequestMapping("deleteFaqs")
    @ResponseBody
    public Map<String,String> deleteFaqs(Integer id){
        Map<String,String> result = new HashMap<>();
        boolean flag = service.deleteFaqs(id);
        if(flag){
            // "{\"OK\":\"true\"}"
//            result = "{\"OK\":\"true\"}";
            result.put("OK","true");
        }else {
//            result = "{\"OK\":\"false\"}";
            result.put("OK","false");
        }
        return result;
    }



}
