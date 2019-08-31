package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.BillMapper;
import cn.pojo.Bill;
import cn.pojo.Provider;

@Service("billService")
public class BillServiceImpl implements BillService{
	@Autowired
	private BillMapper billMapper;
	public BillMapper getBillMapper() {
		return billMapper;
	}

	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}

	@Override
	public int delBillByProviderId(String id) {
		return billMapper.delBillByProviderId(id);
	}

	@Override
	public List<Bill> getBillListByProviderId(String providerId) {
		// TODO Auto-generated method stub
		return billMapper.getBillListByProviderId(providerId);
	}

	@Override
	public int getBillCount() {
		System.err.println(billMapper.count());
		return billMapper.count();
	}

	@Override
	public List<Bill> getBillList(String proName, Integer productId, Integer isPayment, Integer currentPageNo,
			Integer pageSize) {
		return billMapper.getList(proName, productId, isPayment, currentPageNo, pageSize);
	}

	@Override
	public List<Provider> getProviderProName() {
		return billMapper.getProviderProName();
	}

	@Override
	public int addBill(Bill bill) {
		return billMapper.addBill(bill);
	}

	@Override
	public int BillModify(Bill bill) {
		return billMapper.BillModify(bill);
	}

	@Override
	public Bill getBillId(Integer id) {
		return billMapper.getBillById(id);
	}

	@Override
	public int DelBill(Integer id) {
		return billMapper.DelBill(id);
	}

}
