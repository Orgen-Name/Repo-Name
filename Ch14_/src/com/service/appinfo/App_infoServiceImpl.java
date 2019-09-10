package com.service.appinfo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.appinfo.App_infoMapper;
import com.dao.dev_user.DevMapper;
import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Data_dictionary;
import com.pojo.Dev_user;
import com.pojo.App_vorsion;

@Service("App_infoService")
public class App_infoServiceImpl implements App_infoService {

	@Autowired
	public App_infoMapper app_infoMapper;

	@Override
	public List<App_info> getAppinfo(String softwareName, Integer status, Integer categoryLevel1, Integer categoryLevel2,
			Integer categoryLevel3, Integer flatformId, Integer from, Integer pageSize) {
		return app_infoMapper.getAppinfo(softwareName, status, categoryLevel1, categoryLevel2, categoryLevel3, flatformId, from, pageSize);
	}

	@Override
	public int app_infoCount() {
		return app_infoMapper.app_infoCount();
	}

	@Override
	public List<Data_dictionary> getAPP_STATUS(Integer status) {
		return app_infoMapper.getAPP_STATUS(status);
	}

	@Override
	public List<Data_dictionary> getAPP_FLATFORM() {
		return app_infoMapper.getAPP_FLATFORM();
	}
	
	@Override
	public List<App_category> getCotegeryLevel(Integer parentId) {
		return app_infoMapper.getCotegeryLevel(parentId);
	}

	@Override
	public int App_infoAdd(App_info app_info) {
		return app_infoMapper.App_infoAdd(app_info);
	}

	@Override
	public int ExistAPK(String APKName) {
		return app_infoMapper.ExistAPK(APKName);
	}

	@Override
	public App_info getApp_infoID(Integer id) {
		return app_infoMapper.getApp_infoID(id);
	}
	@Override
	public int App_infomodify(App_info appinfo) {
		return app_infoMapper.App_infomodify(appinfo);
	}

	@Override
	public App_info getAppinfoID(Integer id) {
		return app_infoMapper.getAppinfoID(id);
	}

	@Override
	public int getAppinfoDeleteID(Integer id) {
		return app_infoMapper.getAppinfoDeleteID(id);
	}

	@Override
	public List<App_vorsion> getVersion(Integer appId) {
		return app_infoMapper.getVersion(appId);
	}

	@Override
	public App_vorsion getModifyID(Integer id) {
		return app_infoMapper.getModifyID(id);
	}

	@Override
	public int deleteVersionByAppId(Integer appId){
		return app_infoMapper.deleteVersionByAppId(appId);
	}

	@Override
	public App_vorsion getAppVersionById(Integer id){
		return app_infoMapper.getAppVersionById(id);
	}

	@Override
	public int modify(App_vorsion appVersion){
		return app_infoMapper.modifyvorsion(appVersion);
	}

	@Override
	public int deleteApkFile(Integer id){
		return app_infoMapper.deleteApkFile(id);
	}

	@Override
	public int deleteAppLogo(Integer id) {
		return app_infoMapper.deleteAppLogo(id);
	}

	@Override
	public App_info getAppInfo(Integer id, String APKName) throws Exception {
		return app_infoMapper.getAppInfo(id, APKName);
	}

	@Override
	public boolean appsysadd(App_vorsion appVersion) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		Integer versionId = null;
		if(app_infoMapper.appsysadd(appVersion) > 0){
			versionId = appVersion.getId();
			flag = true;
		}
		return flag;
	}

	@Override
	public int appsysUpdateSaleStatusByAppId(App_info appInfo) throws Exception {
		return app_infoMapper.modify(appInfo);
	}
	
	@Override
	public App_info infoStatus(Integer id) {
		return app_infoMapper.infoStatus(id);
	}
	
}
