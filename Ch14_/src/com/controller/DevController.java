package com.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pojo.Dev_user;
import com.service.DevService;

@Controller
@RequestMapping("/dev")
public class DevController {
	private Logger logger = Logger.getLogger(DevController.class);
	
	@Autowired
	public DevService devService;
	
	
	/**
	 * 进入前台用户登录
	 * @return
	 */
	@RequestMapping(value="/login")
	public String Login(){
		return "devlogin";
	}
	/**
	 * 登录是否成功
	 * @return
	 */
	@RequestMapping(value="/dologin.html",method = RequestMethod.POST)
	public String doLogin(String devCode,String devPassword,HttpSession session,Model model){
		Dev_user dev_user = null;
		dev_user = devService.getLogin(devCode, devPassword);
		if(dev_user != null){
			session.setAttribute("devUserSession",dev_user);
			return "redirect:/dev/main";
		}
		String code = devService.findCode(devCode);
		String error = "";
		if (code == null) {
			error = "用户名不存在!";
		} else {
			error = "密码输入错误";
		}
		model.addAttribute("error", error);
		return "devlogin";
	}
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public String Main(){
		logger.debug("==================================>进来了");
		return "/developer/main";
	}
	
	@RequestMapping(value="/logout")
	public String LogOut(HttpSession session){
		session.removeAttribute("devUserSession");
		return "devlogin";
	}
}
