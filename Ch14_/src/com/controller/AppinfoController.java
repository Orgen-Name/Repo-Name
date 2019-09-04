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
		// ����ҳ������
		int pageSize = 5;
		// ��ǰҳ��
		int from = 1;
		// ����Ҫȥ��ѯ���ݣ���ô��Ҫȷ������
		/*if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			_queryUserRole = Integer.parseInt(queryUserRole);
		}*/
		if (pageIndex != null) {
			from = Integer.parseInt(pageIndex);
		}
		// ������
		int totalCount = appinfoService.app_infoCount();
		// ��ҳ��
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(from);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();
		// ������ҳ��βҳ
		if (from < 1) {
			from = 1;
		} else if (from > totalPageCount) {
			from = totalPageCount;
		}
		// ��ѯ��ҳ����
		appinfolist = appinfoService.getAppinfo(querySoftwareName, queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId , from - 1, pageSize);
		
		model.addAttribute("appInfoList", appinfolist);

		List<Data_dictionary> flatformlist = appinfoService.getAPP_FLATFORM();
		
		List<Data_dictionary> statuslist = appinfoService.getAPP_STATUS(null);

		//����
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
		// ��ֵһ��һ���Ĵ���ҳ��
		model.addAttribute("statusList",statuslist );
		model.addAttribute("flatFormList", flatformlist);
		model.addAttribute("pages", page);

		return "/developer/appinfolist";
	}
	
	@RequestMapping(value="/categorylevellist",method = RequestMethod.GET)
	@ResponseBody
	public Object Categorylevellist(@RequestParam String pid){
		logger.debug("pidΪ��"+pid);
		List<App_category> categoryLevelList = null;
		if(pid == null || pid.equals("")){
			categoryLevelList = appinfoService.getCotegeryLevel(null);
		}else if(pid !=null && !pid.equals("")) {
			categoryLevelList = appinfoService.getCotegeryLevel(Integer.parseInt(pid));
		}
		return JSONArray.toJSONString(categoryLevelList);
	}
	

	/**
	 * ��ת�����ҳ��
	 * @return
	 */
	@RequestMapping(value="/appinfoadd")
	public String AppinfoAdd(){
		return "/developer/appinfoadd";
	}
	/**
	 * �첽�������ҳ�������ƽ̨
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
	 * ���Ӧ����Ϣ
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
		//�ж��ļ��Ƿ�Ϊ��
		if(!attach.isEmpty()){
		//�����ύ·��
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"); 
		//��ȡԴ�ļ���
		String oldFileName = attach.getOriginalFilename();
		//�õ�ԭ�ļ���׺
		String prefix=FilenameUtils.getExtension(oldFileName);     
		int filesize = 500000;
		if(attach.getSize() >  filesize){//�ϴ���С���ó��� 500k
			request.setAttribute("fileUploadError", " * �ϴ���С���ó��� 500k");
			return "appinfoadd";
		}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//�ϴ�ͼƬ��ʽ����ȷ
		String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";  
		logger.debug("new fileName======== " + attach.getName());
		File targetFile = new File(path, fileName);  
		if(!targetFile.exists()){  
		targetFile.mkdirs();  
		}  
		//����  
		try {  
		attach.transferTo(targetFile);  
		} catch (Exception e) {  
			e.printStackTrace();  
			//request.setAttribute("uploadFileError", " * �ϴ�ʧ�ܣ�");
			request.setAttribute(errorInfo, " * �ϴ�ʧ�ܣ�");
			return "appinfoadd";
		}  
			a_logoPicPath = path+File.separator+fileName;
			logger.debug("a_logoPicPath��"+a_logoPicPath);
		}else{
			request.setAttribute(errorInfo, " * �ϴ�ͼƬ��ʽ����ȷ");
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
		String oldFileName = attach.getOriginalFilename();//ԭ�ļ���
		String prefix = FilenameUtils.getExtension(oldFileName);//ԭ�ļ���׺
		int filesize = 500000;
		if(attach.getSize() > filesize){//�ϴ���С���ó��� 50k
		 return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
				 +"&error=error4";
		}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
		||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//�ϴ�ͼƬ��ʽ
		 String fileName = APKName + ".jpg";//�ϴ�LOGOͼƬ����:apk����.apk
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
	
	@RequestMapping(value="/delfile",method=RequestMethod.GET)
	@ResponseBody
	public Object DelFile(Integer id,String flag){
		logger.debug("ɾ����IDΪ��"+id);
		HashMap<String, String> Map1 = new HashMap<String,String>();
		if(appinfoService.getDeleFile(id)>0){
			Map1.put("result", "success");
		}else{
			Map1.put("result", "failed");
		}
		return JSONArray.toJSONString(Map1);
	}
	
	
}
