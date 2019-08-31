package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Provider;

public interface ProviderService {
	public List<Provider> getProviderList(@Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize, @Param("proCode") String code, @Param("proName") String name);// 分页查询所有供应商

	public int getProviderCount(@Param("proCode") String code, @Param("proName") String name);// 总记录数

	public int addProvider(Provider provider);// 添加供应商信息

	public Provider getProviderById(String id); // id查询供应商信息

	public int providerModify(Provider provider);// 修改供应商

	public int delProvider(String id); // 删除供应商
}
