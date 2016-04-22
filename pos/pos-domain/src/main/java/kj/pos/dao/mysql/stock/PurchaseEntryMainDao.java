package kj.pos.dao.mysql.stock;


import kj.pos.entity.stock.PurchaseEntryMain;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface PurchaseEntryMainDao{

    public Integer getTotal(PurchaseEntryMain purchaseEntryMain);

	public List<PurchaseEntryMain> getList(PurchaseEntryMain purchaseEntryMain);

	public void create(PurchaseEntryMain purchaseEntryMain);

	public void update(PurchaseEntryMain purchaseEntryMain);

    public void audit(PurchaseEntryMain purchaseEntryMain);

    public void cancel(PurchaseEntryMain purchaseEntryMain);

    public void enterStock(PurchaseEntryMain purchaseEntryMain);
}