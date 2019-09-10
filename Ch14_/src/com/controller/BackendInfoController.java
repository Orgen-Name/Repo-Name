package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.pojo.App_category;
import com.pojo.App_info;
import com.pojo.App_vorsion;
import com.pojo.Backend_user;
import com.pojo.Data_dictionary;
import com.service.backendinfo.BackendInfoService;
import com.tools.PageSupport;

@Controller
@RequestMapping("/backendInfo")
public class BackendInfoController {
	private Logger logger =Logger.getLogger(BackendInfoController.class);
	
	@Autowired
	public BackendInfoService bService;
	 
	@RequestMapping(value="/app")
	public String getUserList(Model model,
			@RequestParam(value="querySoftwareName",required=false)String softwareName,
			@RequestParam(value="queryFlatformId",required=false)String flatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)String categoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)String categoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)String categoryLevel3,
			@RequestParam(value="pageIndex",required=false)String pageIndex) throws Exception{
		logger.info("getUserList ---- > softwareName: " + softwareName);
		logger.info("getUserList ---- > flatformId: " + flatformId);
		logger.info("getUserList ---- > categoryLevel1: " + categoryLevel1);
		logger.info("getUserList ---- > categoryLevel2: " + categoryLevel2);
		logger.info("getUserList ---- > categoryLevel3: " + categoryLevel3);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		int _flatformId = 0;
		int _categoryLevel1=0;
		int _categoryLevel2=0;
		int _categoryLevel3=0;
		List<Backend_user> appInfoList  = null;
		//璁剧疆椤甸潰瀹归噺
		int pageSize = 5;
		//褰撳墠椤电爜
		int currentpageNo = 1;
		//鎴戜滑瑕佸幓鏌ヨ鏁版嵁锛岄偅涔堝氨瑕佺‘瀹氭潯浠�
		if(softwareName == null){
			softwareName = "";
		}
		if(flatformId != null && !flatformId.equals("")){
			_flatformId = Integer.parseInt(flatformId);
		}
		if(categoryLevel1 != null && !categoryLevel1.equals("")){
			_categoryLevel1 = Integer.parseInt(categoryLevel1);
		}
		if(categoryLevel2 != null && !categoryLevel2.equals("")){
			_categoryLevel2 = Integer.parseInt(categoryLevel2);
		}
		if(categoryLevel3 != null && !categoryLevel3.equals("")){
			_categoryLevel3 = Integer.parseInt(categoryLevel3);
		}
		if(pageIndex != null){
			currentpageNo = Integer.parseInt(pageIndex);
		}
		//鎬绘暟閲�
		int totalCount = bService.getbackInfoCount(softwareName, _flatformId, _categoryLevel1, _categoryLevel2, _categoryLevel3);
		//鎬婚〉鏁�
		PageSupport page =new PageSupport();
		page.setCurrentPageNo(currentpageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		System.err.println("page.setCurrentPageNo(currentpageNo);"+page.getCurrentPageNo()+"   page.setPageSize(pageSize);"+page.getPageSize()+"page.setTotalCount(totalCount);"+page.getTotalPageCount());
		int totalPageCount = page.getTotalPageCount();
		//鎺у埗棣栭〉鍜屽熬椤�
		if(currentpageNo < 1){
			currentpageNo = 1;
		} else if(currentpageNo > totalPageCount){
			currentpageNo = totalPageCount;
		}
		//鏌ヨ鍒嗛〉鏁版嵁
		appInfoList  = bService.getbackInfoList(softwareName, _flatformId, _categoryLevel1, _categoryLevel2, _categoryLevel3, ((currentpageNo-1)*pageSize), pageSize);
		System.err.println("list:"+appInfoList);
		model.addAttribute("appInfoList", appInfoList );
		
		//鎶婂�间竴涓竴涓殑浼犵粰椤甸潰
		model.addAttribute("querySoftwareName", softwareName);
		model.addAttribute("queryFlatformId", flatformId);
		model.addAttribute("queryCategoryLevel1", categoryLevel1);
		model.addAttribute("queryCategoryLevel2", categoryLevel2);
		model.addAttribute("queryCategoryLevel3", categoryLevel3);
		model.addAttribute("pages", page);
		List<App_category> list =bService.Selectquery();
		List<Data_dictionary> flatFormList =bService.selectValueName();
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", list);
		logger.debug("宸茬粡鍒颁簡瑕佽烦杞殑鏃跺�欎簡銆傦紒銆傘�傘�傘��");
		return "backend/applist";
		
	}
	
	@RequestMapping(value="/View.json",produces= {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object billlis(int pid) {
		System.err.println("杩涙潵浜�");
		List<App_category> app_categories = new ArrayList<App_category>(); 
		try {
			app_categories = bService.getbackInfo(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(app_categories);
	}
	@RequestMapping(value="/check")
	public String checkApp(@RequestParam(value="aid",required=false)String aid,
			@RequestParam(value="vid",required=false)String vid,HttpSession session){
		System.err.println(vid+"jia"+aid);
		App_info app_infos =new App_info();
		App_vorsion snippets =new App_vorsion();
		int _aid=0;
		int _vid=0;
		if(aid != null && !aid.equals("")){
			_aid = Integer.parseInt(aid);
		}
		if(vid != null && !vid.equals("")){
			_vid = Integer.parseInt(vid);
		}
		app_infos =bService.getbackInfoView(_aid);
		snippets =bService.selectSnippetView(_vid);
		session.setAttribute("appInfo", app_infos);
		session.setAttribute("appVersion", snippets);
		return "backend/appcheck";
	}
	@RequestMapping(value="/checksave")
	public String updatestatus(@RequestParam(value="status",required=false)String status
			,@RequestParam(value="id",required=false)String id){
		System.err.println(status);
		int _id=0;
		int _status=0;
		if(id != null && !id.equals("")){
			_id = Integer.parseInt(id);
		}
		if(status != null && !status.equals("")){
			_status = Integer.parseInt(status);
		}
		bService.updatestatus(_status, _id);
			return "redirect:/backendInfo/app";
	}
}
