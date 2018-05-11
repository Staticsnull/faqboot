package cn.bdqn.dao;

import java.util.List;
import java.util.Map;

import cn.bdqn.entity.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaperMapper {
	
	//查询条数，需要传入查询属性名,关键词,页数
	List<Map<String,Object>> list(@Param("title") String title, @Param("type") int type, @Param("pageNum") int pageNum);
	//添加book
	int add(Paper paper);
	//删除
	int delete(@Param("id") int id);
	//查询主题有几个
	int hasTitle(@Param("title") String title);
	
	Map<String,Object> get(@Param("id") int id);
	
	//查询条数，需要传入查询title,type
	int getCount(@Param("title") String title, @Param("type") int type);
}
