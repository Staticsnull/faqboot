package cn.bdqn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bdqn.dao.PaperMapper;
import cn.bdqn.entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.service.PaperService;

import javax.annotation.Resource;

@Service
public class PaperServiceImpl implements PaperService{
	
	@Resource
    private PaperMapper paperMapper;

	@Override
	public int add(Paper paper) {
		int result = paperMapper.add(paper);
		return result;
	}

	@Override
	public Map<String,Object> list(String title,int type,int pageNum) {
		int allNum = paperMapper.getCount(title, type);
		int allPageNum = mod(allNum, 4);

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> paperList = paperMapper.list(title, type,(pageNum-1)*4);
		result.put("allPageNum", allPageNum);
		result.put("list", paperList);
		return result;
	}

	@Override
	public boolean delete(int id) {
		int result = paperMapper.delete(id);
		return result > 0;
	}

	@Override
	public int hasTitle(String title) {
		int result = paperMapper.hasTitle(title);
		return result;
	}

	@Override
	public Map<String, Object> get(int id) {
		Map<String, Object> result = paperMapper.get(id);
		return result;
	}
	
	private int mod(int x, int y) {
		if (x % y > 0) {
			return (x / y) + 1;
		} else {
			return x / y;
		}

	}

}
