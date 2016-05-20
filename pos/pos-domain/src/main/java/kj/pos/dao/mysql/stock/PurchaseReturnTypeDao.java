package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.PurchaseReturnType;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface PurchaseReturnTypeDao{

    public Integer getTotal(PurchaseReturnType purchaseReturnType);

	public List<PurchaseReturnType> getList(PurchaseReturnType purchaseReturnType);

	public void create(PurchaseReturnType purchaseReturnType);

	public void update(PurchaseReturnType purchaseReturnType);

}