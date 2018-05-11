package cn.bdqn.dao;

import cn.bdqn.entity.Book;
import cn.bdqn.entity.BookUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookUserMapper {
	//查询全部
	BookUser login(String name);


}
