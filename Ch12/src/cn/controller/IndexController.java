package cn.controller;

import java.io.File;
import java.util.ArrayList;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import cn.pojo.PageSupport;
import cn.pojo.Provider;
import cn.pojo.User;
import cn.service.ProviderService;
import cn.tools.Constants;
@Controller
//这种方式是为了保证路径的唯一性
@RequestMapping("/user")
public class IndexController {
	
	//springMVC所有的控制器都是单例的。
	//所以，除了创建service之外，不能在控制器中再创建其他的公共变量
	
	List<User> list = new ArrayList<User>();
	@Autowired
	private ProviderService providerService;
	
	private Logger logger = Logger.getLogger(IndexController.class);
	//定义一个方法
	//method=RequestMethod.POST:设置了提交方式，如果设置了post，就不能以get提交
	@RequestMapping(value="/index",method=RequestMethod.GET,params="username")
	public String index(@RequestParam String userCode){
		logger.info("username:" + userCode);
		return "index";
	}
	
	//使用requestParam来设置参数
	//value属性：对应是相当于我们以前的name
	//required属性:这个参数是否必须
	@RequestMapping("/welcome")
	public String welcome(@RequestParam(value="username",required=false) String userCode){
		logger.info("userCode:" + userCode);
		return "index";
	}
	
	
	//控制器 to 视图 
	@RequestMapping("/index1")
	public ModelAndView index1(@RequestParam String username){
		logger.info("username:" + username);
		ModelAndView mav = new ModelAndView();
		//添加数据
		mav.addObject("username",username);
		
		//添加视图
		mav.setViewName("index");
		return mav;
	}
	
	//就单独使用Model
	@RequestMapping("/index2")
	public String index2(@RequestParam String username,Model model){
		logger.info("username:" + username);
		
		//model就有点像request
		model.addAttribute("username", username);
		//直接传值的用法
		model.addAttribute(username);
		
		//传对象到页面上去
		User user = new User();
		user.setUserName(username);
		//键对值
		model.addAttribute("userAdmin", user);
		//直接传值
		model.addAttribute(user);
		return "index";
	}
	
	//控制器 to 视图
	@RequestMapping("/index3")
	public String index3(@RequestParam String username,Map<String,Object> model){
		logger.info("username:" + username);
		model.put("username",username);
		return "index";
	}
	//供应商查询
	@RequestMapping("/provider")
	public String getProvider(@RequestParam(value="pageIndex",required=false)String pageIndex,Model model
								,@RequestParam(value="proCode",required=false)String code,@RequestParam(value="proName",required=false)String name){
		List<Provider> prolist = null;
		//设置页面容量
		int pageSize = 5;
		//当前页码
		int currentpageNo = 1;
		//我们要去查询数据，那么就要确定条件
		if(pageIndex != null){
			currentpageNo = Integer.parseInt(pageIndex); 
		}
		//总数量
		int totalCount = providerService.getProviderCount(code,name);
		//总页数
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentpageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();
		//控制首页和尾页
		if(currentpageNo < 1){
			currentpageNo = 1;
		} else if(currentpageNo > totalPageCount){
			currentpageNo = totalPageCount;
		}
		//查询分页数据
		prolist = providerService.getProviderList(currentpageNo-1,pageSize,code,name);
		model.addAttribute("proList", prolist);
		//查询角色id
		//把值一个一个的传给页面
		model.addAttribute("proCode", code);
		model.addAttribute("proName", name);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentpageNo);
		model.addAttribute("totalPageCount", totalPageCount);
		
		logger.debug("已经到了要跳转的时候了。！。。。。");
		return "providerlist";
	}
	
	@RequestMapping(value="/provideradd.html")
	public String providerAdd(@ModelAttribute("provider") Provider provider){
		return "provideradd";
	}
	
	@RequestMapping(value= "/providerAddSave.html")
	public String providerAddSave(Provider provider,@RequestParam(value="attachs",required=false)MultipartFile[] attachs ,HttpServletRequest request,
										HttpSession session){

		String companyPath = null; // 企业营业执照路径
		String orgCodePath = null; // 组织机构代码证路径
		String errorInfo = null; // 异常路径
		boolean flag = true;
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles"); // ??
		logger.info("uplaodFile path =========>" + path);
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];
			if (!attach.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadCompnyError";
				} else {
					errorInfo = "uploadorgCodeError";
				}
				String oldFileName = attach.getOriginalFilename(); // 原文件名
				logger.info("upload oldFileName =========>" + oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
				logger.debug("uploadFile prefix========> " + prefix);
				int filesize = 500000;
				logger.debug("uploadFile size========> " + attach.getSize());
				if (attach.getSize() > filesize) {
					request.setAttribute("uploadFileError", "* 上传大小不能超过500KB");
					return "useradd";
				} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg") || prefix.equals("pneg")) {
					String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_personal.jpg";
					logger.debug("new fileName======= " + attach.getName());
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("uploadFileError", "* 上传失败!");
						return "useradd";
					}
					if (i == 0) {
						companyPath = path + File.separator + fileName;
					} else {
						orgCodePath = path + File.separator + fileName;
					}

				} else {
					request.setAttribute("uploadFile", "* 上传图片格式不正确");
					return "provideradd";
				}
			}

		}
		if (flag) {
			provider.setCompanyLicPicPath(companyPath);
			provider.setOrgCodePicPath(orgCodePath);
			if (providerService.addProvider(provider) > 0) {
				// redirect:相当于重定向
				// forword:相当于转发
				return "redirect:/user/provider";
			}
		}
		return "provideradd";
	}

	
	@RequestMapping(value="/providerview/{id}",method=RequestMethod.GET)
	public String providerView(@PathVariable String id,Model model){
		Provider pro = providerService.getProviderById(id);
		model.addAttribute("pro",pro);
		return "providerview";
	} 
	@RequestMapping(value="/providermodify/{id}",method=RequestMethod.GET)
	public String providerModify(@PathVariable String id,Model model){
		Provider pro = providerService.getProviderById(id);
		model.addAttribute("pro",pro);
		return "providermodify";
	}
	@RequestMapping(value="providermodify.html") 
	public String modifyProvider(Provider provider){
		if(providerService.providerModify(provider)>0){
			//redirect:相当于重定向
			//forword:相当于转发
			return "redirect:/user/provider";
		}
		return "redirect:/user/providermodify/{id}";
	}
	@RequestMapping(value="/pwdChange.html")
	public String pwdChange(){
		return "pwdmodify";
	}
}
