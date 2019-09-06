package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.datadictionary.DataDictionaryMapper;
import com.pojo.DataDictionary;
import com.service.DataDictionaryService;


@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
	
	@Resource
	private DataDictionaryMapper mapper;
	
	@Override
	public List<DataDictionary> getDataDictionaryList(String typeCode)
			throws Exception {
		return mapper.getDataDictionaryList(typeCode);
	}

}
