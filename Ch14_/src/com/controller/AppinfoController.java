package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.appinfo.App_infoService;

@Controller
@RequestMapping("/devAppinfo")
public class AppinfoController {

	public App_infoService appinfoService;
	
	@RequestMapping("list")
	public String AppinfoList(){
		
		return null;
	}
	
}
