package com.dao;

import com.pojo.Dev_user;

public interface DevMapper {
	/**
	 * ��¼
	 * @param devCode
	 * @param devpassword
	 * @return
	 */
	public Dev_user getLogin(String devCode,String devpassword);
}
