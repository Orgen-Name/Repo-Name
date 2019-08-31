package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Bill;
import cn.pojo.Role;

public interface RoleMapper {
	public List<Role> getRoleList(@Param("currentPageNo")Integer currentPageNo,
            @Param("pageSize")Integer pageSize)throws Exception;//��ҳ��ʾ��ɫ
	public int getRoleCount();//��ɫ��¼��
	public int RoleCount(String roleCode); //�鿴�����Ƿ��ظ�
	public int RoleAdd(Role role);
	public int RoleModify(Role role);
	public Role getRoleById(Integer id); // id��ѯ������Ϣ
	public int DelRole(Integer id);
	public int getExisId(Integer id);
}
