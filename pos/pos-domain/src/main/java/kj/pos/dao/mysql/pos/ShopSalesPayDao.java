package kj.pos.dao.mysql.pos;

import kj.pos.entity.pos.ShopSalesPay;
import kj.pos.util.mybatis.MyBatisRepository;

import java.util.List;
@MyBatisRepository
public interface ShopSalesPayDao{

    public Integer getTotal(ShopSalesPay shopSalesPay);

	public List<ShopSalesPay> getList(ShopSalesPay shopSalesPay);

	public void create(List<ShopSalesPay> list);

	public void update(ShopSalesPay shopSalesPay);

}