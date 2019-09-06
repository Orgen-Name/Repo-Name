package com.dao.appinfo;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Data_dictionary;
import com.pojo.App_vorsion;

public interface App_infoMapper {
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
	public List<App_info> getAppinfo(@Param("softwareName")String softwareName,@Param("status")Integer status,
			@Param("categoryLevel1")Integer categoryLevel1,@Param("categoryLevel2")Integer categoryLevel2,
			@Param("categoryLevel3")Integer categoryLevel3,@Param("flatformId")Integer flatformId,
			@Param("from")Integer from,@Param("pageSize")Integer pageSize);
	
	/**
	 * 查询app_info表数据总数量
	 * @return
	 */
	public int app_infoCount();
	
	/**
	 * 加载APP状态
	 * @return
	 */
	public List<Data_dictionary> getAPP_STATUS(@Param("valueId")Integer status) ;
	
	/**
	 * 加载所属平台
	 */
	public List<Data_dictionary> getAPP_FLATFORM() ;
	
	/**
	 * 加载菜单
	 */
	public List<App_category> getCotegeryLevel(@Param("parentId")Integer parentId);
	
	
	/**
	 * 添加应用信息
	 * @return
	 */
	public int App_infoAdd(App_info app_info);
	
	/**
	 * 验证APK是否重复
	 */
	public int ExistAPK(String APKName);
	
	/**
	 * 依据ID查询基本信息并跳转到修改页面
	 */
	public App_info getApp_infoID(@Param("id")Integer id);
	
	public App_info getAppInfo(@Param(value="id")Integer id,@Param(value="APKName")String APKName)throws Exception;
	/**
	 * 获取APP状态
	 * @param valueId
	 * @return
	 */
	public String getData_dictionary(@Param("valueId")Integer valueId);
	
	
	/**
	 * 修改info
	 */
	public int App_infomodify(App_info appinfo);
	
	/**
	 * 根据ID查看应用信息
	 */
	public App_info getAppinfoID(@Param("id")Integer id);
	
	/**
	 * 根据ID删除应用信息
	 */
	public int getAppinfoDeleteID(@Param("id")Integer id);
	/**
	 * 查询是否有最新版本号
	 */
	public List<App_vorsion> getVersion(@Param("appId")Integer appId);
	/**
	 * 修改前加载该版本信息
	 */
	public App_vorsion getModifyID(@Param("id")Integer id);
	

	public int deleteVersionByAppId(@Param("appId")Integer appId);
	
	public App_vorsion getAppVersionById(@Param("id")Integer id);
	
	public int modify(App_vorsion appVersion);
	
	/**
	 * 删除资源物理路径
	 * @param id
	 * @return
	 */
	public int deleteApkFile(@Param("id")Integer id);
	
	/**
	 * 删除Logo路基
	 * @return
	 */
	public int deleteAppLogo(@Param(value="id")Integer id);
}
