package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DevMapper;
import com.pojo.Dev_user;

@Service("DevService")
public class DevServiceImpl implements DevService {

	@Autowired
	public DevMapper devMapper;

	@Override
	public Dev_user getLogin(String devCode, String devpassword) {
		return devMapper.getLogin(devCode, devpassword);
	}
	
	
}
