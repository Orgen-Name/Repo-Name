package cn.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.pojo.PageSupport;
import cn.pojo.Role;
import cn.pojo.User;
import cn.service.RoleService;
import cn.service.UserService;
import cn.tools.Constants;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/login.html")
	public String login() {
		logger.debug("welcome SMBMS------->");
		return "login";
	}

	@RequestMapping("/dologin.html")
	public String doLogin(String userCode, String userPassword, HttpSession session, Model model) {
		User user = userService.doLogin(userCode, userPassword);
		logger.debug("user:" + user);
		if (null != user) {
			session.setAttribute("userSession", user);
			// redirect:�൱���ض���
			// forword:�൱��ת��
			return "redirect:/user/main.html";
		}
		String code = userService.findCode(userCode);
		String error = "";
		if (code == null) {
			error = "�û���������!";
		} else {
			error = "�����������";
		}
		model.addAttribute("error", error);
		return "login";
	}

	// ��ת������
	@RequestMapping(value = "/main.html")
	public String main() {
		return "frame";
	}

	@RequestMapping(value = "/logout.do")
	public String loginOut(HttpSession session) {
		session.setAttribute("userSession", null);
		return "login";
	}

	@RequestMapping(value = "/userlist.html")
	public String getUserList(Model model, @RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		logger.info("getUserList ---- > queryUserName: " + queryUserName);
		logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		int _queryUserRole = 0;
		List<User> userlist = null;
		// ����ҳ������
		int pageSize = 5;
		// ��ǰҳ��
		int currentpageNo = 1;
		// ����Ҫȥ��ѯ���ݣ���ô��Ҫȷ������
		if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			_queryUserRole = Integer.parseInt(queryUserRole);
		}
		if (pageIndex != null) {
			currentpageNo = Integer.parseInt(pageIndex);
		}
		// ������
		int totalCount = userService.getUserCount(queryUserName, _queryUserRole);
		// ��ҳ��
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentpageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();
		// ������ҳ��βҳ
		if (currentpageNo < 1) {
			currentpageNo = 1;
		} else if (currentpageNo > totalPageCount) {
			currentpageNo = totalPageCount;
		}
		// ��ѯ��ҳ����
		userlist = userService.getUserList(queryUserName, _queryUserRole, currentpageNo - 1, pageSize);

		model.addAttribute("userList", userlist);
		// ��ѯ��ɫid
		List<Role> roleList = roleService.getRoleList(currentpageNo - 1, pageSize);

		// ��ֵһ��һ���Ĵ���ҳ��
		model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentpageNo);
		model.addAttribute("totalPageCount", totalPageCount);

		logger.debug("�Ѿ�����Ҫ��ת��ʱ���ˡ�����������");
		return "userlist";
	}

	@RequestMapping("/useradd.html")
	public String userAdd() {
		return "useradd";
	}

	@RequestMapping("/useraddsave.html")
	public String userAddSave(User user, HttpSession session, HttpServletRequest requset,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
		String idPicPath = null; // ��һ���ļ�·��
		String workPicPath = null; // �ڶ����ļ�·��
		String errorInfo = null; // �ļ��쳣
		boolean flag = true;
		String path = requset.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		logger.info("uplaodFile path =========>" + path);
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];
			// �ж��ļ��Ƿ�Ϊ��
			if (!attach.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadFileError";
				} else {
					errorInfo = "uploadwpError";
				}
				String oldFileName = attach.getOriginalFilename(); // ԭ�ļ���
				logger.info("upload oldFileName =========>" + oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);// ԭ�ļ���׺
				logger.debug("uploadFile prefix========> " + prefix);
				int filesize = 500000;
				logger.debug("uploadFile size========> " + attach.getSize());
				if (attach.getSize() > filesize) {
					requset.setAttribute("uploadFileError", "* �ϴ���С���ܳ���500KB");
					return "useradd";
				} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg") || prefix.equals("pneg")) {
					String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_personal.jpg";
					logger.debug("new fileName======= " + attach.getName());
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// ����
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						requset.setAttribute("uploadFileError", "* �ϴ�ʧ��!");
						return "useradd";
					}
					if (i == 0) {
						idPicPath = path + File.separator + fileName;
					} else {
						workPicPath = path + File.separator + fileName;
					}

				} else {
					requset.setAttribute("uploadFile", "* �ϴ�ͼƬ��ʽ����ȷ");
					return "useradd";
				}
			}
		}
		if (flag) {
			user.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
			user.setIsPicPath(idPicPath);
			user.setWorkPicPath(workPicPath);
			if (userService.addUser(user) > 0) {
				return "redirect:/user/userlist.html";
			}
		}
		return "useradd";
	}
	@RequestMapping(value="/ucexist.html")
	@ResponseBody
	public Object userCodeIsExit(@RequestParam String userCode){
		logger.debug("userCodeIsExit userCode:"+userCode);
		HashMap<String, String> resultMap = new HashMap<String,String>();
		if(StringUtils.isNullOrEmpty(userCode)){
			resultMap.put("userCode", "exist");
		}else{
			User user = userService.getUserByCode(userCode);
			if(null != user)
				resultMap.put("userCode", "exist");
			else
				resultMap.put("userCode", "noexist");
		}
		return JSONArray.toJSONString(resultMap);
	}
	@RequestMapping(value="pwdmodify")
	@ResponseBody
	public Object pwdmodify(@RequestParam(value="oldpassword") String oldpassword,HttpSession session){
		HashMap<String, String> resultMap = new HashMap<String,String>();
		if(StringUtils.isNullOrEmpty(oldpassword)){
			resultMap.put("result", "error");
		}else{
			User user1 = (User)session.getAttribute("userSession");
			if(null==user1){
				resultMap.put("result", "sessionerror");
			}
			user1.setUserPassword(oldpassword);
			user1.getUserCode();
			User user = userService.getUserPwd(user1);
			if(null!=user)
				resultMap.put("result", "true");
			else
				resultMap.put("result", "false");
		}
		return JSONArray.toJSONString(resultMap);
	}
	@RequestMapping(value="pwdmodify.html")
	public String pwdmodify(){
		return "pwdmodify";
	}
	
	@RequestMapping(value="changepwd.html")
	public String changepwd(String newpassword,HttpSession session){
		User user = (User)session.getAttribute("userSession");
		user.setUserPassword(newpassword);
		if(userService.pwdModify(user)>0){
			return "redirect:/user/userlist.html";
		}
		return "pwdmodify";
	}
	
	@RequestMapping(value="/deluser")
	@ResponseBody
	public Object delUser(String uid){
		HashMap<String, String> resultMap = new HashMap<String,String>();
		if(StringUtils.isNullOrEmpty(uid)){
			resultMap.put("delResult", "noexist");
		}else{
			if(userService.delUser(uid)>0)
				resultMap.put("delResult", "true");
			else
				resultMap.put("delResult", "false");
		}
		return JSONArray.toJSONString(resultMap);
	}
}
