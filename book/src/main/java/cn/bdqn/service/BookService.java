package cn.bdqn.service;

import cn.bdqn.entity.Book;
import cn.bdqn.entity.BookUser;

import java.util.List;
import java.util.Map;

public interface BookService {
	//根据id获取所有商品
	Map<String, Object> list(int pageIndex);
	boolean add(Book book);
	boolean deleteBook(String id);
	Book findById(String id);
	boolean updateBook(Book book);

}
