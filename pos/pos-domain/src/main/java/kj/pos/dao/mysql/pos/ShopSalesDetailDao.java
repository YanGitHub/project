package kj.pos.dao.mysql.pos;


import kj.pos.entity.pos.ShopSalesDetail;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface ShopSalesDetailDao{

    public Integer getTotal(ShopSalesDetail shopSalesDetail);

    public List<ShopSalesDetail> getList(ShopSalesDetail shopSalesDetail);

	public void create(ShopSalesDetail shopSalesDetail);


}