package com.service;
import com.pojo.BackendUser;

public interface BackendUserService {
	/**
	 * 用户登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	
	public BackendUser login(String userCode,String userPassword)throws Exception;
}
