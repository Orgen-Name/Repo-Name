package cn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
import cn.service.BillService;

@Controller 
@RequestMapping("/bill")
public class BillController {
	private Logger logger = Logger.getLogger(BillController.class);
	
	@Autowired
	public BillService billService;
	
	@RequestMapping("/billlist.html")
	public String BillList(Model model, @RequestParam(value = "queryProductName", required = false) String queryProductName,
			@RequestParam(value = "queryProviderId", required = false) Integer queryProviderId,
			@RequestParam(value = "queryIsPayment", required = false) Integer queryIsPayment,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		logger.info("getUserList ---- > queryProductName: " + queryProductName);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		//logger.info("getUserList ---- > ����������Ϊ: " +  billService.getBillCount());
		List<Bill> billlist = null;
		// ����ҳ������
		int pageSize = 5;
		// ��ǰҳ��
		int currentpageNo = 1;
		// ����Ҫȥ��ѯ���ݣ���ô��Ҫȷ������
		if (queryProductName == null) {
			queryProductName = "";
		}
		if (queryProviderId == null) {
			queryProviderId = 0;
		}
		if (queryIsPayment == null) {
			queryIsPayment = 0;
		}
		// ������
		int totalCount = billService.getBillCount();
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
		billlist = billService.getBillList(queryProductName, queryProviderId,queryIsPayment, currentpageNo - 1, pageSize);
		model.addAttribute("billList", billlist);
		// ��ѯ��ɫid
		List<Provider> providerList = billService.getProviderProName();

		// ��ֵһ��һ���Ĵ���ҳ��
		model.addAttribute("providerList", providerList);
		model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", queryProviderId);
		model.addAttribute("queryIsPayment", queryIsPayment);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentpageNo);
		model.addAttribute("totalPageCount", totalPageCount);

		logger.debug("�Ѿ�����Ҫ��ת��ʱ���ˡ�����������");
		return "billlist";
	}
	
	@RequestMapping(value="/providerlist",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object providerlist() {
		List<Provider> providerlist = new ArrayList<Provider>();
		providerlist = billService.getProviderProName();
		return JSONArray.toJSONString(providerlist);
	}
	
	@RequestMapping(value="/billadd.html")
	public String Billadd() {
		return "billadd";
	}
	
	@RequestMapping("addsave.html")
	public String Addsave(Bill bill) {
		bill.setCreationDate(new Date());
		if(billService.addBill(bill)>0) {
			return "redirect:/bill/billlist.html";
		}
		return "addlist";
	}
	
	
	
	@RequestMapping(value="/billmodify/{id}",method = RequestMethod.GET)
	public String billModify(@PathVariable Integer id,Model model) {
		Bill bill = new Bill();
		bill = billService.getBillId(id);
		model.addAttribute("bill",bill);
		return "billmodify";
	}
	
	@RequestMapping(value="/modifysave.html",method = RequestMethod.POST)
	public String Modifysave(Bill bill) {
		if(billService.BillModify(bill)>0) {
			return "redirect:/bill/billlist.html";
		}
		return "billmodify";
		
	}
	
	@RequestMapping(value="/billview/{id}",method = RequestMethod.GET)
	public String billView(@PathVariable Integer id,Model model) {
		Bill bill = new Bill();
		bill = billService.getBillId(id);
		model.addAttribute("bill",bill);
		return "billview";
	}
	
	@RequestMapping(value="/delbill",method = RequestMethod.GET)
	@ResponseBody
	public String delBill(Integer billid) {
		HashMap<String, String> map = new HashMap<String,String>();
		if(billService.DelBill(billid)>0) {
			map.put("delResult", "true");
		}else if(billService.DelBill(billid) == 0) {
			map.put("delResult", "false");
		}else {
			map.put("delResult", "notexist");
		}
		return JSONArray.toJSONString(map);
	}
	
}
