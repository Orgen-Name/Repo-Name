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
//���ַ�ʽ��Ϊ�˱�֤·����Ψһ��
@RequestMapping("/user")
public class IndexController {
	
	//springMVC���еĿ��������ǵ����ġ�
	//���ԣ����˴���service֮�⣬�����ڿ��������ٴ��������Ĺ�������
	
	List<User> list = new ArrayList<User>();
	@Autowired
	private ProviderService providerService;
	
	private Logger logger = Logger.getLogger(IndexController.class);
	//����һ������
	//method=RequestMethod.POST:�������ύ��ʽ�����������post���Ͳ�����get�ύ
	@RequestMapping(value="/index",method=RequestMethod.GET,params="username")
	public String index(@RequestParam String userCode){
		logger.info("username:" + userCode);
		return "index";
	}
	
	//ʹ��requestParam�����ò���
	//value���ԣ���Ӧ���൱��������ǰ��name
	//required����:��������Ƿ����
	@RequestMapping("/welcome")
	public String welcome(@RequestParam(value="username",required=false) String userCode){
		logger.info("userCode:" + userCode);
		return "index";
	}
	
	
	//������ to ��ͼ 
	@RequestMapping("/index1")
	public ModelAndView index1(@RequestParam String username){
		logger.info("username:" + username);
		ModelAndView mav = new ModelAndView();
		//�������
		mav.addObject("username",username);
		
		//�����ͼ
		mav.setViewName("index");
		return mav;
	}
	
	//�͵���ʹ��Model
	@RequestMapping("/index2")
	public String index2(@RequestParam String username,Model model){
		logger.info("username:" + username);
		
		//model���е���request
		model.addAttribute("username", username);
		//ֱ�Ӵ�ֵ���÷�
		model.addAttribute(username);
		
		//������ҳ����ȥ
		User user = new User();
		user.setUserName(username);
		//����ֵ
		model.addAttribute("userAdmin", user);
		//ֱ�Ӵ�ֵ
		model.addAttribute(user);
		return "index";
	}
	
	//������ to ��ͼ
	@RequestMapping("/index3")
	public String index3(@RequestParam String username,Map<String,Object> model){
		logger.info("username:" + username);
		model.put("username",username);
		return "index";
	}
	//��Ӧ�̲�ѯ
	@RequestMapping("/provider")
	public String getProvider(@RequestParam(value="pageIndex",required=false)String pageIndex,Model model
								,@RequestParam(value="proCode",required=false)String code,@RequestParam(value="proName",required=false)String name){
		List<Provider> prolist = null;
		//����ҳ������
		int pageSize = 5;
		//��ǰҳ��
		int currentpageNo = 1;
		//����Ҫȥ��ѯ���ݣ���ô��Ҫȷ������
		if(pageIndex != null){
			currentpageNo = Integer.parseInt(pageIndex); 
		}
		//������
		int totalCount = providerService.getProviderCount(code,name);
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
		prolist = providerService.getProviderList(currentpageNo-1,pageSize,code,name);
		model.addAttribute("proList", prolist);
		//��ѯ��ɫid
		//��ֵһ��һ���Ĵ���ҳ��
		model.addAttribute("proCode", code);
		model.addAttribute("proName", name);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentpageNo);
		model.addAttribute("totalPageCount", totalPageCount);
		
		logger.debug("�Ѿ�����Ҫ��ת��ʱ���ˡ�����������");
		return "providerlist";
	}
	
	@RequestMapping(value="/provideradd.html")
	public String providerAdd(@ModelAttribute("provider") Provider provider){
		return "provideradd";
	}
	
	@RequestMapping(value= "/providerAddSave.html")
	public String providerAddSave(Provider provider,@RequestParam(value="attachs",required=false)MultipartFile[] attachs ,HttpServletRequest request,
										HttpSession session){

		String companyPath = null; // ��ҵӪҵִ��·��
		String orgCodePath = null; // ��֯��������֤·��
		String errorInfo = null; // �쳣·��
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
				String oldFileName = attach.getOriginalFilename(); // ԭ�ļ���
				logger.info("upload oldFileName =========>" + oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);// ԭ�ļ���׺
				logger.debug("uploadFile prefix========> " + prefix);
				int filesize = 500000;
				logger.debug("uploadFile size========> " + attach.getSize());
				if (attach.getSize() > filesize) {
					request.setAttribute("uploadFileError", "* �ϴ���С���ܳ���500KB");
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
						request.setAttribute("uploadFileError", "* �ϴ�ʧ��!");
						return "useradd";
					}
					if (i == 0) {
						companyPath = path + File.separator + fileName;
					} else {
						orgCodePath = path + File.separator + fileName;
					}

				} else {
					request.setAttribute("uploadFile", "* �ϴ�ͼƬ��ʽ����ȷ");
					return "provideradd";
				}
			}

		}
		if (flag) {
			provider.setCompanyLicPicPath(companyPath);
			provider.setOrgCodePicPath(orgCodePath);
			if (providerService.addProvider(provider) > 0) {
				// redirect:�൱���ض���
				// forword:�൱��ת��
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
			//redirect:�൱���ض���
			//forword:�൱��ת��
			return "redirect:/user/provider";
		}
		return "redirect:/user/providermodify/{id}";
	}
	@RequestMapping(value="/pwdChange.html")
	public String pwdChange(){
		return "pwdmodify";
	}
}
