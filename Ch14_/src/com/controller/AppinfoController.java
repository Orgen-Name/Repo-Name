package com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.Data_dictionary;
import com.pojo.Dev_user;
import com.pojo.App_vorsion;
import com.service.appinfo.App_infoService;
import com.tools.Constants;
import com.tools.PageSupport;

@Controller
@RequestMapping("/devAppinfo")
public class AppinfoController {
	
	public static Logger logger = Logger.getLogger(AppinfoController.class);
	@Autowired
	public App_infoService appinfoService;
	
	@RequestMapping("/list")
	public String AppinfoList(Model model, @RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) Integer queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) Integer queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		logger.debug("queryStatus======>"+queryStatus);
		List<App_info> appinfolist = new ArrayList<App_info>();
		// 设置页面容量
		int pageSize = 5;
		// 当前页码
		int from = 1;
		// 我们要去查询数据，那么就要确定条件
		/*if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			_queryUserRole = Integer.parseInt(queryUserRole);
		}*/
		if (pageIndex != null) {
			from = Integer.parseInt(pageIndex);
		}
		// 总数量
		int totalCount = appinfoService.app_infoCount();
		// 总页数
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(from);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();
		// 控制首页和尾页
		if (from < 1) {
			from = 1;
		} else if (from > totalPageCount) {
			from = totalPageCount;
		}
		// 查询分页数据
		appinfolist = appinfoService.getAppinfo(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId , from - 1, pageSize);
		
		model.addAttribute("appInfoList", appinfolist);

		List<Data_dictionary> flatformlist = appinfoService.getAPP_FLATFORM();
		
		List<Data_dictionary> statuslist = appinfoService.getAPP_STATUS(null);

		//分类
		List<App_category> categoryLevel2list=null;
		List<App_category> categoryLevel3list=null;
				
		List<App_category> categoryLevel1list=appinfoService.getCotegeryLevel(null);
		model.addAttribute("categoryLevel1List",categoryLevel1list);
		
		if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
			categoryLevel2list = appinfoService.getCotegeryLevel(queryCategoryLevel1);
			model.addAttribute("categoryLevel2List", categoryLevel2list);
		}
		if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
			categoryLevel3list = appinfoService.getCotegeryLevel(queryCategoryLevel2);
			model.addAttribute("categoryLevel3List", categoryLevel3list);
		}
		// 把值一个一个的传给页面
		model.addAttribute("statusList",statuslist );
		model.addAttribute("flatFormList", flatformlist);
		model.addAttribute("pages", page);
		model.addAttribute("queryStatus", queryStatus);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("queryFlatformId", queryFlatformId);

		return "/developer/appinfolist";
	}
	
	@RequestMapping(value="/categorylevellist",method = RequestMethod.GET)
	@ResponseBody
	public Object Categorylevellist(@RequestParam String pid){
		logger.debug("pid为："+pid);
		List<App_category> categoryLevelList = null;
		if(pid == null || pid.equals("")){
			categoryLevelList = appinfoService.getCotegeryLevel(null);
		}else if(pid !=null && !pid.equals("")) {
			categoryLevelList = appinfoService.getCotegeryLevel(Integer.parseInt(pid));
		}
		return JSONArray.toJSONString(categoryLevelList);
	}
	

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value="/appinfoadd")
	public String AppinfoAdd(){
		return "/developer/appinfoadd";
	}
	/**
	 * 异步加载添加页面的所属平台
	 * @param tcode
	 * @return
	 */
	@RequestMapping(value="/datadictionarylist",method=RequestMethod.GET)
	@ResponseBody
	public Object DateDicList(String tcode){
		List<Data_dictionary> diclist= appinfoService.getAPP_FLATFORM();
		return JSONArray.toJSONString(diclist);
	}
	/**
	 * 添加应用信息
	 * @param app_info
	 * @param session
	 * @param request
	 * @param attach
	 * @return
	 */
	@RequestMapping(value="/appinfoaddsave",method = RequestMethod.POST)
	public String AppinfoAddSave(App_info app_info,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value="a_logoPicPath",required=false)MultipartFile attach) {
		String a_logoPicPath = null;
		String errorInfo = null;
		//判断文件是否为空
		if(!attach.isEmpty()){
		//设置提交路径
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"); 
		//获取源文件名
		String oldFileName = attach.getOriginalFilename();
		//得到原文件后缀
		String prefix=FilenameUtils.getExtension(oldFileName);     
		int filesize = 500000;
		if(attach.getSize() >  filesize){//上传大小不得超过 500k
			request.setAttribute("fileUploadError", " * 上传大小不得超过 500k");
			return "appinfoadd";
		}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
		String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";  
		logger.debug("new fileName======== " + attach.getName());
		File targetFile = new File(path, fileName);  
		if(!targetFile.exists()){  
		targetFile.mkdirs();  
		}  
		//保存  
		try {  
		attach.transferTo(targetFile);  
		} catch (Exception e) {  
			e.printStackTrace();  
			//request.setAttribute("uploadFileError", " * 上传失败！");
			request.setAttribute(errorInfo, " * 上传失败！");
			return "appinfoadd";
		}  
			a_logoPicPath = path+File.separator+fileName;
			logger.debug("a_logoPicPath："+a_logoPicPath);
		}else{
			request.setAttribute(errorInfo, " * 上传图片格式不正确");
			return "appinfoadd";
		}
		}
			app_info.setLogoPicPath(a_logoPicPath);;
			app_info.setCreationDate(new Date());
			if (appinfoService.App_infoAdd(app_info)>0) {
				return "redirect:/devAppinfo/list";
			}
			return "appinfoadd";
	}
	
	@RequestMapping(value="/apkexist",method = RequestMethod.GET)
	@ResponseBody
	public Object ExistAPK(String APKName){
		HashMap<String, String> map = new HashMap<String,String>();
		if(APKName.equals("") || APKName==null){
			map.put("APKName", "empty");
		}else if(appinfoService.ExistAPK(APKName)>0){
			map.put("APKName", "exist");
		}else if(appinfoService.ExistAPK(APKName)<=0){
			map.put("APKName", "noexist");
		}
		return JSONArray.toJSONString(map);
	}
	
	@RequestMapping(value="/appinfomodify")
	public String Appinfomodify(Integer id,Model model){
		App_info appinfo =  appinfoService.getApp_infoID(id);
		int sh = appinfo.getStatus();
		List<Data_dictionary> dic = appinfoService.getAPP_STATUS(sh);
		appinfo.setStatusName(dic.get(0).getValueName());
		model.addAttribute("appInfo",appinfo);
		return "developer/appinfomodify";
	}
	
	@RequestMapping(value="/appinfomodifysave")
	public String AppinFomodifysave(App_info appInfo,HttpSession session,HttpServletRequest request,
			@RequestParam(value="attach",required= false) MultipartFile attach){		
		String logoPicPath =  null;
		String logoLocPath =  null;
		String APKName = appInfo.getAPKName();
		if(!attach.isEmpty()){
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
		logger.info("uploadFile path: " + path);
		String oldFileName = attach.getOriginalFilename();//原文件名
		String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
		int filesize = 500000;
		if(attach.getSize() > filesize){//上传大小不得超过 50k
		 return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
				 +"&error=error4";
		}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
		||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
		 String fileName = APKName + ".jpg";//上传LOGO图片命名:apk名称.apk
		 File targetFile = new File(path,fileName);
		 if(!targetFile.exists()){
			 targetFile.mkdirs();
		 }
		 try {
			attach.transferTo(targetFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
					+"&error=error2";
		} 
		 logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
		 logoLocPath = path+File.separator+fileName;
		}else{
		return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
				 +"&error=error3";
		}
		}
		appInfo.setModifyBy(((Dev_user)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setModifyDate(new Date());
		appInfo.setLogoLocPath(logoLocPath);
		appInfo.setLogoPicPath(logoPicPath);
		try {
			if(appinfoService.App_infomodify(appInfo)>0){
				return "redirect:/devAppinfo/list";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "developer/appinfomodify";
	}
	
	/*@RequestMapping(value="/delfile",method=RequestMethod.GET)
	@ResponseBody
	public Object DelFile(Integer id,String flag){
		logger.debug("删除的ID为："+id);
		HashMap<String, String> Map1 = new HashMap<String,String>();
		
		if(appinfoService.getDeleFile(id)>0){
			Map1.put("result", "success");
		}else{
			Map1.put("result", "failed");
		}
		return JSONArray.toJSONString(Map1);
	}*/
	
	@RequestMapping(value="/appview",method = RequestMethod.GET)
	public String AppView(Integer id ,Model model){
		App_info appinfo = appinfoService.getAppinfoID(id);
		List<App_vorsion> snippetlist = appinfoService.getVersion(id);
		model.addAttribute("appInfo",appinfo);
		model.addAttribute("appVersionList",snippetlist);
		return "/developer/appinfoview";
	}

	@RequestMapping(value="/delapp",method = RequestMethod.GET)
	@ResponseBody
	public Object DelApp(Integer id){
		HashMap<String, String> resultMap = new HashMap<String,String>();
		if(appinfoService.getAppinfoDeleteID(id)>0){
			resultMap.put("delResult", "true");
		}else if(appinfoService.getAppinfoDeleteID(id)==0){
			resultMap.put("delResult", "notexist");
		}else{
			resultMap.put("delResult", "false");
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/appversionmodify",method=RequestMethod.GET)
	public String AppVersionModify(@RequestParam(value="vid",required= false)Integer vid,
			@RequestParam(value="aid",required= false)Integer aid,
			@RequestParam(value="error",required= false)String fileUploadError
			,Model model){
		List<App_vorsion> vorsionlist = appinfoService.getVersion(aid);
		App_vorsion vosion = appinfoService.getModifyID(vid);
		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}
		model.addAttribute("appVersionList",vorsionlist);
		model.addAttribute("appVersion",vosion);
		model.addAttribute("fileUploadError",fileUploadError);
		return "/developer/appversionmodify";
	}
	
	@RequestMapping(value="/appversionmodifysave",method=RequestMethod.POST)
	public String AppVersionModifySave(App_vorsion appVersion,HttpSession session,HttpServletRequest request,
			@RequestParam(value="attach",required= false) MultipartFile attach){	
		
String downloadLink =  null;
String apkLocPath = null;
String apkFileName = null;
if(!attach.isEmpty()){
	String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
	logger.info("uploadFile path: " + path);
	String oldFileName = attach.getOriginalFilename();//原文件名
	String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
	if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
		 String apkName = null;
		 try {
			apkName = appinfoService.getAppInfo(appVersion.getAppId(),null).getAPKName();
		 } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		 }
		 if(apkName == null || "".equals(apkName)){
			 return "redirect:/devAppinfo/appversionmodify?vid="+appVersion.getId()
					 +"&aid="+appVersion.getAppId()
					 +"&error=error1";
		 }
		 apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
		 File targetFile = new File(path,apkFileName);
		 if(!targetFile.exists()){
			 targetFile.mkdirs();
		 }
		 try {
			attach.transferTo(targetFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/devAppinfo/appversionmodify?vid="+appVersion.getId()
					 +"&aid="+appVersion.getAppId()
					 +"&error=error2";
		} 
		downloadLink = request.getContextPath()+"/statics/uploadfiles/"+apkFileName;
		apkLocPath = path+File.separator+apkFileName;
	}else{
		return "redirect:/devAppinfo/appversionmodify?vid="+appVersion.getId()
				 +"&aid="+appVersion.getAppId()
				 +"&error=error3";
	}
}
appVersion.setModifyBy(((Dev_user)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
appVersion.setModifyDate(new Date());
appVersion.setDownloadLink(downloadLink);
appVersion.setApkLocPath(apkLocPath);
appVersion.setApkFileName(apkFileName);
try {
	if(appinfoService.modify(appVersion)>0){
		return "redirect:/devAppinfo/list";
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return "developer/appversionmodify";
}
	/**
	* 修改操作时，删除文件（logo图片/apk文件），并更新数据库（app_info/app_version）
	* @param fileUrlPath
	* @param fileLocPath
	* @param flag
	* @param id
	* @return
	*/
	@RequestMapping(value = "/delfile",method=RequestMethod.GET)
	@ResponseBody
	public Object DelFile(@RequestParam(value="flag",required=false) String flag,
			 @RequestParam(value="id",required=false) String id){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
			if(flag == null || flag.equals("") ||
					id == null || id.equals("")){
					resultMap.put("result", "failed");
			}else if(flag.equals("apk")){//删除apk文件（操作app_version）
					try {
						if(appinfoService.deleteApkFile(Integer.parseInt(id))>0){//更新表
							resultMap.put("result", "success");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			}else if(flag.equals("logo")){//删除logo图片（操作app_info）
				try {
					if(appinfoService.deleteAppLogo(Integer.parseInt(id))>0){//更新表
						resultMap.put("result", "success");
				    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		return JSONArray.toJSONString(resultMap);
		
	}
	
	@RequestMapping(value="/appversionadd",method=RequestMethod.GET)
	public String AppVersionAdd(){
		return null;
	}
}
