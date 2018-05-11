package cn.bdqn.service.impl;

import cn.bdqn.dao.BookUserMapper;
import cn.bdqn.entity.BookUser;
import cn.bdqn.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

	@Resource
    private BookUserMapper mapper;

	@Override
	public BookUser login(String name, String password) throws Exception{
		BookUser user = null;
		user = mapper.login(name);
		if(user != null){
			if (!password.equals(user.getPassword())){
				user = null;
			}
		}
		return user;
	}
}
