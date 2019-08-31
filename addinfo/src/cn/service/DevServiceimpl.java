package cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.DevloginMapper;
import cn.entity.Dev_User;

@Service("DevService")
public class DevServiceimpl implements DevService {
	
	@Autowired
	private DevloginMapper devloginMapper;

	@Override
	public Dev_User devLogin(String devCode, String devpassword) {
		return devloginMapper.devLogin(devCode, devpassword);
	}

}
