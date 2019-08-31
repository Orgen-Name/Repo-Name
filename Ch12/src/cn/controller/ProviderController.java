package cn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.sun.tracing.dtrace.Attributes;

import cn.pojo.Bill;
import cn.service.BillService;
import cn.service.ProviderService;

@RequestMapping("/user")
@Controller
public class ProviderController {
	Logger logger = Logger.getLogger(ProviderController.class);
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private BillService billService;
	
	@RequestMapping(value="delprovider.html")
	@ResponseBody
	public Object delprovider(String proid){
		HashMap<String, String> result = new HashMap<String,String>();
		if(StringUtils.isNullOrEmpty(proid)){
			result.put("delResult", "notexist");
		}else{
			if(billService.getBillListByProviderId(proid).size()>0){
				result.put("delResult","has");
			}else if(providerService.delProvider(proid)>0){
				result.put("delResult","true");
			}else{
				result.put("delResult","false");
			}
		}
		return JSONArray.toJSONString(result);
	}
}
