package com.service.appinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Data_dictionary;
import com.pojo.Dev_user;
import com.pojo.App_vorsion;

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
	public List<Data_dictionary> getAPP_STATUS(Integer status) ;
	
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
	 * �޸�info
	 */
	public int App_infomodify(App_info appinfo);
	
	/**
	 * ����ID�鿴Ӧ����Ϣ
	 */
	public App_info getAppinfoID(@Param("id")Integer id);
	
	/**
	 * ����IDɾ��Ӧ����Ϣ
	 */
	public int getAppinfoDeleteID(@Param("id")Integer id);
	/**
	 * ��ѯ���°汾��
	 */
	public List<App_vorsion> getVersion(Integer appId);
	/**
	 * �޸�ǰ���ظð汾��Ϣ
	 */
	public App_vorsion getModifyID(Integer id);
	
	public int deleteVersionByAppId(Integer appId);
	
	public App_vorsion getAppVersionById(Integer id);
	
	public int modify(App_vorsion appVersion);
	/**
	 * ɾ����Դ����·��
	 */
	public int deleteApkFile(Integer id);
	
	public App_info getAppInfo(Integer id,String APKName) throws Exception;
	/**
	 * ɾ��ͼƬ·��
	 */
	public int deleteAppLogo(Integer id);
}
