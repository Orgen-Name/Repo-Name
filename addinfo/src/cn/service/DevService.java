package cn.service;

import org.apache.ibatis.annotations.Param;

import cn.entity.Dev_User;

public interface DevService {
	public Dev_User devLogin(@Param("devCode")String devCode,@Param("devpassword")String devpassword);
	
}
