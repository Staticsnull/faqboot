package cn.bdqn.service;

import java.util.Map;

import cn.bdqn.entity.Paper;

public interface PaperService {

	int add(Paper paper);
	
	Map<String,Object> list(String title, int type, int pageNum);
	boolean delete(int id);
	int hasTitle(String title);
	Map<String,Object> get(int id);
	
}
