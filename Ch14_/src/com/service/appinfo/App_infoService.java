package com.service.appinfo;

import com.pojo.App_info;
import com.pojo.Dev_user;

public interface App_infoService {
	
	/**
	 * 分页查询全部
	 * @param softwareName
	 * @param status
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @param flatformId
	 * @param devId
	 * @return
	 */
	public App_info getAppinfo(String softwareName,Integer status,
							Integer categoryLevel1,Integer categoryLevel2,
							Integer categoryLevel3,Integer flatformId,
							Integer devId,Integer from,Integer pageSize);

}
