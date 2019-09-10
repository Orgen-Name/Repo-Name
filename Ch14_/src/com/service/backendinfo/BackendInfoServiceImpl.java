package com.service.backendinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.backInfo.backInfoMapper;
import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.App_vorsion;
import com.pojo.Backend_user;
import com.pojo.Data_dictionary;
@Service("BackendInfoService")
public class BackendInfoServiceImpl implements BackendInfoService{

	@Autowired
	public backInfoMapper backendInfoService;
	@Override
	public int getbackInfoCount(String softwareName, int flatformId, int categoryLevel1, int categoryLevel2,
			int categoryLevel3) {
		return backendInfoService.getbackInfoCount(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
	}

	@Override
	public List<Backend_user> getbackInfoList(String softwareName, int flatformId, int categoryLevel1,
			int categoryLevel, int categoryLevel3, int pageIndex, int pageSize) {
		return backendInfoService.getbackInfoList(softwareName, flatformId, categoryLevel1, categoryLevel, categoryLevel3, pageIndex, pageSize);
	}

	@Override
	public List<App_category> getbackInfo(int parentId) {
		return backendInfoService.getbackInfo(parentId);
	}

	@Override
	public List<App_category> Selectquery() {
		return backendInfoService.Selectquery();
	}

	@Override
	public List<Data_dictionary> selectValueName() {
		return backendInfoService.selectValueName();
	}

	@Override
	public App_info getbackInfoView(int id) {
		return backendInfoService.getbackInfoView(id);
	}

	@Override
	public App_vorsion selectSnippetView(int id) {
		return backendInfoService.selectSnippetView(id);
	}

	@Override
	public int updatestatus(int status, int id) {
		return backendInfoService.updatestatus(status, id);
	}


	
}
