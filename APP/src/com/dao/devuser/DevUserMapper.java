package com.dao.devuser;

import org.apache.ibatis.annotations.Param;
import com.pojo.DevUser;

public interface DevUserMapper {
	/**
	 * 通过userCode获取Use
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public DevUser getLoginUser(@Param("devCode")String devCode)throws Exception;
}
