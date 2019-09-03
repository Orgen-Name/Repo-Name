package com.service.appinfo;

import java.util.List;

import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Data_dictionary;
import com.pojo.Dev_user;

public interface App_infoService {
	
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
	public List<App_info> getAppinfo(String softwareName,Integer status,
							Integer categoryLevel1,Integer categoryLevel2,
							Integer categoryLevel3,Integer flatformId,
							Integer from,Integer pageSize);
	/**
	 * ��ѯapp_info������������
	 * @return
	 */
	public int app_infoCount();
	
	/**
	 * ����APP״̬
	 * @return
	 */
	public List<Data_dictionary> getAPP_STATUS() ;
	
	/**
	 * ��������ƽ̨
	 */
	public List<Data_dictionary> getAPP_FLATFORM() ;
	
	/**
	 * ����һ���˵�
	 */
	public List<App_category> getCotegeryLevel1();
	/**
	 * ���ض����˵�
	 */
	public List<App_category> getCotegeryLevel2(Integer uid);
	/**
	 * ���������˵�
	 */
	public List<App_category> getCotegeryLevel3();
}
