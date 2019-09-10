package com.service.backendinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.App_vorsion;
import com.pojo.Backend_user;
import com.pojo.Data_dictionary;

public interface BackendInfoService {
	/**
	 * 鏌ヨ鏁版嵁鎬婚噺
	 */
	public int getbackInfoCount(String softwareName,int flatformId
			, int categoryLevel1,int categoryLevel2
			, int categoryLevel3);

	/**
	 * 鏌ヨ鍒嗛〉鏁版嵁搴�
	 */
	public List<Backend_user> getbackInfoList(String softwareName
			,int flatformId, int categoryLevel1
			,int categoryLevel ,int categoryLevel3
			,int pageIndex,int pageSize);
	/**
	 * 鏌ヨ涓嬫媺妗�
	 */
	public List<App_category> getbackInfo(int parentId);

	public List<App_category> Selectquery();

	public List<Data_dictionary> selectValueName();
	/**
	 * 瀹℃牳鏌ヨ
	 * @param id
	 * @return
	 */
	public App_info getbackInfoView(int id);
	/**
	 * 瀹℃牳鏌ヨ鐗堟湰淇℃伅
	 * @param id
	 * @return
	 */
	public App_vorsion selectSnippetView(int id);
	/**
	 * 瀹℃牳淇敼
	 * @param status
	 * @param id
	 * @return
	 */
	public int updatestatus(@Param("status") int status,@Param("id") int id);
}
