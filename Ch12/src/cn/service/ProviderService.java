package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Provider;

public interface ProviderService {
	public List<Provider> getProviderList(@Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize, @Param("proCode") String code, @Param("proName") String name);// ��ҳ��ѯ���й�Ӧ��

	public int getProviderCount(@Param("proCode") String code, @Param("proName") String name);// �ܼ�¼��

	public int addProvider(Provider provider);// ��ӹ�Ӧ����Ϣ

	public Provider getProviderById(String id); // id��ѯ��Ӧ����Ϣ

	public int providerModify(Provider provider);// �޸Ĺ�Ӧ��

	public int delProvider(String id); // ɾ����Ӧ��
}
