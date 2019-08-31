package cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class DevController {
	@RequestMapping("/devlogin.html")
	public String devLogin(){
		return "devlogin";
	}
}
