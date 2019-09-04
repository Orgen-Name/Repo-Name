package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Bill;
import cn.pojo.Provider;

public interface BillService {
	public int delBillByProviderId(String id); // ���ݹ�Ӧ��idɾ��������Ϣ

	public List<Bill> getBillListByProviderId(String providerId);// ���ݹ�Ӧ��id��ѯ������Ϣ

	/**
	 * ��ѯ��������
	 */
	public int getBillCount();
	
	/**
	 * ��ѯ��ҳ���ݿ�
	 */
	public List<Bill> getBillList(@Param("proName")String proName,@Param("productId")Integer queryProviderId,@Param("isPayment")Integer isPayment
			,@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	
	public List<Provider> getProviderProName();
	
	public int addBill(Bill bill);
	
	public int BillModify(Bill bill);
	
	public int DelBill(Integer id);
	
	public Bill getBillId(Integer id );
	
}
