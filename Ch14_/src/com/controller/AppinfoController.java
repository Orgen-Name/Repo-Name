package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.App_info;
import com.pojo.Data_dictionary;
import com.pojo.Dev_user;
import com.service.appinfo.App_infoService;
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
		
		List<Data_dictionary> statuslist = appinfoService.getAPP_STATUS();
		// ��ֵһ��һ���Ĵ���ҳ��
		model.addAttribute("queryStatus",statuslist );
		model.addAttribute("queryFlatformId", flatformlist);
		model.addAttribute("pages", page);

		logger.debug("�Ѿ�����Ҫ��ת��ʱ���ˡ�����������");
		return "/developer/appinfolist";
	}
	
}
