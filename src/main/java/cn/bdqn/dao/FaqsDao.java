package cn.bdqn.dao;

import cn.bdqn.entity.Faqs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqsDao {
	
	
	Map<String,Object> get(@Param("id") int id);
	int getCount(@Param("keyword") String keyword);
	List<Map<String,Object>> list(@Param("keyword") String keyword, @Param("pageIndex") int pageIndex);
	int add(Faqs faqs);
	int deleteFaqs(int id);
	
}
