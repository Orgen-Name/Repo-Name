package cn.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.pojo.Bill;
import cn.pojo.PageSupport;
import cn.pojo.Provider;
import cn.pojo.Role;
import cn.pojo.User;
import cn.service.RoleService;
import cn.tools.Constants;

@Controller 
@RequestMapping("/role")
public class RoleController {
	private Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	@RequestMapping("/rolelist.html")
	public String RoleList(@RequestParam(value="pageIndex",required=false)String pageIndex,Model model){
		List<Role> roleList = null;
		//����ҳ������
		int pageSize = 5;
		//��ǰҳ��
		int currentpageNo = 1;
		//����Ҫȥ��ѯ���ݣ���ô��Ҫȷ������
		if(pageIndex != null){
			currentpageNo = Integer.parseInt(pageIndex);
		}
		//������
		int totalCount = roleService.getRoleCount();
		//��ҳ��
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentpageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();
		//������ҳ��βҳ
		if(currentpageNo < 1){
			currentpageNo = 1;
		} else if(currentpageNo > totalPageCount){
			currentpageNo = totalPageCount;
		}
		//��ѯ��ҳ����
		try {
			roleList = roleService.getRoleList(currentpageNo-1, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("roleList", roleList);
		//��ѯ��ɫid
		//��ֵһ��һ���Ĵ���ҳ��
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentpageNo);
		model.addAttribute("totalPageCount", totalPageCount);
		
		logger.debug("�Ѿ�����Ҫ��ת��ʱ���ˡ�����������");
		return "rolelist";
	}
	
	@RequestMapping("/add.html")
	public String RoleAdd() {
		return "roleadd";
	}
	
	@RequestMapping(value = "/rcexist",method=RequestMethod.GET)
	@ResponseBody
	public Object ExisRole(String roleCode) {
		HashMap<String, String> map = new HashMap<String,String>();
		logger.debug("�û�������"+roleCode);
		if(roleService.RoleCount(roleCode)>0) {
			map.put("roleCode", "exist");
		}
		return JSONArray.toJSONString(map);
	}
	
	@RequestMapping(value="addsave.html",method=RequestMethod.POST)
	public String addSave(Role role , HttpSession session) {
		//role.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
		role.setCreationDate(new Date());
		if(roleService.RoleAdd(role) > 0) {
			return "redirect:/role/rolelist.html";
		}
		return "roleadd";
	}
	
	@RequestMapping(value="/modify/{id}",method = RequestMethod.GET)
	public String billModify(@PathVariable Integer id,Model model) {
		Role role = new Role();
		role = roleService.getRoleById(id);
		model.addAttribute("role",role);
		return "rolemodify";
	}
	
	@RequestMapping(value="/rolemodifysave.html",method = RequestMethod.POST)
	public String Modifysave(Role role) {
		if(roleService.Rolemodify(role)>0) {
			return "redirect:/role/rolelist.html";
		}
		return "rolemodify";
		
	}
	
	
	@RequestMapping(value="/delrole",method = RequestMethod.GET)
	@ResponseBody
	public Object delRole(Integer roleid) {
		HashMap<String, String> map = new HashMap<String,String>();
		if(roleService.getExisId(roleid) >0) {
			map.put("delResult", "false");
		}else if(roleService.DelRole(roleid)>0) {
			map.put("delResult", "true");
		}else {
			map.put("delResult", "notexist");
		}
		return JSONArray.toJSONString(map);
	}
}
