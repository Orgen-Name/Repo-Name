package com.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pojo.Dev_user;
import com.service.DevService;

@Controller
@RequestMapping("/Dev")
public class DevController {
	private Logger logger = Logger.getLogger(DevController.class);
	
	@Autowired
	public DevService devService;
	
	
	@RequestMapping(value="/dologin.html",method = RequestMethod.POST)
	public String Login(String devCode,String devPassword,HttpSession session){
		Dev_user devuser = new Dev_user();
		devService.getLogin(devCode, devPassword);
		return null;
	}
	
}
