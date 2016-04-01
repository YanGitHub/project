package kj.pos.dao.mysql.stock;

import kj.pos.entity.stock.PurchaseOrderDetail;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PurchaseOrderDetailDao{

	public List<PurchaseOrderDetail> getList(PurchaseOrderDetail purchaseOrderDetail);

	public void create(List<PurchaseOrderDetail> purchaseOrderDetails);

	public void delete(@Param(value = "pid")Long pid);

}