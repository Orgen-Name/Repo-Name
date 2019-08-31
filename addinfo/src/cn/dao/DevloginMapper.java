package cn.dao;

import org.apache.ibatis.annotations.Param;

import cn.entity.Dev_User;

public interface DevloginMapper {
	public Dev_User devLogin(@Param("devCode")String devCode,@Param("devpassword")String devpassword);
}
