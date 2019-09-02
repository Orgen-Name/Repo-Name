package com.service.appinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.appinfo.App_infoMapper;
import com.dao.dev_user.DevMapper;
import com.pojo.App_info;
import com.pojo.Dev_user;

@Service("App_infoService")
public class App_infoServiceImpl implements App_infoService {

	@Autowired
	public App_infoMapper app_infoMapper;

	@Override
	public App_info getAppinfo(String softwareName, Integer status, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer flatformId, Integer devId, Integer from, Integer pageSize) {
		return app_infoMapper.getAppinfo(softwareName, status, categoryLevel1, categoryLevel2, categoryLevel3, flatformId, devId, from, pageSize);
	}

	
}
