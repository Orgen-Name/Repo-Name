package com.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pojo.Backend_user;
import com.service.backendLogin.BackendLoginService;

@Controller
@RequestMapping("/backend")
public class BackendLoginController {
	private Logger logger =Logger.getLogger(BackendLoginController.class);
	
	@Autowired
	public BackendLoginService backendLoginService;
	//璺宠浆鍚庡彴鐧诲綍鐣岄潰
	@RequestMapping(value="/dologin")
	public String doLogin(){
		return "backendlogin";
	}
	//鐧诲綍
	@RequestMapping(value="/gologin",method=RequestMethod.POST)
	 public String Login(String userCode,String userPassword,HttpSession session,Model model){
		logger.debug("进入了gologin 账号"+userCode+"密码"+userPassword);
		Backend_user backend_user = new Backend_user();
		backend_user=backendLoginService.Login(userCode, userPassword);
		if (backend_user != null) {
			session.setAttribute("backend_user",backend_user);
			return "/backend/main";
		}
		String code = backendLoginService.userCode(userCode);
		String error = "";
		if (code ==null) {
			error = "用户名输入错误";
		} else {
			error = "密码输入错误";
		}
		model.addAttribute("error", error);
		return "/backendlogin";
	 }
	//娉ㄩ攢
	@RequestMapping(value="/logout")
	public String QT(HttpSession session){
		session.setAttribute("backend_user", null);
		return "backendlogin";
	}
	
	@RequestMapping(value="/main")
	public String Main(){
		logger.debug("==================================>进来了");
		return "/backend/main";
	}
}
