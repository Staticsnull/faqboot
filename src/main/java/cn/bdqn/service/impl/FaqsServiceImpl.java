package cn.bdqn.service.impl;

import cn.bdqn.dao.FaqsDao;
import cn.bdqn.entity.Faqs;
import cn.bdqn.service.FaqsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FaqsServiceImpl implements FaqsService {

	@Resource
	private FaqsDao faqsDao;

	@Override
	public Map<String,Object> get(int id) {
		Map<String,Object> faqs = faqsDao.get(id);
		return faqs;
	}

	@Override
	public Map<String, Object> list(String keyword, int pageIndex) {
		int tatolCount = faqsDao.getCount(keyword);
		int pageCount = mod(tatolCount, 4);
		System.out.println(pageCount);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list = faqsDao.list(keyword, (pageIndex-1)*4);
		//List<Map<String,Object>> list = faqsDao.list(keyword, pageIndex*4);

		result.put("pageCount", pageCount);
		result.put("list", list);

		return result;
	}

	@Override
	public boolean add(Faqs faqs) {
		return faqsDao.add(faqs) > 0;
	}

	@Override
	public boolean deleteFaqs(Integer id) {
		return faqsDao.deleteFaqs(id) > 0;
	}

	private int mod(int x, int y) {
		if (x % y > 0) {
			return (x / y) + 1;
		} else {
			return x / y;
		}
	}


}
