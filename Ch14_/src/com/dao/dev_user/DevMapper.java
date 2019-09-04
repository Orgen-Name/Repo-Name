package com.dao.dev_user;

import org.apache.ibatis.annotations.Param;

import com.pojo.Dev_user;

public interface DevMapper {
	/**
	 * ��¼
	 * @param devCode
	 * @param devpassword
	 * @return
	 */
	public Dev_user getLogin(@Param("devCode")String devCode,@Param("devPassword")String devPassword);
	/**
	 * �����û���
	 * @param code
	 * @return
	 */
	public String findCode(String code); // �����û���
}
