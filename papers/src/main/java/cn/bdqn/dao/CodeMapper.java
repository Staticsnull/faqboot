package cn.bdqn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.Code;

@Mapper
public interface CodeMapper {
	
	List<Code> getBy(@Param("parCode") String parCode);
}
