package com.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.appcategory.AppCategoryMapper;
import com.pojo.AppCategory;
import com.service.AppCategoryService;
@Service
public class AppCategoryServiceImpl implements AppCategoryService {

	@Resource
	private AppCategoryMapper mapper;
	
	@Override
	public List<AppCategory> getAppCategoryListByParentId(Integer parentId)
			throws Exception {
		// TODO Auto-generated method stub
		return mapper.getAppCategoryListByParentId(parentId);
	}

}
