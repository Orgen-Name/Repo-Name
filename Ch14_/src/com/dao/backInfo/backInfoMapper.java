package com.dao.backInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Backend_user;
import com.pojo.Data_dictionary;
import com.pojo.App_vorsion;


public interface backInfoMapper {
	/**
	 * 鏌ヨ鏁版嵁鎬婚噺
	 */
	public int getbackInfoCount(@Param("softwareName")String softwareName,@Param("flatformId")Integer flatformId
			,@Param("categoryLevel1") int categoryLevel1,@Param("categoryLevel2") int categoryLevel2
			,@Param("categoryLevel3") int categoryLevel3);

	/**
	 * 鏌ヨ鍒嗛〉鏁版嵁搴�
	 */
	public List<Backend_user> getbackInfoList(@Param("softwareName")String softwareName
			,@Param("flatformId")Integer flatformId,@Param("categoryLevel1") int categoryLevel1
			,@Param("categoryLevel2") int categoryLevel ,@Param("categoryLevel3") int categoryLevel3
			,@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	/**
	 * 鏌ヨ涓嬫媺妗�
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel
	 * @param categoryLevel3
	 * @return
	 */
	public List<App_category> getbackInfo(@Param("parentId") int parentId);
	
	public List<App_category> Selectquery();
	
	public List<Data_dictionary> selectValueName();
	
	public App_info getbackInfoView(@Param("id") int id);
	
	public App_vorsion selectSnippetView(@Param("id") int id);
	
	public int updatestatus(@Param("status") int status,@Param("id") int id);
}
