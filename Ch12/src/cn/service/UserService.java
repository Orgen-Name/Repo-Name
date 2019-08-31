package cn.service;

import java.util.List;

import cn.pojo.User;

public interface UserService {
	public User doLogin(String userName, String pwd); // ��¼

	public String findCode(String code); // �����û���

	/**
	 * ��ѯ��������
	 */
	public int getUserCount(String userName, Integer userRole);

	/**
	 * ��ѯ��ҳ���ݿ�
	 */
	public List<User> getUserList(String userName, Integer userRole, Integer currentPageNo, Integer pageSize);

	public int addUser(User user);// ����û�

	public User getUserByCode(String userCode); // �û���Ų�ѯ�û�

	public User getUserPwd(User user); // ��ѯԭ����

	public int pwdModify(User user); // �޸�����

	public int delUser(String id);	//ɾ���û�
}
