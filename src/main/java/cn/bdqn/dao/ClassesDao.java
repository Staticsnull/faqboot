package cn.bdqn.dao;

import cn.bdqn.entity.Classes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassesDao {
	
	List<Classes> list();
	
}
