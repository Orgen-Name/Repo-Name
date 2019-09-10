package com.service.backendLogin;

import com.pojo.Backend_user;

public interface BackendLoginService {

	/**
	 * 鐧诲綍鏂规硶
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public Backend_user Login(String userCode,String userPassword);
	/**
	 * 鏌ヨ鐢ㄦ埛瀵嗙爜鎴栬处鍙锋槸鍚﹀瓨鍦�
	 * @param userCode
	 * @return
	 */
	public String userCode(String userCode);
}
