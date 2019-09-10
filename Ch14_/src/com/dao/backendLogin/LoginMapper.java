package com.dao.backendLogin;

import org.apache.ibatis.annotations.Param;

import com.pojo.Backend_user;

public interface LoginMapper {
	/**
	 * 登录方法
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public Backend_user Login(@Param("userCode")String userCode,@Param("userPassword") String userPassword);
	/**
	 * 查询用户密码或账号是否存在
	 * @param userCode
	 * @return
	 */
	public String userCode(@Param("userCode") String userCode);
}
