package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Role;

public interface RoleService {
	public List<Role> getRoleList(Integer currentPageNo,
			Integer pageSize)throws Exception;//分页显示角色
	public int getRoleCount();//角色记录数
	public int RoleCount(String roleCode); //查看编码是否重复
	public int RoleAdd(Role role);
	public int Rolemodify(Role role);
	public Role getRoleById(Integer id); // id查询订单信息
	public int DelRole(Integer id);
	public int getExisId(Integer id);
}
