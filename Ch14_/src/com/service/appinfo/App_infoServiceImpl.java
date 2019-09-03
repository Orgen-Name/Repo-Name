package com.service.appinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.appinfo.App_infoMapper;
import com.dao.dev_user.DevMapper;
import com.pojo.App_info;
import com.pojo.Data_dictionary;
import com.pojo.Dev_user;

@Service("App_infoService")
public class App_infoServiceImpl implements App_infoService {

	@Autowired
	public App_infoMapper app_infoMapper;

	@Override
	public List<App_info> getAppinfo(String softwareName, Integer status, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer flatformId, Integer from, Integer pageSize) {
		return app_infoMapper.getAppinfo(softwareName, status, categoryLevel1, categoryLevel2, categoryLevel3, flatformId, from, pageSize);
	}

	@Override
	public int app_infoCount() {
		return app_infoMapper.app_infoCount();
	}

	@Override
	public List<Data_dictionary> getAPP_STATUS() {
		return app_infoMapper.getAPP_STATUS();
	}

	@Override
	public List<Data_dictionary> getAPP_FLATFORM() {
		return app_infoMapper.getAPP_FLATFORM();
	}

	
}
