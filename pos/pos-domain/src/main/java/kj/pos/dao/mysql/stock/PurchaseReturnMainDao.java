package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.PurchaseReturnMain;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;

/**
 * Created by Administrator on 16-5-20.
 */
@MyBatisRepository
public interface PurchaseReturnMainDao {
    public Integer getTotal(PurchaseReturnMain purchaseReturnMain);

    public List<PurchaseReturnMain> getList(PurchaseReturnMain purchaseReturnMain);

    public void create(PurchaseReturnMain purchaseReturnMain);

    public void update(PurchaseReturnMain purchaseReturnMain);

    public void audit(PurchaseReturnMain purchaseReturnMain);

    public void cancel(PurchaseReturnMain purchaseReturnMain);
}
