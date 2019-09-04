package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Bill;
import cn.pojo.Provider;

public interface BillMapper {
	public int delBillByProviderId(String id); // 根据供应商id删除订单信息

	public List<Bill> getBillListByProviderId(String providerId);// 根据供应商id查询订单信息
	
	public List<Bill> getList(@Param("productName") String productName,@Param("providerId") Integer providerId,@Param("isPayment")Integer isPayment,
			@Param("pageSize")int pageSize, @Param("currentPageNo")int currentPageNo);
	
	public int count();
	public int addBill(Bill bill);
	public int BillModify(Bill bill);
	public int DelBill(Integer id);
	public Bill getBillById(Integer id); // id查询订单信息
	public List<Provider> getProviderProName();

}
