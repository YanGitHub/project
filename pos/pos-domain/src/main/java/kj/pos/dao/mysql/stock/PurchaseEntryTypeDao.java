package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.PurchaseEntryType;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface PurchaseEntryTypeDao{

    public Integer getTotal(PurchaseEntryType purchaseEntryType);

	public List<PurchaseEntryType> getList(PurchaseEntryType purchaseEntryType);

	public void create(PurchaseEntryType purchaseEntryType);

	public void update(PurchaseEntryType purchaseEntryType);

}