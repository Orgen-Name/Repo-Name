package com.service;

import com.pojo.Dev_user;

public interface DevService {
	/**
	 * ��¼
	 * @param devCode
	 * @param devpassword
	 * @return
	 */
	public Dev_user getLogin(String devCode,String devpassword);

}
