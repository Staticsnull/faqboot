package cn.bdqn.service;

import java.util.List;

import cn.bdqn.entity.Code;

public interface CodeService {
	
	List<Code> getBy(String parCode);
	
}
