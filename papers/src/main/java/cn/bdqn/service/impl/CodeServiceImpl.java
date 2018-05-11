package cn.bdqn.service.impl;

import java.util.List;

import cn.bdqn.dao.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.entity.Code;
import cn.bdqn.service.CodeService;

import javax.annotation.Resource;

@Service
public class CodeServiceImpl implements CodeService {

	@Resource
    private CodeMapper codeMapper;
	
	@Override
	public List<Code> getBy(String parCode) {
		List<Code> codeList = codeMapper.getBy(parCode);
		return codeList;
	}

}
