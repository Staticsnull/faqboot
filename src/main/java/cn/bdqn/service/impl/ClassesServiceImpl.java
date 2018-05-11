package cn.bdqn.service.impl;

import cn.bdqn.dao.ClassesDao;
import cn.bdqn.entity.Classes;
import cn.bdqn.service.ClassesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {
	
	@Resource
	private ClassesDao classesDao;

	@Override
	public List<Classes> list() {
		List<Classes> list = classesDao.list();
		return list;
	}

}
