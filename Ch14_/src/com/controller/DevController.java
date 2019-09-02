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
	 * ����ǰ̨�û���¼
	 * @return
	 */
	@RequestMapping(value="/login")
	public String Login(){
		return "devlogin";
	}
	/**
	 * ��¼�Ƿ�ɹ�
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
			error = "�û���������!";
		} else {
			error = "�����������";
		}
		model.addAttribute("error", error);
		return "devlogin";
	}
	
	/**
	 * ��ת����ҳ��
	 * @return
	 */
	@RequestMapping(value="/main")
	public String Main(){
		logger.debug("==================================>������");
		return "/developer/main";
	}
	
	@RequestMapping(value="/logout")
	public String LogOut(HttpSession session){
		session.removeAttribute("devUserSession");
		return "devlogin";
	}
}
