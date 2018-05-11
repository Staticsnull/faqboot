package cn.bdqn.service.impl;

import cn.bdqn.dao.BookMapper;
import cn.bdqn.dao.BookUserMapper;
import cn.bdqn.entity.Book;
import cn.bdqn.entity.BookUser;
import cn.bdqn.service.BookService;
import cn.bdqn.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

	@Resource
    private BookMapper bookMapper;

	@Override
	public Map<String, Object> list(int pageIndex) {
		int tatolCount = bookMapper.getCount();
		int pageCount = mod(tatolCount, 4);
		System.out.println(pageCount);
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list = bookMapper.list((pageIndex-1)*4);
		//List<Map<String,Object>> list = faqsDao.list(keyword, pageIndex*4);

		result.put("pageCount", pageCount);
		result.put("list", list);

		return result;
	}

	@Override
	public boolean add(Book book) {
		int row = bookMapper.add(book);
		return row > 0;
	}

	private int mod(int x, int y) {
		if (x % y > 0) {
			return (x / y) + 1;
		} else {
			return x / y;
		}
	}

	@Override
	public boolean deleteBook(String id) {
		int row = bookMapper.deleteBook(id);
		return row > 0;
	}

	@Override
	public Book findById(String id) {
		return bookMapper.findById(id);
	}

	@Override
	public boolean updateBook(Book book) {
		int row = bookMapper.updateBook(book);
		return row > 0;
	}
}
