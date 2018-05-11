package cn.bdqn.dao;

import cn.bdqn.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
	//查询全部
	List<Map<String,Object>> list(int pageIndex);
	int getCount();
	int add(Book book);
	int deleteBook(String id);
	Book findById(String id);
	int updateBook(Book book);


}
