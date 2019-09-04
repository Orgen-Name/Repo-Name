package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.ProviderMapper;
import cn.pojo.Provider;
@Service("providerService")
public class ProviderServiceImpl implements ProviderService{
	@Autowired
	private ProviderMapper providerMapper;
	@Override
	public List<Provider> getProviderList(Integer currentPageNo, Integer pageSize, String code, String name) {
		return providerMapper.getProviderList(currentPageNo, pageSize, code, name);
	} 
	@Override
	public int getProviderCount(String code, String name) {
		return providerMapper.getProviderCount(code, name);
	}
	@Override
	public int addProvider(Provider provider) {
		return providerMapper.addProvider(provider);
	}
	@Override
	public Provider getProviderById(String id) {
		return providerMapper.getProviderById(id);
	}
	@Override
	public int providerModify(Provider provider) {
		return providerMapper.providerModify(provider);
	}
	@Override
	public int delProvider(String id) {
		return providerMapper.delProvider(id);
	}

}
