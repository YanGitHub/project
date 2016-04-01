package kj.pos.dao.mysql.stock;


import kj.pos.entity.stock.PurchaseType;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface PurchaseTypeDao{

    public Integer getTotal(PurchaseType purchaseType);

	public List<PurchaseType> getList(PurchaseType purchaseType);

	public void create(PurchaseType purchaseType);

	public void update(PurchaseType purchaseType);

}