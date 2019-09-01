package com.service;

import com.pojo.Dev_user;

public interface DevService {
	/**
	 * µÇÂ¼
	 * @param devCode
	 * @param devpassword
	 * @return
	 */
	public Dev_user getLogin(String devCode,String devpassword);

}
