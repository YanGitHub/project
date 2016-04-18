package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.PurchaseOrderMain;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface PurchaseOrderMainDao{

    public Integer getTotal(PurchaseOrderMain purchaseOrderMain);

	public List<PurchaseOrderMain> getList(PurchaseOrderMain purchaseOrderMain);

	public void create(PurchaseOrderMain purchaseOrderMain);

	public void update(PurchaseOrderMain purchaseOrderMain);

    public void audit(PurchaseOrderMain purchaseOrderMain);

    public void cancel(PurchaseOrderMain purchaseOrderMain);
}