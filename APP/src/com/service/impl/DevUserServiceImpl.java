package com.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.dao.devuser.DevUserMapper;
import com.pojo.DevUser;
import com.service.DevUserService;


@Service
public class DevUserServiceImpl implements DevUserService {
	@Resource
	private DevUserMapper mapper;
	@Override
	public DevUser login(String devCode, String devPassword) throws Exception {
		// TODO Auto-generated method stub
		DevUser user = null;
		user = mapper.getLoginUser(devCode);
		if(null != user){
			if(!user.getDevPassword().equals(devPassword))
				user = null;
		}
		return user;
	}

}
