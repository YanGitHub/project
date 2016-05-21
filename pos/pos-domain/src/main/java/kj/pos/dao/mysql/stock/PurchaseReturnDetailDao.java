package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.PurchaseReturnDetail;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 16-5-20.
 */
@MyBatisRepository
public interface PurchaseReturnDetailDao {
    public List<PurchaseReturnDetail> getList(PurchaseReturnDetail purchaseReturnDetail);

    public void create(List<PurchaseReturnDetail> purchaseReturnDetails);

    public void delete(@Param(value = "pid")Long pid);
}
