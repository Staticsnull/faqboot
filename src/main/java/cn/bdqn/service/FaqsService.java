package cn.bdqn.service;

import cn.bdqn.entity.Faqs;

import java.util.Map;

public interface FaqsService {

	Map<String,Object> list(String keyword, int pageIndex);
	
	Map<String,Object> get(int id);
	
	boolean add(Faqs faqs);
	boolean deleteFaqs(Integer id);
	
}
