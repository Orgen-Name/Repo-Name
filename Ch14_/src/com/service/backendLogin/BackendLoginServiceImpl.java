package com.service.backendLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.backendLogin.LoginMapper;
import com.pojo.Backend_user;

@Service("BackendLoginService")
public class BackendLoginServiceImpl implements BackendLoginService{

	@Autowired
	public LoginMapper login;
	@Override
	public Backend_user Login(String userCode, String userPassword) {
		return login.Login(userCode, userPassword);
	}

	@Override
	public String userCode(String userCode) {
		return login.userCode(userCode);
	}

}
