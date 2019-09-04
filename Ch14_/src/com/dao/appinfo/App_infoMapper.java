package com.dao.appinfo;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
	public List<Data_dictionary> getAPP_STATUS(@Param("valueId")Integer status) ;
	
	/**
	 * ��������ƽ̨
	 */
	public List<Data_dictionary> getAPP_FLATFORM() ;
	
	/**
	 * ���ز˵�
	 */
	public List<App_category> getCotegeryLevel(@Param("parentId")Integer parentId);
	
	
	/**
	 * ���Ӧ����Ϣ
	 * @return
	 */
	public int App_infoAdd(App_info app_info);
	
	/**
	 * ��֤APK�Ƿ��ظ�
	 */
	public int ExistAPK(String APKName);
	
	/**
	 * ����ID��ѯ������Ϣ����ת���޸�ҳ��
	 */
	public App_info getApp_infoID(@Param("id")Integer id);
	/**
	 * ��ȡAPP״̬
	 * @param valueId
	 * @return
	 */
	public String getData_dictionary(@Param("valueId")Integer valueId);
	
	/**
	 * ɾ��ͼƬ·��
	 */
	public int getDeleFile(@Param("id")Integer id);
	
	/**
	 * �޸�info
	 */
	public int App_infomodify(App_info appinfo);
}
