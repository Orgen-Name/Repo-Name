package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Bill;
import cn.pojo.Provider;

public interface BillService {
	public int delBillByProviderId(String id); // 根据供应商id删除订单信息

	public List<Bill> getBillListByProviderId(String providerId);// 根据供应商id查询订单信息

	/**
	 * 查询数据总量
	 */
	public int getBillCount();
	
	/**
	 * 查询分页数据库
	 */
	public List<Bill> getBillList(@Param("proName")String proName,@Param("productId")Integer queryProviderId,@Param("isPayment")Integer isPayment
			,@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	public List<Provider> getProviderProName();
	
	public int addBill(Bill bill);
	
	public int BillModify(Bill bill);
	
	public int DelBill(Integer id);
	
	public Bill getBillId(Integer id );
	
}
