package com.dao.appinfo;


import com.pojo.App_info;

public interface App_infoMapper {
	/**
	 * ��ҳ��ѯȫ��
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
