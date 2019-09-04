package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Provider;
import cn.pojo.User;

public interface UserMapper {
	public User doLogin(@Param("userName") String name, @Param("pwd") String pwd);// 登录

	public String findCode(String code); // 查找用户名

	/**
	 * 查询数据总量
	 */
	public int getUserCount(@Param("userName") String userName, @Param("userRole") Integer userRole);

	/**
	 * 查询分页数据库
	 */
	public List<User> getUserList(@Param("userName") String userName, @Param("userRole") Integer userRole,
			@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	public int addUser(User user);// 添加用户

	public User getUserByCode(String userCode); // 用户编号查询用户

	public User getUserPwd(User user); // 查询原密码

	public int pwdModify(User user); // 修改密码
	
	public int delUser(String id);	//删除用户
}
