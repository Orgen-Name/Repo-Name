package com.dao.appinfo;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Data_dictionary;

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
	public List<App_info> getAppinfo(@Param("softwareName")String softwareName,@Param("status")Integer status,
			@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
			@Param("categoryLevel3")Integer categoryLevel3,@Param("flatformId")Integer flatformId,
			@Param("from")Integer from,@Param("pageSize")Integer pageSize);
	
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
