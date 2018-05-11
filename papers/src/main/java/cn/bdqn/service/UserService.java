package cn.bdqn.service;

import java.util.List;

import cn.bdqn.entity.User;

public interface UserService {

	List<User> isLogin(String userName, String password);
	
	int hasEmail(String userName);
}
