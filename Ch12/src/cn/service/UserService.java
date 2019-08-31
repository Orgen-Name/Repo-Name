package cn.service;

import java.util.List;

import cn.pojo.User;

public interface UserService {
	public User doLogin(String userName, String pwd); // 登录

	public String findCode(String code); // 查找用户名

	/**
	 * 查询数据总量
	 */
	public int getUserCount(String userName, Integer userRole);

	/**
	 * 查询分页数据库
	 */
	public List<User> getUserList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize);

	public int addUser(User user);// 添加用户

	public User getUserByCode(String userCode); // 用户编号查询用户

	public User getUserPwd(User user); // 查询原密码

	public int pwdModify(User user); // 修改密码

	public int delUser(String id);	//删除用户
}
