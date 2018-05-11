package cn.bdqn.service;

import cn.bdqn.entity.BookUser;

public interface UserService {
	//根据id获取所有商品
	BookUser login(String name, String password)throws  Exception;

}
