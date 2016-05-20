package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.PurchaseOutboundType;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface PurchaseOutboundTypeDao{

    public Integer getTotal(PurchaseOutboundType purchaseOutboundType);

	public List<PurchaseOutboundType> getList(PurchaseOutboundType purchaseOutboundType);

	public void create(PurchaseOutboundType purchaseOutboundType);

	public void update(PurchaseOutboundType purchaseOutboundType);

}