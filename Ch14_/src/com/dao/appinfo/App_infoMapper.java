package com.dao.appinfo;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Data_dictionary;

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
	public List<Data_dictionary> getAPP_STATUS() ;
	
	/**
	 * 加载所属平台
	 */
	public List<Data_dictionary> getAPP_FLATFORM() ;
	
	/**
	 * 加载一级菜单
	 */
	public List<App_category> getCotegeryLevel1();
	/**
	 * 加载二级菜单
	 */
	public List<App_category> getCotegeryLevel2(Integer uid);
	/**
	 * 加载三级菜单
	 */
	public List<App_category> getCotegeryLevel3();
}
