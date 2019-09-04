package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.UserMapper;
import cn.pojo.User;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	public UserMapper getUserMapper(){
		return userMapper;
	}  
	public void setUserMapper(UserMapper userMapper){
		this.userMapper = userMapper;
	}
	@Override 
	public User doLogin(String userName, String pwd){
		return userMapper.doLogin(userName, pwd);
	}
	@Override
	public String findCode(String code) {
		return userMapper.findCode(code);
	}
	@Override
	public int getUserCount(String userName, Integer userRole) {
		return userMapper.getUserCount(userName, userRole);
	}
	@Override
	public List<User> getUserList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize) {
		return userMapper.getUserList(userName, userRole, currentPageNo, pageSize);
	}
	@Override
	public int addUser(User user) {
		return userMapper.addUser(user);
	}
	@Override
	public User getUserByCode(String userCode) {
		return userMapper.getUserByCode(userCode);
	}
	@Override
	public User getUserPwd(User user) {
		return userMapper.getUserPwd(user);
	}
	@Override
	public int pwdModify(User user) {
		return userMapper.pwdModify(user);
	}
	@Override
	public int delUser(String id) {
		return userMapper.delUser(id);
	}
}
