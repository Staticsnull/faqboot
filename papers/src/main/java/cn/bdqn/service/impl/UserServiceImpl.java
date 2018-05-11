package cn.bdqn.service.impl;


import java.util.List;

import cn.bdqn.dao.UserMapper;
import cn.bdqn.entity.User;
import cn.bdqn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

	@Resource
    private UserMapper userMapper;

	@Override
	public List<User> isLogin(String userName, String password) {
		List<User> list = userMapper.isLogin(userName, password);
		return list;
	}

	@Override
	public int hasEmail(String userName) {
		int result = userMapper.hasEmail(userName);
		return result;
	}

}
