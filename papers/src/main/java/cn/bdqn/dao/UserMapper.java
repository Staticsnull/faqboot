package cn.bdqn.dao;

import java.util.List;

import cn.bdqn.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	
	List<User> isLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);
	int hasEmail(@Param("userName") String userName);
}
