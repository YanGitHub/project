package kj.pos.dao.mysql.stock;


import kj.pos.entity.stock.PurchaseEntryDetail;
import kj.pos.util.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface PurchaseEntryDetailDao{

	public List<PurchaseEntryDetail> getList(PurchaseEntryDetail purchaseEntryDetail);

	public void create(List<PurchaseEntryDetail> purchaseEntryDetail);

    public void delete(@Param(value = "pid")Long pid);

}
