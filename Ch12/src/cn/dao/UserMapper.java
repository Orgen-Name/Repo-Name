package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Provider;
import cn.pojo.User;

public interface UserMapper {
	public User doLogin(@Param("userName") String name, @Param("pwd") String pwd);// ��¼

	public String findCode(String code); // �����û���

	/**
	 * ��ѯ��������
	 */
	public int getUserCount(@Param("userName") String userName, @Param("userRole") Integer userRole);

	/**
	 * ��ѯ��ҳ���ݿ�
	 */
	public List<User> getUserList(@Param("userName") String userName, @Param("userRole") Integer userRole,
			@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	public int addUser(User user);// ����û�

	public User getUserByCode(String userCode); // �û���Ų�ѯ�û�

	public User getUserPwd(User user); // ��ѯԭ����

	public int pwdModify(User user); // �޸�����
	
	public int delUser(String id);	//ɾ���û�
}
