package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Bill;
import cn.pojo.Provider;

public interface BillMapper {
	public int delBillByProviderId(String id); // ���ݹ�Ӧ��idɾ��������Ϣ

	public List<Bill> getBillListByProviderId(String providerId);// ���ݹ�Ӧ��id��ѯ������Ϣ
	
	public List<Bill> getList(@Param("productName") String productName,@Param("providerId") Integer providerId,@Param("isPayment")Integer isPayment,
			@Param("pageSize")int pageSize, @Param("currentPageNo")int currentPageNo);
	
	public int count();
	public int addBill(Bill bill);
	public int BillModify(Bill bill);
	public int DelBill(Integer id);
	public Bill getBillById(Integer id); // id��ѯ������Ϣ
	public List<Provider> getProviderProName();

}
