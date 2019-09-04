package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.regexp.internal.recompile;

import cn.dao.RoleMapper;
import cn.pojo.Role;
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> getRoleList(Integer currentPageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.getRoleList(currentPageNo, pageSize);
	}
	@Override
	public int getRoleCount() {
		// TODO Auto-generated method stub
		return roleMapper.getRoleCount();
	}
	@Override
	public int RoleCount(String roleCode) {
		return roleMapper.RoleCount(roleCode);
	}
	@Override
	public int RoleAdd(Role role) {
		return roleMapper.RoleAdd(role);
	}
	@Override
	public int Rolemodify(Role role) {
		return roleMapper.RoleModify(role);
	}
	@Override
	public Role getRoleById(Integer id) {
		return	roleMapper.getRoleById(id);
	}
	@Override
	public int DelRole(Integer id) {
		return	roleMapper.DelRole(id);
	}
	@Override
	public int getExisId(Integer id) {
		return	roleMapper.getExisId(id);
	}

}
