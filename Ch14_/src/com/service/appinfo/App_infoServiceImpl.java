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
	/*@Override
	public boolean appsysUpdateSaleStatusByAppId(App_info appInfo) throws Exception {
		
		 * 上架： 
			1 更改status由【2 or 5】 to 4 ， 上架时间
			2 根据versionid 更新 publishStauts 为 2 
			
			下架：
			更改status 由4给为5
		 
		
		Integer operator = appInfo.getModifyBy();
		if(operator < 0 || appInfo.getId() < 0 ){
			throw new Exception();
		}
		
		//get appinfo by appid
		App_info appInfo1 = app_infoMapper.getAppInfo(appInfo.getId(), null);
		if(null == appInfo){
			return false;
		}else{
			switch (appInfo.getStatus()) {
				case 2: //当状态为审核通过时，可以进行上架操作
					onSale(appInfo,operator,4,2);
					break;
				case 5://当状态为下架时，可以进行上架操作
					onSale(appInfo,operator,4,2);
					break;
				case 4://当状态为上架时，可以进行下架操作
					offSale(appInfo,operator,5);
					break;

			default:
				return false;
			}
		}
		return true;
	}*/
	/**
	 * on Sale
	 * @param appInfo
	 * @param operator
	 * @param appInfStatus
	 * @param versionStatus
	 * @throws Exception
	 */
	private void onSale(App_info appInfo,Integer operator,Integer appInfStatus,Integer versionStatus) throws Exception{
		offSale(appInfo,operator,appInfStatus);
		setSaleSwitchToAppVersion(appInfo,operator,versionStatus);
	}
	/**
	 * offSale
	 * @param appInfo
	 * @param operator
	 * @param appInfStatus
	 * @return
	 * @throws Exception
	 */
	private boolean offSale(App_info appInfo,Integer operator,Integer appInfStatus) throws Exception{
		App_info _appInfo = new App_info();
		_appInfo.setId(appInfo.getId());
		_appInfo.setStatus(appInfStatus);
		_appInfo.setModifyBy(operator);
		_appInfo.setOffSaleDate(new Date(System.currentTimeMillis()));
		app_infoMapper.modify(_appInfo);
		return true;
	}
	
	/**
	 * set sale method to on or off
	 * @param appInfo
	 * @param operator
	 * @return
	 * @throws Exception
	 */
	private boolean setSaleSwitchToAppVersion(App_info appInfo,Integer operator,Integer saleStatus) throws Exception{
		App_vorsion appVersion = new App_vorsion();
		appVersion.setId(appInfo.getVersionId());
		appVersion.setPublishStatus(saleStatus);
		appVersion.setModifyBy(operator);
		appVersion.setModifyDate(new Date(System.currentTimeMillis()));
		app_infoMapper.modifyvorsion(appVersion);
		return false;
	}

	@Override
	public App_info infoStatus(Integer id) {
		return app_infoMapper.infoStatus(id);
	}
	
}
